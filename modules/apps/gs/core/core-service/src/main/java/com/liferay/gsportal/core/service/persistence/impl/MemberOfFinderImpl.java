package com.liferay.gsportal.core.service.persistence.impl;

import com.liferay.gsportal.core.model.StaffMember;
import com.liferay.gsportal.core.service.persistence.MemberOfFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Thomas Stewart
 */

public class MemberOfFinderImpl extends MemberOfFinderBaseImpl
	implements MemberOfFinder{

	public int countStaffByClient(long clientId)
		throws PortalException, SystemException {

		Long count = null;

		// Open database session

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(MemberOfFinderImpl.class, COUNT_STAFF_BY_CLIENT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(clientId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				count = itr.next();
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		finally {
			if (count == null) {

				// Convert the count to a meaningful value

				count = Long.valueOf(0);
			}

			// Close database session

			session.close();
		}

		return count.intValue();
	}

	public List<StaffMember> findStaffByClient(long clientId)
		throws PortalException, SystemException {

		List<StaffMember> list = Collections.EMPTY_LIST;

		// Open database session

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(MemberOfFinderImpl.class, FIND_STAFF_BY_CLIENT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Core_StaffMember", StaffMember.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(clientId);

			list = q.list();
		}
		catch (Exception e) {
			_log.error(e);
		}
		finally {

			// Close database session

			session.close();
		}

		return list;
	}

	public int countStaffByProject(long projectId)
		throws PortalException, SystemException {

		Long count = null;

		// Open database session

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(MemberOfFinderImpl.class, COUNT_STAFF_BY_PROJECT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);

			Iterator<Long> itr = q.list().iterator();

			if (itr.hasNext()) {
				count = itr.next();
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
		finally {
			if (count == null) {

				// Convert the count to a meaningful value

				count = Long.valueOf(0);
			}

			// Close database session

			session.close();
		}

		return count.intValue();
	}

	public List<StaffMember> findStaffByProject(long projectId)
		throws PortalException, SystemException {

		List<StaffMember> list = Collections.EMPTY_LIST;

		// Open database session

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(MemberOfFinderImpl.class, FIND_STAFF_BY_PROJECT);

			SQLQuery q = session.createSQLQuery(sql);

			q.addEntity("Core_StaffMember", StaffMember.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);

			list = q.list();
		}
		catch (Exception e) {
			_log.error(e);
		}
		finally {

			// Close database session

			session.close();
		}

		return list;
	}

	// IDs of the custom SQL specified in custom-sql/default.xml

	private static String COUNT_STAFF_BY_CLIENT = MemberOfFinderImpl.class.getName()
		+ ".countStaffByClient";
	private static String COUNT_STAFF_BY_PROJECT = MemberOfFinderImpl.class.getName()
			+ ".countStaffByProject";
	private static String FIND_STAFF_BY_CLIENT = MemberOfFinderImpl.class.getName()
			+ ".findStaffByClient";
	private static String FIND_STAFF_BY_PROJECT = MemberOfFinderImpl.class.getName()
		+ ".findStaffByProject";

	private static Log _log = LogFactoryUtil.getLog(MemberOfFinderImpl.class);
}