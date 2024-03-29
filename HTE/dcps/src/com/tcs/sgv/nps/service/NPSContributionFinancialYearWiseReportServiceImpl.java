package com.tcs.sgv.nps.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.acl.login.valueobject.LoginDetails;
import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.nps.dao.NPSContributionFinancialYearWiseReportDAOImpl;
import com.tcs.sgv.nps.dao.NpsDetailsAndStatusDaoImpl;

public class NPSContributionFinancialYearWiseReportServiceImpl extends DefaultReportDataFinder implements ReportDataFinder {
	
	private static final Logger gLogger = Logger.getLogger("PensionpayReports");
	private NPSContributionFinancialYearWiseReportDAOImpl gObjRptQueryDAO = null;
	ServiceLocator serviceLocator = null;
	SessionFactory gObjSessionFactory = null;
	String gStrLocCode = null;
	String gStrDdoCode = null;
	String lStrForMonth = null;
	String lStrForYear = null;
	Integer lIntMonthYear = null;
	Long gLngLangId = null;
	Long gPostId=null;
	Map lMapSeriesHeadCode = null;
	Session ghibSession = null;
	
	private ResourceBundle gObjRsrcBndle = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");

	private ResourceBundle bundleConst = ResourceBundle.getBundle(("resources/pensionpay/PensionConstants"));

	public Collection findReportData(ReportVO lObjReport, Object criteria) throws ReportException {
		
		List lLstDataList = new ArrayList();
		Map lMapRequestAttributes = null;
		Map lMapSessionAttributes = null;
		LoginDetails lObjLoginVO = null;
		try {
			lMapRequestAttributes = (Map) ((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
			lMapSessionAttributes = (Map) ((Map) criteria).get(IReportConstants.SESSION_KEYS);
			lObjLoginVO = (LoginDetails) lMapSessionAttributes.get("loginDetails");
			lStrForMonth = (String) lObjReport.getParameterValue("forMonth");
			lStrForYear = (String) lObjReport.getParameterValue("forYear");
			serviceLocator = (ServiceLocator) lMapRequestAttributes.get("serviceLocator");
			gObjSessionFactory = serviceLocator.getSessionFactorySlave();
			gStrLocCode = lObjLoginVO.getLocation().getLocationCode();
			//gStrDdoCode= lObjLoginVO.getDdo().getDdoCode();
			gLngLangId = lObjLoginVO.getLangId();
			gPostId=lObjLoginVO.getLoggedInPost().getPostId();
			ghibSession = gObjSessionFactory.getCurrentSession();
			gObjRptQueryDAO = new NPSContributionFinancialYearWiseReportDAOImpl(null, gObjSessionFactory);

			gObjRptQueryDAO.setReportHeaderAndFooter(lObjReport, lObjLoginVO);
			
			String lStrMonthYear = null;
			if (Integer.parseInt(lStrForMonth) < 10) {
				lStrMonthYear = lStrForYear + "0" + lStrForMonth;
			} else {
				lStrMonthYear = lStrForYear + lStrForMonth;
			}
			if (lStrMonthYear != null) {
				lIntMonthYear = Integer.valueOf(lStrMonthYear);
			}
			
			String ddocode = (String) lObjReport.getParameterValue("ddocode");
			String level2ddocode = (String) lObjReport.getParameterValue("ddocode");
			
			if (lObjReport.getReportCode().equals("80000949")) {
				lLstDataList = getNPSContributionFinancialYearWiseReport(lObjReport, gStrLocCode,ddocode);
			}
			
			
			
		}
		catch (Exception e) {
			gLogger.info("findReportData(): Exception is" + e, e);
		}
		return lLstDataList;
	}

	
	private List getNPSContributionFinancialYearWiseReport(ReportVO lObjReport, String lStrLocCode,
			String ddocode) {
		System.out.println("Hiiii i am here");	
		List lArrReturn = new ArrayList();
		
		String forYear = "";
		String forMonth = "";
		
		NPSContributionFinancialYearWiseReportDAOImpl gObjRptQueryDAO = new NPSContributionFinancialYearWiseReportDAOImpl(null, gObjSessionFactory);
		
		try {
			  			
			forYear = lObjReport.getParameterValue("forYear").toString();
			forMonth = lObjReport.getParameterValue("forMonth").toString();
			gLogger.info("forYear*************"+forYear);
			gLogger.info("forMonth*************"+forMonth);
			int month=0;
			
			if(Integer.parseInt(forMonth)>0 && Integer.parseInt(forMonth)<10){
				month=Integer.parseInt(forMonth)+3;
			}
			
			if(Integer.parseInt(forMonth)>= 10 || Integer.parseInt(forMonth)<=12){
				month=Integer.parseInt(forMonth);
			}
			gLogger.info("forMonth*****222********"+month);
			//String header1 = "<p style=\"text-align:center;\"><b> NPS Contribution Financial Year Wise Report</b> </p>";
			//lObjReport.setAdditionalHeader(header1);
			lArrReturn=gObjRptQueryDAO.getNPSContributionFinancialYearWiseReport(lStrLocCode, ddocode,forYear,forMonth);
		}
		
		catch (Exception e) {
			gLogger.error("Error is :" + e);
		}
		return lArrReturn;

		
	}


	public List getDDO(String lStrLangId, String lStrLocCode) {
		List<Object> lArrgetDDo = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator()
			.getSessionFactory().getCurrentSession();

			String lStrBufLang = "SELECT map.REPT_DDO_CODE,ddo.DDO_NAME|| ' ' || ddo.ddo_code FROM ORG_DDO_MST ddo inner join RLT_ZP_DDO_MAP map on ddo.DDO_CODE = map.ZP_DDO_CODE where DDO_NAME is not null order by map.REPT_DDO_CODE";

			Query lObjQuery = lObjSession.createSQLQuery(lStrBufLang);
			

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrgetDDo.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return lArrgetDDo;
	}
	
	public List getLevel2DdoCode(String lStrLangId, String lStrLocCode) {
		List<Object> lArrgetDDo = new ArrayList<Object>();
		try {
			Session lObjSession = ServiceLocator.getServiceLocator()
			.getSessionFactory().getCurrentSession();

			String lStrBufLang = "SELECT distinct(map.REPT_DDO_CODE),ddo.DDO_NAME || ' ' || ddo.ddo_code FROM RLT_ZP_DDO_MAP map inner join ORG_DDO_MST ddo on map.REPT_DDO_CODE = ddo.DDO_CODE";

			Query lObjQuery = lObjSession.createSQLQuery(lStrBufLang);

			List lLstResult = lObjQuery.list();
			ComboValuesVO lObjComboValuesVO = null;
			Object[] lArrData = null;

			if (lLstResult != null && !lLstResult.isEmpty()) {
				for (int lIntCtr = 0; lIntCtr < lLstResult.size(); lIntCtr++) {
					lObjComboValuesVO = new ComboValuesVO();
					lArrData = (Object[]) lLstResult.get(lIntCtr);
					lObjComboValuesVO.setId(lArrData[0].toString());
					lObjComboValuesVO.setDesc(lArrData[1].toString());
					lArrgetDDo.add(lObjComboValuesVO);
				}
			}
		} catch (Exception e) {
			gLogger.error("Error is : " + e, e);
		}

		return lArrgetDDo;
	}		
}

