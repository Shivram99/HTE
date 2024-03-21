package com.tcs.sgv.eis.zp.zpDdoOffice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.service.ObjectionServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAO;
import com.tcs.sgv.common.utils.fileupload.dao.CmnAttachmentMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnAttachmentMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.AddNewDDOConfigDAOImpl;
import com.tcs.sgv.dcps.dao.ChangesFormDAO;
import com.tcs.sgv.dcps.dao.ChangesFormDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.service.DcpsCommonDAO;
import com.tcs.sgv.dcps.service.DcpsCommonDAOImpl;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.DdoOffice;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.MstEmpNmn;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.dcps.valueobject.RltDdoAsst;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ReportingDDODaoImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAO;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.dao.ZpAdminOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpAdminOffice.valueobject.ZpAdminOfficeMst;
import com.tcs.sgv.eis.zp.zpDdoOffice.dao.ZpDDOOfficeMstDAOImpl;
import com.tcs.sgv.eis.zp.zpDdoOffice.valueobject.ZpRltDdoMap;
import com.tcs.sgv.eis.zp.zpDepartmentMst.dao.ZpDepartmentDAOImpl;
import com.tcs.sgv.eis.zp.zpDepartmentMst.valueobject.ZpDepartmentMst;

public class ZpDDOOfficeServiceImpl extends ServiceImpl implements
		ZpDDOOfficeService {
	private static Logger logger = Logger
			.getLogger(ZpDDOOfficeServiceImpl.class);
	private ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources/eis/zp/zpAdminOffice/ZpAdminOfficeLabels_en_US");
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");
	private final Date todayDate = new Date();
	/* Global Variable for PostId */
	private Long POST_ID = null;
	/* Global Variable for UserId */
	private Long USER_ID = null;
	/* Global Variable for LangId */
	private Long LANG_ID = null;
	/* Global Variable for LocationId */
	private String LOC_ID = "";

	private String gStrPostId = null; /* STRING POST ID */

	private String gStrUserId = null; /* STRING USER ID */

	private String gStrLocale = null; /* STRING LOCALE */

	private Locale gLclLocale = null; /* LOCALE */

	private Long gLngLangId = null; /* LANG ID */

	private Long gLngDBId = null; /* DB ID */

	private Date gDtCurDate = null; /* CURRENT DATE */

	private HttpServletRequest request = null; /* REQUEST OBJECT */

	private ServiceLocator serv = null; /* SERVICE LOCATOR */

	private HttpSession session = null; /* SESSION */

	/* Global Variable for PostId */
	Long gLngPostId = null;

	/* Global Variable for UserId */
	Long gLngUserId = null;

	/* Global Variable for Current Date */
	Date gDtCurrDt = null;

	/* Global Variable for Location Code */
	String gStrLocationCode = null;

	/* Global Variable for User Loc Map */
	static HashMap sMapUserLoc = new HashMap();

	/* Global Variable for User Location */
	String gStrUserLocation = null;

	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/eis/zp/zpDDOOffice/DCPSConstantsZP");

	/**
	 * <This Method is used to Save/Insert the details>
	 * 
	 * @method name : <saveZpAdminOfficeDtls >
	 * @params : <objectArgs>
	 * @returns : <objRes>
	 */

	private void setSessionInfo(Map inputMap) {

		try {
			request = (HttpServletRequest) inputMap.get("requestObj");
			session = request.getSession();
			serv = (ServiceLocator) inputMap.get("serviceLocator");
			gLclLocale = new Locale(SessionHelper.getLocale(request));
			gStrLocale = SessionHelper.getLocale(request);
			gLngLangId = SessionHelper.getLangId(inputMap);
			gLngPostId = SessionHelper.getPostId(inputMap);
			gStrPostId = gLngPostId.toString();
			gLngUserId = SessionHelper.getUserId(inputMap);
			gStrUserId = gLngUserId.toString();
			gStrLocationCode = SessionHelper.getLocationCode(inputMap);
			gLngDBId = SessionHelper.getDbId(inputMap);
			gDtCurDate = SessionHelper.getCurDate();
			gDtCurrDt = SessionHelper.getCurDate();
		} catch (Exception e) {

		}

	}

	/*public ResultObject saveZpDDODtls(Map objectArgs) throws Exception {
		logger.info("Entering into saveZpDDODtls of ZpDDOOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");

		// AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new
		// AddNewDDOConfigDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
		Long gLngPostId = SessionHelper.getPostId(objectArgs);
		Long gLngUserId = SessionHelper.getUserId(objectArgs);
		String strAdminOfc = StringUtility.getParameter("cmbAdminOffice",
				request).trim();

		String lLngFieldDept = null;// StringUtility.getParameter("cmbAdminOffice",
									// request).trim(); // TODO -- It will
									// Change in future

		String strDistCode = StringUtility.getParameter("cmbDistOffice",
				request).trim();
		// String strDeptCode=StringUtility.getParameter("cmbDept",
		// request).trim();
		String strRepoDDOCode = StringUtility.getParameter("txtRepDDOCode",
				request).trim();
		String lStrDdoName = StringUtility.getParameter("txtDDOName", request)
				.trim().trim();
		String lstrFinalDDOCode = StringUtility.getParameter("txtFinalDDOCode",
				request).trim();
		String lstrSpecialDDOCode = StringUtility.getParameter(
				"txtSpecialDDOCode", request).trim();
		String lstrLevel = StringUtility.getParameter("radioFinalLevel",
				request).trim();
		logger.info("...........lLongLevel..............." + lstrLevel);
		String lStrDdoPersonalName = StringUtility.getParameter("txtDDOName",
				request).trim();// TODO -- It will Change in future
		Long lLngDesignID = 1l; // Long.parseLong(StringUtility.getParameter("1",
								// request).trim());// TODO -- It will Change in
								// future
		// Long lLngAdminDept =
		// Long.parseLong(StringUtility.getParameter("cmbDept",
		// request).trim());// TODO -- It will Change in future
		String lLngDistrictCode = StringUtility.getParameter("cmbDistOffice",
				request).trim();

		String lStrGender = StringUtility.getParameter("radioGender", request)
				.trim().trim();
		String strTOName = StringUtility.getParameter("txtTreasuryName",
				request).trim();
		String lLngTreasuryCode = StringUtility.getParameter("txtTreasuryCode",
				request).trim();
		String strSubTOName = StringUtility.getParameter("txtSubTreasuryName",
				request).trim();
		String lStrDesgnName = StringUtility
				.getParameter("txtDDODsgn", request).trim();
		String lStrDdoOfficeName = StringUtility.getParameter("txtOfficeName",
				request).trim();
		lStrDdoName = lStrDdoOfficeName;
		String lStrDdoCode = StringUtility.getParameter("txtDDOCode", request)
				.trim();
		logger
				.info(".............................lStrDdoCode............................."
						+ lStrDdoCode);
		Long lLngLocPin = 1l;// Long.parseLong(StringUtility.getParameter("1",
								// request).trim());// TODO -- Need Change
								// Temporary "1" is added.

		AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new AddNewDDOConfigDAOImpl(
				CmnLocationMst.class, serviceLocator.getSessionFactory());
		ZpDDOOfficeMstDAOImpl objZpDDOOfficeMstDAOImpl = new ZpDDOOfficeMstDAOImpl(
				ZpRltDdoMap.class, serviceLocator.getSessionFactory());

		logger
				.info("..............................Entries Start in Below tables............................");
		logger
				.info(".........01................Inserting insertLocation............................");

		List ParentDtls1 = objZpDDOOfficeMstDAOImpl
				.RetirveParentdtlfrmReptCode(strRepoDDOCode);
		logger.info("after parent...");

		logger.info("DDO 2 : **********" + ParentDtls1);

		Object[] p1 = (Object[]) ParentDtls1.get(0);
		logger.info("p1111111 : **********" + p1);

		String lstrHOD_Code1 = p1[1].toString();
		logger.info("lstrHOD_Code1 : **********" + lstrHOD_Code1);

		lLngFieldDept = lstrHOD_Code1;

		String lStrLocCode = lObjAddNewDdoConfig.insertLocation(
				lStrDdoOfficeName, gLngUserId, gLngPostId, Long
						.valueOf(lLngFieldDept), String.valueOf(lLngLocPin),
				objectArgs, lLngDistrictCode);
		logger
				.info(".........01................Inserted insertLocation............................");

		logger
				.info(".........02................Inserting insertUserMst............................");
		Long lLngUserId = lObjAddNewDdoConfig.insertUserMst(lStrDdoCode,
				gLngUserId, gLngPostId, objectArgs);
		logger
				.info(".........02................Inserted insertUserMst............................");

		Long lLngPostId = new Long(lLngUserId);
		logger
				.info(".........03................Inserting insertEmpMst............................");
		lObjAddNewDdoConfig.insertEmpMst(lLngUserId, lStrDdoPersonalName,
				gLngUserId, gLngPostId, lStrGender, objectArgs);
		logger
				.info(".........03................Inserting insertEmpMst............................");

		logger
				.info(".........04................Inserting insertOrgPostMst............................");
		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostId, lStrLocCode,
				gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);
		logger
				.info(".........04................Inserting insertOrgPostMst............................");

		logger
				.info(".........05................Inserting insertPostDtlsRlt............................");
		lObjAddNewDdoConfig
				.insertPostDtlsRlt(lStrLocCode, lLngPostId, lStrDesgnName,
						lLngDesignID, gLngUserId, gLngPostId, objectArgs);
		logger
				.info(".........05................Inserting insertPostDtlsRlt............................");

		logger
				.info(".........06................Inserting insertPostRoleRlt............................");
		// lObjAddNewDdoConfig.insertPostRoleRlt(lLngPostId, gLngUserId,
		// gLngPostId, objectArgs,"DDO");
		objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(lLngPostId, gLngUserId,
				gLngPostId, objectArgs, "DDO");
		logger
				.info(".........06................Inserting insertPostRoleRlt............................");

		logger
				.info(".........07................ Inserting insertUserPostRlt............................");
		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostId, lLngUserId,
				gLngUserId, gLngPostId, objectArgs);
		logger
				.info(".........07................Inserting insertUserPostRlt............................");

		logger
				.info(".........08................ Inserting insertOrgDdoMst............................");

		String lstrDdoType = "";
		List ofcCode = objZpDDOOfficeMstDAOImpl.retirveDdoType(strAdminOfc);
		lstrDdoType = ofcCode.get(0).toString();// Note : Column Added in
												// ORG_DDO_MST-DDOType

		List ParentDtls = objZpDDOOfficeMstDAOImpl
				.RetirveParentdtlfrmReptCode(strRepoDDOCode);
		Object[] p = (Object[]) ParentDtls.get(0);
		String lstrDept_Code = p[0].toString();
		String lstrHOD_Code = p[1].toString();

		
		 * List
		 * DeptCode=objZpDDOOfficeMstDAOImpl.RetirveAdminDeptType(strDeptCode);
		 * String lstrDeptType=null; if(DeptCode!=null && DeptCode.size()>0)
		 * lstrDeptType=DeptCode.get(0).toString();
		 

		String lstrAdminDeptType = null;
		objZpDDOOfficeMstDAOImpl.insertOrgDdoMst(lStrDdoCode, lStrDdoName,
				lStrDdoPersonalName, lLngPostId, gLngUserId, lStrLocCode,
				gLngPostId, null, lstrDdoType, lstrDept_Code, lstrHOD_Code,
				null, objectArgs);
		logger
				.info(".........08................Inserting insertOrgDdoMst............................");

		logger
				.info(".........09................Inserting insertMstDcpsDdoOffice............................");
		lObjAddNewDdoConfig.insertMstDcpsDdoOffice(lStrDdoCode,
				lStrDdoOfficeName, lLngDistrictCode.toString(), Long
						.parseLong(lStrLocCode), gLngUserId, gLngPostId,
				objectArgs);
		logger
				.info(".........09................Inserting insertMstDcpsDdoOffice............................");

		logger
				.info(".........10................Inserting insertRltDdoOrg............................");
		lObjAddNewDdoConfig.insertRltDdoOrg(gLngUserId, gLngPostId,
				lStrDdoCode, lLngTreasuryCode.toString(), objectArgs);
		lObjAddNewDdoConfig.insertWfOrgPost(lLngPostId.toString());
		lObjAddNewDdoConfig.insertWfOrgLoc(lStrLocCode);
		lObjAddNewDdoConfig.insertWfOrgUser(lLngUserId);
		logger
				.info(".........10................Inserting insertRltDdoOrg............................");
		logger
				.info(".........................Entries Done in above tables............................");

		// Entries for DDO Assistant as user
		logger
				.info(".........................Entries for Asst Start............................");
		Long lLngUserIdAsst = lLngUserId + 1;
		String lStrDDOAsstUserName = lStrDdoCode.trim() + "_AST";
		lObjAddNewDdoConfig.insertUserMstAsst(lLngUserIdAsst,
				lStrDDOAsstUserName, gLngUserId, gLngPostId, objectArgs);

		Long lLngPostIdAsst = lLngPostId + 1;
		String lStrDdoPersonalNameAsst = lStrDdoPersonalName + "_AST";

		String lStrDesgnNameAsst = lStrDesgnName + "_AST";

		lObjAddNewDdoConfig.insertEmpMst(lLngUserIdAsst,
				lStrDdoPersonalNameAsst, gLngUserId, gLngPostId, lStrGender,
				objectArgs);

		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostIdAsst, lStrLocCode,
				gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);

		lObjAddNewDdoConfig.insertPostDtlsRlt(lStrLocCode, lLngPostIdAsst,
				lStrDesgnNameAsst, lLngDesignID, gLngUserId, gLngPostId,
				objectArgs);

		objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(lLngPostIdAsst, gLngUserId,
				gLngPostId, objectArgs, "ASST");

		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostIdAsst, lLngUserIdAsst,
				gLngUserId, gLngPostId, objectArgs);

		lObjAddNewDdoConfig.insertWfOrgPost(lLngPostIdAsst.toString());

		lObjAddNewDdoConfig.insertWfOrgUser(lLngUserIdAsst);

		RltDdoAsst lObjRltDdoAsst = new RltDdoAsst();
		lObjRltDdoAsst.setAsstPostId(lLngPostIdAsst);
		lObjRltDdoAsst.setDdoPostId(lLngPostId);

		Long lLongRltDDOAsstId = IFMSCommonServiceImpl.getNextSeqNum(
				"rlt_dcps_ddo_asst", objectArgs);
		lObjRltDdoAsst.setRltDdoAsstId(lLongRltDDOAsstId);
		lObjAddNewDdoConfig.create(lObjRltDdoAsst);
		logger
				.info(".........................Entries for Asst End............................");
		// Entries for DDO Assistant as a user overs

		Long ZP_DDO_POST_ID = lLngPostId;
		List RepoDDO = objZpDDOOfficeMstDAOImpl
				.retirveRepoDDOPostId((strRepoDDOCode));
		List FinalDDO = null;
		Long FINAL_DDO_POST_ID = null;
		List SpecDDO = null;
		Long SPECIAL_DDO_POST_ID = null;
		Long REPT_DDO_POST_ID = Long.valueOf(RepoDDO.get(0).toString());
		if (lstrFinalDDOCode != null && !lstrFinalDDOCode.equals("")) {
			FinalDDO = objZpDDOOfficeMstDAOImpl.retirveFinalDDOPostId(Long
					.valueOf(lstrFinalDDOCode));
			FINAL_DDO_POST_ID = Long.valueOf(FinalDDO.get(0).toString());
		}

		if (lstrSpecialDDOCode != null && !lstrSpecialDDOCode.equals("")) {
			SpecDDO = objZpDDOOfficeMstDAOImpl.retirveFinalDDOPostId(Long
					.valueOf(lstrSpecialDDOCode));
			SPECIAL_DDO_POST_ID = Long.valueOf(SpecDDO.get(0).toString());
		}

		if (lstrLevel.equalsIgnoreCase("radioFinalLevel2")) {
			lstrLevel = "2";
			Long lstrReporole = 700019L;
			List checkRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					REPT_DDO_POST_ID, lstrReporole);
			if (checkRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "ReportingDDO");
			}
		}
		if (lstrLevel.equalsIgnoreCase("radioFinalLevel3")) {
			lstrLevel = "3";
			Long lstrReporole = 700019L;
			List checkRepoRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					REPT_DDO_POST_ID, lstrReporole);
			if (checkRepoRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "ReportingDDO");
			}
			Long lstrFinalrole = 700020L;
			List checkFinalRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					FINAL_DDO_POST_ID, lstrFinalrole);
			if (checkFinalRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(FINAL_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "FinalDDO");
			}
		}
		if (lstrLevel.equalsIgnoreCase("radioFinalLevel4")) {
			lstrLevel = "4";
			Long lstrReporole = 700019L;
			List checkRepoRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					REPT_DDO_POST_ID, lstrReporole);
			if (checkRepoRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "ReportingDDO");
			}
			Long lstrFinalrole = 700020L;
			List checkFinalRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					FINAL_DDO_POST_ID, lstrFinalrole);
			if (checkFinalRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(FINAL_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "FinalDDO");
			}
			Long lstrSpecialrole = 700021L;
			List checkSpecialRole = objZpDDOOfficeMstDAOImpl.checkRepopostrole(
					SPECIAL_DDO_POST_ID, lstrSpecialrole);
			if (checkSpecialRole.size() == 0) {
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(SPECIAL_DDO_POST_ID,
						gLngUserId, gLngPostId, objectArgs, "SpecialDDO");
			}
		}
		logger.info(">>>>>>>>>>>>>>>>REPT_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"
				+ REPT_DDO_POST_ID);
		logger.info(">>>>>>>>>>>>>>>>FINAL_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"
				+ FINAL_DDO_POST_ID);
		logger.info(">>>>>>>>>>>>>>>>SPECIAL_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"
				+ SPECIAL_DDO_POST_ID);
		objZpDDOOfficeMstDAOImpl.insertRltZpDdoMap(ZP_DDO_POST_ID,
				REPT_DDO_POST_ID, FINAL_DDO_POST_ID, SPECIAL_DDO_POST_ID,
				lStrDdoCode, strRepoDDOCode, lstrFinalDDOCode,
				lstrSpecialDDOCode, lstrLevel, gLngUserId, gLngPostId,
				objectArgs);
		logger
				.info("######################................................Inserted into insertRltZpDdoMap");

		// Workflow Tables TODO -- Need to Check By Vihan
		logger
				.info("...................inserting Worflow Tables...............");
		objZpDDOOfficeMstDAOImpl.insertWorkFlow(lLngPostIdAsst, lLngPostId,
				REPT_DDO_POST_ID, FINAL_DDO_POST_ID, SPECIAL_DDO_POST_ID,
				gLngUserId, lStrLocCode, objectArgs, lstrLevel);
		// insertWorkFlow(Long lLngPostIdAsst,Long lLongDDOPostId,Long
		// lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecialPostId,Long
		// lLongCreatedByUserId,String lStrLocCode,Map inputMap,String
		// lstrLevel) throws Exception {
		// lObjAddNewDdoConfig.insertWfHierarchyTableSeqMst(lStrLocCode,
		// objectArgs);
		// lObjAddNewDdoConfig.insertWfHierachyPostMpgChanges(REPT_DDO_POST_ID,
		// lLngPostIdAsst, REPT_DDO_POST_ID, lStrLocCode, objectArgs);

		logger
				.info("....................Inserting in CMN TABLE MST...................");
		ReportingDDODaoImpl rpt = new ReportingDDODaoImpl(null, serviceLocator
				.getSessionFactory());
		Long seqId = rpt.getNextSeqNum();
		rpt.getSeqTable(lStrLocCode, objectArgs, seqId);
		rpt.updateUserMstSeq();
		logger
				.info("....................Inserting in CMN TABLE MST...................");

		logger
				.info("...................inserting Worflow Tables...............");
		// Workflow Tables

		logger
				.info("....................Inserting in CMN TABLE MST...................");
		ReportingDDODaoImpl rpt2 = new ReportingDDODaoImpl(null, serviceLocator
				.getSessionFactory());
		rpt2.getSeqTable(lStrLocCode);
		rpt2.updateUserMstSeq();
		logger
				.info("....................Inserting in CMN TABLE MST...................");

		
		 * IFMSCommonServiceImpl lObjIFMSCommonServiceImpl=new
		 * IFMSCommonServiceImpl();
		 * lObjIFMSCommonServiceImpl.clearCacheForMapDDOAsst
		 * (serv.getSessionFactory());
		 * lObjIFMSCommonServiceImpl.clearCacheForMapDDOAsst
		 * (serv.getSessionFactorySlave());
		 

		// objectArgs.put("redirectMap", redirectMap);
		ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
				ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
				.getAllDDOOfficeDtlsData();
		logger.info("zpdistrictOfficelst::" + zpDDOOfficelst.size());
		objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);

		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("zpDDOOfficeView");
		return objRes;
	}*/
	
	public ResultObject saveZpDDODtls(Map objectArgs) throws Exception
	{
		logger.info("Entering into saveZpDDODtls of ZpDDOOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		//AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new AddNewDDOConfigDAOImpl(CmnLocationMst.class,serv.getSessionFactory());
		Long gLngPostId = SessionHelper.getPostId(objectArgs);
		Long gLngUserId = SessionHelper.getUserId(objectArgs);
		String strAdminOfc=StringUtility.getParameter("cmbAdminOffice", request).trim();

		String lLngFieldDept=null;//StringUtility.getParameter("cmbAdminOffice", request).trim(); // TODO -- It will Change in future

		String strDistCode=StringUtility.getParameter("cmbDistOffice", request).trim();
		logger.info("...........cmbDistOffice..............."+strDistCode);
		String strDeptCode=StringUtility.getParameter("cmbDept", request).trim();
		String strRepoDDOCode=StringUtility.getParameter("txtRepDDOCode", request).trim();
		String lStrDdoName=StringUtility.getParameter("txtDDOName", request).trim().trim();
		String lstrFinalDDOCode=StringUtility.getParameter("txtFinalDDOCode", request).trim();
		String lstrSpecialDDOCode=StringUtility.getParameter("txtSpecialDDOCode", request).trim();
		String lstrLevel=StringUtility.getParameter("radioFinalLevel", request).trim();
		logger.info("...........lLongLevel..............."+lstrLevel);
		String lStrDdoPersonalName=StringUtility.getParameter("txtDDOName", request).trim();// TODO -- It will Change in future
		logger.info("...........lStrDdoPersonalName..............."+lStrDdoPersonalName);

		Long lLngDesignID =1l; //Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO -- It will Change in future
		Long lLngAdminDept=0l;
		if(StringUtility.getParameter("cmbDept", request).trim()!=null){
		lLngAdminDept = -1l;//Long.parseLong(((StringUtility.getParameter("cmbDept", request).trim()!=null || !StringUtility.getParameter("cmbDept", request).trim().equals(""))?StringUtility.getParameter("cmbDept", request).trim():-1) );// TODO -- It will Change in future
		}
		String lLngDistrictCode=StringUtility.getParameter("cmbDistrict", request).trim();

		String lStrGender=StringUtility.getParameter("radioGender", request).trim().trim();
		String strTOName=StringUtility.getParameter("txtTreasuryName", request).trim();
		String lLngTreasuryCode=StringUtility.getParameter("txtTreasuryCode", request).trim();
		String strSubTOName=StringUtility.getParameter("txtSubTreasuryName", request).trim();
		String lStrDesgnName=StringUtility.getParameter("txtDDODsgn", request).trim();
		String lStrDdoOfficeName=StringUtility.getParameter("txtOfficeName", request).trim();
		lStrDdoName=lStrDdoOfficeName;
		String lStrDdoCode=StringUtility.getParameter("txtDDOCode", request).trim();
		
		logger.info(".............................lStrDdoCode............................."+lStrDdoCode);
		Long lLngLocPin = 1l;//Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO -- Need Change Temporary "1" is added.  

		AddNewDDOConfigDAOImpl lObjAddNewDdoConfig = new AddNewDDOConfigDAOImpl(CmnLocationMst.class,serviceLocator.getSessionFactory());
		ZpDDOOfficeMstDAOImpl objZpDDOOfficeMstDAOImpl=new ZpDDOOfficeMstDAOImpl(ZpRltDdoMap.class,serviceLocator.getSessionFactory());

		logger.info("..............................Entries Start in Below tables............................");
		logger.info(".........01................Inserting insertLocation............................");
		
		logger.info("strRepoDDOCode............................"+strRepoDDOCode);
		
		List ParentDtls1=objZpDDOOfficeMstDAOImpl.RetirveParentdtlfrmReptCode(strRepoDDOCode);
		
		logger.info("ParentDtls1............................"+ParentDtls1);
		
		String tmp[] = ParentDtls1.get(0).toString().split("##");
		String lstrHOD_Code1=tmp[1].toString();
		lLngFieldDept=lstrHOD_Code1;
//String lStrLocCode = lObjAddNewDdoConfig.insertLocation(lStrDdoOfficeName, gLngUserId, gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), objectArgs);
		String lStrLocCode = lObjAddNewDdoConfig.insertLocation(lStrDdoOfficeName, gLngUserId, gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), objectArgs,strDistCode);
		logger.info(".........01................Inserted insertLocation............................");
		logger.info(".........02................Inserting insertUserMst............................");
		Long lLngUserId = lObjAddNewDdoConfig.insertUserMst(lStrDdoCode, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........02................Inserted insertUserMst............................");
		Long lLngPostId = new Long(lLngUserId);
		logger.info("lLngPostId "+lLngPostId);

		logger.info(".........03................Inserting insertEmpMst............................");
		lObjAddNewDdoConfig.insertEmpMst(lLngUserId, lStrDdoPersonalName, gLngUserId, gLngPostId, lStrGender, objectArgs);
		logger.info(".........03................Inserting insertEmpMst............................");

		logger.info(".........04................Inserting insertOrgPostMst............................");
		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostId, lStrLocCode, gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);
		logger.info(".........04................Inserting insertOrgPostMst............................");

		logger.info(".........05................Inserting insertPostDtlsRlt............................");
		lObjAddNewDdoConfig.insertPostDtlsRlt(lStrLocCode, lLngPostId, lStrDesgnName, lLngDesignID, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........05................Inserting insertPostDtlsRlt............................");

		logger.info(".........06................Inserting insertPostRoleRlt............................");
		//lObjAddNewDdoConfig.insertPostRoleRlt(lLngPostId, gLngUserId, gLngPostId, objectArgs,"DDO");
		objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(lLngPostId, gLngUserId, gLngPostId, objectArgs,"DDO");
		logger.info(".........06................Inserting insertPostRoleRlt............................");

		logger.info(".........07................Inserting insertUserPostRlt............................");
		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostId, lLngUserId, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........07................Inserting insertUserPostRlt............................");

		logger.info(".........08................Inserting insertOrgDdoMst............................");
		String lstrDdoType="";
		List ofcCode=objZpDDOOfficeMstDAOImpl.retirveDdoType(strAdminOfc);
		lstrDdoType=ofcCode.get(0).toString();//Note : Column Added in ORG_DDO_MST-DDOType

		List ParentDtls=objZpDDOOfficeMstDAOImpl.RetirveParentdtlfrmReptCode(strRepoDDOCode);
		
		
		/*Object[] p =(Object[])ParentDtls.get(0);
		String lstrDept_Code=p[0].toString();
		String lstrHOD_Code=p[1].toString();*/
		
		String tmp1[] = ParentDtls.get(0).toString().split("##");
		String lstrDept_Code=tmp[0].toString();
		String lstrHOD_Code=tmp[1].toString();
		
		
		List DeptCode=objZpDDOOfficeMstDAOImpl.RetirveAdminDeptType(strDeptCode);
		String lstrDeptType=null;
		if(DeptCode!=null && DeptCode.size()>0)
			lstrDeptType=DeptCode.get(0).toString();

		String lstrAdminDeptType=null;
		objZpDDOOfficeMstDAOImpl.insertOrgDdoMst(lStrDdoCode, lStrDdoName, lStrDdoPersonalName, lLngPostId, gLngUserId, lStrLocCode, gLngPostId, lLngAdminDept.toString(),lstrDdoType,lstrDept_Code,lstrHOD_Code,lstrDeptType,objectArgs);
		logger.info(".........08................Inserting insertOrgDdoMst............................");

		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");
		//Edited by samadhan to save uniqe institute number generated by system
		String uniqeInstituteId=generateUniqeInstituteId(lStrDdoCode,lLngDistrictCode.toString(), objectArgs);
		//lObjAddNewDdoConfig.insertMstDcpsDdoOffice(lStrDdoCode, lStrDdoOfficeName, lLngDistrictCode.toString(), Long.parseLong(lStrLocCode), gLngUserId, gLngPostId, objectArgs);
		lObjAddNewDdoConfig.insertMstDcpsDdoOffice(lStrDdoCode, lStrDdoOfficeName, lLngDistrictCode.toString(), Long.parseLong(lStrLocCode), gLngUserId, gLngPostId, objectArgs,uniqeInstituteId);
		logger.info(".........09................Inserting insertMstDcpsDdoOffice............................");

		logger.info(".........10................Inserting insertRltDdoOrg............................");
		lObjAddNewDdoConfig.insertRltDdoOrg(gLngUserId, gLngPostId, lStrDdoCode, lLngTreasuryCode.toString(), objectArgs);
		lObjAddNewDdoConfig.insertWfOrgPost(lLngPostId.toString()); 
		lObjAddNewDdoConfig.insertWfOrgLoc(lStrLocCode);
		lObjAddNewDdoConfig.insertWfOrgUser(lLngUserId);
		logger.info(".........10................Inserting insertRltDdoOrg............................");
		logger.info(".........................Entries Done in above tables............................");





		// Entries for DDO Assistant as user
		logger.info(".........................Entries for Asst Start............................");
		Long lLngUserIdAsst = lLngUserId + 1;
		String lStrDDOAsstUserName = lStrDdoCode.trim() + "_AST";
		lObjAddNewDdoConfig.insertUserMstAsst(lLngUserIdAsst, lStrDDOAsstUserName, gLngUserId, gLngPostId, objectArgs);

		Long lLngPostIdAsst = lLngPostId + 1;
		String lStrDdoPersonalNameAsst =  lStrDdoPersonalName + "_AST";

		String lStrDesgnNameAsst = lStrDesgnName + "_AST";
		logger.info(".........................Entries for Asst Start1............................");
		lObjAddNewDdoConfig.insertEmpMst(lLngUserIdAsst, lStrDdoPersonalNameAsst, gLngUserId, gLngPostId, lStrGender, objectArgs);
		logger.info(".........................Entries for Asst Start2............................");
		lObjAddNewDdoConfig.insertOrgPostMst(lLngPostIdAsst, lStrLocCode, gLngUserId, gLngPostId, lLngDesignID.toString(), objectArgs);
		logger.info(".........................Entries for Asst Start3............................");
		lObjAddNewDdoConfig.insertPostDtlsRlt(lStrLocCode, lLngPostIdAsst, lStrDesgnNameAsst, lLngDesignID, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........................Entries for Asst Start4............................");
		objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(lLngPostIdAsst, gLngUserId, gLngPostId, objectArgs,"ASST");
		logger.info(".........................Entries for Asst Start5............................");
		lObjAddNewDdoConfig.insertUserPostRlt(lLngPostIdAsst, lLngUserIdAsst, gLngUserId, gLngPostId, objectArgs);
		logger.info(".........................Entries for Asst Start6............................");
		lObjAddNewDdoConfig.insertWfOrgPost(lLngPostIdAsst.toString());
		logger.info(".........................Entries for Asst Start7............................");
		lObjAddNewDdoConfig.insertWfOrgUser(lLngUserIdAsst);
		logger.info(".........................Entries for Asst Start8............................");
		RltDdoAsst lObjRltDdoAsst = new RltDdoAsst();
		lObjRltDdoAsst.setAsstPostId(lLngPostIdAsst);
		lObjRltDdoAsst.setDdoPostId(lLngPostId);
		logger.info(".........................Entries for Asst Start9............................");
		//Long lLongRltDDOAsstId = IFMSCommonServiceImpl.getNextSeqNum("rlt_dcps_ddo_asst",objectArgs);
		Long lLongRltDDOAsstId = lObjAddNewDdoConfig.getNextSeqNoLocForRltDcpsDdoAsst();
		lObjRltDdoAsst.setRltDdoAsstId(lLongRltDDOAsstId);
		logger.info(".........................Entries for Asst Start10............................");
		lObjAddNewDdoConfig.create(lObjRltDdoAsst);
		logger.info(".........................Entries for Asst End............................");
		// Entries for DDO Assistant as a user overs



		Long ZP_DDO_POST_ID=lLngPostId;
		List RepoDDO=objZpDDOOfficeMstDAOImpl.retirveRepoDDOPostId((strRepoDDOCode));
		List FinalDDO=null;
		Long FINAL_DDO_POST_ID=null;
		List SpecDDO=null;
		Long SPECIAL_DDO_POST_ID=null;
		Long REPT_DDO_POST_ID=Long.valueOf(RepoDDO.get(0).toString());
		if(lstrFinalDDOCode!=null && !lstrFinalDDOCode.equals("") )
		{
			FinalDDO=objZpDDOOfficeMstDAOImpl.retirveFinalDDOPostId(Long.valueOf(lstrFinalDDOCode));
			FINAL_DDO_POST_ID=Long.valueOf(FinalDDO.get(0).toString());
		}


		if(lstrSpecialDDOCode!=null && !lstrSpecialDDOCode.equals("") )
		{
			logger.info("lstrSpecialDDOCode-----sunitha"+lstrSpecialDDOCode);
			SpecDDO=objZpDDOOfficeMstDAOImpl.retirveFinalDDOPostId(Long.valueOf(lstrSpecialDDOCode));
			logger.info("SpecDDO-----sunitha"+SpecDDO);
			SPECIAL_DDO_POST_ID=Long.valueOf(SpecDDO.get(0).toString());
		}

		if(lstrLevel.equalsIgnoreCase("radioFinalLevel2")){
			lstrLevel="2";
			Long lstrReporole=700019L; 
			List checkRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(REPT_DDO_POST_ID,lstrReporole);
			if(checkRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"ReportingDDO");
			}
		}
		if(lstrLevel.equalsIgnoreCase("radioFinalLevel3")){
			lstrLevel="3";
			Long lstrReporole=700019L; 
			List checkRepoRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(REPT_DDO_POST_ID,lstrReporole);
			if(checkRepoRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"ReportingDDO");
			}
			Long lstrFinalrole=700020L; 
			List checkFinalRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(FINAL_DDO_POST_ID,lstrFinalrole);
			if(checkFinalRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(FINAL_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"FinalDDO");
			}
		}
		if(lstrLevel.equalsIgnoreCase("radioFinalLevel4")){
			lstrLevel="4";
			Long lstrReporole=700019L; 
			List checkRepoRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(REPT_DDO_POST_ID,lstrReporole);
			if(checkRepoRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(REPT_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"ReportingDDO");
			}
			Long lstrFinalrole=700020L; 
			List checkFinalRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(FINAL_DDO_POST_ID,lstrFinalrole);
			if(checkFinalRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(FINAL_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"FinalDDO");
			}
			Long lstrSpecialrole=700021L; 
			List checkSpecialRole=objZpDDOOfficeMstDAOImpl.checkRepopostrole(SPECIAL_DDO_POST_ID,lstrSpecialrole);
			if(checkSpecialRole.size()==0){
				objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(SPECIAL_DDO_POST_ID, gLngUserId, gLngPostId, objectArgs,"SpecialDDO");
			}
		}
		logger.info(">>>>>>>>>>>>>>>>REPT_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"+REPT_DDO_POST_ID);
		logger.info(">>>>>>>>>>>>>>>>FINAL_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"+FINAL_DDO_POST_ID);
		logger.info(">>>>>>>>>>>>>>>>SPECIAL_DDO_POST_ID>>>>>>>>>>>>>>>>>>>>>"+SPECIAL_DDO_POST_ID);
		objZpDDOOfficeMstDAOImpl.insertRltZpDdoMap(ZP_DDO_POST_ID, REPT_DDO_POST_ID, FINAL_DDO_POST_ID, SPECIAL_DDO_POST_ID, lStrDdoCode, strRepoDDOCode, lstrFinalDDOCode,lstrSpecialDDOCode, lstrLevel,gLngUserId, gLngPostId, objectArgs);
		logger.info("######################................sunitha................Inserted into insertRltZpDdoMap");





		//  Workflow Tables TODO -- Need to Check By Vihan 
		logger.info("...................inserting Worflow Tables...............");
		objZpDDOOfficeMstDAOImpl.insertWorkFlow(lLngPostIdAsst,lLngPostId,REPT_DDO_POST_ID, FINAL_DDO_POST_ID,SPECIAL_DDO_POST_ID, gLngUserId, lStrLocCode, objectArgs,lstrLevel);
		// insertWorkFlow(Long lLngPostIdAsst,Long lLongDDOPostId,Long lLongTOAsstPostId,Long lLongTOPostId,Long lLongSpecialPostId,Long lLongCreatedByUserId,String lStrLocCode,Map inputMap,String lstrLevel) throws Exception {
		//lObjAddNewDdoConfig.insertWfHierarchyTableSeqMst(lStrLocCode, objectArgs);
		//lObjAddNewDdoConfig.insertWfHierachyPostMpgChanges(REPT_DDO_POST_ID, lLngPostIdAsst, REPT_DDO_POST_ID, lStrLocCode, objectArgs);
		logger.info("...................inserting Worflow Tables...............");
		//  Workflow Tables 

		logger.info("....................Inserting in CMN TABLE MST...................");
		ReportingDDODaoImpl rpt=new ReportingDDODaoImpl(null,serviceLocator.getSessionFactory()); 
		Long seqId= rpt.getNextSeqNum();
		rpt.getSeqTable(lStrLocCode,objectArgs,seqId);
		rpt.updateUserMstSeq();
		logger.info("....................Inserting in CMN TABLE MST...................");


		ZpDDOOfficeMstDAOImpl  zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
		//commented by vaibhav tyagi
		//List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO.getAllDDOOfficeDtlsData();
		
		//added by vaibhav tyagi:start
		String districtSelected =null;
		String talukaSelected=null;
		String adminTypeSelected=null;
		List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO.getAllDDOOfficeDtlsData(districtSelected,talukaSelected,adminTypeSelected);
		//added by vaibhav tyagi:end
		logger.info("zpdistrictOfficelst::"+zpDDOOfficelst.size());
		
		List districtList= zpDDOOfficeMstDAO.getAllDistrict();
		List adminTypeList= zpDDOOfficeMstDAO.getAllAdminType();
		List taluka = zpDDOOfficeMstDAO.getAllTalukaByDistrict(strDistCode);
		objectArgs.put("zpDDOOfficelst",zpDDOOfficelst);
		
		objectArgs.put("districtList",districtList);
		objectArgs.put("adminTypeList",adminTypeList);
		objectArgs.put("zpDDOOfficelst",zpDDOOfficelst);
		objectArgs.put("talukaList",taluka);
		
		
		objectArgs.put("lStrDdoCode",lStrDdoCode);
		objectArgs.put("uniqeInstituteId",uniqeInstituteId);
		districtList=null;
		adminTypeList=null;
		zpDDOOfficelst=null;
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("zpDDOOfficeView");
		return objRes;
	}

	

	public ResultObject saveZpAdminOfficeDtls(Map objectArgs) throws Exception {
		logger
				.info("Entering into saveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");

		ZpAdminOfficeMst zpAdminOfficeMstVO = null;

		if (objectArgs.get("zpAdminOfficeMstVO") != null) {
			zpAdminOfficeMstVO = (ZpAdminOfficeMst) objectArgs
					.get("zpAdminOfficeMstVO");
		}
		// setSessionInfo(objectArgs);
		String updateFlag = (String) objectArgs.get("updateFlag");
		Map redirectMap = new HashMap();
		redirectMap.put("actionFlag", "getDisplayMessage");

		if (updateFlag.equalsIgnoreCase("false")) {
			// Insert Starts............
			/* Insert Into Main Table : Starts....... */
			ZpAdminOfficeDAO zpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			String strTableZpAdminOfficeMst = "ZP_ADMIN_OFFICE_MST";

			Long zpAdminOfficeMstPkID = IFMSCommonServiceImpl.getNextSeqNum(
					strTableZpAdminOfficeMst, objectArgs);
			zpAdminOfficeMstVO.setOfcId(zpAdminOfficeMstPkID);
			zpAdminOfficeMstVO.setLangId(1l);
			zpAdminOfficeMstVO.setCreatedDate(todayDate);
			zpAdminOfficeMstVO.setCreatedBy(USER_ID);
			zpAdminOfficeMstDAOImpl.create(zpAdminOfficeMstVO);
			/* Insert Into Main Table : Ends....... */

			redirectMap.put("MESSAGECODE", resourceBundle
					.getObject("SUBMIT_SUCESSFULL_MSG")); // Sucess Message
			// Insert Ends............
		} else if (updateFlag.equalsIgnoreCase("true")) {
			// Udpate Starts............
			/* Update For Main Table : Starts....... */
			ZpAdminOfficeDAO zpAdminOfficeMstDAOImpl = new ZpAdminOfficeDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			zpAdminOfficeMstVO.setUpdatedDate(todayDate);
			zpAdminOfficeMstVO.setUpdatedBy(USER_ID);
			zpAdminOfficeMstDAOImpl.update(zpAdminOfficeMstVO);
			/* Update For Main Table : Ends....... */

			/* Update Child or Add Row Table : Starts....... */
			/* Update Child or Add Row Table : Ends....... */
			redirectMap.put("MESSAGECODE", resourceBundle
					.getObject("UPDATE_SUCESSFULL_MSG")); // Update Success
															// Message
			// Update Ends............
			zpAdminOfficeMstVO = null;
		}
		logger
				.info("Exit from saveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		objectArgs.put("redirectMap", redirectMap);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("redirect view");
		return objRes;
	}

	public ResultObject checkRepoDDOCode(Map objectArgs) throws Exception {
		logger
				.info("Entering into checkRepoDDOCode of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		ZpDDOOfficeMstDAOImpl check = new ZpDDOOfficeMstDAOImpl(null,
				serviceLocator.getSessionFactory());

		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		String DDOCode = StringUtility.getParameter("repoDDOCode", request)
				.trim();
		Long TDDOCode = null;
		if (!DDOCode.equalsIgnoreCase("")) {
			TDDOCode = Long.valueOf(DDOCode);
		}
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + TDDOCode);
		List RepoDDO = check.checkRepoDDO(TDDOCode);
		List TODetail = check.getTreasuryName(TDDOCode);
		Object[] o = (Object[]) TODetail.get(0);
		String TOName = o[1] != null ? o[1].toString() : "";
		String TOCode = o[0] != null ? o[0].toString() : "";
		StringBuilder lStrBldXML = new StringBuilder();
		lStrBldXML.append("<lstSubTO>");
		lStrBldXML.append("<id>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(TOCode);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</id>");
		lStrBldXML.append("<name>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(TOName);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</name>");
		// Sub TO : START
		ReportingDDODaoImpl rptSubTO = new ReportingDDODaoImpl(null,
				serviceLocator.getSessionFactory());
		List lstSubTO = rptSubTO.getSubTreasury(Long.valueOf(TOCode));
		List<ComboValuesVO> lstSubTO1 = new ArrayList<ComboValuesVO>();
		Map result = new HashMap();
		ComboValuesVO cmbVO1 = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lstSubTO1.add(cmbVO1);

		if (lstSubTO != null && lstSubTO.size() > 0) {
			Iterator IT = lstSubTO.iterator();

			cmbVO1 = new ComboValuesVO();
			Object[] lObj = null;

			while (IT.hasNext()) {
				cmbVO1 = new ComboValuesVO();
				lObj = (Object[]) IT.next();
				cmbVO1.setId(lObj[0].toString());
				cmbVO1.setDesc(lObj[1].toString());
				lstSubTO1.add(cmbVO1);

				lStrBldXML.append("<id>");
				lStrBldXML.append("<![CDATA[");
				lStrBldXML.append(lObj[0].toString());
				lStrBldXML.append("]]>");
				lStrBldXML.append("</id>");
				lStrBldXML.append("<name>");
				lStrBldXML.append("<![CDATA[");
				lStrBldXML.append(lObj[1].toString());
				lStrBldXML.append("]]>");
				lStrBldXML.append("</name>");

			}

		}
		lStrBldXML.append("</lstSubTO>");
		lStrBldXML.append("</XMLDOC>");
		// Sub TO : END

		/*
		 * // DDO Code Generation : Start String
		 * AdminOfc=(StringUtility.getParameter("AdminOfc", request)); List
		 * getOfcCode=check.getAdminOfcCode(AdminOfc) ; // Object[] ofcCode
		 * =(Object[])getOfcCode.get(0); String
		 * AOfcCode=getOfcCode.get(0).toString(); String
		 * CreatedDDOCode=AOfcCode; CreatedDDOCode+=TOCode; List
		 * getCountCode=check.getCountofDDOCode(CreatedDDOCode); //Object[]
		 * newCode =(Object[])getCountCode.get(0); String newDDOCode= "";
		 * if(getCountCode.get(0) != null) {
		 * newDDOCode=getCountCode.get(0).toString(); } else newDDOCode=
		 * CreatedDDOCode + "00000"; String temp; String FinalpreFixed="";
		 * if(!newDDOCode.equalsIgnoreCase("") && newDDOCode!=null){
		 * temp=String.valueOf((Long.valueOf(newDDOCode)+1));
		 * CreatedDDOCode=String.valueOf(temp); FinalpreFixed+=CreatedDDOCode; }
		 * else{ CreatedDDOCode = String.format(CreatedDDOCode + "%06d", 1);
		 * FinalpreFixed+=CreatedDDOCode; }
		 * 
		 * if(FinalpreFixed.length()==10) FinalpreFixed="0"+FinalpreFixed; //
		 * DDO Code Generation : End
		 * 
		 * String lSBStatus; String lStrResult;
		 * 
		 * // Check Valid Final & Speclia DDO : START String
		 * FinalDDOCode=StringUtility.getParameter("txtFinalDDOCode",
		 * request).trim(); logger.error(">>>>>>>>>>>>>>>>>>"+FinalDDOCode);
		 * String SpecialDDOCode=StringUtility.getParameter("txtSpecialDDOCode",
		 * request).trim(); logger.error(">>>>>>>>>>>>>>>>>>"+SpecialDDOCode);
		 * String strStatusFinalDDO=null; String strStatusSpecialDDO=null;
		 * 
		 * if(!SpecialDDOCode.equalsIgnoreCase("")) { List
		 * lstSpecialDDO=check.checkRepoDDO(Long.valueOf(SpecialDDOCode));
		 * logger.info(">>>>>>>>>>>>>>>>>>"+lstSpecialDDO.size());
		 * if(lstSpecialDDO.size()>0) { strStatusSpecialDDO="true"; } else {
		 * strStatusSpecialDDO="false"; } }
		 * if(!FinalDDOCode.equalsIgnoreCase("")) { List
		 * lstFinalDDO=check.checkRepoDDO(Long.valueOf(FinalDDOCode));
		 * 
		 * logger.info(">>>>>>>>>>>>>>>>>>"+lstFinalDDO.size());
		 * 
		 * if(lstFinalDDO.size()>0) { strStatusFinalDDO="true"; } else {
		 * strStatusFinalDDO="false"; } }
		 */
		// Check Valid Final & Speclia DDO : END
		// lSBStatus=getResponseXMLDocSaveData(TOName,TOCode,FinalpreFixed,strStatusFinalDDO,strStatusSpecialDDO).toString();
		String lSBStatus;
		String lStrResult;
		lSBStatus = getResponseXMLDocSaveData(TOName, TOCode, lstSubTO)
				.toString();
		lSBStatus = lSBStatus + lStrBldXML;
		lStrResult = new AjaxXmlBuilder().addItem("ajax_key", lSBStatus)
				.toString();

		// logger.info("********************************************"+lStrResult);
		// String AjaxResult = new AjaxXmlBuilder().addItems(lstSubTO1, "desc",
		// "id").toString();
		objectArgs.put("ajaxKey", lStrResult);
		// objectArgs.put("ajaxKey1", AjaxResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");

		// objectArgs.put("TOName", TOName);
		// objectArgs.put("TOCode", TOCode);

		/*
		 * Map result = new HashMap(); String AjaxResult = new
		 * AjaxXmlBuilder().addItems(lLstAllDept, "desc", "id").toString();
		 * result.put("ajaxKey", AjaxResult);
		 */

		return objRes;

	}

	private StringBuilder getResponseXMLDocSaveData(String TOName,
			String TOCode, List lstSubTO) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<TOName>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(TOName);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</TOName>");
		lStrBldXML.append("<TOCode>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(TOCode);
		lStrBldXML.append("]]>");
		lStrBldXML.append("</TOCode>");
		/*
		 * lStrBldXML.append("<strStatusFinalDDO>");
		 * lStrBldXML.append("<![CDATA["); lStrBldXML.append(strStatusFinalDDO);
		 * lStrBldXML.append("]]>"); lStrBldXML.append("</strStatusFinalDDO>");
		 * lStrBldXML.append("<strStatusSpecialDDO>");
		 * lStrBldXML.append("<![CDATA[");
		 * lStrBldXML.append(strStatusSpecialDDO); lStrBldXML.append("]]>");
		 * lStrBldXML.append("</strStatusSpecialDDO>");
		 */

		return lStrBldXML;
	}

	public ResultObject retirveDept(Map objectArgs) throws Exception {
		logger.info("Entering into retirveDept of ZpDDOOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");

		ZpDepartmentDAOImpl getDept = new ZpDepartmentDAOImpl(
				ZpDepartmentMst.class, serviceLocator.getSessionFactory());
		String OfcId = (StringUtility.getParameter("ofcId", request));
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + OfcId);
		ZpDDOOfficeMstDAOImpl getACode = new ZpDDOOfficeMstDAOImpl(
				ZpDepartmentMst.class, serviceLocator.getSessionFactory());
		List lstDept = getACode.getAdminOfcCode((OfcId));
		String OfcCode = lstDept.get(0).toString();
		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		List Depts = null;

		/*
		 * if(OfcId==99200003232l) {
		 */
		logger
				.info("~~~~~~~~~~~~~~~~~~~~~~~~in IF COndition ~~~~~~~~~~~~~~~~~~~~");
		Depts = getDept.retriveDepts(OfcCode);
		logger.info("::::::::::: ZpDDOOfficeServiceImpl :::: Depts"
				+ Depts.size());
		if (Depts != null && Depts.size() > 0) {
			Iterator IT = Depts.iterator();

			cmbVO = new ComboValuesVO();
			Object[] lObj = null;
			while (IT.hasNext()) {
				cmbVO = new ComboValuesVO();
				lObj = (Object[]) IT.next();
				cmbVO.setId(lObj[0].toString());
				String desc = "<![CDATA[" + lObj[1].toString() + "]]>";
				cmbVO.setDesc(desc);
				lLstAllDept.add(cmbVO);
			}
		}
		Map result = new HashMap();
		String AjaxResult = new AjaxXmlBuilder().addItems(lLstAllDept, "desc",
				"id").toString();
		result.put("ajaxKey", AjaxResult);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(result);
		objRes.setViewName("ajaxData");
		// }
		// objectArgs.put("Depts", Depts);

		return objRes;
	}

	public ResultObject retriveDiscOfcList(Map objectArgs) throws Exception 
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		ZpDDOOfficeMstDAOImpl hh = new ZpDDOOfficeMstDAOImpl(null,
				serviceLocator.getSessionFactory());

		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");

		Long OfcId = Long.valueOf(StringUtility.getParameter("ofcId", request));
		List t = null;
		String flag = StringUtility.getParameter("flag", request);
		logger.info("flag" + flag);
		
		//added by samadhan
		String treasuryId = StringUtility.getParameter("treasuryId", request);
		logger.info("################treasuryId################: "+treasuryId);
		
		if (flag.equals("lvl2")) {
			t = hh.getDistOfcByCode(OfcId,treasuryId);
			logger.info("flag3" + flag);
		} else
			t = hh.retriveDisctOfcList(OfcId);
		List<ComboValuesVO> lLstAllDept = new ArrayList<ComboValuesVO>();
		ComboValuesVO cmbVO = new ComboValuesVO();
		cmbVO.setId("-1");
		cmbVO.setDesc("Select");
		lLstAllDept.add(cmbVO);

		if (t != null && t.size() > 0) {
			Iterator IT = t.iterator();

			cmbVO = new ComboValuesVO();
			Object[] lObj = null;
			while (IT.hasNext()) {
				cmbVO = new ComboValuesVO();
				lObj = (Object[]) IT.next();
				cmbVO.setId(lObj[1].toString());
				cmbVO.setDesc(lObj[0].toString());
				lLstAllDept.add(cmbVO);
			}
		}
		Map result = new HashMap();
		String AjaxResult = new AjaxXmlBuilder().addItems(lLstAllDept, "desc",
				"id").toString();
		logger.info("-----------------------" + AjaxResult);
		result.put("ajaxKey", AjaxResult);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setResultValue(result);
		objRes.setViewName("ajaxData");

		return objRes;
	}

	public ResultObject newDdoOfficeData(Map objectArgs) throws Exception

	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(-1);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");

		ZpAdminOfficeMst ZpAdminOfficeMstVO = new ZpAdminOfficeMst();
		ZpAdminOfficeMstDAOImpl hh = new ZpAdminOfficeMstDAOImpl(
				ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		ZpDepartmentDAOImpl getDept = new ZpDepartmentDAOImpl(
				ZpDepartmentMst.class, serviceLocator.getSessionFactory());
		
		
		/*Added By Shivram 30012023*/
		 Map loginMap = (Map) objectArgs.get("baseLoginMap");
		String loginName = String.valueOf(loginMap.get("loginName").toString());
		logger.info("loginName"+loginName);
		loginName = loginName.replace("_AST", "");
		logger.info("loginNameWithReplaceAST "+loginName);
		/*Ended By Shivram 30012023*/


/*Added By Shivram 27032023*/
				String userNameMDC = "MDC";
				
				if(!userNameMDC.equals(loginName)){
					HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
					HttpServletResponse responce = (HttpServletResponse) objectArgs.get("responseObj");
					RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					try {
						rd.forward(request,responce);
					} catch(Exception e){
						e.printStackTrace();
					}
				}
				/*Ended By Shivram 27032023*/
			
		
		
		
		

		List t = hh.retriveOfcList("");

		List Depts = getDept.retriveDepts();

		objectArgs.put("AdminOfficeList", t);
		// objectArgs.put("ZpDeptsList", Depts);
		objRes.setResultValue(objectArgs);
		objRes.setViewName("view-zpDDOOffice");
		return objRes;
	}

	public ResultObject loadDdoOfficeData(Map objectArgs) throws Exception 
	{
		logger.info("Entering into loadData of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");

		try {
			
			ZpDDOOfficeMstDAOImpl  zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory());
			//commented by vaibhav tyagi
			//List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO.getAllDDOOfficeDtlsData();
			
			//added by vaibhav tyagi :start
			String districtSelected=StringUtility.getParameter("district",request);
			String talukaSelected=StringUtility.getParameter("taluka",request);
			String adminTypeSelected=StringUtility.getParameter("adminType",request);
			
			
			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO.getAllDDOOfficeDtlsData(districtSelected,talukaSelected,adminTypeSelected);
			//added by vaibhav tyagi :end
			logger.info("zpdistrictOfficelst::"+zpDDOOfficelst.size());

			List districtList= zpDDOOfficeMstDAO.getAllDistrict();
			List adminTypeList= zpDDOOfficeMstDAO.getAllAdminType();
			List talukaList=null;
			
			logger.info("districtSelected*********"+districtSelected);
			
			if((districtSelected!=null)&&(districtSelected!=""))
			{
			talukaList= zpDDOOfficeMstDAO.getAllTalukaByDistrict(districtSelected);
			}
			
			logger.info("talukaList*********"+talukaList);
			
			objectArgs.put("zpDDOOfficelst",zpDDOOfficelst);
			
			objectArgs.put("districtList",districtList);
			objectArgs.put("adminTypeList",adminTypeList);
			objectArgs.put("talukaList",talukaList);
			
			objectArgs.put("districtSelected",districtSelected);
			objectArgs.put("talukaSelected",talukaSelected);
			objectArgs.put("adminTypeSelected",adminTypeSelected);
			
			objRes.setResultValue(objectArgs);
			objRes.setViewName("zpDDOOfficeView");


		}
		catch(Exception e)
		{		
			logger.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: "+ e.getMessage());
			objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");		

		}
		return objRes;
	}

	@Override
	public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs)
			throws Exception {
		logger
				.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		ZpAdminOfficeMstDAOImpl hh = new ZpAdminOfficeMstDAOImpl(
				ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		List OfcDtls = hh.retriveOfcList("");
		logger
				.info("Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl:::"
						+ OfcDtls.size());
		objectArgs.put("ZpAdminOfficeMstList", OfcDtls);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-searchZpAdminOffice");
		return objRes;
	}

	public ResultObject search_ZpAdminOffice(Map objectArgs) throws Exception {
		logger.info("ZpAdminOfficeServiceImpl : search_ZpAdminOffice Called");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ZpAdminOfficeMstDAOImpl hh = new ZpAdminOfficeMstDAOImpl(
				ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		String OfcName = StringUtility.getParameter("txtAdminOfficeName",
				request).toString();
		List SerachedOfcDtls = hh.retriveOfcList(OfcName);
		objectArgs.put("ZpAdminOfficeMstList", SerachedOfcDtls);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-searchZpAdminOffice");
		return objRes;
	}

	/**
	 * <This Method is used to retrieve the details>
	 * 
	 * @method name : <retrieveZpAdminOfficeDtls>
	 * @params : <objectArgs>
	 * @returns : <objRes>
	 */
	/*
	 * public ResultObject retrieveZpAdminOfficeDtls(Map objectArgs) throws
	 * Exception{logger.info(
	 * "Entering into retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl");
	 * ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
	 * ServiceLocator serviceLocator = (ServiceLocator)
	 * objectArgs.get("serviceLocator");
	 * 
	 * 
	 * /CmnSearchListVO cmnSearchListVO = null; List<CmnSearchListVO>
	 * lstCmnSearch = new ArrayList<CmnSearchListVO>(); Map loginMap = (Map)
	 * objectArgs.get("baseLoginMap"); CommonUserVO commonUserVO =
	 * (CommonUserVO) loginMap.get("commonUserVO"); // add dynamic field
	 * setSessionInfo(objectArgs);
	 * 
	 * objectArgs = populateCombo(objectArgs); ZpAdminOfficeMst
	 * zpAdminOfficeMstVO = null;
	 * 
	 * if(objectArgs.get("zpAdminOfficeMstVO")!=null){ zpAdminOfficeMstVO =
	 * (ZpAdminOfficeMst) objectArgs.get("zpAdminOfficeMstVO"); }
	 * PaginatedListImpl paginateList = (PaginatedListImpl)
	 * objectArgs.get("paginateList"); int startIndex=
	 * paginateList.getFirstRecordIndex(); int pageSize=
	 * paginateList.getPageSize(); ZpAdminOfficeDAO readZpAdminOfficeMstDAOImpl
	 * = new
	 * ZpAdminOfficeDAOImpl(ZpAdminOfficeMst.class,serviceLocator.getSessionFactory
	 * ()); List zpAdminOfficeMstList =
	 * readZpAdminOfficeMstDAOImpl.searchZpAdminOfficeDetailsList
	 * (zpAdminOfficeMstVO,startIndex,pageSize);
	 * paginateList.setList(zpAdminOfficeMstList); if(zpAdminOfficeMstList
	 * !=null && zpAdminOfficeMstList.size()>0){ Iterator it =
	 * zpAdminOfficeMstList.listIterator(); MstScreenCustVO mstScreenVO =
	 * (MstScreenCustVO)it.next();
	 * paginateList.setTotalNumberOfRows(Integer.parseInt
	 * (mstScreenVO.getColumn()[0])); }else{
	 * paginateList.setTotalNumberOfRows(0); } objectArgs.put("paginateList",
	 * paginateList); objectArgs.put("ZpAdminOfficeMstList",
	 * zpAdminOfficeMstList);
	 * logger.info("Exit from retrieveZpAdminOfficeDtls of ZpAdminOfficeServiceImpl"
	 * ); objRes.setResultCode(ErrorConstants.SUCCESS);
	 * objRes.setResultValue(objectArgs);
	 * objRes.setViewName("view-searchZpAdminOffice"); return objRes; }
	 */
	/**
	 * <This Method is used to loadAddData details>
	 * 
	 * @method name : <loadAddData>
	 * @params : <objectArgs>
	 * @returns : <objRes>
	 */
	/*
	 * public ResultObject loadAddData(Map objectArgs) throws Exception{
	 * ResultObject objRes = new ResultObject(ErrorConstants.ERROR); objectArgs
	 * = populateCombo(objectArgs); objectArgs.put("updateFlag", "false");
	 * 
	 * objRes.setResultValue(objectArgs);
	 * objRes.setResultCode(ErrorConstants.SUCCESS);
	 * objRes.setViewName("view-zpAdminOffice");
	 * logger.info("ZpAdminOfficeServiceImpl : loadAddData End"); return objRes;
	 * }
	 */

	public ResultObject loadUpdateData(Map objectArgs) throws Exception {
		logger.info("ZpAdminOfficeServiceImpl : loadUpdatedata Called");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");

		// objectArgs = populateCombo(objectArgs);

		ZpAdminOfficeMst readZpAdminOfficeMstVO = new ZpAdminOfficeMst();
		ZpAdminOfficeMstDAOImpl readZpAdminOfficeMstDAOImpl = new ZpAdminOfficeMstDAOImpl(
				ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
		Long zpAdminOfficeMstPkID = Long.valueOf(StringUtility.getParameter(
				"zpAdminOfficeMstPkID", request).toString());
		readZpAdminOfficeMstVO = readZpAdminOfficeMstDAOImpl
				.read(zpAdminOfficeMstPkID);
		objectArgs.put("ZpAdminOfficeMstVO", readZpAdminOfficeMstVO);

		objectArgs.put("updateFlag", "true");
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("view-zpAdminOffice");
		logger.info("ZpAdminOfficeServiceImpl : loadUpdateData End");
		return objRes;
	}

	private List getHierarchyUsers(Map inputMap) {
		ServiceLocator serviceLocator = (ServiceLocator) inputMap
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");
		List UserList = null;

		try {

			// setSessionInfo(inputMap);
			/*
			 * Integer llFromLevelId = 0; UserList = new ArrayList<String>();
			 * 
			 * // Get the Subject Name String subjectName = gObjRsrcBndle
			 * .getString("DCPS.RegistrationForm");
			 * 
			 * // Get the Hierarchy Id Long lLngHierRefId =
			 * WorkFlowHelper.getHierarchyByPostIDAndDescription
			 * (POST_ID.toString(),subjectName, inputMap);
			 * 
			 * // Get the From level Id llFromLevelId =
			 * WorkFlowHelper.getLevelFromPostMpg(POST_ID.toString(),
			 * lLngHierRefId, inputMap);
			 * 
			 * // Get the List of Post ID of the users at the next Level List
			 * rsltList = WorkFlowHelper.getUpperPost(POST_ID.toString(),
			 * lLngHierRefId, llFromLevelId, inputMap);
			 * 
			 * Object[] lObjNextPost = null;
			 * 
			 * for (Integer lInt = 0; lInt < rsltList.size(); lInt++) {
			 * 
			 * lObjNextPost = (Object[]) rsltList.get(lInt);
			 * 
			 * if (!(lObjNextPost.equals(null))) {
			 * UserList.add(lObjNextPost[0].toString()); } }
			 */

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	/* Function to get hierarchy from Asst to ATo */
	private List getAsstHierarchyUsers(Map inputMap) {

		List UserList = null;

		try {

			// setSessionInfo(inputMap);
			/*
			 * Integer llFromLevelId = 0; UserList = new ArrayList<String>();
			 * 
			 * // Get the Subject Name String subjectName = gObjRsrcBndle
			 * .getString("DCPS.RegistrationForm");
			 * 
			 * // Get the Hierarchy Id Long lLngHierRefId = WorkFlowHelper
			 * .getHierarchyByPostIDAndDescription(POST_ID.toString(),
			 * subjectName, inputMap);
			 * 
			 * // Get the From level Id llFromLevelId = 20;
			 * 
			 * // Get the List of Post ID of the users at the next Level List
			 * rsltList = WorkFlowHelper.getUpperPost(POST_ID.toString(),
			 * lLngHierRefId, llFromLevelId, inputMap);
			 * 
			 * Object[] lObjNextPost = null;
			 * 
			 * for (Integer lInt = 0; lInt < rsltList.size(); lInt++) {
			 * 
			 * lObjNextPost = (Object[]) rsltList.get(lInt);
			 * 
			 * if (!(lObjNextPost.equals(null))) {
			 * UserList.add(lObjNextPost[0].toString()); } }
			 */

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" Error is : " + e, e);
		}
		return UserList;
	}

	public ResultObject loadRegistrationFormZP(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		Long gLngPostId = SessionHelper.getPostId(inputMap);
		POST_ID = gLngPostId;
		LANG_ID = 1l;
		try {

			// setSessionInfo(inputMap);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
					serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			if (request != null) {
				String lStrEmpId = StringUtility.getParameter("empId", request);
				if (!lStrEmpId.equals("")) {
					Long lLngEmpID = Long.parseLong(lStrEmpId);
					MstEmp MstEmpObj = (MstEmp) lObjNewRegDdoDAO
							.read(lLngEmpID);
					inputMap.put("lObjEmpData", MstEmpObj);
				}
			}
			String lStrDdoCode = null;
			StringUtility.getParameter("User", request);

			inputMap.put("EditForm", "Y");
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(POST_ID);
			inputMap.put("DDOCODE", lStrDdoCode);

			// Get Employee code for New Registration

			// Long lLongDcpsEmpIdNewReg =
			// IFMSCommonServiceImpl.getNextSeqNum(
			// "MST_DCPS_EMP", inputMap);

			// Get the StateNames
			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			inputMap.put("STATENAMES", lLstState);

			// Get the office list from the database
			List listOfficeNames = lObjDcpsCommonDAO
					.getCurrentOffices(lStrDdoCode);
			inputMap.put("OFFICELIST", listOfficeNames);

			// Get the Bank Names
			List lLstBankNames = lObjDcpsCommonDAO.getBankNames();
			inputMap.put("BANKNAMES", lLstBankNames);

			// Get Class List from lookup Master
			List listCityClass = IFMSCommonServiceImpl.getLookupValues(
					"DCPS_OFFICE_CLASS", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("listCityClass", listCityClass);

			// Get Salutation List from Lookup Master
			List listSalutation = IFMSCommonServiceImpl.getLookupValues(
					"Salutation", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listSalutation", listSalutation);

			// Get type of Pay Commission from lookup Master
			List listPayCommission = IFMSCommonServiceImpl.getLookupValues(
					"PayCommissionDCPS", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("listPayCommission", listPayCommission);

			// Get Type of RelationList from lookup Master
			List listRelationship = IFMSCommonServiceImpl
					.getLookupValues("RelationList", SessionHelper
							.getLangId(inputMap), inputMap);
			inputMap.put("listRelationship", listRelationship);

			// Set the current date for validation of date of birth
			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			// Set the date of joining limit as 01-Nov-2005
			inputMap.put("lDtJoiDtLimit", "01/11/2005");

			// Get PF Account Maintained from lookup Master
			List lLstPFAccntMntdBy = IFMSCommonServiceImpl.getLookupValues(
					"AccountMaintaindedBy", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("lLstPFAccntMntdBy", lLstPFAccntMntdBy);

			// Get AcDcpsMaintainedBy from Lookup Master
			List lLstPFAccntMntdByDCPS = IFMSCommonServiceImpl.getLookupValues(
					"AccountMaintainedByForDCPSEmp", SessionHelper
							.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdByDCPS", lLstPFAccntMntdByDCPS);

			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv
					.getSessionFactory());
			List lLstAllDesignations = lObjDdoProfileDAO
					.getAllDesignation(LANG_ID);
			inputMap.put("lLstAllDesignations", lLstAllDesignations);

			// Code Added to get all states List

			List lLstAllStates = lObjDcpsCommonDAO.getStates(1l);
			inputMap.put("lLstAllStates", lLstAllStates);

			String lStrDdoCodeRpt = lObjDcpsCommonDAO
					.getRptDDOCodeForZPDDO(lStrDdoCode);

			List lStrParentDept = lObjDcpsCommonDAO
					.getParentDeptForDDO(lStrDdoCodeRpt);
			List listParentDept = null;

			if (lStrParentDept != null) {
				Object[] lListObj = (Object[]) lStrParentDept.get(0);

				Long ParentDeptId = Long.valueOf(lListObj[0].toString());
				String ParentDeptDesc = lListObj[1].toString();
				inputMap.put("ParentDeptIdByDefault", ParentDeptId);
				inputMap.put("ParentDeptDescByDefault", ParentDeptDesc);

				listParentDept = lObjDcpsCommonDAO
						.getAllHODDepartment(Long.parseLong(gObjRsrcBndle
								.getString("DCPS.CurrentFieldDeptID")), LANG_ID);
				inputMap.put("listParentDept", listParentDept);

				// Get the Cadre list from the database
				List listCadres = lObjDcpsCommonDAO
						.getCadreForDept(ParentDeptId);
				inputMap.put("CADRELIST", listCadres);

				List lLstDesignation = lObjDcpsCommonDAO
						.getDesigsForPFDAndCadre(ParentDeptId);

				inputMap.put("lLstDesignation", lLstDesignation);

			}

			List UserList = getHierarchyUsers(inputMap);
			inputMap.put("UserList", UserList);

			List ATOUserList = getAsstHierarchyUsers(inputMap);
			inputMap.put("ATOUserList", ATOUserList);

			/* Changes for GIS Details */
			List lLstGISDetails = IFMSCommonServiceImpl.getLookupValues(
					"GISDetails", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISDetails", lLstGISDetails);

			List lLstGISGroup = IFMSCommonServiceImpl.getLookupValues(
					"GISGroup", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISGroup", lLstGISGroup);

			/* Changes for GIS Details ends */

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegFormZP");

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject popUpEmpDtlsZP(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		ServiceLocator serv = (ServiceLocator) inputMap.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) inputMap
				.get("requestObj");

		String lStrEmpId = null;
		MstEmp MstEmpObj = null;
		DcpsCadreMst lObjMstCadre = null;
		RltDcpsPayrollEmp RltDcpsPayrollEmpObj = null;
		Set<CmnAttachmentMpg> cmnAttachmentMpgs = null;
		Long lLngPhotoAttachmentId = null;
		Long lLngSignAttachmentId = null;
		Iterator<CmnAttachmentMpg> cmnAttachmentMpgIterator = null;
		CmnAttachmentMpg cmnAttachmentMpg = null;
		Long lLngFieldDept = null;

		try {
			// setSessionInfo(inputMap);

			SimpleDateFormat lObjDateFormat = new SimpleDateFormat("dd/MM/yyyy");

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
					serv.getSessionFactory());

			NewRegDdoDAO lObjNewRegDdoDAOForCadre = new NewRegDdoDAOImpl(
					DcpsCadreMst.class, serv.getSessionFactory());
			NewRegDdoDAO lObjNewRegPayrollDdoDAO = new NewRegDdoDAOImpl(
					RltDcpsPayrollEmp.class, serv.getSessionFactory());

			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			if (request != null) {
				lStrEmpId = StringUtility.getParameter("empId", request);
				if (!lStrEmpId.equals("")) {
					Long lLngEmpID = Long.parseLong(lStrEmpId);
					MstEmpObj = (MstEmp) lObjNewRegDdoDAO.read(lLngEmpID);
					inputMap.put("lObjEmpData", MstEmpObj);

					if (lObjNewRegDdoDAO
							.checkDcpsEmpPayrollIdForEmpIdExists(lLngEmpID)) {
						Long lLngDcpsPayrollId = lObjNewRegDdoDAO
								.getDcpsEmpPayrollIdForEmpId(lLngEmpID);
						RltDcpsPayrollEmpObj = (RltDcpsPayrollEmp) lObjNewRegPayrollDdoDAO
								.read(lLngDcpsPayrollId);
						inputMap
								.put("lObjEmpPayrollData", RltDcpsPayrollEmpObj);
					}

				}
			}

			SimpleDateFormat lObjSimpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");

			String lStrDobEmp = lObjSimpleDateFormat.format(MstEmpObj.getDob());

			if (MstEmpObj.getParentDept() != null) {
				lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
				List lLstDesignation = lObjDcpsCommonDAO
						.getDesigsForPFDAndCadre(lLngFieldDept);
				inputMap.put("lLstDesignation", lLstDesignation);
			}

			if (MstEmpObj.getCadre() != null
					&& !MstEmpObj.getCadre().equalsIgnoreCase("")) {

				lObjMstCadre = (DcpsCadreMst) lObjNewRegDdoDAOForCadre
						.read(Long.valueOf(MstEmpObj.getCadre()));
				inputMap.put("SuperAnnAge", lObjMstCadre.getSuperAntunAge());

				String lStrGroupName = lObjDcpsCommonDAO
						.getCmnLookupNameFromId(Long.valueOf(lObjMstCadre
								.getGroupId().trim()));
				inputMap.put("GroupName", lStrGroupName.trim());
				// inputMap.put("GroupName", lObjMstCadre.getGroupId());

				/*
				 * String lStrWithoutYear = lStrDobEmp.substring(0, 6); Long
				 * SuperAnnuationAge = lObjMstCadre.getSuperAntunAge(); Long
				 * lLongBirthYear = Long.valueOf(lStrDobEmp.substring(6)); Long
				 * lLongRetiringYear = lLongBirthYear + SuperAnnuationAge;
				 * String lStrRetiringYear = lStrWithoutYear +
				 * lLongRetiringYear.toString();
				 */

				String lStrRetiringYear = null;
				if (MstEmpObj.getSuperAnndate() != null) {
					if (!"".equals(lStrRetiringYear)) {
						lStrRetiringYear = lObjSimpleDateFormat
								.format(MstEmpObj.getSuperAnndate());
					}
				}

				inputMap.put("SuperAnnDate", lStrRetiringYear);

				List lLstOfficesForPost = null;
				if (RltDcpsPayrollEmpObj.getPostId() != null
						&& RltDcpsPayrollEmpObj.getPostId() != -1) {
					lLstOfficesForPost = lObjDcpsCommonDAO
							.getOfficesForPost(RltDcpsPayrollEmpObj.getPostId());
				}
				inputMap.put("lLstOfficesForPost", lLstOfficesForPost);
			}

			/*
			 * Check if Pay commission is added, then get list of concerned
			 * payscales
			 */

			List PayScaleList = null;
			if (MstEmpObj.getPayCommission() != null
					&& !MstEmpObj.getPayCommission().equalsIgnoreCase("")) {

				if (!MstEmpObj.getPayCommission().equals("700337")) {
					Map voToServiceMap = new HashMap();
					voToServiceMap.put("commissionId", MstEmpObj
							.getPayCommission());

					inputMap.put("voToServiceMap", voToServiceMap);

					resObj = serv.executeService("GetScalefromDesg", inputMap);
					PayScaleList = (List) inputMap.get("PayScaleList");
				}
				inputMap.put("PayScaleList", PayScaleList);
			}

			if (MstEmpObj.getDesignation() != null
					&& !MstEmpObj.getDesignation().equalsIgnoreCase("")) {
				Map voToServiceMap = new HashMap();

				voToServiceMap.put("dsgnId", MstEmpObj.getDesignation());
				inputMap.put("voToServiceMap", voToServiceMap);
				resObj = serv
						.executeService("GetPostfromDesignation", inputMap);

				List CurrentPostList = (List) inputMap.get("CurrentPostList");
				inputMap.put("CurrentPostList", CurrentPostList);

			}

			/* code for accessing Ddo Office VO from Office Id */

			if (MstEmpObj.getCurrOff() != null) {
				Long lLongDdoOfficeId = Long.valueOf(MstEmpObj.getCurrOff());
				DdoOffice lObjDdoOfficeVO = lObjNewRegDdoDAO
						.getDdoOfficeVO(lLongDdoOfficeId);
				inputMap.put("lObjDdoOfficeVO", lObjDdoOfficeVO);
			}

			// Added for viewing Photo and signature
			CmnAttachmentMstDAO lObjCmnAttachmentMstDAO = new CmnAttachmentMstDAOImpl(
					CmnAttachmentMst.class, serv.getSessionFactory());
			CmnAttachmentMst lObjCmnAttachmentMst = null;

			if (MstEmpObj.getPhotoAttachmentID() != null
					&& MstEmpObj.getPhotoAttachmentID().doubleValue() > 0) {
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
						.findByAttachmentId(Long.parseLong(MstEmpObj
								.getPhotoAttachmentID().toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

				if (lObjCmnAttachmentMst != null) {
					lLngPhotoAttachmentId = lObjCmnAttachmentMst
							.getAttachmentId();
				}
				if (lObjCmnAttachmentMst != null
						&& lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
					cmnAttachmentMpgs = lObjCmnAttachmentMst
							.getCmnAttachmentMpgs();
				}
				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
				Long srNo = 0L;
				for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase(
							"photo")) {
						srNo = cmnAttachmentMpg.getSrNo();
						inputMap.put("Photo", lObjCmnAttachmentMst);
						inputMap.put("PhotoId", lLngPhotoAttachmentId);
						inputMap.put("PhotosrNo", srNo);
					}
				}
			}

			if (MstEmpObj.getSignatureAttachmentID() != null
					&& MstEmpObj.getSignatureAttachmentID().doubleValue() > 0) {
				lObjCmnAttachmentMst = new CmnAttachmentMst();
				lObjCmnAttachmentMst = lObjCmnAttachmentMstDAO
						.findByAttachmentId(Long.parseLong(MstEmpObj
								.getSignatureAttachmentID().toString()));

				cmnAttachmentMpgs = new HashSet<CmnAttachmentMpg>();

				if (lObjCmnAttachmentMst != null) {
					lLngSignAttachmentId = lObjCmnAttachmentMst
							.getAttachmentId();
				}
				if (lObjCmnAttachmentMst != null
						&& lObjCmnAttachmentMst.getCmnAttachmentMpgs() != null) {
					cmnAttachmentMpgs = lObjCmnAttachmentMst
							.getCmnAttachmentMpgs();
				}

				cmnAttachmentMpgIterator = cmnAttachmentMpgs.iterator();
				Long srNo = 0L;
				for (Integer lInt = 0; lInt < cmnAttachmentMpgs.size(); lInt++) {
					cmnAttachmentMpg = cmnAttachmentMpgIterator.next();
					if (cmnAttachmentMpg.getAttachmentDesc().equalsIgnoreCase(
							"signature")) {
						srNo = cmnAttachmentMpg.getSrNo();
						inputMap.put("Sign", lObjCmnAttachmentMst);
						inputMap.put("SignId", lLngSignAttachmentId);
						inputMap.put("SignsrNo", srNo);
					}
				}
			}

			// Added for viewing photo and signature overs
			// Get the StateNames

			List<MstEmpNmn> NomineesList = lObjNewRegDdoDAO
					.getNominees(lStrEmpId);
			inputMap.put("NomineesList", NomineesList);

			Integer lIntTotalNominees = NomineesList.size();
			inputMap.put("lIntTotalNominees", lIntTotalNominees);

			String lStrDdoCode = null;
			String lStrUser = StringUtility.getParameter("User", request);

			if (lStrUser.equals("DDO")) {
				inputMap.put("EditForm", "N");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(POST_ID);
				inputMap.put("DDOCODE", lStrDdoCode);

			}

			else if (lStrUser.equals("Asst")) {

				inputMap.put("EditForm", "Y");
				lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(POST_ID);
				inputMap.put("DDOCODE", lStrDdoCode);

			}

			/* Changes for GIS Details */
			List lLstGISDetails = IFMSCommonServiceImpl.getLookupValues(
					"GISDetails", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISDetails", lLstGISDetails);

			List lLstGISGroup = IFMSCommonServiceImpl.getLookupValues(
					"GISGroup", SessionHelper.getLangId(inputMap), inputMap);

			inputMap.put("lLstGISGroup", lLstGISGroup);

			/* Changes for GIS Details ends */

			String lStrFromSearch = StringUtility.getParameter("FromSearchEmp",
					request);

			inputMap.put("lStrFromSearch", lStrFromSearch);

			List lLstState = lObjDcpsCommonDAO.getStateNames(1L);
			inputMap.put("STATENAMES", lLstState);

			// Get the office list from the database
			List listOfficeNames = lObjDcpsCommonDAO
					.getCurrentOffices(lStrDdoCode);
			inputMap.put("OFFICELIST", listOfficeNames);

			// Get Salutation List from Lookup Master
			List listSalutation = IFMSCommonServiceImpl.getLookupValues(
					"Salutation", SessionHelper.getLangId(inputMap), inputMap);
			inputMap.put("listSalutation", listSalutation);

			lLngFieldDept = Long.parseLong(MstEmpObj.getParentDept());
			// Long lLngPayCmnId = Long.parseLong(MstEmpObj.getPayCommission());

			List listRelationship = IFMSCommonServiceImpl
					.getLookupValues("RelationList", SessionHelper
							.getLangId(inputMap), inputMap);
			inputMap.put("listRelationship", listRelationship);

			// Get the Bank Names
			List lLstBankNames = lObjDcpsCommonDAO.getBankNames();
			inputMap.put("BANKNAMES", lLstBankNames);

			// Get the BankBranchNames
			if (MstEmpObj.getBankName() != null) {
				List lLstBrachNames = lObjDcpsCommonDAO.getBranchNames(Long
						.valueOf(MstEmpObj.getBankName()));
				inputMap.put("BRANCHNAMES", lLstBrachNames);
			}

			// Get the list of all states
			List lLstAllStates = lObjDcpsCommonDAO.getStates(1l);
			inputMap.put("lLstAllStates", lLstAllStates);

			// Get the Cadre list from the database
			List listCadres = lObjDcpsCommonDAO.getCadreForDept(lLngFieldDept);
			inputMap.put("CADRELIST", listCadres);

			List listParentDept = lObjDcpsCommonDAO.getAllHODDepartment(Long
					.parseLong(gObjRsrcBndle
							.getString("DCPS.CurrentFieldDeptID")), LANG_ID);
			inputMap.put("listParentDept", listParentDept);

			List listPayCommission = IFMSCommonServiceImpl.getLookupValues(
					"PayCommissionDCPS", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("listPayCommission", listPayCommission);

			// Set the current date for validation of date of birth
			Date lDtcurDate = SessionHelper.getCurDate();
			inputMap.put("lDtCurDate", lObjDateFormat.format(lDtcurDate));

			// Set the date of joining limit as 01-Nov-2005
			inputMap.put("lDtJoiDtLimit", "01/11/2005");

			// Get PF Account Maintained from lookup Master
			List lLstPFAccntMntdBy = IFMSCommonServiceImpl.getLookupValues(
					"AccountMaintaindedBy", SessionHelper.getLangId(inputMap),
					inputMap);
			inputMap.put("lLstPFAccntMntdBy", lLstPFAccntMntdBy);

			// Get AcDcpsMaintainedBy from Lookup Master
			List lLstPFAccntMntdByDCPS = IFMSCommonServiceImpl.getLookupValues(
					"AccountMaintainedByForDCPSEmp", SessionHelper
							.getLangId(inputMap), inputMap);
			inputMap.put("lLstPFAccntMntdByDCPS", lLstPFAccntMntdByDCPS);

			// Get PF Series from lookup Master
			List lLstPFSeries = null;
			String lStrAcMntndBy = RltDcpsPayrollEmpObj.getAcMaintainedBy();
			String MumbaiOrNagpurAG = null;
			if (lStrAcMntndBy != null && !lStrAcMntndBy.equals("")) {
				if (lStrAcMntndBy.equals("700092")) {
					lLstPFSeries = IFMSCommonServiceImpl.getLookupValues(
							"PF_Series", SessionHelper.getLangId(inputMap),
							inputMap);
					MumbaiOrNagpurAG = "Yes";
				} // PF Series for AG Mumbai
				else if (lStrAcMntndBy.equals("700093")) {
					lLstPFSeries = IFMSCommonServiceImpl.getLookupValues(
							"PF_Series_AG_Nagpur", SessionHelper
									.getLangId(inputMap), inputMap);
					MumbaiOrNagpurAG = "Yes";
				} // PF Series for AG Nagpur
				else {
					MumbaiOrNagpurAG = "No";
				}
			}
			inputMap.put("lLstPFSeries", lLstPFSeries);
			inputMap.put("MumbaiOrNagpurAG", MumbaiOrNagpurAG);

			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(null, serv
					.getSessionFactory());
			List lLstAllDesignations = lObjDdoProfileDAO
					.getAllDesignation(LANG_ID);
			inputMap.put("lLstAllDesignations", lLstAllDesignations);

			inputMap.put("UserType", lStrUser);
			List UserList = getHierarchyUsers(inputMap);
			inputMap.put("UserList", UserList);

			List ATOUserList = getAsstHierarchyUsers(inputMap);
			inputMap.put("ATOUserList", ATOUserList);

			// Get the type of user

			resObj.setResultValue(inputMap);
			resObj.setViewName("DCPSRegistrationForm");

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject viewDraftFormsZP(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		List empList = null;

		try {

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
					serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			inputMap.put("DDOCODE", lStrDdoCode);
			String lStrUser = "Asst";

			List lLstParentDept = lObjDcpsCommonDAO
					.getParentDeptForDDO(lStrDdoCode);
			Object[] objParentDept = (Object[]) lLstParentDept.get(0);

			List lLstDesignation = lObjDcpsCommonDAO.getAllDesignation(
					(Long) objParentDept[0], gLngLangId);
			inputMap.put("lLstDesignation", lLstDesignation);

			String lStrSearchCriteria = StringUtility.getParameter(
					"searchCriteria", request);
			String lStrSearchValue = StringUtility.getParameter("searchValue",
					request);

			if (!lStrSearchCriteria.equals("")) {
				if (lStrSearchCriteria.equals("Designation")) {
					empList = lObjNewRegDdoDAO.getAllDcpsEmployeesForDesig(
							lStrUser, gStrPostId, lStrDdoCode, lStrSearchValue);
					inputMap.put("DesignationId", lStrSearchValue.trim());
					inputMap.put("CaseStatus", "");
				}
				if (lStrSearchCriteria.equals("Case Status")) {
					empList = lObjNewRegDdoDAO
							.getAllDcpsEmployeesForCaseStatus(lStrUser,
									gStrPostId, lStrDdoCode, lStrSearchValue);
					inputMap.put("CaseStatus", lStrSearchValue.trim());
					inputMap.put("DesignationId", "");
				}
				inputMap.put("SearchCriteria", lStrSearchCriteria.trim());
			} else {
				empList = lObjNewRegDdoDAO.getAllDcpsEmployees(lStrUser,
						gStrPostId, lStrDdoCode);
				inputMap.put("DesignationId", "");
				inputMap.put("CaseStatus", "");
				inputMap.put("SearchCriteria", "");
			}

			inputMap.put("empList", empList);
			inputMap.put("EditForm", "Y");

			resObj.setResultValue(inputMap);
			resObj.setViewName("NewRegDrafts");

		} catch (Exception e) {
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;
	}

	public ResultObject generateDDOCode(Map objectArgs) throws Exception {
		logger
				.info("Entering into generateDDOCode of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(-1);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		ZpDDOOfficeMstDAOImpl check = new ZpDDOOfficeMstDAOImpl(null,
				serviceLocator.getSessionFactory());

		// DDO Code Generation : Start
		String AdminOfc = (StringUtility.getParameter("AdminOfc", request));
		String SubTOCode = (StringUtility.getParameter("SubTOCode", request));

		List getOfcCode = check.getAdminOfcCode(AdminOfc);
		// Object[] ofcCode =(Object[])getOfcCode.get(0);
		String AOfcCode = getOfcCode.get(0).toString();
		String CreatedDDOCode = AOfcCode;
		CreatedDDOCode += SubTOCode;
		List getCountCode = check.getCountofDDOCode(CreatedDDOCode);
		// Object[] newCode =(Object[])getCountCode.get(0);
		String FinalpreFixed = "";
		String suffix = "";
		String midfix = "";
		if (getCountCode.get(0) != null) {
			Long temp = Long.parseLong(getCountCode.get(0).toString()) + 1;
			suffix = temp.toString();
		}
		if (suffix.length() == 1)
			midfix = "0000";
		else if (suffix.length() == 2)
			midfix = "000";
		else if (suffix.length() == 3)
			midfix = "00";
		else if (suffix.length() == 4)
			midfix = "0";

		FinalpreFixed = CreatedDDOCode + midfix + suffix;
		// DDO Code Generation : End
		// String lstrCode=getResponseXMLDocDDOCode(FinalpreFixed).toString();
		String lStrCodeResult = new AjaxXmlBuilder().addItem("GeneratedCode",
				FinalpreFixed).toString();
		logger.info("Entering into generateDDOCode of ZpAdminOfficeServiceImpl"
				+ lStrCodeResult);
		objectArgs.put("ajaxKey", lStrCodeResult);
		// objectArgs.put("ajaxKey1", AjaxResult);
		objRes.setResultValue(objectArgs);
		objRes.setResultCode(ErrorConstants.SUCCESS);
		objRes.setViewName("ajaxData");
		return objRes;
	}

	public ResultObject loadFormListForDDOZP(Map inputMap) {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		String lStrDdoCode = null;

		try {

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(null, serv
					.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			getHierarchyUsers(inputMap);
			lStrDdoCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);

			List AllFormsList = lObjNewRegDdoDAO.getFormListForDDO(lStrDdoCode);
			inputMap.put("AllFormsList", AllFormsList);

			resObj.setResultValue(inputMap);
			resObj.setViewName("FormListForDDO");

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" Error is : " + e, e);
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");
		}

		return resObj;

	}

	/*
	 * public Map populateCombo(Map objectArgs){ setSessionInfo(objectArgs);
	 * return objectArgs; }
	 */

	/**
	 * <This Method is used to setSessionInfo details>
	 * 
	 * @method name : <setSessionInfo>
	 * @params : <inputMap>
	 * @returns : <>
	 */
	/*
	 * private void setSessionInfo(Map inputMap){ Map loginMap = (Map)
	 * inputMap.get("baseLoginMap"); CommonUserVO commonUserVO = (CommonUserVO)
	 * loginMap.get("commonUserVO"); LOC_ID =
	 * loginMap.get("locationCode").toString(); LANG_ID =
	 * commonUserVO.getLangId(); POST_ID =
	 * commonUserVO.getLoggedInPostVO().getPostCode(); USER_ID =
	 * commonUserVO.getUserId(); }
	 */

	// Added by Demolisher
	public ResultObject approveDdoOfficeDataList(Map objectArgs)
			throws Exception {
		logger
				.info("Entering into  approveDdoOfficeDataList of ZpAdminOfficeServiceImpl");

		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");

		try {
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			logger.info("The post ID is :" + gLngPostId);

			// Long ddoType = zpDDOOfficeMstDAO.getAllDDOOfficeType(gLngPostId);
			// logger.info("The DDO Type ::::::::::::::::"+ddoType);

			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
					.getAllDDOOfficeDtlsDataByPostID(gLngPostId);
			logger.info("zpdistrictOfficelst::" + zpDDOOfficelst.size());

			objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("approveDDOOfficeView");

		} catch (Exception e) {
			logger
					.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");

		}
		return objRes;
	}

	public ResultObject updateApproveStatus(Map objectArgs) throws Exception {
		logger
				.info("Entering into updateApproveStatus of ZpDDOOfficeServiceImpl");

		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		try {
			int flag = 0;
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			String ddocde = StringUtility.getParameter("ddocode", request);
			if (ddocde.charAt(0) == '0') {
				flag = 1;
				logger.info("Flag :::::::::" + flag);
			}
			long ddocode = Long.parseLong(ddocde);
			logger.info("DDO Code :::::::::" + ddocode);
			ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			zpDDOOfficeMstDAO.approveEmployeeLogin(ddocode, flag);
			logger.info("DDO Code :::::::::" + ddocode);
			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
					.getAllDDOOfficeDtlsDataByPostID(gLngPostId);
			objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			logger.info("DDO Code :::::::::" + ddocode);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("approveDDOOfficeView");

		} catch (Exception e) {
			logger.info("Null Pointer Exception Ocuures...updateApproveStatus");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");
		}
		return objRes;
	}

	public ResultObject updateRejectStatus(Map objectArgs) throws Exception {
		logger
				.info("Entering into updateRejectStatus of ZpDDOOfficeServiceImpl");

		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs
				.get("requestObj");
		try {
			int flag = 0;
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			String ddocde = StringUtility.getParameter("ddocode", request);
			if (ddocde.charAt(0) == '0') {
				flag = 1;
				logger.info("Flag :::::::::" + flag);
			}
			long ddocode = Long.parseLong(ddocde);
			logger.info("DDO Code :::::::::" + ddocode);
			ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			zpDDOOfficeMstDAO.rejectEmployeeLogin(ddocode, flag);
			logger.info("DDO Code :::::::::" + ddocode);
			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
					.getAllDDOOfficeDtlsDataByPostID(gLngPostId);
			objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			logger.info("DDO Code :::::::::" + ddocode);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("approveDDOOfficeView");

		} catch (Exception e) {
			logger.info("Null Pointer Exception Ocuures...updateRejectStatus");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");
		}
		return objRes;
	}

	public ResultObject viewApprovedOffices(Map objectArgs) throws Exception {
		logger
				.info("Entering into viewApprovedOffices of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");

		try {
			int flag = 0;
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
					.viewAllDDOApproveOfficeDtlsData(gLngPostId);
			logger.info("zpdistrictOfficelst::" + zpDDOOfficelst.size());

			objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);
			objectArgs.put("flag", flag);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("DDOOfficeStatusMain");

		} catch (Exception e) {
			logger
					.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");

		}
		return objRes;
	}

	public ResultObject viewRejectedOffices(Map objectArgs) throws Exception {
		logger.info("Entering into viewRejectedOffices of ZpAdminOfficeServiceImpl");
		ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs
				.get("serviceLocator");

		try {

			int flag = 1;
			Long gLngPostId = SessionHelper.getPostId(objectArgs);
			Long gLngUserId = SessionHelper.getUserId(objectArgs);
			ZpDDOOfficeMstDAOImpl zpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(
					ZpAdminOfficeMst.class, serviceLocator.getSessionFactory());
			List<ZpRltDdoMap> zpDDOOfficelst = zpDDOOfficeMstDAO
					.viewAllDDORejectOfficeDtlsData(gLngPostId);
			logger.info("zpdistrictOfficelst::" + zpDDOOfficelst.size());

			objectArgs.put("zpDDOOfficelst", zpDDOOfficelst);
			objectArgs.put("flag", flag);
			objRes.setResultCode(ErrorConstants.SUCCESS);
			objRes.setResultValue(objectArgs);
			objRes.setViewName("DDOOfficeStatusMain");

		} catch (Exception e) {
			logger
					.info("Null Pointer Exception Ocuures...insertDistrictMPGDtls");
			logger.error("Error is: " + e.getMessage());
			objectArgs.put("msg",
					"There is Some Problem.Please Try Again Later.");
			objRes.setResultValue(objectArgs);
			objRes.setViewName("errorInsert");

		}
		return objRes;
	}
	//added by samdahan
	private String generateUniqeInstituteId(String strDdoCode, String districtCode,Map objectArgs) 
	{
		String uniqeInstituteId="";
		int uniqeInstituteIdCount=0;
		String distName="";
		
		ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
		ZpDDOOfficeMstDAOImpl objZpDDOOfficeMstDAOImpl=new ZpDDOOfficeMstDAOImpl(ZpRltDdoMap.class,serviceLocator.getSessionFactory());
		
		uniqeInstituteIdCount=objZpDDOOfficeMstDAOImpl.getUniqeInstituteIdCount(strDdoCode);
		distName=objZpDDOOfficeMstDAOImpl.getDistName(districtCode);
		
		String tmp = String.valueOf((uniqeInstituteIdCount+1));
		if(tmp.length()<8)
		{
			String prefix="";
			for(int i=0;i<(7-tmp.length());i++)
			{
				prefix = prefix+"0";
			}
			tmp=prefix+tmp;
		}
		
		uniqeInstituteId = distName.substring(0, 3) + tmp; 
		
		logger.info("uniqeInstituteId: "+uniqeInstituteId);
		
		return uniqeInstituteId;
	}
	
	
    public ResultObject approveRejectDtls(final Map objectArgs) throws Exception
    {
        logger.info("into approveRejectDtls");

        final ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        final ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
        try
        {
            SessionHelper.getPostId(objectArgs);
            SessionHelper.getUserId(objectArgs);
            final String ddocode = StringUtility.getParameter("ddocode", request);
            logger.info("into approveRejectDtls"+ddocode);
            final Integer hierarchy = Integer.parseInt(StringUtility.getParameter("hierarchy", request));
            logger.info("into approveRejectDtls"+hierarchy);
            final ZpDDOOfficeMstDAOImpl objZpDDOOfficeMstDAO = new ZpDDOOfficeMstDAOImpl(ZpAdminOfficeMst.class,
                    serviceLocator.getSessionFactory());
            final List ddoInfoList = objZpDDOOfficeMstDAO.getDDOinfo(ddocode);
            logger.info("into approveRejectDtls 0----All OK"  );
            String officeName = objZpDDOOfficeMstDAO.getOfficeName(ddocode);
            officeName = officeName.substring(1, (officeName.length() - 1));
            String headMasterName = objZpDDOOfficeMstDAO.getHeadMasterName(ddocode);
            headMasterName = headMasterName.substring(1, (headMasterName.length() - 1));
           // String diseCode = objZpDDOOfficeMstDAO.getDiceCode(ddocode);
            //diseCode = diseCode.substring(1, (diseCode.length() - 1));
            final String ddoMobileNo = objZpDDOOfficeMstDAO.getMobileNo(ddocode);
            logger.info("DDO Code :::::::::" + ddocode);

            objectArgs.put("hierarchy", hierarchy);
            objectArgs.put("ddoInfoList", ddoInfoList);
            objectArgs.put("officeName", officeName);
            objectArgs.put("headMasterName", headMasterName);
            //objectArgs.put("diseCode", diseCode);
            objectArgs.put("ddoMobileNo", ddoMobileNo);
            objectArgs.put("ddocode", ddocode);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("approveRejectDDOOfficeView");

        } catch (final Exception e)
        {
            logger.info("Null Pointer Exception Ocuures...updateApproveStatus");
            logger.error("Error is: " + e.getMessage());
            objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
            objRes.setResultValue(objectArgs);
            objRes.setViewName("errorInsert");
        }
        return objRes;
    }
	
}
