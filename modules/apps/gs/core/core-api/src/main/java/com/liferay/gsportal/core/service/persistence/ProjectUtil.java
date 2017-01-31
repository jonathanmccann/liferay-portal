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

package com.liferay.gsportal.core.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gsportal.core.model.Project;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the project service. This utility wraps {@link com.liferay.gsportal.core.service.persistence.impl.ProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectPersistence
 * @see com.liferay.gsportal.core.service.persistence.impl.ProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Project project) {
		getPersistence().clearCache(project);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Project> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Project> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Project> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Project> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Project update(Project project) {
		return getPersistence().update(project);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Project update(Project project, ServiceContext serviceContext) {
		return getPersistence().update(project, serviceContext);
	}

	/**
	* Returns all the projects where clientId = &#63;.
	*
	* @param clientId the client ID
	* @return the matching projects
	*/
	public static List<Project> findByClientId(long clientId) {
		return getPersistence().findByClientId(clientId);
	}

	/**
	* Returns a range of all the projects where clientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param clientId the client ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of matching projects
	*/
	public static List<Project> findByClientId(long clientId, int start, int end) {
		return getPersistence().findByClientId(clientId, start, end);
	}

	/**
	* Returns an ordered range of all the projects where clientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param clientId the client ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects
	*/
	public static List<Project> findByClientId(long clientId, int start,
		int end, OrderByComparator<Project> orderByComparator) {
		return getPersistence()
				   .findByClientId(clientId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the projects where clientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param clientId the client ID
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching projects
	*/
	public static List<Project> findByClientId(long clientId, int start,
		int end, OrderByComparator<Project> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByClientId(clientId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws NoSuchProjectException if a matching project could not be found
	*/
	public static Project findByClientId_First(long clientId,
		OrderByComparator<Project> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchProjectException {
		return getPersistence().findByClientId_First(clientId, orderByComparator);
	}

	/**
	* Returns the first project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	*/
	public static Project fetchByClientId_First(long clientId,
		OrderByComparator<Project> orderByComparator) {
		return getPersistence()
				   .fetchByClientId_First(clientId, orderByComparator);
	}

	/**
	* Returns the last project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws NoSuchProjectException if a matching project could not be found
	*/
	public static Project findByClientId_Last(long clientId,
		OrderByComparator<Project> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchProjectException {
		return getPersistence().findByClientId_Last(clientId, orderByComparator);
	}

	/**
	* Returns the last project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	*/
	public static Project fetchByClientId_Last(long clientId,
		OrderByComparator<Project> orderByComparator) {
		return getPersistence().fetchByClientId_Last(clientId, orderByComparator);
	}

	/**
	* Returns the projects before and after the current project in the ordered set where clientId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public static Project[] findByClientId_PrevAndNext(long projectId,
		long clientId, OrderByComparator<Project> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchProjectException {
		return getPersistence()
				   .findByClientId_PrevAndNext(projectId, clientId,
			orderByComparator);
	}

	/**
	* Removes all the projects where clientId = &#63; from the database.
	*
	* @param clientId the client ID
	*/
	public static void removeByClientId(long clientId) {
		getPersistence().removeByClientId(clientId);
	}

	/**
	* Returns the number of projects where clientId = &#63;.
	*
	* @param clientId the client ID
	* @return the number of matching projects
	*/
	public static int countByClientId(long clientId) {
		return getPersistence().countByClientId(clientId);
	}

	/**
	* Caches the project in the entity cache if it is enabled.
	*
	* @param project the project
	*/
	public static void cacheResult(Project project) {
		getPersistence().cacheResult(project);
	}

	/**
	* Caches the projects in the entity cache if it is enabled.
	*
	* @param projects the projects
	*/
	public static void cacheResult(List<Project> projects) {
		getPersistence().cacheResult(projects);
	}

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectId the primary key for the new project
	* @return the new project
	*/
	public static Project create(long projectId) {
		return getPersistence().create(projectId);
	}

	/**
	* Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectId the primary key of the project
	* @return the project that was removed
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public static Project remove(long projectId)
		throws com.liferay.gsportal.core.exception.NoSuchProjectException {
		return getPersistence().remove(projectId);
	}

	public static Project updateImpl(Project project) {
		return getPersistence().updateImpl(project);
	}

	/**
	* Returns the project with the primary key or throws a {@link NoSuchProjectException} if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public static Project findByPrimaryKey(long projectId)
		throws com.liferay.gsportal.core.exception.NoSuchProjectException {
		return getPersistence().findByPrimaryKey(projectId);
	}

	/**
	* Returns the project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project, or <code>null</code> if a project with the primary key could not be found
	*/
	public static Project fetchByPrimaryKey(long projectId) {
		return getPersistence().fetchByPrimaryKey(projectId);
	}

	public static java.util.Map<java.io.Serializable, Project> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the projects.
	*
	* @return the projects
	*/
	public static List<Project> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of projects
	*/
	public static List<Project> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects
	*/
	public static List<Project> findAll(int start, int end,
		OrderByComparator<Project> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of projects
	*/
	public static List<Project> findAll(int start, int end,
		OrderByComparator<Project> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProjectPersistence, ProjectPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ProjectPersistence.class);
}