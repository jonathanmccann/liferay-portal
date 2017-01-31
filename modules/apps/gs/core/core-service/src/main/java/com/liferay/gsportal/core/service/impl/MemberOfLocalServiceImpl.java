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
import com.liferay.gsportal.core.model.MemberOf;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.gsportal.core.service.StaffMemberLocalService;
import com.liferay.gsportal.core.service.base.MemberOfLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the member of local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gsportal.core.service.MemberOfLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author GS
 * @see MemberOfLocalServiceBaseImpl
 * @see com.liferay.gsportal.core.service.MemberOfLocalServiceUtil
 */
@ProviderType
public class MemberOfLocalServiceImpl extends MemberOfLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.gsportal.memberof.service.MemberOfLocalServiceUtil} to access the member of local service.
	 */

	@Override
	public void setMemberOfs(
			long clientId, long projectId, long engagementId, long[] userIds,
			int[] memberRoleIds)
		throws PortalException, SystemException {

		validate(userIds, memberRoleIds);

		List<MemberOf> currentMemberOfusers = new ArrayList<MemberOf>();
		List<MemberOf> unmodifiableList = memberOfPersistence.findByC_P_E(
			clientId, projectId, engagementId);

		currentMemberOfusers.addAll(unmodifiableList);

		// Add/update current memberOf objects
		for (int i = 0; i < userIds.length; i++) {
			updateMemberOf(
				clientId, projectId, engagementId, userIds[i],
				memberRoleIds[i], currentMemberOfusers);
		}

		// Delete stale memberOf objects
		for (MemberOf memberOf : currentMemberOfusers) {
			memberOfPersistence.remove(memberOf);
		}
	}

	private void updateMemberOf(
			long clientId, long projectId, long engagementId, long userId,
			int memberRoleId, List<MemberOf> currentMemberOfusers)
		throws PortalException, SystemException {

		MemberOf memberOf = memberOfPersistence.fetchByU_C_P_E(
				userId, clientId, projectId, engagementId);

		if (memberOf != null) {
			currentMemberOfusers.remove(memberOf);
			memberOf.setMemberRoleId(memberRoleId);
		}
		else {
			memberOf = createMemberOf(
				counterLocalService.increment(MemberOf.class.getName()));

			long companyId = _clientLocalService.getClient(clientId).getCompanyId();
			memberOf.setCompanyId(companyId);
			memberOf.setUserId(userId);
			memberOf.setClientId(clientId);
			memberOf.setProjectId(projectId);
			memberOf.setEngagementId(engagementId);
			memberOf.setMemberRoleId(memberRoleId);
			memberOf.setCreateDate(new Date());
		}

		memberOfPersistence.update(memberOf);
	}

	protected void validate(long[] userIds, int[] roleIds)
		throws PortalException, SystemException {

		if (userIds.length != roleIds.length) {
			throw new PortalException(
				"User Id and Role Id arrays must be the same size");
		}
	}
//
//	@Override
//	public int countStaffByProject(long projectId)
//		throws PortalException, SystemException {
//
//		return memberOfFinder.countStaffByProject(projectId);
//	}
//
//	@Override
//	public int countStaffByClient(long clientId)
//			throws PortalException, SystemException {
//
//		return memberOfFinder.countStaffByClient(clientId);
//	}
//
//	@Override
//	public List<StaffMember> getClientStaff(long clientId)
//		throws PortalException, SystemException {
//
//		return memberOfFinder.findStaffByClient(clientId);
//	}
//
//	@Override
//	public List<StaffMember> getProjectStaff(long projectId)
//		throws PortalException, SystemException {
//
//		return memberOfFinder.findStaffByProject(projectId);
//	}

	private StaffMemberLocalService _staffMemberLocalService;

	private ClientLocalService _clientLocalService;
}