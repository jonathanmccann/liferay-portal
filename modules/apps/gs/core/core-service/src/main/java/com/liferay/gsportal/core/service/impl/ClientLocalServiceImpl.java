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

import java.util.Date;
import java.util.List;

import com.liferay.gsportal.core.exception.InvalidNameException;
import com.liferay.gsportal.core.exception.NoSuchClientException;
import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.model.Project;
import com.liferay.gsportal.core.service.MemberOfLocalService;
import com.liferay.gsportal.core.service.ProjectLocalService;
import com.liferay.gsportal.core.service.StaffMemberLocalService;
import com.liferay.gsportal.core.service.base.ClientLocalServiceBaseImpl;
import com.liferay.gsportal.core.util.CoreConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the client local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gsportal.client.service.ClientLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author GS
 * @see ClientLocalServiceBaseImpl
 * @see com.liferay.gsportal.client.service.ClientLocalServiceUtil
 */
@ProviderType
public class ClientLocalServiceImpl extends ClientLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.gsportal.client.service.ClientLocalServiceUtil} to access the client local service.
	 */

	@Override
	public Client addClient(
			long parentClientId, long companyId,
			String name, String description, String dashboardUrl, long logoId,
			String logoUrl, String websiteUrl, Address address, ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(name, parentClientId);

		Client client = clientPersistence.create(
			counterLocalService.increment(Client.class.getName()));

		Date now = new Date();

		client.setCompanyId(companyId);
		client.setCreateDate(now);
		client.setModifiedDate(now);

		client.setParentClientId(parentClientId);
		client.setName(name);
		client.setDescription(description);
		client.setDashboardUrl(dashboardUrl);
		client.setLogoId(logoId);
		client.setLogoUrl(logoUrl);
		client.setWebsiteUrl(websiteUrl);
		client.setActive(true);

		address = addClientAddress(serviceContext.getUserId(), client.getClientId(), address, serviceContext);
		client.setAddressId(address.getAddressId());

		client = clientPersistence.update(client);

		//indexes the client
		Indexer indexer = IndexerRegistryUtil.getIndexer(Client.class);
		indexer.reindex(Client.class.getName(), client.getClientId());

		return client;
	}

	@Override
	public Address addClientAddress(
			long userId, long clientId, Address address,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return addressLocalService.addAddress(userId, Client.class.getName(),
			clientId, address.getStreet1(), address.getStreet2(),
			address.getStreet3(), address.getCity(), address.getZip(),
			address.getRegionId(), address.getCountryId(), address.getTypeId(),
			address.getMailing(), address.getPrimary(), serviceContext);
	}

	/**
	 * Delete a client and remove the address and website from the database. If
	 * a client has children projects or subclients, throw an exception to
	 * prevent deletion.
	 *
	 * @throws SystemException
	 * @throws PortalException
	 */
	@Override
	public Client deleteClient(long companyId, long clientId)
		throws PortalException, SystemException {

		Client client = clientLocalService.getClient(clientId);

		List<Client> subClients = clientPersistence.findByParentClientId(clientId);

		if (subClients.size() > 0) {
			throw new PortalException("Cannot delete a client with subclients");
		}

		List<Project> projects = _projectLocalService.getProjectsByClientId(clientId);

		if (projects.size() > 0) {
			throw new PortalException("Cannot delete a client with projects");
		}

		addressLocalService.deleteAddress(client.getAddressId());

		return clientPersistence.remove(clientId);
	}

	@Override
	public int getClientsCount() throws SystemException {
		return clientPersistence.countAll();
	}

	@Override
	public Client getParentClient(long clientId)
			throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		Client client = clientLocalService.getClient(clientId);
		List<Client> clients = clientPersistence.findByParentClientId(
			client.getParentClientId());

		if (clients.size() > 0) {
			return clients.get(0);
		}
		else {
			throw new NoSuchClientException(Long.toString(client.getParentClientId()));
		}
	}

	@Override
	public List<Client> getSubclientsByClientId(long clientId, int start, int end)
		throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		return clientPersistence.findByParentClientId(clientId, start, end);
	}

	@Override
	public int getSubclientsCountByClientId(long clientId)
		throws PortalException, SystemException {

		if (clientId <= 0) {
			throw new NoSuchClientException(Long.toString(clientId));
		}

		return clientPersistence.countByParentClientId(clientId);
	}

	/**
	 * Overwrites the specified fields for Client with the given clientId
	 *
	 * @param clientId
	 * @param parentClientId
	 * @param name
	 * @param description
	 * @param dashboardUrl
	 * @param logoId
	 * @param logoUrl
	 * @param websiteUrl
	 * @param addressId
	 * @return
	 * @throws PortalException
	 * @throws SystemException
	 */
	@Override
	public Client updateClient(
			long clientId, long parentClientId, String name,
			String description, String dashboardUrl, long logoId,
			String logoUrl, String websiteUrl, long addressId)
		throws PortalException, SystemException {

		validate(name, parentClientId);

		Client client = clientLocalService.getClient(clientId);

		client.setModifiedDate(new Date());
		client.setParentClientId(parentClientId);
		client.setName(name);
		client.setDescription(description);
		client.setDashboardUrl(dashboardUrl);
		client.setLogoId(logoId);
		client.setLogoUrl(logoUrl);
		client.setWebsiteUrl(websiteUrl);
		client.setAddressId(addressId);

		clientPersistence.update(client);

		//indexes the client
		Indexer indexer = IndexerRegistryUtil.getIndexer(Client.class);
		indexer.reindex(Client.class.getName(), clientId);

		return client;
	}

	/**
	 * Sets the staffMembers associated with the client
	 * @param clientId
	 * @param staffMemberIds
	 * @throws SystemException
	 */
	@Override
	public void addClientStaffMembers(
			long clientId, long[] staffMemberIds, int[] memberRoleIds)
		throws PortalException, SystemException{

		_memberOfLocalService.setMemberOfs(
			clientId, CoreConstants.NOT_IN, CoreConstants.NOT_IN, staffMemberIds, memberRoleIds);
	}

//	@Override
//	public int countClientStaff(long clientId)
//		throws PortalException, SystemException {
//
//		return _memberOfLocalService.countStaffByClient(clientId);
//	}
//
//	@Override
//	public List<StaffMember> getClientStaff(long clientId)
//		throws PortalException, SystemException {
//
//		return _memberOfLocalService.getClientStaff(clientId);
//
//	}

	private void validate(String name, long parentId) throws PortalException {

		if (Validator.isNull(name)) {
			throw new InvalidNameException();
		}

		// If there is a parent client, validate its ID.
		if (parentId != CoreConstants.NO_PARENT_ID) {
			try {
				getClient(parentId);
			}
			catch (SystemException se) {
				_log.error("Error found when checking parent client.", se);
			}
		}
	}

	@Override
	public Hits getClientByKeywords(
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

		if (Validator.isNull(searchContext)) {
			throw new PortalException("searchContext cannot be null");
		}
		// keywords can be null -> we will still restrict the search on one or more types

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);

		BooleanQuery typeFilter = BooleanQueryFactoryUtil.create(searchContext);
		boolean likeMatchOnType = false;

		typeFilter.addTerm(
			Field.ENTRY_CLASS_NAME, Client.class.getName(), likeMatchOnType,
			BooleanClauseOccur.SHOULD);

		query.add(typeFilter, BooleanClauseOccur.MUST);

		if (Validator.isNotNull(keywords) && !keywords.isEmpty()) {
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

	private StaffMemberLocalService _staffMemberLocalService;

	private MemberOfLocalService _memberOfLocalService;

	private ProjectLocalService _projectLocalService;

	private static Log _log = LogFactoryUtil.getLog(ClientLocalServiceImpl.class);
}