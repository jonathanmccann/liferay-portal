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

import com.liferay.gsportal.core.exception.NoSuchProjectException;
import com.liferay.gsportal.core.model.Project;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gsportal.core.service.persistence.impl.ProjectPersistenceImpl
 * @see ProjectUtil
 * @generated
 */
@ProviderType
public interface ProjectPersistence extends BasePersistence<Project> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectUtil} to access the project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the projects where clientId = &#63;.
	*
	* @param clientId the client ID
	* @return the matching projects
	*/
	public java.util.List<Project> findByClientId(long clientId);

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
	public java.util.List<Project> findByClientId(long clientId, int start,
		int end);

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
	public java.util.List<Project> findByClientId(long clientId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator);

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
	public java.util.List<Project> findByClientId(long clientId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project
	* @throws NoSuchProjectException if a matching project could not be found
	*/
	public Project findByClientId_First(long clientId,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator)
		throws NoSuchProjectException;

	/**
	* Returns the first project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching project, or <code>null</code> if a matching project could not be found
	*/
	public Project fetchByClientId_First(long clientId,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator);

	/**
	* Returns the last project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project
	* @throws NoSuchProjectException if a matching project could not be found
	*/
	public Project findByClientId_Last(long clientId,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator)
		throws NoSuchProjectException;

	/**
	* Returns the last project in the ordered set where clientId = &#63;.
	*
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching project, or <code>null</code> if a matching project could not be found
	*/
	public Project fetchByClientId_Last(long clientId,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator);

	/**
	* Returns the projects before and after the current project in the ordered set where clientId = &#63;.
	*
	* @param projectId the primary key of the current project
	* @param clientId the client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next project
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public Project[] findByClientId_PrevAndNext(long projectId, long clientId,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator)
		throws NoSuchProjectException;

	/**
	* Removes all the projects where clientId = &#63; from the database.
	*
	* @param clientId the client ID
	*/
	public void removeByClientId(long clientId);

	/**
	* Returns the number of projects where clientId = &#63;.
	*
	* @param clientId the client ID
	* @return the number of matching projects
	*/
	public int countByClientId(long clientId);

	/**
	* Caches the project in the entity cache if it is enabled.
	*
	* @param project the project
	*/
	public void cacheResult(Project project);

	/**
	* Caches the projects in the entity cache if it is enabled.
	*
	* @param projects the projects
	*/
	public void cacheResult(java.util.List<Project> projects);

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectId the primary key for the new project
	* @return the new project
	*/
	public Project create(long projectId);

	/**
	* Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectId the primary key of the project
	* @return the project that was removed
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public Project remove(long projectId) throws NoSuchProjectException;

	public Project updateImpl(Project project);

	/**
	* Returns the project with the primary key or throws a {@link NoSuchProjectException} if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project
	* @throws NoSuchProjectException if a project with the primary key could not be found
	*/
	public Project findByPrimaryKey(long projectId)
		throws NoSuchProjectException;

	/**
	* Returns the project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project, or <code>null</code> if a project with the primary key could not be found
	*/
	public Project fetchByPrimaryKey(long projectId);

	@Override
	public java.util.Map<java.io.Serializable, Project> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the projects.
	*
	* @return the projects
	*/
	public java.util.List<Project> findAll();

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
	public java.util.List<Project> findAll(int start, int end);

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
	public java.util.List<Project> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator);

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
	public java.util.List<Project> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Project> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	*/
	public int countAll();
}