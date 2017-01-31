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
import com.liferay.gsportal.core.exception.NoSuchClientException;
import com.liferay.gsportal.core.model.Project;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.gsportal.core.service.MemberOfLocalService;
import com.liferay.gsportal.core.service.base.ProjectLocalServiceBaseImpl;
import com.liferay.gsportal.core.util.CoreConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
import com.liferay.portal.kernel.util.Validator;

import javax.naming.InvalidNameException;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gsportal.core.service.ProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author GS
 * @see ProjectLocalServiceBaseImpl
 * @see com.liferay.gsportal.core.service.ProjectLocalServiceUtil
 */
@ProviderType
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.gsportal.project.service.ProjectLocalServiceUtil} to access the project local service.
	 */

	/**
	 * Creates a new project with the specified fields.
	 *
	 * @param clientId
	 * @param name
	 * @param description
	 * @param dashboardUrl
	 * @param lesaProjectKey
	 * @param logoId
	 * @param logoUrl
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Override
	public Project addProject(
			long clientId, String name,
			String description, String dashboardUrl, String lesaProjectKey,
			long logoId, String logoUrl)
		throws PortalException, SystemException {

		validate(name);

		long companyId = _clientLocalService.getClient(clientId).getCompanyId();

		Project project = projectPersistence.create(
			counterLocalService.increment(Project.class.getName()));

		Date now = new Date();

		project.setCompanyId(companyId);
		project.setCreateDate(now);
		project.setModifiedDate(now);

		project.setClientId(clientId);
		project.setName(name);
		project.setDescription(description);
		project.setDashboardUrl(dashboardUrl);
		project.setLesaProjectKey(lesaProjectKey);
		project.setLogoId(logoId);
		project.setLogoUrl(logoUrl);

		project = projectPersistence.update(project);

		//indexes the project
//		TODO: Migrate Project Indexer from 6.2
//		Indexer<Project> indexer = IndexerRegistryUtil.getIndexer(Project.class);
//		indexer.reindex(Project.class.getName(), project.getProjectId());

		return project;
	}

	/**
	 * Sets the staffMembers associated with the project
	 * @param projectId
	 * @param staffMemberIds
	 * @throws SystemException
	 */
	@Override
	public void addProjectStaffMembers(
			long projectId, long[] staffMemberIds, int[] memberRoleIds)
		throws PortalException, SystemException{

		long clientId = projectLocalService.getProject(projectId).getClientId();

		_memberOfLocalService.setMemberOfs(
			clientId, projectId, CoreConstants.NOT_IN, staffMemberIds, memberRoleIds);
	}

	@Override
	public List<Project> getProjectsByClientId(long clientId)
		throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		return projectPersistence.findByClientId(clientId);
	}

	@Override
	public List<Project> getProjectsByClientId(long clientId, int start, int end)
		throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		return projectPersistence.findByClientId(clientId, start, end);
	}

	@Override
	public int getProjectsCount() throws SystemException {
		return projectPersistence.countAll();
	}

	@Override
	public int getProjectsCountByClientId(long clientId)
		throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		return projectPersistence.countByClientId(clientId);
	}

//	@Override
//	public int countProjectStaff(long projectId)
//		throws PortalException, SystemException {
//
//		return _memberOfLocalService.countStaffByProject(projectId);
//	}
//
//	@Override
//	public List<StaffMember> getProjectStaff(long projectId)
//		throws PortalException, SystemException {
//
//		return _memberOfLocalService.getProjectStaff(projectId);
//	}

	/**
	 * Overwrites the specified fields for Project with a given projectId.
	 *
	 * @param projectId
	 * @param clientId
	 * @param name
	 * @param description
	 * @param dashboardUrl
	 * @param lesaProjectKey
	 * @param logoId
	 * @param logoUrl
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Override
	public Project updateProject(long projectId, long clientId,
			String name, String description, String dashboardUrl,
			String lesaProjectKey, long logoId, String logoUrl)
		throws PortalException, SystemException {

		validate(name);

		Project project = projectPersistence.findByPrimaryKey(projectId);
		project.setClientId(clientId);
		project.setName(name);
		project.setModifiedDate(new Date());
		project.setDescription(description);
		project.setDashboardUrl(dashboardUrl);
		project.setLesaProjectKey(lesaProjectKey);
		project.setLogoId(logoId);
		project.setLogoUrl(logoUrl);

		projectPersistence.update(project);

		//indexes the project
		Indexer indexer = IndexerRegistryUtil.getIndexer(Project.class);
		indexer.reindex(Project.class.getName(), projectId);

		return project;
	}

	private void validate(String name) throws PortalException {

		if (Validator.isNull(name)) {
			throw new PortalException(new InvalidNameException());
		}
	}

	@Override
	public Hits getProjectByKeywords(
			long companyId, String keywords, int start, int end)
		throws PortalException, SystemException {

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);
		searchContext.setAndSearch(false);

		Query query = _getSearchQuery(searchContext, keywords);

		return SearchEngineUtil.search(searchContext, query);
	}

	private Query _getSearchQuery(SearchContext searchContext, String keywords)
		throws PortalException {

		if (searchContext == null) {
			throw new PortalException("searchContext cannot be null");
		}

		// keywords can be null -> we will still restrict the search on one or more types

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);

		BooleanQuery typeFilter = BooleanQueryFactoryUtil.create(searchContext);
		boolean likeMatchOnType = false;

		typeFilter.addTerm(
			Field.ENTRY_CLASS_NAME, Project.class.getName(), likeMatchOnType,
			BooleanClauseOccur.SHOULD);

		query.add(typeFilter, BooleanClauseOccur.MUST);

		if (keywords != null && !keywords.isEmpty()) {
			BooleanQuery keywordsFilter = BooleanQueryFactoryUtil.create(searchContext);

			boolean likeMatchOnNameAndDescription = false;

			keywordsFilter.addTerm(
				Field.NAME, keywords, likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);
			keywordsFilter.addTerm(
				Field.DESCRIPTION, keywords, likeMatchOnNameAndDescription, BooleanClauseOccur.SHOULD);

			query.add(keywordsFilter, BooleanClauseOccur.MUST);
		}

		return query;
	}

	private MemberOfLocalService _memberOfLocalService;
	private ClientLocalService _clientLocalService;

}