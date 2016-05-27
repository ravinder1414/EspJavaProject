package netthandelen_Listeners;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.SkipException;

import commonfunctions.ExcelReader;
import netthandelen_Variables.EnvironmentVariables;

public class DisableKnownTestFailures implements IInvokedMethodListener{
	
	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod objMethod, ITestResult arg1) {
		// TODO Auto-generated method stub
		int iterationRowCount, iRowCount;
		int iColClass = 0;
		int iColMethod = 1;
		int iColReason = 2;
		int iColDefect = 3;
		String sMethodClass, sMethodName, sReason, sDefectID;
		HSSFWorkbook wbDisableKnownTest = ExcelReader.InItWorkbook(".//srm_TestData//DisableKnownTest.xls");
		HSSFSheet wsDisableTest = wbDisableKnownTest.getSheet(EnvironmentVariables.sEnv.toUpperCase());		
		iRowCount = ExcelReader.GetRowCount(wsDisableTest, 0);
		for(iterationRowCount=1; iterationRowCount < iRowCount;iterationRowCount++)
		{
			
			sMethodClass = ExcelReader.ReadCellValue(wsDisableTest, iterationRowCount, iColClass);
			sMethodName = ExcelReader.ReadCellValue(wsDisableTest, iterationRowCount, iColMethod);
			sReason = ExcelReader.ReadCellValue(wsDisableTest, iterationRowCount, iColReason);
			sDefectID = ExcelReader.ReadCellValue(wsDisableTest, iterationRowCount, iColDefect);			
			if(objMethod.getTestMethod().getRealClass().getCanonicalName().trim().equalsIgnoreCase(sMethodClass))
			{
				if(objMethod.getTestMethod().getMethodName().trim().equalsIgnoreCase(sMethodName))
				{
					
					throw new SkipException(sReason + " DefectID: - " + sDefectID);
				}
			}
		}		
	}


}
