package com.tcs.sgv.filter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.ess.valueobject.OrgUserMst;

public class PriveledgeFilterDAOImpl extends GenericDaoHibernateImpl {

	public PriveledgeFilterDAOImpl(Class<OrgUserMst> type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	public List getRoleListForUser(String userName) {
		List roleList = null;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select rolepost.role_id from ORG_USER_MST user ");
		sb.append("inner join ORG_USERPOST_RLT userpost on user.user_id=userpost.user_id ");
		sb.append("inner join ACL_POSTROLE_RLT rolepost on userpost.post_id=rolepost.post_id ");
		sb.append("where user.user_name='" + userName + "'");
		Query query = session.createSQLQuery(sb.toString());
		roleList = query.list();
		return roleList;
	}

	public boolean checkPriveledgeForElementId(String elementId, String roleId) {
		boolean isValid = false;
		Session session = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from acl_role_element_rlt ");
		sb.append("where element_code=" + elementId + " and role_id in(" + roleId + ") and activate_flag=1");
		Query query = session.createSQLQuery(sb.toString());

		if (Integer.parseInt(query.list().get(0).toString()) > 0) {
			isValid = true;
		}
		return isValid;
	}
}
