/* Copyright TCS 2011, All Rights Reserved.
 * 
 * 
 ******************************************************************************
 ***********************Modification History***********************************
 *  Date   				Initials	     Version		Changes and additions
 ******************************************************************************
 * 	Apr 25, 2011		Vihan Khatri								
 *******************************************************************************
 */
package com.tcs.sgv.dcps.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.math.BigDecimal;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.helper.WorkFlowHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDao;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.dao.ChangesFormDAO;
import com.tcs.sgv.dcps.dao.ChangesFormDAOImpl;
import com.tcs.sgv.dcps.dao.DdoProfileDAO;
import com.tcs.sgv.dcps.dao.DdoProfileDAOImpl;
import com.tcs.sgv.dcps.dao.EmpSevenPCNewDaoImpl;
import com.tcs.sgv.dcps.dao.NewRegDdoDAO;
import com.tcs.sgv.dcps.dao.NewRegDdoDAOImpl;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAO;
import com.tcs.sgv.dcps.dao.SearchEmployeeDAOImpl;
import com.tcs.sgv.dcps.valueobject.HstChangePayScaleDtls;
import com.tcs.sgv.dcps.valueobject.HstDcpsOfficeChanges;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.user.dao.UserPostDAO;
import com.tcs.sgv.user.valueobject.UserPostCustomVO;

/**
 * Class Description -
 * 
 * 
 * @author Vihan Khatri
 * @version 0.1
 * @since JDK 5.0 Apr 25, 2011
 */
public class SearchEmployeeServiceImpl extends ServiceImpl implements
		SearchEmployeeService {
	ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
	/* Global Variable for Logger Class */
	private final Log gLogger = LogFactory.getLog(getClass());

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

	/* Resource bundle for the constants */
	private ResourceBundle gObjRsrcBndle = ResourceBundle
			.getBundle("resources/dcps/DCPSConstants");

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
		} catch (Exception e) {

		}

	}

	public ResultObject searchEmployee(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		List listSavedRequests = null;

		try {

			setSessionInfo(inputMap);
			new SimpleDateFormat("dd/MM/yyyy");

			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());

			Integer lIntCriteria = 5;
			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);

			/*
			 * String lStrEmpName =
			 * StringUtility.getParameter("txtEmployeeName", request); String
			 * lStrDcpsId = StringUtility.getParameter("txtEmployeeId",
			 * request); String lStrEmpDOB =
			 * StringUtility.getParameter("txtBirthDate", request);
			 * inputMap.put("lStrEmpName", lStrEmpName);
			 * inputMap.put("lStrDcpsId", lStrDcpsId);
			 * inputMap.put("lStrEmpDOB", lStrEmpDOB); Date lDateEmpDOB = null;
			 * if (lStrEmpDOB != "") { lDateEmpDOB =
			 * simpleDateFormat.parse(lStrEmpDOB); } listSavedRequests =
			 * lObjSearchEmployeeDAO.searchEmployees(lStrDcpsId, lStrEmpName,
			 * lDateEmpDOB, lStrDdoCode);
			 */

			String lStrSevarthId = StringUtility.getParameter("sevarthId",
					request).trim();
			String lStrName = StringUtility.getParameter("employeeName",
					request).trim();
			if (!"".equals(lStrSevarthId) || !"".equals(lStrName)) {
				listSavedRequests = lObjSearchEmployeeDAO.searchEmps(
						lStrSevarthId, lStrName, lStrDdoCode);
			}

			inputMap.put("lStrSevarthId", lStrSevarthId);
			inputMap.put("lStrName", lStrName);

			List UserList = getHierarchyUsers(inputMap);

			inputMap.put("UserList", UserList);
			inputMap.put("Criteria", lIntCriteria);
			inputMap.put("CaseList", listSavedRequests);

			String lStrType = StringUtility.getParameter("Type", request);
			inputMap.put("Type", lStrType);

			resObj.setViewName("DCPSCompletedForms1");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			//ex.printStackTrace();
		}

		return resObj;
	}

	public ResultObject loadSearchEmpForSRKA(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);

		try {

			setSessionInfo(inputMap);
			inputMap.put("totalRecords", 0);
			resObj.setViewName("DCPSEmpSearchSRKA");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			//ex.printStackTrace();
			gLogger.error("Error is;" + ex, ex);
		}

		return resObj;
	}

	public ResultObject searchEmpForSRKA(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		List listSavedRequests = null;
		Integer totalRecords = 0;

		try {

			setSessionInfo(inputMap);
			new SimpleDateFormat("dd/MM/yyyy");

			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			/*
			 * String lStrEmpName =
			 * StringUtility.getParameter("txtEmployeeName", request); String
			 * lStrDcpsId = StringUtility.getParameter("txtEmployeeId",
			 * request); String lStrEmpDOB =
			 * StringUtility.getParameter("txtBirthDate", request);
			 * inputMap.put("lStrEmpName", lStrEmpName);
			 * inputMap.put("lStrDcpsId", lStrDcpsId);
			 * inputMap.put("lStrEmpDOB", lStrEmpDOB);
			 * 
			 * Date lDateEmpDOB = null;
			 * 
			 * if (lStrEmpDOB != "") { lDateEmpDOB =
			 * simpleDateFormat.parse(lStrEmpDOB); }
			 * 
			 * listSavedRequests =
			 * lObjSearchEmployeeDAO.searchEmployeesForSRKA(lStrDcpsId,
			 * lStrEmpName, lDateEmpDOB);
			 */

			String lStrSevarthId = StringUtility.getParameter("sevarthId",
					request).trim();
			inputMap.put("lStrSevarthId", lStrSevarthId);

			String lStrEmpName = StringUtility.getParameter("employeeName",
					request).trim();
			inputMap.put("lStrEmpName", lStrEmpName);

			if (!"".equals(lStrSevarthId) || !"".equals(lStrEmpName)) {
				listSavedRequests = lObjSearchEmployeeDAO.searchEmpsForSRKA(
						lStrSevarthId, lStrEmpName);
			}

			if (listSavedRequests != null) {
				if (listSavedRequests.size() != 0) {
					totalRecords = listSavedRequests.size();
				}
			}

			inputMap.put("totalRecords", totalRecords);
			inputMap.put("CaseList", listSavedRequests);

			resObj.setViewName("DCPSEmpSearchSRKA");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			//ex.printStackTrace();
			gLogger.error("Error is;" + ex, ex);
		}

		return resObj;
	}

	public ResultObject getPayDetails(Map inputMap) throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS);
		Long PIPB = null;
		Long curnsvnSelect= null;
		Long GradePay = null;
		
		try {

			setSessionInfo(inputMap);

			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv
					.getSessionFactory());
			DdoProfileDAO lObjDdoProfileDAO = new DdoProfileDAOImpl(
					MstEmp.class, serv.getSessionFactory());

			String lStrEmpId = StringUtility.getParameter("EmpId", request);
			Long lLngEmpId = Long.parseLong(lStrEmpId);

			MstEmp lObjMstEmp = (MstEmp) lObjSearchEmployeeDAO.read(lLngEmpId);

			String lStrDdoCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);

			List lLstOffices = lObjDcpsCommonDAO.getCurrentOffices(lStrDdoCode);
			
			String lStrFieldDept = lObjDcpsCommonDAO.getFieldDeptOfDDO(lStrDdoCode);

			List lLstCadre = lObjDcpsCommonDAO.getCadreForDept(Long
					.parseLong(lStrFieldDept.trim()));

			List lLstDesignation = lObjDcpsCommonDAO.getDesigsForPFDAndCadre(Long.parseLong(lStrFieldDept.trim()));

			List lLstBillGroup = lObjDcpsCommonDAO.getBillGroups(lStrDdoCode);

			List listPayCommission = IFMSCommonServiceImpl.getLookupValues(
					"PayCommissionDCPS", SessionHelper.getLangId(inputMap),
					inputMap);

			String lStrCurrOff = lObjMstEmp.getCurrOff();
			Long lLngCurrOff = 0l;
			if (null != lStrCurrOff) {
				lLngCurrOff = Long.parseLong(lObjMstEmp.getCurrOff());
			}
			List lLstVacantPost = lObjDdoProfileDAO.getVacantPostsInOffice(
					lLngCurrOff, Long.valueOf(gStrLocationCode),lObjMstEmp.getDdoCode());
			inputMap.put("orgEmpId", lObjMstEmp.getOrgEmpMstId());
			inputMap.put("PostList", lLstVacantPost);
			inputMap.put("OfficeList", lLstOffices);
			inputMap.put("CadreList", lLstCadre);
			inputMap.put("DesignationList", lLstDesignation);
			inputMap.put("BillGroupList", lLstBillGroup);
			inputMap.put("listPayCommission", listPayCommission);
			inputMap.put("EmpName", lObjMstEmp.getName());
			inputMap.put("DcpsEmpId", lObjMstEmp.getDcpsEmpId());
			inputMap.put("DcpsId", lObjMstEmp.getDcpsId());
			inputMap.put("CurrOff", lObjMstEmp.getCurrOff());
			inputMap.put("CurrCadre", lObjMstEmp.getCadre());
			inputMap.put("CurrDesig", lObjMstEmp.getDesignation());
			inputMap.put("CurrBillGroup", lObjMstEmp.getBillGroupId());
			
			if(lObjMstEmp.getBasicPay() != null)
			{
				inputMap.put("CurrBasic", lObjMstEmp.getBasicPay().intValue());
			}
			else
			{
				inputMap.put("CurrBasic", lObjMstEmp.getBasicPay());
			}
			
			inputMap.put("CurrPayScale", lObjMstEmp.getPayScale());
			inputMap.put("CurrPayComm", lObjMstEmp.getPayCommission());
			

			String lStrHidDcpsId = StringUtility.getParameter("hidDcpsId",
					request);
			String lStrHidEmpName = StringUtility.getParameter("hidEmpName",
					request);
			String lStrHidBirthDate = StringUtility.getParameter(
					"hidBirthDate", request);
			inputMap.put("hidDcpsId", lStrHidDcpsId);
			inputMap.put("hidEmpName", lStrHidEmpName);
			inputMap.put("hidBirthDate", lStrHidBirthDate);

			String lStrHidSevarthId = StringUtility.getParameter(
					"hidSevarthId", request).trim();
			inputMap.put("hidSevarthId", lStrHidSevarthId);

			String lStrHidName = StringUtility.getParameter("hidName", request)
					.trim();
			inputMap.put("hidName", lStrHidName);

			Map voToServiceMap = new HashMap();
			voToServiceMap.put("commissionId", lObjMstEmp.getPayCommission());

			inputMap.put("voToServiceMap", voToServiceMap);

			resObj = serv.executeService("GetScalefromDesg", inputMap);

			List PayScaleList = (List) inputMap.get("PayScaleList");
			
			inputMap.put("PayScaleList", PayScaleList);

			List lListReasonsForSalaryChangeMst = null;
			Long lLongPaycommissionMst = Long.valueOf(lObjMstEmp.getPayCommission());
		
			
			
			if (lLongPaycommissionMst == 700005) {
				lListReasonsForSalaryChangeMst = IFMSCommonServiceImpl
						.getLookupValues("ReasonsForSalaryChangeIn5PC",
								SessionHelper.getLangId(inputMap), inputMap);
				
				String lstPaybandIdSevenPC = lObjDcpsCommonDAO.getPayBandIdSevenPC(String.valueOf(lObjMstEmp.getSevenPcLevel()));
				inputMap.put("cmbLevel", lstPaybandIdSevenPC);
				
                voToServiceMap.put("cell", lObjMstEmp.getSevenPcLevel());
                inputMap.put("voToServiceMap", voToServiceMap);
                 
                resObj = serv.executeService("GetSevenPcBasic", inputMap);
                List lsSvnPcBasic = (List) inputMap.get("SvnPcBasic");
                inputMap.put("SvnPcBasic", lsSvnPcBasic);
				
				
				Double lstSvnPcBasic = lObjMstEmp.getSevenPcBasic();
				 
				BigDecimal lstSvnPcBasic1 ;//=new BigDecimal("0");
				if(lstSvnPcBasic>0) {
					lstSvnPcBasic1 =new BigDecimal(lstSvnPcBasic);
				}else
				{
					lstSvnPcBasic1 =new BigDecimal("0");
				}
				
				
				
				inputMap.put("lstSvnPcBasic1",lstSvnPcBasic1);
				inputMap.put("sevenPcBasic",lstSvnPcBasic);
				
				Long reasonChangeId = lObjMstEmp.getReasonForPSChange();
				inputMap.put("rsnForChange",reasonChangeId);
				
				
					
			}
			if (lLongPaycommissionMst == 700016)
			{
				lListReasonsForSalaryChangeMst = IFMSCommonServiceImpl.getLookupValues("ReasonsForSalaryChangeIn6PC",
								SessionHelper.getLangId(inputMap), inputMap);
				
				PIPB = lObjMstEmp.getPayInPayBand();
			
				
				GradePay = lObjMstEmp.getGradePay();
				
				Long reasonChangeId = lObjMstEmp.getReasonForPSChange();
				inputMap.put("rsnForChange",reasonChangeId);
			}

			inputMap.put("lListReasonsForSalaryChangeMst",lListReasonsForSalaryChangeMst);
			
			inputMap.put("PIPB", PIPB);
			inputMap.put("GradePay", GradePay);
			
			Object[] lObjArrPostBGAndGR = lObjDdoProfileDAO.getPostBGAndGROrderForEmp(lLngEmpId);
			if(lObjArrPostBGAndGR[0] != null)
			{
				String lStrPostId = lObjArrPostBGAndGR[0].toString().trim();
				inputMap.put("PostId", lStrPostId.trim());
			}
			if(lObjArrPostBGAndGR[1] != null)
			{
				String lStrPostDesc = lObjArrPostBGAndGR[1].toString().trim();
				inputMap.put("PostDesc", lStrPostDesc.trim());
			}
			if(lObjArrPostBGAndGR[2] != null)
			{
				String lStrBGId =  lObjArrPostBGAndGR[2].toString().trim();
				inputMap.put("BGId", lStrBGId.trim());
			}
			if(lObjArrPostBGAndGR[3] != null)
			{
				String lStrBGDesc =  lObjArrPostBGAndGR[3].toString().trim();
				inputMap.put("BGDesc", lStrBGDesc.trim());
			}
			if(lObjArrPostBGAndGR[4] != null)
			{
				String lStrGROrderId =  lObjArrPostBGAndGR[4].toString().trim();
				inputMap.put("GROrderId", lStrGROrderId.trim());
			}
			if(lObjArrPostBGAndGR[5] != null)
			{
				String lStrGROrderDesc =  lObjArrPostBGAndGR[5].toString().trim();
				inputMap.put("GROrderDesc", lStrGROrderDesc.trim());
			}
			

			resObj.setViewName("ChangeDcpsPayDetails");
			resObj.setResultValue(inputMap);

		} catch (Exception ex) {
			gLogger.error("Error is;" + ex, ex);
			//ex.printStackTrace();
		}

		return resObj;
	}

	private List getHierarchyUsers(Map inputMap) {

		int llFromLevelId = 0;
		List UserList = new ArrayList<String>();
		try {
			setSessionInfo(inputMap);
			// Get the Subject Name
			String subjectName = gObjRsrcBndle
					.getString("DCPS.RegistrationForm");

			// Get the Hierarchy Id
			Long lLngHierRefId = WorkFlowHelper
					.getHierarchyByPostIDAndDescription(gStrPostId,
							subjectName, inputMap);

			// Get the From level Id
			llFromLevelId = WorkFlowHelper.getLevelFromPostMpg(gStrPostId,
					lLngHierRefId, inputMap);

			// Get the List of Post ID of the users at the next Level
			List rsltList = WorkFlowHelper.getUpperPost(gStrPostId,
					lLngHierRefId, llFromLevelId, inputMap);

			Object[] lObjNextPost = null;

			for (int i = 0; i < rsltList.size(); i++) {

				lObjNextPost = (Object[]) rsltList.get(i);

				if (!(lObjNextPost.equals(null))) {
					UserList.add(lObjNextPost[0].toString());
				}
			}

		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			//e.printStackTrace();
		}
		return UserList;
	}
						
	public ResultObject changePayDtlsOfEmp(Map inputMap) throws Exception {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
		Boolean lBlFlag = false;
		Long lLongHstChangePayScaleDtlsId = null;
		String lStrDdoCode = null;
		String lStrOldPayScale = null;
		Double calculateBsicPercentage=null;
		
		
		
		
		try {

			setSessionInfo(inputMap);

			NewRegDdoDAO lObjNewRegDdoDAO = new NewRegDdoDAOImpl(MstEmp.class,
					serv.getSessionFactory());
			
			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					RltDcpsPayrollEmp.class, serv.getSessionFactory());

			ChangesFormDAO lObjOfficeChangesFormDAO = new ChangesFormDAOImpl(
					HstDcpsOfficeChanges.class, serv.getSessionFactory());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");

			String lStrDcpsEmpId = StringUtility.getParameter(
					"hidDcpsEmpIdPassed", request).trim();
			String lStrBillGroup = StringUtility.getParameter("hidbillGroupId",
					request).trim();
			String lStrOffice = StringUtility
					.getParameter("cmbOffice", request).trim();
			String lStrCadre = StringUtility.getParameter("cmbCadre", request).trim();
			String lStrDesignation = StringUtility.getParameter(
					"cmbDesignation", request).trim();
			String lStrPayCommission = StringUtility.getParameter(
					"cmbPayCommission", request).trim();
			String lStrPayScale = StringUtility.getParameter("cmbPayScale",
					request).trim();
			
			
			System.out.println("Selected Payscale Level is ++++++" +lStrPayScale);
	
			String lStrBasic = StringUtility.getParameter("txtBasicPay",
					request).trim();
			
			
			 
			
			System.out.println("lStrBasic of basic :::"+lStrBasic);
			
			String lstPercentage = StringUtility.getParameter("selperesentage",
					request).trim();
			System.out.println("percentage of basic :::"+lstPercentage);
			
			if(lstPercentage!=null && lstPercentage!="" ){
			 calculateBsicPercentage = ((Double.parseDouble(lStrBasic)) * (Double.parseDouble(lstPercentage)))/100;
			System.out.println("calculateBsicPercentage::->"+calculateBsicPercentage);
			}
			String lstselmedical = StringUtility.getParameter("selmedical",
					request).trim();
			System.out.println("lstselmedical :::"+lstselmedical);
			
			
			String lsttravell = StringUtility.getParameter("seltravel",
					request).trim();
			System.out.println("lsttravell :::"+lsttravell);
			
			String lStrReasonForSalChange = StringUtility.getParameter(
					"cmbReasonForSalChange", request).trim();
			String lStrOtherReason = StringUtility.getParameter(
					"txtOtherReason", request).trim();
			String lStrWithEffectFromDate = StringUtility.getParameter(
					"txtWithEffectFromDate", request).trim();
			
			String lStrOldPost = StringUtility.getParameter("hidOldPost", request).trim();
			String lStrNewPost = StringUtility.getParameter("cmbPost", request).trim();
			String lStrNewBillGroup = StringUtility.getParameter("cmbBillGroup", request).trim();
			Long lLongBillGroup = null;
			Long lLngNewPost = 0l;
			if (!lStrNewPost.equals("")) {
				lLngNewPost = Long.parseLong(lStrNewPost);
			}

			MstEmp MstEmpObj = (MstEmp) lObjNewRegDdoDAO.read(Long.valueOf(lStrDcpsEmpId));
			
			lStrDdoCode = MstEmpObj.getDdoCode();
			lStrOldPayScale = MstEmpObj.getPayScale();
			
			if (lStrDesignation != null && !"".equals(lStrDesignation)) {
				MstEmpObj.setDesignation(lStrDesignation);
			}
			if (lLngNewPost != -1) {
				
				if(!lStrOldPost.equals(lStrNewPost))
				{
					lObjSearchEmployeeDAO.UpdateNewPost(Long.valueOf(lStrDcpsEmpId), lLngNewPost);
	
					String lStrDsgnId = lObjSearchEmployeeDAO
							.getDesigFromPost(lLngNewPost);
	
					MstEmpObj.setDesignation(lStrDsgnId.trim());
				}
			}
			
			// Changes bill group when any bill group is not associated with post and new bill group is selected
			if(!lStrNewBillGroup.equals("") && lStrNewBillGroup!=null && !"-1".equals(lStrNewBillGroup))
			{
				if(!lStrOldPost.equals(lStrNewPost))
				{
					lLongBillGroup = Long.valueOf(lStrNewBillGroup);
					MstEmpObj.setBillGroupId(lLongBillGroup);
				}
			}
			
			// Changes bill group when some bill group is associated with a post
			if (lStrBillGroup != null && !"".equals(lStrBillGroup)) {
				if(!lStrOldPost.equals(lStrNewPost))
				{
					MstEmpObj.setBillGroupId(Long.parseLong(lStrBillGroup));
				}
			}

			if (lStrOffice != null && !"".equals(lStrOffice)) {
				MstEmpObj.setCurrOff(lStrOffice);
			}
			if (lStrCadre != null && !"".equals(lStrCadre) && !"-1".equals(lStrCadre)) {
				MstEmpObj.setCadre(lStrCadre);
			}
			
			// Changes pay scale only for 6th and 5th Pay commission

			if (lStrPayCommission != null && !"".equals(lStrPayCommission)) 
			{
					MstEmpObj.setPayCommission(lStrPayCommission);
				
			}
			
			
			
			List levelList = null;
			String levelId = "";
			String gradePay = "";
			EmpSevenPCNewDaoImpl pcNewDaoImpl = new EmpSevenPCNewDaoImpl(HrPayPaybill.class,serv.getSessionFactory());
			if (lStrPayCommission.equals(700005l))
		      {
			
			List lListReasonsForSalaryChangeMst = IFMSCommonServiceImpl.getLookupValues("ReasonsForSalaryChangeIn6PC",
								SessionHelper.getLangId(inputMap), inputMap);
				MstEmp lObjMstEmp=null;
				Object curnsvnSelect = lObjMstEmp.getSevenPcBasic();
			
		      }
		      else if (lStrPayScale != null && !"".equals(lStrPayScale) && !"-1".equals(lStrPayScale)) 
		      {
		    	  if(!lStrPayCommission.equals("700005"))
		    	  {
		    		  MstEmpObj.setPayScale(lStrPayScale);
		    	  }
		    	  else if (lStrPayCommission.equals("700005"))
			      {
		    		  
		    		 levelList = pcNewDaoImpl.getLevelId(lStrPayScale);
		 			 if (levelList != null && levelList.size() > 0)
		   				{
		   				Iterator IT = levelList.iterator();
		   				Object[] lObj = null;
		   				while (IT.hasNext())
		   				{					
		   					lObj = (Object[]) IT.next();
		   					levelId =lObj[0].toString();
		   					gradePay= lObj[1].toString();
		   				}
		   			}
			    	  MstEmpObj.setSevenPcLevel(Double.valueOf(levelId));
			      }
			   }
		      
			
		      if (lStrPayCommission.equals("700005"))
		      {
		    	 Object lLongPKValue = null;
		    	 GenericDao lObjNewRegDdoDAO1 = null;
				
		    	    String lStrcurnsvnSelect = StringUtility.getParameter("txtBasicPay", request).trim();
					if(!"".equals(lStrcurnsvnSelect) && !"0".equals(lStrcurnsvnSelect))
					{
						MstEmpObj.setSevenPcBasic(Double.valueOf(lStrcurnsvnSelect));
						  MstEmp lObjDcpsEmpMstDtls = null;
					}
				
		      }
		      else if (lStrBasic != null && !"".equals(lStrBasic)) {
		    	  MstEmpObj.setBasicPay(Double.parseDouble(lStrBasic));
		    
			}
			
			if (lstselmedical != null && !"".equals(lstselmedical)) {
				MstEmpObj.setMedicalAllow(Long.parseLong(lstselmedical));
				}
			
			if (lsttravell != null && !"".equals(lsttravell)) {
				MstEmpObj.setTravelAllow(Long.parseLong(lsttravell));
				}
			
			NewRegDdoDAO objnewRegDdo = new NewRegDdoDAOImpl(HrEisOtherDtls.class, serv.getSessionFactory());
			SearchEmployeeDAO searchEmployeeDAO = new SearchEmployeeDAOImpl(SearchEmployeeDAOImpl.class, serv.getSessionFactory());
			Long otherId=searchEmployeeDAO.getOtherId(MstEmpObj.getOrgEmpMstId());
			
			if(lStrPayCommission.equals("700016"))
			{
				String lStrPIPB = StringUtility.getParameter("txtPayInPayBand", request).trim();
				String lStrGradePay = StringUtility.getParameter("txtGradePay", request).trim();
				if(!"".equals(lStrPIPB) && !"0".equals(lStrPIPB))
				{
					MstEmpObj.setPayInPayBand(Long.valueOf(lStrPIPB));
				}
				if(!"".equals(lStrGradePay) && !"0".equals(lStrGradePay))
				{
					MstEmpObj.setGradePay(Long.valueOf(lStrGradePay));
				}
				
				MstEmpObj.setSevenPcBasic(Double.valueOf(0));
				MstEmpObj.setSevenPcLevel(null);				
				lObjSearchEmployeeDAO.updateOtherCurrentSVNPC(otherId);
			}
			
			if (lStrReasonForSalChange != null
					&& !"".equals(lStrReasonForSalChange)
					&& !"-1".equals(lStrReasonForSalChange)) {
				MstEmpObj.setReasonForPSChange(Long
						.valueOf(lStrReasonForSalChange));
			}
			if (lStrOtherReason != null && !"".equals(lStrOtherReason)) {
				MstEmpObj.setOtherReasonForPSChange(lStrOtherReason);
			}

			Date lDateWithEffectFromDate = null;
			if (!"".equals(lStrWithEffectFromDate)) {
				lDateWithEffectFromDate = simpleDateFormat
						.parse(lStrWithEffectFromDate);
			}

			if (lDateWithEffectFromDate != null
					&& !(lDateWithEffectFromDate.equals(""))) {
				MstEmpObj.setWithEffectFromDate(lDateWithEffectFromDate);
			}
			
			lObjNewRegDdoDAO.update(MstEmpObj);
			
			Double sevenPcBasic1 = MstEmpObj.getSevenPcBasic();
			
			HrEisOtherDtls hrEisOtherDtls= (HrEisOtherDtls) objnewRegDdo.read(otherId);
			
			
			if(MstEmpObj.getPayCommission().equals("700005"))
			{	
				lObjSearchEmployeeDAO.updateOtherCurrentSevenBasic(otherId ,sevenPcBasic1);
			}
			
			objnewRegDdo.update(hrEisOtherDtls);
		

			// Code to Send Variables to Payroll

			String lStrAuthorityLetterNo = StringUtility.getParameter(
					"txtAuthorityLetterNo", request).trim();
			String lStrAuthorityLetterDate = StringUtility.getParameter("txtAuthorityLetterDate", request).trim();
			
			Date lDateAuthorityLetterDate = null;
			if (!"".equals(lStrAuthorityLetterDate)) {
				lDateAuthorityLetterDate = simpleDateFormat
						.parse(lStrAuthorityLetterDate);
			}
			
			String lStrReasonForPayScaleChange = StringUtility.getParameter(
					"cmbReasonForSalChange", request);
			String lStrOtherReasonForPayScaleChange = StringUtility
					.getParameter("txtOtherReason", request);

			String groupId = null;

			if (!"".equals(lStrCadre)) {
				groupId = lObjOfficeChangesFormDAO.getGroupIdForCadreId(Long
						.valueOf(lStrCadre));
			}
			
			
			
			// Code added to save Authority details in hst_change_payscale_dtls table
			
			lLongHstChangePayScaleDtlsId = IFMSCommonServiceImpl.getNextSeqNum("hst_change_payscale_dtls", inputMap);
			HstChangePayScaleDtls lObjHstChangePayScaleDtls = new HstChangePayScaleDtls();
			lObjHstChangePayScaleDtls.setChangePayScaleDtlsId(lLongHstChangePayScaleDtlsId);
			
			lObjHstChangePayScaleDtls.setDcpsEmpId(Long.valueOf(lStrDcpsEmpId));
			lObjHstChangePayScaleDtls.setDdoCode(lStrDdoCode);
			
			lObjHstChangePayScaleDtls.setOldPS(lStrOldPayScale);
			lObjHstChangePayScaleDtls.setNewPS(lStrPayScale);
			
			Long lLongOldPost = -1l;
			if(!"".equals(lStrOldPost))
			{
				lLongOldPost = Long.valueOf(lStrOldPost);
			}
			
			lObjHstChangePayScaleDtls.setOldPost(lLongOldPost);
			lObjHstChangePayScaleDtls.setNewPost(lLngNewPost);
			
			lObjHstChangePayScaleDtls.setAuthorityLetterNo(lStrAuthorityLetterNo);
			lObjHstChangePayScaleDtls.setAuthorityLetterDate(lDateAuthorityLetterDate);
			
			if (lStrReasonForSalChange != null
					&& !"".equals(lStrReasonForSalChange)
					&& !"-1".equals(lStrReasonForSalChange))
			{
				lObjHstChangePayScaleDtls.setReasonForPSChange(Long.valueOf(lStrReasonForSalChange));
			}
			if (lDateWithEffectFromDate != null
					&& !(lDateWithEffectFromDate.equals(""))) 
			{
				lObjHstChangePayScaleDtls.setWithEffectFromDate(lDateWithEffectFromDate);
			}
			
			if (lStrOtherReason != null && !"".equals(lStrOtherReason))
			{
				lObjHstChangePayScaleDtls.setOtherReasonForPSChange(lStrOtherReason);
			}
			
			lObjHstChangePayScaleDtls.setLangId(gLngLangId);
			lObjHstChangePayScaleDtls.setLocId(Long.valueOf(gStrLocationCode));
			lObjHstChangePayScaleDtls.setDbId(gLngDBId);
			lObjHstChangePayScaleDtls.setCreatedDate(gDtCurDate);
			lObjHstChangePayScaleDtls.setCreatedPostId(gLngPostId);
			lObjHstChangePayScaleDtls.setCreatedUserId(gLngUserId);
			
			lObjSearchEmployeeDAO.create(lObjHstChangePayScaleDtls);
			
			// Overs Code added to save Authority details in hst_change_payscale_dtls table
			
			inputMap.put("orgEmpId", MstEmpObj.getOrgEmpMstId());
			inputMap.put("empId", Long.valueOf(lStrDcpsEmpId));
			inputMap.put("dsgnId", lStrDesignation);
			inputMap.put("scaleId", lStrPayScale);
			inputMap.put("groupId", groupId);
			inputMap.put("orderId", lStrAuthorityLetterNo.trim());
			inputMap.put("reasonForScaleChange", Long
					.valueOf(lStrReasonForPayScaleChange));
			inputMap.put("reasonForScaleChangeOther",
					lStrOtherReasonForPayScaleChange);
			inputMap.put("commissionId", lStrPayCommission);
			inputMap.put("WEFDate", lDateWithEffectFromDate);
			inputMap.put("FromDDO", "NO");
			inputMap.put("basic", lStrBasic);
			inputMap.put("NewPost", lLngNewPost);
			inputMap.put("NewBillGroup", lLongBillGroup);
			inputMap.put("DateWithEffectFromDate", lDateWithEffectFromDate);
			
		
			try 
			{
				if (lStrPayScale != null) {
					ResultObject resObj = serv.executeService("scaleChangeService",inputMap);

					if (resObj.getResultCode() == ErrorConstants.ERROR) {
						throw new Exception();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		

			lBlFlag = true;

			String lSBStatus = getResponseXMLDoc(lBlFlag).toString();
			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",lSBStatus).toString();

			inputMap.put("ajaxKey", lStrResult);
			objRes.setViewName("ajaxData");
			objRes.setResultValue(inputMap);

		} catch (Exception e) {
			gLogger.error("Error is;" + e, e);
			objRes.setResultValue(null);
			objRes.setThrowable(e);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
		}
		return objRes;
	}

	public ResultObject getEmpNameForAutoCompleteDCPS(
			Map<String, Object> inputMap) {

		ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		List finalList = null;
		String lStrEmpName = null;
		String lStrSearchBy = null;
		String lStrDDOCode = null;
		String lStrSearchType = null;

		try {
			setSessionInfo(inputMap);
			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(MstEmp.class, serv.getSessionFactory());
			DcpsCommonDAO lObjDcpsCommonDAO = new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			lStrEmpName = StringUtility.getParameter("searchKey", request).trim();

			lStrSearchBy = StringUtility.getParameter("searchBy", request).trim();

			lStrSearchType = StringUtility.getParameter("searchType", request);

			if (lStrSearchBy.equals("searchByDDOAsst")) 
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCode(gLngPostId);
			}
			if (lStrSearchBy.equals("searchFromDDODeSelection")|| lStrSearchBy.equals("searchByDDO")) 
			{
				lStrDDOCode = lObjDcpsCommonDAO.getDdoCodeForDDO(gLngPostId);
			}
			if (lStrSearchBy.equals("searchBySRKA")) 
			{
				lStrDDOCode = null;
			}

			finalList = lObjSearchEmployeeDAO.getEmpNameForAutoComplete(lStrEmpName.toUpperCase(), lStrSearchType, lStrDDOCode,lStrSearchBy);

			gLogger.info("in seach emoployee,  finalList.size(): "+finalList.size());
			
			String lStrTempResult = null;
			if (finalList != null) 
			{
				lStrTempResult = new AjaxXmlBuilder().addItems(finalList,"desc", "id", true).toString();
			}
			inputMap.put("ajaxKey", lStrTempResult);
			objRes.setResultValue(inputMap);
			objRes.setViewName("ajaxData");

		} catch (Exception ex) 
		{
			objRes.setResultValue(null);
			objRes.setThrowable(ex);
			objRes.setResultCode(ErrorConstants.ERROR);
			objRes.setViewName("errorPage");
			//ex.printStackTrace();
			return objRes;
		}

		return objRes;

	}

	public ResultObject getBGAndGRForPost(Map<String, Object> inputMap)
			throws Exception {

		ResultObject resObj = new ResultObject(ErrorConstants.SUCCESS, "FAIL");

		try {
			/* Sets the Session Information */
			setSessionInfo(inputMap);

			new DcpsCommonDAOImpl(null, serv.getSessionFactory());

			SearchEmployeeDAO lObjSearchEmployeeDAO = new SearchEmployeeDAOImpl(
					MstEmp.class, serv.getSessionFactory());

			/* Gets the Bill Group Id from request */
			Long postId = Long.valueOf(StringUtility.getParameter("postId",
					request));

			/* Gets the Scheme Code and Scheme Name */
			Object[] billGroupIdAndDesc = lObjSearchEmployeeDAO
					.getBGForPost(postId);
			String billGroupId = "";
			String billGroupDesc = "";
			if (billGroupIdAndDesc[0] != null) {
				billGroupId = billGroupIdAndDesc[0].toString();
			}
			if (billGroupIdAndDesc[1] != null) {
				billGroupDesc = billGroupIdAndDesc[1].toString();
			}

			Object[] GROrderNoAndName = lObjSearchEmployeeDAO
					.getGRForPost(postId);
			String orderId = "";
			String orderName = "";
			if (GROrderNoAndName[0] != null) {
				orderId = GROrderNoAndName[0].toString();
			}
			if (GROrderNoAndName[1] != null) {
				orderName = GROrderNoAndName[1].toString();
			}

			/* Sends the Scheme Name and Scheme Code using AJAX. */
			String lSBScheme = getResponseXMLDocForBGAndGRForPost(billGroupId,
					billGroupDesc, orderId, orderName).toString();

			String lStrResult = new AjaxXmlBuilder().addItem("ajax_key",
					lSBScheme).toString();

			inputMap.put("ajaxKey", lStrResult);
			resObj.setResultValue(inputMap);
			resObj.setViewName("ajaxData");

		} catch (Exception e) {

			gLogger.error(" Error is : " + e, e);
			//e.printStackTrace();
			resObj.setResultValue(null);
			resObj.setThrowable(e);
			resObj.setResultCode(ErrorConstants.ERROR);
			resObj.setViewName("errorPage");

		}

		return resObj;
	}

	private StringBuilder getResponseXMLDocForBGAndGRForPost(
			String billGroupId, String billGroupDesc, String orderId,
			String orderName) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");

		lStrBldXML.append("<billGroupId>");
		lStrBldXML.append(billGroupId.trim());
		lStrBldXML.append("</billGroupId>");
		
		lStrBldXML.append("<billGroupDesc>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(billGroupDesc.trim());
		lStrBldXML.append("]]>");
		lStrBldXML.append("</billGroupDesc>");
		
		lStrBldXML.append("<orderId>");
		lStrBldXML.append(orderId.trim());
		lStrBldXML.append("</orderId>");
		
		lStrBldXML.append("<orderName>");
		lStrBldXML.append("<![CDATA[");
		lStrBldXML.append(orderName.trim());
		lStrBldXML.append("]]>");
		lStrBldXML.append("</orderName>");

		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

	private StringBuilder getResponseXMLDoc(Boolean flag) {

		StringBuilder lStrBldXML = new StringBuilder();

		lStrBldXML.append("<XMLDOC>");
		lStrBldXML.append("<Flag>");
		lStrBldXML.append(flag);
		lStrBldXML.append("</Flag>");
		lStrBldXML.append("</XMLDOC>");

		return lStrBldXML;
	}

}
