/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gsportal.core.service.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.gsportal.core.exception.StaffMemberException;
import com.liferay.gsportal.core.model.StaffMember;
import com.liferay.gsportal.core.service.base.StaffMemberLocalServiceBaseImpl;
import com.liferay.gsportal.core.service.util.PropsUtil;
import com.liferay.gsportal.staffmember.util.EmployeeType;
import com.liferay.gsportal.staffmember.util.InternalUsersFilter;
import com.liferay.gsportal.staffmember.util.LgspPropsValues;
import com.liferay.gsportal.staffmember.util.StaffMemberConstants;
import com.liferay.portal.kernel.exception.ContactBirthdayException;
import com.liferay.portal.kernel.exception.ContactFirstNameException;
import com.liferay.portal.kernel.exception.ContactLastNameException;
import com.liferay.portal.kernel.exception.DuplicateUserEmailAddressException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.ContactLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the staff member remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gsportal.core.service.StaffMemberLocalService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author GS
 * @see StaffMemberLocalServiceBaseImpl
 * @see com.liferay.gsportal.core.service.StaffMemberLocalServiceUtil
 */
@ProviderType
public class StaffMemberLocalServiceImpl extends StaffMemberLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.gsportal.staffmember.service.StaffMemberServiceUtil} to access the staff member remote service.
	 */


	@Override
	public StaffMember getStaffMember(long userId) throws SystemException {

		return staffMemberPersistence.fetchByPrimaryKey(userId);
	}

	/**
	 * Create or update a Staff Member and associated User
	 *
	 * For given userId:
	 * (1) if staff member found and user not found, throw exception because
	 * all staff member should have user
	 * (2) if staff member and user found, update staff member and user according
	 * to rules: external staff members can be updated in all fields, internal staff
	 * members can only have technical skills updated
	 * (3) if user not found, create new user
	 * (4) if staff member not found, create new staff member
	 */
	@Override
	public StaffMember updateStaffMember(
			long userId, String firstName, String lastName, String employerName,
			String jobTitle, String emailAddress, int employeeType,
			long[] technicalSkillIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long groupId = CompanyLocalServiceUtil.getCompany(
				serviceContext.getCompanyId()).getGroupId();


		//TODO: Check permission in remote service layer
		// Check permission

//		boolean hasPermission = getPermissionChecker().hasPermission(
//				groupId, StaffMemberConstants.STAFF_PACKAGE, groupId,
//				StaffMemberConstants.EDIT_STAFF_MEMBER_PERMISSION);
//
//		if (userId == serviceContext.getUserId()) {
//			hasPermission = true;
//		}
//
//		if (!hasPermission) {
//			throw new PrincipalException(StaffMemberConstants.NO_PERMISSION);
//		}

		StaffMember staffMember = staffMemberPersistence.fetchByPrimaryKey(userId);
		User user = userPersistence.fetchByPrimaryKey(userId);

		// If updating an existing internal staff member

		if (Validator.isNotNull(user) &&
			Validator.isNotNull(staffMember) &&
			staffMember.isInternal()) {

			// Inputs are disabled so these values come in empty

			firstName = user.getFirstName();
			lastName = user.getLastName();
			jobTitle = user.getJobTitle();
			emailAddress = user.getEmailAddress();
			employeeType = staffMember.getEmployeeType();
		}

		// Validate staffMember values

		validateStaffMember(
			firstName, lastName, emailAddress, employerName, employeeType,
			groupId);

		if (Validator.isNotNull(staffMember) && Validator.isNull(user)) {

			// There is a staff member, but no user

			throw new StaffMemberException("Staff member is not associated with a user");
		}

		if (Validator.isNull(user)) {

			// Create user

			user = createUser(firstName, lastName, employerName, jobTitle, emailAddress, serviceContext);
		}
		else {

			// Update user

			user = updateUser(user, firstName, lastName, jobTitle, emailAddress, serviceContext);
		}

		if (Validator.isNull(staffMember)) {

			// There is no staff member, but user exists: add staff member

			staffMember = createStaffMember(user, employerName, employeeType, serviceContext);
		}
		else {

			// Update staff member

			staffMember = updateStaffMember(staffMember, employerName, employeeType);
		}

		// Update Technical Skills

		updateTechnicalSkills(user, technicalSkillIds, serviceContext);

		// Indexer

		if ((serviceContext == null) || serviceContext.isIndexingEnabled()) {
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(StaffMember.class);
			indexer.reindex(staffMember);
		}

		return staffMember;
	}

	@Override
	public StaffMember deleteStaffMember(long userId)
		throws PortalException, SystemException {

		StaffMember staffMember = staffMemberLocalService.fetchStaffMember(userId);
		if (Validator.isNotNull(staffMember)) {
			staffMemberPersistence.remove(userId);

			// Delete user if they have not already been deleted

			User user = UserLocalServiceUtil.fetchUser(userId);
			if (Validator.isNotNull(user)) {
				userLocalService.deleteUser(user);
			}

			// Indexer

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(StaffMember.class);
			indexer.delete(staffMember);
		}

		return staffMember;
	}

	protected StaffMember createStaffMember(
			User user, String employerName, int employeeType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		StaffMember staffMember = null;

		// Make sure email address is not liferay and not internal

		InternalUsersFilter domainFilter = new InternalUsersFilter(
				LgspPropsValues.INTERNAL_USERS_DOMAIN_NAME);

		if (employeeType == EmployeeType.LIFERAY.getValue() ||
			domainFilter.isInternal(user.getEmailAddress())) {
			throw new StaffMemberException(StaffMemberConstants.CANNOT_ADD_INTERNAL_STAFF_MEMBER);
		}

		staffMember = staffMemberPersistence.create(user.getUserId());
		staffMember.setCompanyId(user.getCompanyId());
		staffMember.setEmployerName(employerName);
		staffMember.setEmployeeType(employeeType);
		staffMemberPersistence.update(staffMember);

		return staffMember;
	}

	protected User createUser(String firstName, String lastName, String employerName,
			String jobTitle, String emailAddress, ServiceContext serviceContext)
		throws PortalException, SystemException {

		String middleName = StringPool.BLANK;
		boolean autoPassword = true;
		String password1 = PropsUtil.get(PropsKeys.DEFAULT_ADMIN_PASSWORD);
		String password2 = password1;
		boolean autoScreenName = true;
		String screenName = StringPool.BLANK;
		long facebookId = 0;
		String openId = StringPool.BLANK;
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		boolean sendEmail = false;
		User user = null;

		try {
			user = userLocalService.addUser(
					serviceContext.getUserId(), serviceContext.getCompanyId(), autoPassword,
					password1, password2, autoScreenName, screenName, emailAddress,
					facebookId, openId, serviceContext.getLocale(), firstName,
					middleName, lastName, prefixId, suffixId, male, birthdayMonth,
					birthdayDay, birthdayYear, jobTitle, null, null, null, null,
					sendEmail, serviceContext);
		}
		catch (Exception e) {
			if (e instanceof ContactBirthdayException ||
				e.getCause() instanceof ContactBirthdayException) {
					throw new StaffMemberException("birthday-is-invalid");
			}

			if (e instanceof ContactFirstNameException) {
				throw new StaffMemberException("invalid-first-name");
			}

			if (e instanceof ContactLastNameException) {
				throw new StaffMemberException("invalid-last-name");
			}

			if (e instanceof DuplicateUserEmailAddressException) {
				throw new StaffMemberException("the-email-address-you-requested-is-already-taken");
			}

			if (e instanceof UserEmailAddressException) {
				throw new StaffMemberException("invalid-email");
			}

			throw new StaffMemberException("error-adding-staff-member");
		}

		userLocalService.updateEmailAddressVerified(user.getUserId(), true);
		userLocalService.updatePasswordReset(user.getUserId(), false);

		return user;
	}

	protected StaffMember updateStaffMember(
			StaffMember staffMember, String employerName, int employeeType)
		throws SystemException {

		staffMember.setEmployerName(employerName);
		staffMember.setEmployeeType(employeeType);

		return staffMemberPersistence.update(staffMember);
	}

	protected void updateTechnicalSkills(
			User user, long[] categoryIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (categoryIds == null || categoryIds.length < 1) {
			return;
		}

		List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
				User.class.getName(), user.getUserId());
		String[] assetTagNames = StringUtil.split(ListUtil.toString(assetTags,
				AssetTag.NAME_ACCESSOR));

		userLocalService.updateAsset(user.getUserId(), user, categoryIds, assetTagNames);
	}

	protected User updateUser(
			User user, String firstName, String lastName, String jobTitle,
			String emailAddress, ServiceContext serviceContext)
		throws PortalException, SystemException {

		emailAddress = StringUtil.toLowerCase(emailAddress.trim());

		Date now = new Date();

		// User

		user.setModifiedDate(now);

		if (serviceContext != null) {
			String uuid = serviceContext.getUuid();

			if (Validator.isNotNull(uuid)) {
				user.setUuid(uuid);
			}
		}

		user.setEmailAddress(emailAddress);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setJobTitle(jobTitle);
		user.setExpandoBridgeAttributes(serviceContext);

		userPersistence.update(user, serviceContext);

		// Contact

		long contactId = user.getContactId();

		Contact contact = contactLocalService.getContact(contactId);

		contact.setModifiedDate(now);
		contact.setEmailAddress(user.getEmailAddress());
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setJobTitle(jobTitle);

		contactLocalService.updateContact(contact);

		return user;
	}

	@Override
	public StaffMember fetchStaffMember(long userId) throws SystemException {

		return staffMemberPersistence.fetchByPrimaryKey(userId);
	}

	@Override
	public List<StaffMember> getStaffMembers(int start, int end)
		throws SystemException {

		return staffMemberPersistence.findAll(start, end);
	}

	@Override
	public List<StaffMember> getStaffMembers() throws SystemException {

		return staffMemberPersistence.findAll();
	}

	@Override
	public int getStaffMemberCount() throws SystemException {

		return staffMemberPersistence.countAll();
	}

//	@Override
//	public TreeSet<String> getUniqueEmployerNames(long companyId)
//		throws PortalException, SystemException{
//		TreeSet<String> employerNames = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
//		SearchContext searchContext = _createSearchContext(companyId);
//		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
//
//		query.addTerm(Field.ENTRY_CLASS_NAME,
//				StaffMember.class.getName(), false,
//				BooleanClauseOccur.MUST);
//
//		List<Document> docs = _doSearch(searchContext, query).toList();
//		for (Document doc : docs){
//			String curEmployerName = doc.get(StaffMemberConstants.EMPLOYER_NAME);
//			if (!curEmployerName.isEmpty()){
//				employerNames.add(curEmployerName);
//			}
//		}
//
//		return employerNames;
//	}

	protected void validateStaffMember(
			String firstName, String lastName, String emailAddress,
			String employerName, int employeeType, long groupId)
		throws PortalException, SystemException {

		List<String> errors = new ArrayList<String>();

		// Validate first name

		if (Validator.isBlank(firstName)) {
			errors.add(StaffMemberConstants.NO_FIRSTNAME);
		}

		// Validate last name

		if (Validator.isBlank(lastName)) {
			errors.add(StaffMemberConstants.NO_LASTNAME);
		}

		// Validate required emailAddress

		if (Validator.isBlank(emailAddress)) {
			errors.add(StaffMemberConstants.NO_EMAIL);
		}

		// Validate valid email

		if (!Validator.isEmailAddress(emailAddress)) {
			errors.add(StaffMemberConstants.INVALID_EMAIL);
		}

		// Validate employee type

		if (employeeType != EmployeeType.LIFERAY.getValue() &&
			employeeType != EmployeeType.PARTNER.getValue() &&
			employeeType != EmployeeType.CUSTOMER.getValue()) {
			errors.add(StaffMemberConstants.INVALID_EMPLOYEE_TYPE);
		}

		// Validate employer name

		if (employeeType != EmployeeType.LIFERAY.getValue()) {
			if (Validator.isNull(employerName)) {
				errors.add(StaffMemberConstants.NO_EMPLOYER_NAME);
			}
		}

		if (!errors.isEmpty()) {
			throw new StaffMemberException(errors);
		}
	}

	@Override
	public Hits search(
			long companyId, String searchKeywords, String engagementTypeFilterWords,
			String employerNameFilterWords, String techAttributeFilterWords)
		throws PortalException, SystemException {

		SearchContext searchContext = _createSearchContext(companyId);

		Query query = _getSearchQuery(
				searchContext, searchKeywords, engagementTypeFilterWords,
				employerNameFilterWords, techAttributeFilterWords);

		return _doSearch(searchContext, query);
	}

	@Override
	public Hits search(
			long companyId, String searchKeywords, String engagementTypeFilterWords,
			String employerNameFilterWords, String techAttributeFilterWords,
			int start, int end)
		throws PortalException, SystemException {

		SearchContext searchContext = _createSearchContext(companyId);

		searchContext.setStart(start);
		searchContext.setEnd(end);

		Query query = _getSearchQuery(
				searchContext, searchKeywords, engagementTypeFilterWords,
				employerNameFilterWords, techAttributeFilterWords);

		return _doSearch(searchContext, query);
	}

	private SearchContext _createSearchContext(long companyId)
		throws PortalException {

		SearchContext searchContext = new SearchContext();

		searchContext.setCompanyId(companyId);
		searchContext.setAndSearch(false);

		return searchContext;
	}

	/**
	 * Execute search using <code>SearchEngineUtil.search()</code>.
	 */
	private Hits _doSearch(SearchContext searchContext, Query query)
		throws PortalException, SystemException {

		if (_log.isDebugEnabled()) {
			_log.debug("Searching index using query " + query.toString());
		}

		Hits hits = SearchEngineUtil.search(searchContext.getSearchEngineId(),
				searchContext.getCompanyId(), query, searchContext.getStart(),
				searchContext.getEnd());

		return hits;
	}

	/**
	 * Creates Search Query, with exact match for StaffMember types and with
	 * keywords like match in either name or description.
	 *
	 * TODO: need to revisit this
	 *
	 * @param searchContext
	 *            the context of the current search query
	 * @param searchKeywords
	 *            the text to search for within name or description
	 * @return
	 * @throws PortalException
	 */
	private Query _getSearchQuery(
			SearchContext searchContext, String searchKeywords,
			String engagementTypeFilterWords, String employerNameFilterWords,
			String techAttributeFilterWords)
		throws PortalException {

		if (Validator.isNull(searchContext)) {
			throw new PortalException("searchContext cannot be null");
		}

		// TODO this method needs Unit test

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);

		BooleanQuery typeFilter = BooleanQueryFactoryUtil.create(searchContext);
		boolean likeMatchOnType = false;


		typeFilter.addTerm(Field.ENTRY_CLASS_NAME,
				StaffMember.class.getName(), likeMatchOnType,
				BooleanClauseOccur.SHOULD);

		query.add(typeFilter, BooleanClauseOccur.MUST);


		BooleanQuery keywordsFilter = BooleanQueryFactoryUtil.create(
			searchContext);

		boolean likeMatchOnNameAndDescription = true;

		if (!engagementTypeFilterWords.isEmpty() && !engagementTypeFilterWords.equals("false")) {
			keywordsFilter.addTerm(StaffMemberConstants.ENGAGEMENT_TYPE_TITLES, engagementTypeFilterWords,
					likeMatchOnNameAndDescription, BooleanClauseOccur.MUST);
		}
//		if (!employerNameFilterWords.isEmpty() && !employerNameFilterWords.equals("false")){
//			keywordsFilter.addTerm(StaffMemberConstants.EMPLOYER_NAME, employerNameFilterWords,
//					likeMatchOnNameAndDescription, BooleanClauseOccur.MUST);
//		}
		if (!techAttributeFilterWords.isEmpty() && !techAttributeFilterWords.equals("false")) {
			keywordsFilter.addTerm(StaffMemberConstants.TECHNICAL_SKILLS_TITLES, techAttributeFilterWords,
					likeMatchOnNameAndDescription, BooleanClauseOccur.MUST);
		}

		keywordsFilter.addTerm(Field.USER_ID, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(Field.COMPANY_ID, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.ENGAGEMENT_TYPE_IDS, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.ENGAGEMENT_TYPE_TITLES, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.TECHNICAL_SKILLS_IDS, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.TECHNICAL_SKILLS_TITLES, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.FIRST_NAME, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.LAST_NAME, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.EMAIL_ADDRESS,
				searchKeywords, likeMatchOnNameAndDescription,
				BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.EMPLOYER_NAME,
				searchKeywords, likeMatchOnNameAndDescription,
				BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.FULL_NAME, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.JOB_TITLE, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.SCREEN_NAME, searchKeywords,
				likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
		keywordsFilter.addTerm(StaffMemberConstants.EMPLOYEE_TYPE,
				searchKeywords, likeMatchOnNameAndDescription,
				BooleanClauseOccur.SHOULD);
		query.add(keywordsFilter, BooleanClauseOccur.MUST);

		return query;
	}

	ContactLocalService _contactLocalService = getContactLocalService();

	private static final Log _log = LogFactoryUtil.getLog(StaffMemberLocalServiceImpl.class);
}