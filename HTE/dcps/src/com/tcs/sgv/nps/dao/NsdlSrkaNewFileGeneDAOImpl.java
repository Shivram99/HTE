package com.tcs.sgv.nps.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
  
import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.nps.service.PerformFileUpload;
import com.tcs.sgv.nps.service.STPWebServicePOJO;
import com.tcs.sgv.nps.dao.STPWebServicePOJOService;

//import net.sf.hibernate.cfg.Environment;

import org.apache.log4j.Logger;

public class NsdlSrkaNewFileGeneDAOImpl extends GenericDaoHibernateImpl {
	private Session ghibSession = null;
	private final Logger gLogger = Logger.getLogger(getClass());
	private List <String> fileList;	
	private static ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/nps/NPSConstants");

	public NsdlSrkaNewFileGeneDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		ghibSession = sessionFactory.getCurrentSession();
	}

	// changes By Akanksha 
	public List getEmployeeListNsdl(String locId, String treasury,String gStrLocationCode) {
		List empLst = null;
		StringBuilder Strbld = new StringBuilder();

		try {
			Strbld.append("  SELECT emp.EMP_NAME,emp.DCPS_ID, emp.PRAN_NO,legacy.emp_contri,legacy.employer_contri, legacy.emp_int,legacy.employer_int,    ");
			Strbld.append(" loc.loc_name,reg.DTO_REG_NO,reg.ddo_reg_no ,NPS_ID,emp.ddo_code FROM mst_dcps_emp  emp ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE    ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE   ");
//			Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE  ");
		//	Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.ZP_DDO_CODE  ");
			Strbld.append(" inner join mst_dto_reg reg on reg.ddo_code = zp.ZP_DDO_CODE  ");
		//	Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)    ");
			if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> 2222 ");
			} else {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> 2222 ");
			}
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)    ");
			Strbld.append(" where  emp.PRAN_NO is not null and emp.zp_STATUS=10 and  legacy.batch_id is  null  and  emp.dcps_or_gpf='Y' and legacy.STATUS = 1  and legacy.period = "+ treasury+
					" and substr(ddo2.ddo_code,1,4)=" + locId + " and ddo2.LOCATION_CODE='"+gStrLocationCode+"'");
			
			Strbld.append("  order by  reg.ddo_reg_no ");

			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());

			empLst = lQuery.list();

		} catch (Exception e) {
			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empLst;
	}

	public List getFinyear() {
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		String query = "select finYearCode,finYearCode from SgvcFinYearMst where finYearCode between '2015' and '"
				+ currentYear + "' order by finYearCode ASC";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;

		if ((lLstResult != null) && (lLstResult.size() != 0)) {
			lLstReturnList = new ArrayList();

			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				Object[] obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}
		return lLstReturnList;
	}

	public List getAllData(String yrCode, String month, String gLocId,String strDDOCode)
			throws Exception {
		List contrList = null;
		Query lQuery = null;
		StringBuilder lSBQuery = null;
		try {
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT distinct bh.FILE_NAME,bh.BH_EMP_AMOUNT,bh.BH_EMPLR_AMOUNT,NVL(bh.TRANSACTION_ID,'') ,  case when bh.file_status = 0 then cast('File not Validated' as varchar(20))  when bh.file_status = 1 then cast('File is validated' as varchar(20))   when bh.file_status = 2 then cast('File is rejected' as varchar(20)) when bh.file_status = 5 then cast('Contribution file send' as varchar(25)) when bh.file_status =11 then cast('Transaction Id Updated' as varchar(25)) when bh.file_status =12 then cast('Bill Locked' as varchar(25))  end ,bh.file_status,bh.BH_BATCH_FIX_ID,\r\n" + 
					"        bh.VOUCHER_NO,bh.VOUCHER_DATE,bh.BDS_NO,bh.BANK_REFNO ,( SELECT LOOKUP_NAME FROM ifms.CMN_LOOKUP_MST where LOOKUP_ID =(SELECT distinct PERIOD FROM ifms.DCPS_LEGACY_DATA where BATCH_ID= substr(bh.FILE_NAME,2,length(bh.FILE_NAME)))) FROM NSDL_BH_DTLS bh ");
			lSBQuery.append("  inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=bh.DDO_CODE where bh.MONTH='" + month + "' and bh.YEAR= '"
					+ yrCode + "'  and rlt.REPT_DDO_CODE='"+strDDOCode+"'  and bh.file_name like 'R" + gLocId+ "%' and bh.STATUS <> -1  and bh.IS_LEGACY_DATA='Y'  ");

			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("lQuery*******is to get the list" + lQuery);
			contrList = lQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw e;
		}
		return contrList;
	}

	public List getAllData(String yrCode, String month, String gLocId)
			throws Exception {
		List contrList = null;
		Query lQuery = null;
		StringBuilder lSBQuery = null;
		try {
			lSBQuery = new StringBuilder();
			lSBQuery.append(" SELECT distinct bh.FILE_NAME,bh.BH_EMP_AMOUNT,bh.BH_EMPLR_AMOUNT,NVL(bh.TRANSACTION_ID,'') ,  case when bh.file_status = 0 then cast('File not Validated' as varchar(20))  when bh.file_status = 1 then cast('File is validated' as varchar(20))   when bh.file_status = 2 then cast('File is rejected' as varchar(20)) when bh.file_status = 5 then cast('Contribution file send' as varchar(25)) when bh.file_status =11 then cast('Transaction Id Updated' as varchar(25))  end ,bh.file_status,bh.BH_BATCH_FIX_ID  FROM NSDL_BH_DTLS bh     ");
			lSBQuery.append("  inner join ifms.RLT_ZP_DDO_MAP as rlt on rlt.ZP_DDO_CODE=bh.DDO_CODE where bh.MONTH='" + month + "' and bh.YEAR= '"
					+ yrCode + "'  and  bh.file_name like 'R" + gLocId+ "%' and bh.STATUS <> -1 and bh.IS_LEGACY_DATA='Y'   ");

			lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
			logger.info("lQuery*******is to get the list" + lQuery);
			contrList = lQuery.list();
		} catch (Exception e) {
			e.printStackTrace();
			gLogger.error("Error is :" + e, e);
			throw e;
		}
		return contrList;
	}
	public List getFinyeardesc() {
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		String query = "select finYearId,finYearDesc from SgvcFinYearMst where finYearCode between '2012' and '"
				+ currentYear + "' order by finYearCode ASC";
		List<Object> lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(query);
		Query selectQuery = ghibSession.createQuery(sb.toString());
		List lLstResult = selectQuery.list();
		ComboValuesVO lObjComboValuesVO = null;

		if ((lLstResult != null) && (lLstResult.size() != 0)) {
			lLstReturnList = new ArrayList();

			for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
				Object[] obj = (Object[]) lLstResult.get(liCtr);
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId(obj[0].toString());
				lObjComboValuesVO.setDesc(obj[1].toString());
				lLstReturnList.add(lObjComboValuesVO);
			}
		} else {
			lLstReturnList = new ArrayList();
			lObjComboValuesVO = new ComboValuesVO();
			lObjComboValuesVO.setId("-1");
			lObjComboValuesVO.setDesc("--Select--");
			lLstReturnList.add(lObjComboValuesVO);
		}
		return lLstReturnList;
	}

	public String getDtoRegNo(String FileName) {
		List temp = null;
		String data = "";
		StringBuilder Strbld = new StringBuilder();
		Strbld.append("SELECT DTO_REG_NO FROM ifms.NSDL_DH_DTLS as a ");
		Strbld.append("inner join ifms.MST_DTO_REG as b  on a.DH_DDO_REG_NO=b.DDO_REG_NO where a.FILE_NAME='"+FileName+"'");
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("Query to getErrorData in  heaqder**********"
				+ Strbld.toString());

		temp = lQuery.list();
		logger.info("temp size" + temp.size());
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}

		return data;
}
	
	public String getDtoRegNo1(String treasuryCode) {
		List temp = null;
		String data = "";
		StringBuilder Strbld = new StringBuilder();
 		Strbld.append(" SELECT ASSOCIATED_DTA_REG_NO FROM MST_DTO_REG where loc_id ="
				+ treasuryCode);
		logger.info("treasuryCode" + treasuryCode);
		if ((treasuryCode != null) && (treasuryCode.equalsIgnoreCase("3333"))) {
			Strbld.append("  and loc_id <> 3301 ");
		} else {
			Strbld.append("  and loc_id <> 3333 ");
		}
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("Query to getErrorData in  heaqder**********"
				+ Strbld.toString());
		temp = lQuery.list();
		logger.info("temp size" + temp.size());
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}

		return data;
	}

	public String getBatchData(String fileNumber) {
		String lLstReturnList = "";
		StringBuilder sb = new StringBuilder();

		sb.append(" select SR_NO||'^'||HEADER_NAME||'^'||BH_NO||'^'||BH_COL2||'^'||BH_FIX_NO||'^'||BH_DATE||'^'||BH_BATCH_FIX_ID||'^^'||BH_DDO_COUNT||'^'||BH_PRAN_COUNT||'^'||BH_EMPLR_AMOUNT||'^'||BH_EMP_AMOUNT||'^^'||BH_TOTAL_AMT||'^' from NSDL_BH_dtls ");
		sb.append(" where FILE_NAME='" + fileNumber + "' and IS_LEGACY_DATA='Y' ");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.uniqueResult().toString();

		return lLstReturnList;
	}

	public List getDHData(String fileNumber) {
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||DH_NO||'^'||DH_COL2||'^'||DH_DDO_REG_NO||'^'||BH_SD_COUNT||'^'||DH_EMPLR_AMOUNT||'^'||DH_EMP_AMOUNT||'^^',DH_DDO_REG_NO FROM NSDL_DH_dtls ");
		sb.append(" where FILE_NAME='" + fileNumber + "' and IS_LEGACY_DATA='Y' order by SR_NO asc");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}

	public List getSDDtls(String fileNumber, String ddoRegNo) {
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT SR_NO||'^'||HEADER_NAME||'^'||SD_NO||'^'||SD_NO_2||'^'||SD_NO_3||'^'||SD_PRAN_NO||'^'||SD_EMPLR_AMOUNT||'^'||SD_EMP_AMOUNT||'^'||'^'||SD_TOTAL_AMT||'^'||SD_STATUS||'^'||SD_REMARK||'^' FROM NSDL_SD_DTLS  ");
		sb.append(" where   FILE_NAME='" + fileNumber + "'and DDO_REG_NO='"
				+ ddoRegNo + "' and IS_LEGACY_DATA='Y' order by SR_NO asc ");

		Query selectQuery = ghibSession.createSQLQuery(sb.toString());
		lLstReturnList = selectQuery.list();

		return lLstReturnList;
	}

	public void updateFileStatus(int fileStatus, String fileno, String errorData) {
		StringBuilder sb = new StringBuilder();
		errorData = errorData.replace("'", "");
		sb.append("  update NSDL_BH_DTLS set file_status='" + fileStatus
				+ "'  ");

		if ((errorData != null) && (!errorData.equals(""))) {
			sb.append(" , error_data='" + errorData + "' ");
		}

		sb.append("   where FILE_NAME='" + fileno + "' and IS_LEGACY_DATA='Y' ");
		Query updateQuery = ghibSession.createSQLQuery(sb.toString());

		logger.info("Query to delete in deleteNsdlFile heaqder**********"
				+ sb.toString());

		updateQuery.executeUpdate();
	}

	public String getEmployeeIntList(String finType, String treasury) {
		List empLst = null;
		String Intamount = null;

		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" select cast(sum(abc.INT_EMPLR_DIFF) as double) from  ( SELECT emp.EMP_NAME,emp.DCPS_ID,emp.PRAN_NO,yr.CONTRIB_EMPLOYEE,yr.CONTRIB_EMPLOYER,cast(yr.INT_CONTRB_EMPLOYEE as double) as a,cast(yr.INT_CONTRB_EMPLOYER as double) as b, ");
		Strbld.append(" loc.loc_name,dto.dto_reg_no,reg.ddo_reg_no as ddoreg ,      ");
		Strbld.append("\t(cast(yr.CONTRIB_EMPLOYEE as double) - cast(nvl(ldata.EMP_CONTRI,0) as double))as CONTRIB_EMP_DIFF, ");
		Strbld.append("\t(cast(yr.CONTRIB_EMPLOYER as double) - cast(nvl(ldata.EMPLR_CONTRI,0) as double))as CONTRIB_EMPLR_DIFF, ");
		Strbld.append("\tdec((cast(yr.INT_CONTRB_EMPLOYEE as double)-cast(nvl(ldata.EMP_INT,0) as double )),25,2) as INT_EMP_DIFF, ");
		Strbld.append("\t dec((cast(yr.INT_CONTRB_EMPLOYER as double)-cast(nvl(ldata.EMPLR_INT,0) as double )),25,2) as INT_EMPLR_DIFF  ");
		Strbld.append(" FROM MST_DCPS_CONTRIBUTION_YEARLY yr   ");
		Strbld.append(" inner join mst_dcps_emp emp on emp.DCPS_ID= yr.DCPS_ID    ");
		Strbld.append("  inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(emp.ddo_code,1,2)  ");
		if ((treasury != null) && (treasury.equalsIgnoreCase("3333"))) {
			Strbld.append("  and dto.loc_id <> 3301 ");
		} else {
			Strbld.append("  and dto.loc_id <> 3333 ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on substr(loc.LOC_ID,1,2)=substr(emp.DDO_CODE,1,2) and loc.department_id=100003  ");
		Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code=emp.DDO_CODE    ");
		Strbld.append(" left outer join dcps_legacy_data ldata on yr.DCPS_ID=ldata.dcps_id and yr.YEAR_ID=ldata.fin_year_code  ");
		Strbld.append(" where   yr.YEAR_ID='"
				+ finType
				+ "' and loc.loc_id='"
				+ treasury
				+ "'and emp.AC_DCPS_MAINTAINED_BY=700174  and emp.pran_no is not null and yr.batch_id is  null and (emp.EMP_SERVEND_DT  >= '2015-04-01' or emp.EMP_SERVEND_DT is null)");
		Strbld.append("  order by  reg.ddo_reg_no ) abc   ");

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst != null) && (empLst.size() > 0) && (empLst.get(0) != null)) {
			Intamount = empLst.get(0).toString();
		}

		return Intamount;
	}

	public String getFinyrdesc(long yrId) {
		List lyrDesc = null;
		String yrDesc = null;

		StringBuilder lSBQuery = new StringBuilder();

		Query lQuery = null;

		lSBQuery.append(" select fin_Year_Desc from Sgvc_Fin_Year_Mst where fin_Year_id="
				+ yrId);

		lQuery = ghibSession.createSQLQuery(lSBQuery.toString());

		lyrDesc = lQuery.list();
		if ((lyrDesc.size() != 0) && (lyrDesc != null)
				&& (lyrDesc.get(0) != null)) {
			yrDesc = lyrDesc.get(0).toString();
		}

		return yrDesc;
	}

	public String getEmployeeContriTotalList(String locId, String treasury) {
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.emp_contri) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
		Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	 	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
			Strbld.append("  and reg.ddo_code <> '2222' ");
		} else {
			Strbld.append("  and reg.ddo_code <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where  substr(ddo2.ddo_code,1,4)="
				+ locId
				+ "     and emp.PRAN_NO is not null and emp.zp_STATUS=10 and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
				+ treasury);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
	}

	public String getEmployeeContriTotalListInterest(String locId, String treasury) {
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.EMP_INT) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
		Strbld.append(" inner join mst_dto_reg reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
		//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
		if ((locId != null) && (locId.equalsIgnoreCase("\"2222\""))) {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		} else {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
				+ treasury);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
	}

	/**********FOR sepeaartion of ddo wise ******************/
	
	public String getEmployeeContriTotalList(String locId, String treasury ,String ddoCode) {
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.emp_contri) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
		Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	 	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
			Strbld.append("  and reg.ddo_code <> '2222' ");
		} else {
			Strbld.append("  and reg.ddo_code <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where  zp.REPT_DDO_CODE='"+ddoCode+"' and substr(ddo2.ddo_code,1,4)="
				+ locId
				+ "     and emp.PRAN_NO is not null and emp.zp_STATUS=10 and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
				+ treasury);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
	}

	public String getEmployeeContriTotalListInterest(String locId, String treasury,String ddoCode) {
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.EMP_INT) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
		Strbld.append(" inner join mst_dto_reg reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
		//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
		if ((locId != null) && (locId.equalsIgnoreCase("\"2222\""))) {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		} else {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where zp.REPT_DDO_CODE='"+ddoCode+"' and substr(ddo2.ddo_code,1,4)="+ locId+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
				+ treasury);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
	}
	
	
	public String getEmploylerContriTotalList(String locId, String treasury,String ddoCode) {
		// TODO Auto-generated method stub
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.EMPLOYER_CONTRI) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
//		Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE   ");
		Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
		//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
		if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		} else {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where zp.REPT_DDO_CODE='"+ddoCode+"' and  substr(ddo2.ddo_code,1,4)="+ locId+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10 and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
				+ treasury);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;

	}
	public String getEmploylerContriTotalListInterest(String locId, String treasuryyno,String ddoCode) {
		// TODO Auto-generated method stub
		List empLst = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT sum(legacy.EMPLOYER_INT) FROM mst_dcps_emp  emp  ");
		Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
		Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
		Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
		Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
		//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
		if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		} else {
			Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
		}
		Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
		Strbld.append(" where zp.REPT_DDO_CODE='"+ddoCode+"' and substr(ddo2.ddo_code,1,4)="+ locId
				+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "+ treasuryyno);

		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("script for all employee ---------" + lQuery.toString());

		empLst = lQuery.list();
		if ((empLst.size() > 0) || (empLst != null)) {
			amountTotal = empLst.get(0).toString();
		}

		return amountTotal;
	}
	
	/**********FOR sepeaartion of ddo wise ******************/
	
	
	public List getEmployeeList(String locId, String treasury) {
		List empLst = null;
		StringBuilder Strbld = new StringBuilder();
		try {
			Strbld.append(" SELECT emp.EMP_NAME,emp.DCPS_ID,emp.SEVARTH_ID,legacy.PERIOD,legacy.emp_contri,legacy.employer_contri,legacy.emp_int,legacy.employer_int,legacy.TOTAL,CLM.LOOKUP_DESC,cast(legacy.status as int),emp.pran_no,legacy.REMARKS");
			Strbld.append(" FROM mst_dcps_emp  emp    ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE  ");
			Strbld.append(" inner join CMN_LOOKUP_MST CLM on CLM.LOOKUP_ID=legacy.PERIOD  ");
			Strbld.append("inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE ");
			Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId+ "   and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null  ");
			Strbld.append(" and  emp.dcps_or_gpf='Y' and legacy.period = "+ treasury + " and legacy.status=2  ");
			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employee ---------" + lQuery.toString());

			empLst = lQuery.list();

		} catch (Exception e) {

			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empLst;
	}
	
	//Method overloading
	public List getEmployeeList(String locId, String treasury,String strDDoCode) {
		List empLst = null;
		StringBuilder Strbld = new StringBuilder();
		try {
			Strbld.append(" SELECT emp.EMP_NAME,emp.DCPS_ID,emp.SEVARTH_ID,legacy.PERIOD,legacy.emp_contri,legacy.employer_contri,legacy.emp_int,legacy.employer_int,legacy.TOTAL,CLM.LOOKUP_DESC,cast(legacy.status as int),emp.pran_no,legacy.REMARKS");
			Strbld.append(" FROM mst_dcps_emp  emp    ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE  ");
			Strbld.append(" inner join CMN_LOOKUP_MST CLM on CLM.LOOKUP_ID=legacy.PERIOD  ");
			Strbld.append("inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE ");
			Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId+ " and zp.REPT_DDO_CODE='"+strDDoCode+"'  and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null  ");
			Strbld.append(" and  emp.dcps_or_gpf='Y' and legacy.period = "+ treasury + " and legacy.status=2  ");
			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employee ---------" + lQuery.toString());

			empLst = lQuery.list();

		} catch (Exception e) {

			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empLst;
	}

	public String getEmployeeListDdoregNsdl(String locId, String treasury,
			String ddoRegNo) {
		List empLst = null;

		String empDdoLst = null;

		StringBuilder Strbld = new StringBuilder();

		try {
			Strbld.append("   SELECT cast (sum(legacy.emp_contri) as double) FROM mst_dcps_emp  emp  ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
			Strbld.append(" inner join mst_dto_reg reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
			//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
			if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			} else {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			}

			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOcation_code=substr(ddo2.DDO_CODE,1,4)     ");
			Strbld.append(" where  substr(ddo2.ddo_code,1,4)="
					+ locId
					+ "     and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null  and legacy.STATUS = 1  and  emp.dcps_or_gpf='Y' and legacy.period = "
					+ treasury + " and reg.DDO_REG_NO='" + ddoRegNo + "' ");

			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employee ---------" + lQuery.toString());

			empLst = lQuery.list();
			if ((empLst != null) && (empLst.size() > 0)) {
				empDdoLst = empLst.get(0).toString();
			}

		} catch (Exception e) {
			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empDdoLst;
	}

	public String getEmployeeListDdoregNsdlInt(String locId, String treasury,
			String ddoRegNo) {
		List empLst = null;

		String empDdoLst = null;

		StringBuilder Strbld = new StringBuilder();

		try {
			Strbld.append("   SELECT cast (sum(legacy.emp_int) as double) FROM mst_dcps_emp  emp  ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
			Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
			//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
			if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			} else {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			}
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOcation_code=substr(ddo2.DDO_CODE,1,4)     ");
			Strbld.append(" where  substr(ddo2.ddo_code,1,4)="
					+ locId
					+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null  and legacy.STATUS = 1  and  emp.dcps_or_gpf='Y' and legacy.period = "
					+ treasury + " and reg.DDO_REG_NO='" + ddoRegNo + "' ");

			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employee ---------" + lQuery.toString());

			empLst = lQuery.list();
			if ((empLst != null) && (empLst.size() > 0)) {
				empDdoLst = empLst.get(0).toString();
			}

		} catch (Exception e) {
			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empDdoLst;
	}

	public String getEmployeeRecordCountDdoregNsdl(String locId,
			String treasury, String ddoRegNo) {
		List empLst = null;

		String empDdoLst = null;

		StringBuilder Strbld = new StringBuilder();

		try {
			Strbld.append(" select sum(a.count1)+sum(a.count2)  from   ");
			Strbld.append(" (SELECT case when legacy.emp_contri = 0 then 0 else 1 end as count1, case when legacy.emp_int <= 0 then 0 else 1 end as count2  FROM mst_dcps_emp  emp     ");
			Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID    ");
			Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE   ");
			Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE    ");
			Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE    ");
			//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)   ");
			if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			} else {
				Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
			}
			Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOcation_code=substr(ddo2.DDO_CODE,1,4)   ");
			Strbld.append(" where  substr(ddo2.ddo_code,1,4)="
					+ locId
					+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null  and legacy.STATUS = 1 ");
			Strbld.append(" and  emp.dcps_or_gpf='Y' and legacy.period = "
					+ treasury + " and reg.DDO_REG_NO='" + ddoRegNo + "' )a  ");

			logger.info("   ---------" + Strbld.toString());
			SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
			logger.info("script for all employee ---------" + lQuery.toString());

			empLst = lQuery.list();
			if ((empLst != null) && (empLst.size() > 0)) {
				empDdoLst = empLst.get(0).toString();
			}

		} catch (Exception e) {
			logger.info("Error occer in  getEmployeeList ---------" + e);
		}
		return empDdoLst;
	}

	public List getAllTreasuries() {
		List lstAis = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("  SELECT LOOKUP_ID,LOOKUP_NAME FROM CMN_LOOKUP_MST where PARENT_LOOKUP_ID = 10001198257 ");
			Query LsQuery = ghibSession.createSQLQuery(sb.toString());

			logger.info("Script is ----------------" + LsQuery.toString());
			List lLstResult = LsQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			if ((lLstResult != null) && (lLstResult.size() != 0)) {
				lstAis = new ArrayList();

				for (int liCtr = 0; liCtr < lLstResult.size(); liCtr++) {
					Object[] obj = (Object[]) lLstResult.get(liCtr);
					lObjComboValuesVO = new ComboValuesVO();
					lObjComboValuesVO.setId(obj[0].toString());
					lObjComboValuesVO.setDesc(obj[1].toString());
					lstAis.add(lObjComboValuesVO);
				}
			} else {
				lstAis = new ArrayList();
				lObjComboValuesVO = new ComboValuesVO();
				lObjComboValuesVO.setId("-1");
				lObjComboValuesVO.setDesc("--ALL--");
				lstAis.add(lObjComboValuesVO);
			}
		} catch (Exception e) {
			logger.info("Error found in getAllTreasuries ----------" + e);
		}
		return lstAis;
	}

	public String getLocationId(String treasuryCode) {
		List temp = null;
		String data = "";
		StringBuilder Strbld = new StringBuilder();
		Strbld.append("  SELECT LOC.LOC_ID  FROM CMN_LOCATION_MST loc inner join ORG_DDO_MST org on substr(org.DDO_CODE,1,4)=loc.LOC_ID where org.LOCATION_CODE="
				//+ treasuryCode + "  AND LOC.DEPARTMENT_ID=100003"); commented By Akanksha
				+ treasuryCode + " ");
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("Query to getErrorData in  heaqder**********"
				+ Strbld.toString());
		temp = lQuery.list();
		logger.info("temp size" + temp.size());
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}

		logger.info("data" + data);
		return data;
	}

	public String getBatchId(String treasuryCode) {
		List temp = null;
		String data = "0";
		StringBuilder Strbld = new StringBuilder();
		Strbld.append(" SELECT cast(max(substr(FILE_NAME,2,14)) as bigint)+1 FROM NSDL_BH_DTLS where FILE_NAME like 'R"
				+ treasuryCode + "%' and  IS_LEGACY_DATA='Y'");
		SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
		logger.info("Query to getErrorData in  heaqder**********"
				+ Strbld.toString());
		temp = lQuery.list();
		logger.info("temp size" + temp.size());
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}

		logger.info("data" + data);
		return data;
	}

	public void updateBatchId(String BatchId, List finalNpdId) {
		Session hibSession = getSession();
		StringBuffer str = new StringBuffer();

		str.append("update DCPS_LEGACY_DATA set batch_id='"
				+ BatchId
				+ "', STATUS = '3' where  batch_id is null and  NPS_ID in  ( :billIds )  ");

		Query query1 = hibSession.createSQLQuery(str.toString());
		query1.setParameterList("billIds", finalNpdId);
		logger.info("Query to updateBatchId**********" + query1.toString());
		query1.executeUpdate();
	}

	public void insertDHDetails(Long lLngPkIdForDh, int i, String string,
			String string2, int j, String ddoRegNo, Long empCount,
			String totalEmplyDHContri, String totalEmplyerDHContri,
			String batchId) {
		Session session = getSession();
		StringBuffer str = new StringBuffer();
		str.append("insert into NSDL_DH_dtls values (\t'" + lLngPkIdForDh
				+ "',\t'" + i + "','" + string + "','" + string2 + "','" + j
				+ "','" + ddoRegNo + "','" + empCount + "',");
		str.append("'" + totalEmplyDHContri + "','" + totalEmplyerDHContri
				+ "','" + batchId + "',0,'Y')");
		Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in insertDHDetails heaqder**********"
				+ str.toString());

		updateQuery.executeUpdate();
	}

	public void insertSDDetails(Long lLngPkIdForSd, int i, String string,
			String string2, int j, int empCount, String pranno,
			String govEmpContri, String subempContri, String string3,
			String string4, String batchId, String string5, String ddoRegNo) {
		Session session = getSession();
		StringBuffer str = new StringBuffer();
		str.append("insert into NSDL_SD_dtls values ('" + lLngPkIdForSd
				+ "',\t'" + i + "','" + string + "','" + string2 + "','" + j
				+ "','" + empCount + "','" + pranno + "',");
		str.append("'" + govEmpContri + "','" + subempContri + "','" + string3
				+ "','" + string4 + "','" + batchId + "','" + string5 + "','"
				+ ddoRegNo + "',0,'Y')");
		Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in insertSDDetails heaqder**********"
				+ str.toString());

		updateQuery.executeUpdate();
	}

	public void insertBatchHeader(Long lLngPkIdForBh, String bhHeader1,
			String bhHeader, String bhHeader2, String string4, String string5,
			String currentdate, String string6, long ddoCount, int count,
			String govContri, String subContri, String total, String fileName,
			int year1, String month1,String ddoCode) {
		Session session = getSession();
		StringBuffer str = new StringBuffer();
		str.append("insert into NSDL_BH_dtls values ( '" + lLngPkIdForBh
				+ "', '" + bhHeader1 + "','" + bhHeader + "','" + bhHeader2
				+ "','" + string4 + "','" + string5 + "','" + currentdate
				+ "',");
		str.append("'" + string6 + "','" + ddoCount + "','" + count + "','"
				+ govContri + "','" + subContri + "','" + total + "','"
				+ fileName + "','" + year1 + "','" + month1 + "','" + 0
				+ "',null,0,null,null,null,null,null,'0','Y','"+ddoCode+"',sysdate,null,null,null,null,null,null)");
		Query updateQuery = session.createSQLQuery(str.toString());
		logger.info("Query to insert in batch heaqder**********"
				+ str.toString());

		updateQuery.executeUpdate();
	}

	
	public  String sendContriFile(HttpServletResponse response, String ddoCode, String batchId, String dtoUserId,String dtoRegNo) throws NoSuchAlgorithmException, IOException 
    {	
        String str ="";	
    	try{	
			System.out.println("creating ws");	
			final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");	
			
			System.setProperty("https.protocols", "TLSv1.2");
			
			
			System.out.println("current protocol>>>"+SSLContext.getDefault().getSupportedSSLParameters().getProtocols());
		
	        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
	        System.out.println("wsdlURL"+wsdlURL);
	        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
	       System.out.println("creating ws1"+ss);	
	       
	        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
	      //  System.out.println("http://121.240.64.237/STPWeb/STPWebServicePOJOPort?wsdl");	
	        System.out.println("creating ws2");	
	        
	        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
	        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
					 
				}else 
				{
					ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
					 
				}
	        
	        BindingProvider bindingProvider = (BindingProvider) port;	
	    
	        /*bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	*/
	        
	        bindingProvider.getRequestContext().put(	
	        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY	,ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
	        	// "https://cra-nsdl.com/STPWeb/STPWebServicePOJOPort?wsdl");	
//	        "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");
	        
	        	   //"https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");	
	         // "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	
	        
	      
	    
	        System.out.println("creating ws3");	
	        
	        // {	
	   	System.out.println("Invoking performFileUpload...");	
	    	java.util.List<byte[]> _performFileUpload_arg0 = new java.util.ArrayList<byte[]>();	
	    	
	    	String OUTPUT_ZIP_FILE=null;
	    	String	OUTPUT_ZIP_Contri_FILE=null;
	    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
	    		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
	    		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
			}else 
			{
				OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
				OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
			}
	    	
	    	String fileUpload=OUTPUT_ZIP_FILE+"/"+dtoUserId+".sig";
	    	String directoryName =OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv";	
	 	   File directory = new File(directoryName);	
	 		if (!directory.exists()) {	
	 			directory.mkdir();	
	 			// If you require it to make the entire directory path including	
	 			// parents,	
	 			// use directory.mkdirs(); here instead.	
	 		}	
	    	System.out.println("hello every one "+ddoCode );	
	    	String zipFolderName="contri_after_fuv.zip";	
	    	File source = new File(fileUpload);	
	    	File dest = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig");	
	    	FileUtils.copyFile(source, dest);	
	    		
	   	
	    	File sourceFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+batchId+".fvu");	
	    	File destFile = new File(OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/contributionFile.pao");	
	    	
	    	
	    	if (!destFile.exists()) {	
	    		destFile.createNewFile();		
	 		}
	    		
	    	FileUtils.copyFile(sourceFile,destFile); 
	    	
	    	
	    	/*if (sourceFile.renameTo(destFile)) {	
	    	    System.out.println("File moved successfully");	
	    	    	} else {	
	    	    System.out.println("Failed to move file");	
	    	    	}	*/
	    	
	   String outputzipFile = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
       	
       String[] srcFiles = { OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/contributionFile.pao", OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig"};	
       try {	
            	
           // create byte buffer	
           byte[] buffer = new byte[1024];	
           FileOutputStream fos = new FileOutputStream(outputzipFile);	
           ZipOutputStream zos = new ZipOutputStream(fos);	
            	
           for (int i=0; i < srcFiles.length; i++) {	
                	
               File srcFile = new File(srcFiles[i]);	
               FileInputStream fis = new FileInputStream(srcFile);	
               // begin writing a new ZIP entry, positions the stream to the start of the entry data	
               zos.putNextEntry(new ZipEntry(srcFile.getName()));	
                	
               int length;	
               while ((length = fis.read(buffer)) > 0) {	
                   zos.write(buffer, 0, length);	
               }	
               zos.closeEntry();	
               // close the InputStream	
               fis.close();	
                	
           }	
           // close the ZipOutputStream	
           zos.close();	
            	
       }	catch(Exception e){	
       		
       e.printStackTrace()	;	
       }	
	   	
	   	
	   
	    		
	    	System.out.println("--------"+ddoCode);	
	        //String zipFileName = OUTPUT_ZIP_FILE;//"E:/nsdl/"+ddoCode+".zip";	
	        String zipFileName = OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/"+zipFolderName;	
	        PerformFileUpload fileUploadd = new PerformFileUpload();	
	        byte[] fileByte;	
	        File zipFile = new File(outputzipFile);	
	        FileInputStream fis = new FileInputStream(zipFileName);	
	      	
	        fileByte = new byte[(int) zipFile.length()];	
	        fis.read(fileByte);	
	        System.out.println("Hello every one sysdate");	
	        System.out.println("--"+fileByte.toString());	
	        byte[][] _performFileUpload_arg0Val1 = new byte[3][0];	
	        _performFileUpload_arg0Val1[0] = dtoUserId.getBytes();	
	        _performFileUpload_arg0Val1[1] = "Upload SubscriberContribution-DSC".getBytes(); //ye wala	
	        _performFileUpload_arg0Val1[2] = fileByte; // zip file byte	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[0]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[1]);	
	        _performFileUpload_arg0.add(_performFileUpload_arg0Val1[2]);	
	       java.lang.String _performFileUpload__return = port.performFileUpload(_performFileUpload_arg0);	
	        System.out.println("performFileUpload.result=" + _performFileUpload__return);	
	         str = _performFileUpload__return.substring(_performFileUpload__return.indexOf("<file-reference-number>") + "<file-reference-number>".length(),	
	        		_performFileUpload__return.indexOf("</file-reference-number>"));	
	     	}finally {	
	     			
	     	}	
    	
    	
    	return str;	
    		
    }	

	public void updatebatchdetaisls(String refCode, String bhID) {
		// TODO Auto-generated method stub
		Session hibSession =getSession();
		StringBuffer str2 = new StringBuffer();
		str2.append("update NSDL_BH_DTLS set FILE_STATUS = 5, status = 5,OLD_TRANSACTION_ID= '"+refCode+"' ,file_upload_created_date=sysdate where BH_BATCH_FIX_ID = '"+bhID+"'  and IS_LEGACY_DATA='Y'");
		logger.error("NSDL_BH_DTLS------"+str2.toString());
		Query query3 = hibSession.createSQLQuery(str2.toString());
		query3.executeUpdate();
	}

	public String getContriStatus(HttpServletResponse response, String ddoCode, String refCode, String dtoUserId,
			String batchId, String dtoRegNo) throws IOException, NoSuchAlgorithmException, KeyManagementException, ClassNotFoundException, SQLException
	{
		
	 	//final QName SERVICE_NAME = new QName("http://webservice.core.stp.cra.com/", "STPWebServicePOJOService");
		final QName SERVICE_NAME = new QName("{https://121.240.64.237/", "STPWebServicePOJOService");
        URL wsdlURL = STPWebServicePOJOService.WSDL_LOCATION;	
    	System.setProperty("https.protocols", "TLSv1.2");
    	

		
		System.out.println("current protocol>>>"+SSLContext.getDefault().getSupportedSSLParameters().getProtocols());
      	
        STPWebServicePOJOService ss = new STPWebServicePOJOService(wsdlURL);	
        System.out.println("creating ws1");	
        STPWebServicePOJO port = ss.getSTPWebServicePOJOPort();  	
//        System.out.println("creating ws2");	
//        System.out.println("https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");
        
        String ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL=null;
        if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
        	ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION") ;
				 
			}else 
			{
				ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL = gObjRsrcBndle.getString("NPS.ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER");
				 
			}
        
        BindingProvider bindingProvider = (BindingProvider) port;	
        bindingProvider.getRequestContext().put(	
        	      BindingProvider.ENDPOINT_ADDRESS_PROPERTY,ENDPOINT_ADDRESS_PROPERTY_CONTRIBUTION_SERVER_URL);
        	     //  "https://www.npscan-cra.com/STPWeb/STPWebServicePOJOPort?wsdl");	
        // "http://121.240.64.237/STPWeb/STPWebServicePOJOPort");	
       	
        System.out.println("creating ws3");	
 	
    	System.out.println("Invoking perform status enquiry...16-11");	
        java.util.List<byte[]> _performStatusInquiry_arg0 = new java.util.ArrayList<byte[]>();	
        	
       //String zipFileName = "/disk2/disk1/dto_sign/"+ddoCode+".sig";	
    	//String zipFileName="E:/sign/"+ddoCode+".sig";	
        	
    //String zipFileName = "/disk2/disk1/DTO_SIGN/"+ddoCode+".sig";  //tomcat9	
//String zipFileName="/disk1/NPS/DTO_SIGN/"+dtoRegNo+".sig"; //43


        //C:\output\Jalsevaarth\Contribution\7101003892\3333202201001\output\contri_after_fuv
        
        String OUTPUT_ZIP_FILE=null;
    	String	OUTPUT_ZIP_Contri_FILE=null;
    	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
    		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
    		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
    	System.out.println("ref code>>>"+refCode);
        

String zipFileName=OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"/output/contri_after_fuv/"+dtoUserId+".sig";
//String zipFileName=OUTPUT_ZIP_Contri_FILE+ddoCode+"/"+batchId+"//output//contri_after_fuv/112448630.sig";
	System.out.println("PATH:"+zipFileName);
//important code for path  start	
	// Get Image start
//	String key = "";
//String rootPath = "";
//	String strOSName = System.getProperty("os.name");
//	boolean test = strOSName.contains("Windows");
//	if (strOSName.contains("Windows")) {
//	//	key = "serverempconfigimagepath";
//	} else {
//		key = "serverContributionFolderPath";
//		String filepath = "E:\\Nsdldata";
//		//OUTPUT_ZIP_Contri_FILE = environment.getRequiredProperty(key);
//		OUTPUT_ZIP_Contri_FILE = filepath;
//		zipFileName=OUTPUT_ZIP_Contri_FILE+"/DTO_SIGN/"+dtoRegNo+".sig";;
//		
//	}
	
	

        PerformFileUpload fileUpload = new PerformFileUpload();	
        byte[] fileByte;	
        File zipFile = new File(zipFileName);	
        FileInputStream fis = new FileInputStream(zipFileName);	
        fileByte = new byte[(int) zipFile.length()];	
        fis.read(fileByte);	
        String statusRefCode=refCode;//347602	
        System.out.println(fileByte.toString());	
        String userId=dtoUserId.substring(0, dtoUserId.length() - 2);	
    	byte[][] _performStatusInquiry_arg0Val1 = new byte[5][0];	
    	 _performStatusInquiry_arg0Val1[0] = dtoUserId.getBytes();	
    	 _performStatusInquiry_arg0Val1[1] = statusRefCode.getBytes();	
    	 _performStatusInquiry_arg0Val1[2] = userId.getBytes(); // zip file byte	
    	 _performStatusInquiry_arg0Val1[3] = fileByte; // zip file byte	
         _performStatusInquiry_arg0Val1[4] = "File Status-SubscriberContribution".getBytes();; // zip file byte	
         	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[0]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[1]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[2]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[3]);	
         _performStatusInquiry_arg0.add(_performStatusInquiry_arg0Val1[4]);	
         java.lang.String _performStatusInquiry__return = port.performStatusInquiry(_performStatusInquiry_arg0);	
         System.out.println("performStatusInquiry.result=" + _performStatusInquiry__return);	
         	
        String str= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<transaction_id>") + "<transaction_id>".length(),	
        		 _performStatusInquiry__return.indexOf("</transaction_id>"));
        String fileStatus= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<file_status>") + "<file_status>".length(),	
       		 _performStatusInquiry__return.indexOf("</file_status>"));
       if(!str.isEmpty() && !str.equals("0"))
        {
     		String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<contr_submission_form_html>") + "<contr_submission_form_html>".length(),	
	        		 _performStatusInquiry__return.indexOf("</response_html>"));	
           StringBuilder htmlStringBuilder=new StringBuilder();	
           //append html header and title	
           htmlStringBuilder.append(str1);	
      WriteToFileTrn(response,htmlStringBuilder.toString(),str+"_Challan.html",ddoCode,str,batchId);	
           
        } 
        if(str.isEmpty()){	
        	if(fileStatus=="1") {
        		//For delete record issue resolve here.
        		
        		System.out.println("Error existes");
        		
        	}else {
		    	String str1= _performStatusInquiry__return.substring(_performStatusInquiry__return.indexOf("<response_html>") + "<response_html>".length(),	
		        		 _performStatusInquiry__return.indexOf("</response_html>"));	
	            StringBuilder htmlStringBuilder=new StringBuilder();	
	            //append html header and title	
	            htmlStringBuilder.append(str1);	
	            str =  WriteToFile(response,htmlStringBuilder.toString(),"testfile.html",ddoCode);	
            
        	}
            
            
	    }
    return str;	
    }	

public static String WriteToFile(HttpServletResponse response,String fileContent, String fileName, String ddoCode) throws IOException {	
	
	   String OUTPUT_ZIP_FILE=null;
   	String	OUTPUT_ZIP_Contri_FILE=null;
   	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
   		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
   		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
       
    String projectPath = OUTPUT_ZIP_FILE +ddoCode;	
    
    
    File file1 = new File(projectPath);	
    if (!file1.exists()) {	
    	file1.mkdir();	
    }
    
    
    String tempFile = projectPath + File.separator+fileName;	
    String temporaryFile = fileName;	
    File file = new File(tempFile);	
    // if file does exists, then delete and create a new file	
    if (file.exists()) {	
        try {	
            File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);	
            file.renameTo(newFileName);	
            file.createNewFile();	
        } catch (IOException e) {	
            e.printStackTrace();	
        }	
    }	
    //write to file with OutputStreamWriter	
    OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());	
    Writer writer=new OutputStreamWriter(outputStream);	
    writer.write(fileContent);	
    writer.close();	
    PrintWriter outputfile = response.getWriter();
    try {
    	String fileN = "ErrorFile" + ".html";
			response.setContentType("text/plain;charset=UTF-8");

			response.addHeader("Content-disposition",
					"attachment; filename=" + fileN);
			response.setCharacterEncoding("UTF-8");

			outputfile.write(fileContent);
			outputfile.flush();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//logger.info("All fine 7 is *********");
			if (outputfile != null)
				outputfile.close();
		}
     
       
    
    return tempFile;
}	

public static void WriteToFileTrn(HttpServletResponse response,String fileContent, String fileName, String ddoCode, String str, String batchId) throws IOException {	
	
	   String OUTPUT_ZIP_FILE=null;
   	String	OUTPUT_ZIP_Contri_FILE=null;
   	if(System.getProperty("os.name").toLowerCase().split(" ")[0].equals("windows")) {
   		OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN");
   		OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE");
		}else 
		{
			OUTPUT_ZIP_FILE=gObjRsrcBndle.getString("NPS.DTO_SIGN_SERVER");
			OUTPUT_ZIP_Contri_FILE=gObjRsrcBndle.getString("NPS.OUTPUT_FILE_SERVER");
		}
       
	
    String projectPath = OUTPUT_ZIP_Contri_FILE +ddoCode+"/"+batchId;	
    File directory = new File(projectPath);
	if (!directory.exists()) {
		directory.mkdir();
		// If you require it to make the entire directory path including
		// parents,
		// use directory.mkdirs(); here instead.
	}

    String tempFile = projectPath + File.separator+fileName;	
    String temporaryFile = fileName;
    File file = new File(tempFile);	
    // if file does exists, then delete and create a new file	
    if (file.exists()) {	
        try {	
            File newFileName = new File(projectPath + File.separator+ "backup_"+fileName);	
            file.renameTo(newFileName);	
            file.createNewFile();	
        } catch (IOException e) {	
            e.printStackTrace();	
        }	
    }	
    //write to file with OutputStreamWriter	
    OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());	
    Writer writer=new OutputStreamWriter(outputStream);	
    writer.write(fileContent);	
    writer.close();	
    
  
}

public void updateTransactionId(String transactioId, String bhID) {
	// TODO Auto-generated method stub
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("update NSDL_BH_DTLS set TRANSACTION_ID = '"+transactioId+"',FILE_STATUS = 11 ,Challan_received_created_date=sysdate where BH_BATCH_FIX_ID = '"+bhID+"'  and IS_LEGACY_DATA='Y'");
	logger.error("NSDL_BH_DTLS------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
}

 
public void updateTransactionIdError(String transactioId, String bhID) {
	// TODO Auto-generated method stub
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("update NSDL_BH_DTLS set FILE_STATUS = '"+1+"' where BH_BATCH_FIX_ID = '"+bhID+"' ");
	logger.error("NSDL_BH_DTLS------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
}

public String getNsdlStatusCode(String bhID) {
	// TODO Auto-generated method stub
	List empLst = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT OLD_TRANSACTION_ID FROM NSDL_BH_DTLS where BH_BATCH_FIX_ID='"+bhID+"'  and IS_LEGACY_DATA='Y'");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	empLst = lQuery.list();
	if ((empLst.size() > 0) || (empLst != null)) {
		amountTotal = empLst.get(0).toString();
	}

	return amountTotal;
	
}

public String getDdoCode(String bhID, String fileno) {
	// TODO Auto-generated method stub
	List ddocode = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT DDO_CODE FROM NSDL_BH_DTLS where BH_BATCH_FIX_ID='"+bhID+"' and FILE_NAME = '"+fileno+"' and IS_LEGACY_DATA='Y' ");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	ddocode = lQuery.list();
	if ((ddocode.size() > 0) || (ddocode != null)) {
		amountTotal = ddocode.get(0).toString();
	}

	return amountTotal;
}

public String getDdoCode(String fileno) {
	// TODO Auto-generated method stub
	List ddocode = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT DDO_CODE FROM NSDL_BH_DTLS where FILE_NAME = '"+fileno+"' ");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	ddocode = lQuery.list();
	if ((ddocode.size() > 0) || (ddocode != null)) {
		amountTotal = ddocode.get(0).toString();
	}

	return amountTotal;
}

public String getDtouserId(String ddocode) {
	// TODO Auto-generated method stub
	List dtouserId = null;

	String dtoUser = null;
	StringBuilder Strbld = new StringBuilder();
   
	Strbld.append(" SELECT User_ID FROM MST_DtO_REG where DDO_CODE = '" + ddocode + "' ");
 
	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	dtouserId = lQuery.list();
	if ((dtouserId.size() > 0) || (dtouserId != null)) {
		dtoUser = dtouserId.get(0).toString();
	}

	return dtoUser;
}

public String getDtoReg(String ddocode) {
	// TODO Auto-generated method stub
	List dtouserId = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();
	/*if(ddocode.equals("2222222222")) {
		//ddocode="20541200001";
	}*/
	//SELECT distinct User_ID FROM ifms.MST_DTO_REG as dto inner join ifms.RLT_ZP_DDO_MAP as rlt on dto.DDO_CODE=rlt.ZP_DDO_CODE  where  rlt.REPT_DDO_CODE = '"+ddocode+"' ");
	Strbld.append(" SELECT distinct dto.DTO_REG_NO FROM ifms.MST_DTO_REG as dto inner join ifms.RLT_ZP_DDO_MAP as rlt on dto.DDO_CODE=rlt.ZP_DDO_CODE  where  rlt.zp_DDO_CODE = '"+ddocode+"' ");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	dtouserId = lQuery.list();
	logger.info("dtouserId ---------" + dtouserId);
	if ((dtouserId.size() > 0) || (dtouserId != null)) {
		amountTotal = dtouserId.get(0).toString();
	}

	return amountTotal;
}

public String getFileStatus(String ddocode, String fileno,  String bhID) {
	// TODO Auto-generated method stub
	List dtouserId = null;

	String file_status = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT file_status FROM NSDL_BH_DTLS where FILE_NAME = '"+fileno+"' and DDO_CODE = '"+ddocode+"' ");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	dtouserId = lQuery.list();
	if ((dtouserId.size() > 0) || (dtouserId != null)) {
		file_status = dtouserId.get(0).toString();
	}

	return file_status;
}

public void deleteFileDetails(String fileno, String ddocode, String batch_id) {
	// TODO Auto-generated method stub
	Session hibSession =getSession();
	StringBuffer str2 = new StringBuffer();
	str2.append("upadte from ifms.NSDL_BH_DTLS set status='-1' where FILE_NAME = '"+fileno+"' and DDO_CODE = '"+ddocode+"' and IS_LEGACY_DATA='Y'");
	logger.error("NSDL_BH_DTLS------"+str2.toString());
	Query query3 = hibSession.createSQLQuery(str2.toString());
	query3.executeUpdate();
	

	StringBuffer str3 = new StringBuffer();
	str3.append("update from ifms.NSDL_DH_DTLS  set dh_status='-1' where FILE_NAME = '"+fileno+"' and IS_LEGACY_DATA='Y'");
	logger.error("NSDL_BH_DTLS------"+str3.toString());
	Query query4 = hibSession.createSQLQuery(str3.toString());
	query4.executeUpdate();
	

	StringBuffer str4 = new StringBuffer();
	str4.append("update from ifms.NSDL_SD_DTLS  set status='-1' where FILE_NAME = '"+fileno+"' and IS_LEGACY_DATA='Y'");
	logger.error("NSDL_SD_DTLS------"+str4.toString());
	Query query5 = hibSession.createSQLQuery(str4.toString());
	query5.executeUpdate();
	

	StringBuffer str5 = new StringBuffer();
	str5.append("Delete from DCPS_LEGACY_DATA where BATCH_ID = '"+batch_id+"' and ddo_code = '"+ddocode+"' ");
	logger.error("DCPS_LEGACY_DATA------"+str5.toString());
	Query query6 = hibSession.createSQLQuery(str5.toString());
	query6.executeUpdate();
	
}

public String getEmploylerContriTotalList(String locId, String treasury) {
	// TODO Auto-generated method stub
	List empLst = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT sum(legacy.EMPLOYER_CONTRI) FROM mst_dcps_emp  emp  ");
	Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
	Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
	Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
//	Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE   ");
	Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	} else {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	}
	Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
	Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10 and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "
			+ treasury);

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	empLst = lQuery.list();
	if ((empLst.size() > 0) || (empLst != null)) {
		amountTotal = empLst.get(0).toString();
	}

	return amountTotal;

}


// for SD section totl employeer amount
public String getEmploylerContriTotalListDH(String locId, String treasury,String ddoRegNo) {
	// TODO Auto-generated method stub
	List empLst = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT sum(legacy.EMPLOYER_CONTRI) FROM mst_dcps_emp  emp  ");
	Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
	Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
	Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
//	Strbld.append(" inner join mst_ddo_reg reg on reg.ddo_code = zp.REPT_DDO_CODE   ");
	Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	} else {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	}
	Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4) ");
	Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10 and "
			+ " legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "	+ treasury +" and reg.DDO_REG_NO='"+ddoRegNo+"'");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	empLst = lQuery.list();
	if ((empLst.size() > 0) || (empLst != null)) {
		amountTotal = empLst.get(0).toString();
	}

	return amountTotal;

}
//for SD section totl employeer amount interrest
public String getEmploylerContriTotalListInterestDH(String locId, String treasuryyno, String ddoRegNo) {
	// TODO Auto-generated method stub
	List empLst = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT sum(legacy.EMPLOYER_INT) FROM mst_dcps_emp  emp  ");
	Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
	Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
	Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
	Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	} else {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	}
	Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
	Strbld.append(" where  substr(ddo2.ddo_code,1,4)=" + locId + " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and "
			+ " legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "+ treasuryyno +" and reg.DDO_REG_NO='"+ddoRegNo+"'");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	empLst = lQuery.list();
	if ((empLst.size() > 0) || (empLst != null)) {
		amountTotal = empLst.get(0).toString();
	}

	return amountTotal;
}


public String getEmploylerContriTotalListInterest(String locId, String treasuryyno) {
	// TODO Auto-generated method stub
	List empLst = null;

	String amountTotal = null;
	StringBuilder Strbld = new StringBuilder();

	Strbld.append(" SELECT sum(legacy.EMPLOYER_INT) FROM mst_dcps_emp  emp  ");
	Strbld.append(" inner join DCPS_LEGACY_DATA legacy on legacy.SEVARTH_ID =emp.SEVARTH_ID  ");
	Strbld.append(" inner join RLT_ZP_DDO_MAP zp on zp.ZP_DDO_CODE =emp.DDO_CODE     ");
	Strbld.append(" inner join ORG_DDO_MST ddo2 on ddo2.DDO_CODE = zp.REPT_DDO_CODE     ");
	Strbld.append(" inner join MST_DTO_REG reg on reg.ddo_code = zp.ZP_DDO_CODE   ");
	//Strbld.append(" inner join MST_DTO_REG dto on substr(dto.LOC_ID,1,2)=substr(ddo2.ddo_code,1,2)     ");
	if ((locId != null) && (locId.equalsIgnoreCase("2222"))) {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	} else {
		Strbld.append("  and substr(reg.ddo_code,1,4) <> '2222' ");
	}
	Strbld.append(" inner join CMN_LOCATION_MST loc on loc.LOC_ID=substr(ddo2.DDO_CODE,1,4)     ");
	Strbld.append(" where  substr(ddo2.ddo_code,1,4)="+ locId
			+ " and emp.PRAN_NO is not null and emp.zp_STATUS=10  and  legacy.batch_id is  null   and legacy.STATUS = 1 and  emp.dcps_or_gpf='Y' and legacy.period = "+ treasuryyno);

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	logger.info("script for all employee ---------" + lQuery.toString());

	empLst = lQuery.list();
	if ((empLst.size() > 0) || (empLst != null)) {
		amountTotal = empLst.get(0).toString();
	}

	return amountTotal;
}

public List getlegacyDataDetailsHis(String fileno, String ddocode, String batch_id) {
	// TODO Auto-generated method stub
	Session hibSession = getSession();
    StringBuffer sb = new StringBuffer();
	sb.append("Select * from DCPS_LEGACY_DATA where BATCH_ID = '"+batch_id+"' and ddo_code = '"+ddocode+"' ");
	Query lQuery = ghibSession.createSQLQuery(sb.toString());
	 logger.error("query is---"+lQuery.toString());
	
	 return lQuery.list();
	
}
 
public void insertlegacyDataHistorytdetails(BigInteger nPS_ID, String dDO_CODE, String sEVARTH_ID, String dCPS_ID,
		BigInteger dCPS_EMP_ID, Double eMP_CONTRI, Double eMPLOYER_CONTRI, Double eMP_INT,
		Double eMPLOYER_INT, Double tOTAL, Short yEAR, Short mONTH, String sTATUS, Date cREATED_DATE,
		BigInteger cREATED_POST_ID, Date uPDATED_DATE, BigInteger uPDATED_POST_ID, Date aPPROVAL_DATE, String rEMARKS,
		String pERIOD, Date cONTRI_START_DATE, Date cONTRI_END_DATE, String bATCH_ID, Date rEJECTION_DATE) {
	// TODO Auto-generated method stub
	
	   Session hibSession = getSession();
	    StringBuffer strBfr = new StringBuffer();
	    
	    strBfr.append("INSERT INTO DCPS_LEGACY_DATA_HIST (NPS_ID,DDO_CODE,SEVARTH_ID,DCPS_ID,DCPS_EMP_ID,EMP_CONTRI,EMPLOYER_CONTRI,EMP_INT,EMPLOYER_INT,TOTAL,YEAR,MONTH,STATUS,CREATED_DATE,CREATED_POST_ID,UPDATED_DATE,UPDATED_POST_ID,APPROVAL_DATE,REMARKS,PERIOD,CONTRI_START_DATE,CONTRI_END_DATE,BATCH_ID,REJECTION_DATE) VALUES");
	    strBfr.append("(:NPS_ID,:DDO_CODE,:SEVARTH_ID,:DCPS_ID,:DCPS_EMP_ID,:EMP_CONTRI,:EMPLOYER_CONTRI,:EMP_INT,:EMPLOYER_INT,:TOTAL,:YEAR, ");
	    strBfr.append(":MONTH,:STATUS,:CREATED_DATE,:CREATED_POST_ID,:UPDATED_DATE,:UPDATED_POST_ID,:APPROVAL_DATE,:REMARKS,:PERIOD,:CONTRI_START_DATE,:CONTRI_END_DATE,:BATCH_ID,:REJECTION_DATE)");
	    Query lQuery = this.ghibSession.createSQLQuery(strBfr.toString());
	    lQuery.setParameter("NPS_ID", nPS_ID);
	    lQuery.setParameter("DDO_CODE", dDO_CODE);
	    lQuery.setParameter("SEVARTH_ID",sEVARTH_ID);
	    lQuery.setParameter("DCPS_ID", dCPS_ID);
	    lQuery.setParameter("DCPS_EMP_ID", dCPS_EMP_ID);
	      lQuery.setParameter("EMP_CONTRI",eMP_CONTRI);
	      lQuery.setParameter("EMPLOYER_CONTRI",eMPLOYER_CONTRI);
	      lQuery.setParameter("EMP_INT", eMP_INT);
	      lQuery.setParameter("EMPLOYER_INT",eMPLOYER_INT);
	    lQuery.setParameter("TOTAL", tOTAL);
	    lQuery.setParameter("YEAR", yEAR);
	    lQuery.setParameter("MONTH", mONTH);    
	    lQuery.setParameter("STATUS",sTATUS);
	    lQuery.setParameter("CREATED_DATE", cREATED_DATE);
	    lQuery.setParameter("CREATED_POST_ID", cREATED_POST_ID);
	    lQuery.setParameter("UPDATED_DATE", uPDATED_DATE);
	    lQuery.setParameter("UPDATED_POST_ID", uPDATED_POST_ID);
	    lQuery.setParameter("APPROVAL_DATE", aPPROVAL_DATE);
	    lQuery.setParameter("REMARKS", rEMARKS);
	    lQuery.setParameter("PERIOD", pERIOD);
	    lQuery.setParameter("CONTRI_START_DATE", cONTRI_START_DATE);
	    lQuery.setParameter("CONTRI_END_DATE", cONTRI_END_DATE);
	    lQuery.setParameter("BATCH_ID", bATCH_ID);
	    lQuery.setParameter("REJECTION_DATE", rEJECTION_DATE);
	    lQuery.executeUpdate();   
	    this.gLogger.info("insertlegacyDataHistorytdetails insertion query. 1.." + strBfr);
	}

public void insertlegacyDataHistorytdetails(BigInteger nPS_ID, String dDO_CODE, String sEVARTH_ID, String dCPS_ID,
		BigInteger dCPS_EMP_ID, Double eMP_CONTRI, Double eMPLOYER_CONTRI, Double eMP_INT, Double eMPLOYER_INT,
		Double tOTAL, Short yEAR, Short mONTH, Integer sTATUS, Date cREATED_DATE, BigInteger cREATED_POST_ID,
		Date uPDATED_DATE, BigInteger uPDATED_POST_ID, Date aPPROVAL_DATE, String rEMARKS, String pERIOD,
		Date cONTRI_START_DATE, Date cONTRI_END_DATE, String bATCH_ID, Date rEJECTION_DATE) {
	// TODO Auto-generated method stub
	
}


public int checkNPSLegacyFileExistsOrNot(String fileNo, String BhID) {
	// TODO Auto-generated method stub

	List temp=null;
	int regCount=0;
	StringBuilder  Strbld = new StringBuilder();

	Strbld.append("SELECT count(*)  FROM NSDL_BH_DTLS where FILE_NAME = '"+fileNo+"' and BH_BATCH_FIX_ID = '"+BhID+"' and  STATUS = '5' and FILE_STATUS= '11'");

	SQLQuery lQuery = ghibSession.createSQLQuery(Strbld.toString());
	temp=lQuery.list();
	logger.info("temp size"+temp.size());
	if(temp!=null && temp.size()>0){
		regCount=(int) temp.get(0);
		logger.info("regCount+++++++++ "+regCount);

	}

	return regCount;
}

public void updateVoucherOrBDSDtls(String fileNumber, String bhid, String month, String year,
		String voucherNo, String bankRefNo, String bdsNo, Date voucherDate, String batch_id) {
	
	try {
        StringBuilder lSBQuery = new StringBuilder();
        lSBQuery.append("update NSDL_BH_DTLS set BDS_NO =:bdsNo,BANK_REFNO =:bankRefNo,VOUCHER_NO=:voucherNo,VOUCHER_DATE=:voucherDate,FILE_STATUS='12' where FILE_NAME = :fileNumber");
        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        lQuery.setParameter("voucherNo", voucherNo);
        lQuery.setDate("voucherDate", voucherDate);
        lQuery.setParameter("bdsNo", bdsNo);
        lQuery.setParameter("fileNumber", fileNumber);
        lQuery.setParameter("bankRefNo", bankRefNo);
        lQuery.executeUpdate();
    } catch (Exception e) {
        logger.error(" Error is updateVoucherOrBDSDtls: " + e, e);
    }
	
	try {
        StringBuilder lSBQuery = new StringBuilder();
        lSBQuery.append("update DCPS_LEGACY_DATA set BDS_NO =:bdsNo,BANK_REFNO =:bankRefNo,VOUCHER_NO=:voucherNo,VOUCHER_DATE=:voucherDate where BATCH_ID = :batch_id");
        Query lQuery = ghibSession.createSQLQuery(lSBQuery.toString());
        lQuery.setParameter("voucherNo", voucherNo);
        lQuery.setDate("voucherDate", voucherDate);
        lQuery.setParameter("bdsNo", bdsNo);
        lQuery.setParameter("batch_id", batch_id);
        lQuery.setParameter("bankRefNo", bankRefNo);
        lQuery.executeUpdate();
    } catch (Exception e) {
        logger.error(" Error is updateVoucherOrBDSDtls: " + e, e);
    }
	
}

public int getCountOfNpsBillNotLocked(String ddoCode, Long currentmonth, Long currentyear) {
    int count = 0;
    long previousyear = currentyear - 1L;

    try {
       Session hibSession = this.getSession();
       StringBuilder strQuery = new StringBuilder();
       strQuery.append(" SELECT  count(*) FROM ifms.NSDL_BH_DTLS as bh inner join ifms.DCPS_LEGACY_DATA as LDATA on bh.FILE_NAME='R'||lDATA.BATCH_ID and LDATA.STATUS=3 ");
       strQuery.append(" inner join ifms.RLT_ZP_DDO_MAP as map on map.zp_ddo_code=LDATA.DDO_CODE  where bh.file_status in(1,11,5)  and map.REPT_DDO_CODE='"+ddoCode+"' ");
       strQuery.append(" and ((bh.Year = '"+currentyear+"' and bh.Month < '"+currentmonth+"') or (bh.Year <= '"+currentyear+"' )) and bh.STATUS <> -1 and bh.IS_LEGACY_DATA='Y' ");
     /*  strQuery.append(" SELECT count(*) FROM NSDL_BH_DTLS where file_status in(1,11,5) and ((Year = '" + currentyear + "' and Month < '" + currentmonth + "') or (Year <= '" + previousyear + "' )) and  ");
       strQuery.append(" file_name like 'R" + trCode + "%' and STATUS <> -1 ");*/
       this.logger.error("Query getCountOfNpsBillNotLocked: " + strQuery.toString());
       Query query = hibSession.createSQLQuery(strQuery.toString());
       count = Integer.parseInt(query.uniqueResult().toString());
    } catch (Exception var10) {
       var10.printStackTrace();
    }

    return count;
 }
 
}

