package com.tcs.sgv.eis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

public class SchoolValidationReportDAOImpl extends GenericDaoHibernateImpl implements SchoolValidationReportDAO{

	Logger logger = Logger.getLogger(EmployeeValidationDAOImpl.class );
	Session hibSession=null;
	public SchoolValidationReportDAOImpl(Class<SchoolValidationReportDAOImpl> class1,
			SessionFactory sessionFactory) {	
		super(class1);
		hibSession = sessionFactory.getCurrentSession();
		setSessionFactory(sessionFactory);
	}

	@Override
	public List getTotalSchoolsConfig() {
		
		List totalSchoolsConf=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,dist.district_name,zp.admin_code, zp.admin_name ,count(temp.ddo_code) Total ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,dist.district_name,zp.admin_code, zp.admin_name ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getTotalSchoolsConfig DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			totalSchoolsConf= query.list();
		}
		
		logger.info("totalSchoolsConf size: "+totalSchoolsConf.size());
		return totalSchoolsConf;
	}

	@Override
	public List getApprovedSchools() {
		List noOfApprovedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Approved ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getApprovedSchools DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			noOfApprovedSchools= query.list();
		}
		
		logger.info("noOfApprovedSchools size: "+noOfApprovedSchools.size());
		return noOfApprovedSchools;
	}

	@Override
	public List getPendingSchools() {
		List noOfpendingSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Pending ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=0)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getPendingSchools DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			noOfpendingSchools= query.list();
		}
		
		logger.info("noOfpendingSchools size: "+noOfpendingSchools.size());
		return noOfpendingSchools;
	}

	@Override
	public List getRejectedSchools() {
		List noOfRejectedSchools=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(temp.ddo_code) Rejected ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status=-1)) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getRejectedSchools DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			noOfRejectedSchools= query.list();
		}
		
		logger.info("noOfRejectedSchools size: "+noOfRejectedSchools.size());
		return noOfRejectedSchools;
	}
	
	public List getDataEntryInitiatedSchools() {
		List dataEntryInitiated=null;
		StringBuffer str= new StringBuffer();
		str.append("select temp.district,zp.admin_code ,count(distinct(emp.ddo_code)) schools_initiated ");
		str.append(" from (select distinct ddo_code, district from mst_dcps_ddo_office where ddo_code in ");
		str.append(" (select zp_ddo_code from rlt_zp_ddo_map where status in (0,1,-1))) temp ");
		str.append(" inner join zp_admin_name_mst zp on zp.admin_code= substr(temp.ddo_code,1,2) ");
		str.append(" inner join mst_dcps_emp emp on emp.ddo_code=temp.ddo_code ");
		str.append(" left outer join cmn_district_mst dist on temp.district=dist.district_id ");
		str.append(" where dist.lang_id=1 and emp.form_status in(0,1,-1) and emp.reg_status in (0,1,2) and emp.created_date>'2012-08-15' ");
		str.append(" group by temp.district,zp.admin_code ");
		str.append(" order by temp.district, zp.admin_code");
		logger.info("getDataEntryInitiatedSchools DAO------"+str.toString());
		Query query= hibSession.createSQLQuery(str.toString());
		
		if((query.list()!=null)){
			dataEntryInitiated= query.list();
		}
	
		
		logger.info("dataEntryInitiated size: "+dataEntryInitiated.size());
		return dataEntryInitiated;
	}

	  public List getUsername() {
    List list = new ArrayList();
    Session hibSession = getSession();
    StringBuffer strBfr = new StringBuffer();
    strBfr.append("  SELECT ORG.USER_NAME,ft.HOST_IP,ft.LOGIN_DATE_TIME,ft.REMARK,ft.LOGOUT_TIME, ");
    strBfr.append("  CASE WHEN (ft.LOGOUT_TIME is null and ft.LOGIN_STATUS=142) THEN 'Still Login' ");
    strBfr.append("  WHEN (ft.LOGOUT_TIME is null and ft.LOGIN_STATUS=143) THEN 'Password is Invalid' ");
    strBfr.append("  WHEN (ft.LOGOUT_TIME is not null and ft.LOGIN_STATUS=142) THEN 'logout  Successful' ");
    strBfr.append("  Else 'logout  Successful' end as comment ");
    strBfr.append("  FROM frm_login_audit ft inner join ORG_USER_MST ORG  on ft.USER_ID = ORG.USER_ID ");
    strBfr.append("  where to_char (ft.LOGIN_DATE_TIME,'YYYY-MM-DD')=to_char(CURRENT_DATE,'YYYY-MM-DD') ");
    strBfr.append(" ORDER BY ft.LOGIN_DATE_TIME DESC limit 100 ");
    SQLQuery sQLQuery = hibSession.createSQLQuery(strBfr.toString());
    this.logger.info("getUsername---->" + sQLQuery);
    list = sQLQuery.list();
    return list;
  }

}