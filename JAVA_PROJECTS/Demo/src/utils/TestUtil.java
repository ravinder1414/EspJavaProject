package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;



public class TestUtil {
		static Logger log = Logger.getLogger(TestUtil.class);
		
		//static Logger log = Logger.getLogger(TestUtil.class);
		
		// true - Y
		// false - N
		public static boolean isTestCaseExecutable(String testCase, Xls_Reader xls){
			
			for(int rNum=2;rNum<=xls.getRowCount("Test Cases");rNum++){
				if(testCase.equals(xls.getCellData("Test Cases", "TCID", rNum))){
					// check runmode
					if(xls.getCellData("Test Cases", "Runmode", rNum).equals("Y"))
						return true;
					else
						return false;
				}
					
			}
			
			return false;
			
		}
		
		
		
		public static Object[][] getData(String testCase,Xls_Reader xls){
			System.out.println("****** "+ testCase +" *******");
			// find the test in xls
			// find number of cols in test
			// number of rows in test
			// put the data in hashtable and put hashtable in object array
			// return object array
			
			int testCaseStartRowNum=0;
			for(int rNum=1;rNum<=xls.getRowCount("Test Data");rNum++){
				if(testCase.equals(xls.getCellData("Test Data", 0, rNum))){
					testCaseStartRowNum = rNum;
					break;
				}
			}
			System.out.println("Test Starts from row -> "+ testCaseStartRowNum);
			log.info("Test Starts from row -> "+ testCaseStartRowNum);
			
			// total cols
			int colStartRowNum=testCaseStartRowNum+1;
			int cols=0;
			while(!xls.getCellData("Test Data", cols, colStartRowNum).equals("")){
				cols++;
			}
			System.out.println("Total cols in test -> "+ cols);
			log.info("Total rows in test ->  "+ cols);

			// rows
			int rowStartRowNum=testCaseStartRowNum+2;
			int rows=0;
			while(!xls.getCellData("Test Data", 0, (rowStartRowNum+rows)).equals("")){
				rows++;
			}
			System.out.println("Total rows in test -> "+ rows);
			log.info("Total rows in test ->  "+ rows);
			Object[][] data = new Object[rows][1];
			Hashtable<String,String> table=null;
			
			// print the test data
			for(int rNum=rowStartRowNum;rNum<(rows+rowStartRowNum);rNum++){
			table=new Hashtable<String,String>();
				for(int cNum=0;cNum<cols;cNum++){
					table.put(xls.getCellData("Test Data", cNum, colStartRowNum),xls.getCellData("Test Data", cNum, rNum));
					//System.out.print(xls.getCellData("Test Data", cNum, rNum)+" - ");
				}
				data[rNum-rowStartRowNum][0]=table;
				//System.out.println();
			}
		
			return data;// dummy
		}
		
		
		// returns current date and time
				public static String now(String dateFormat) {
				    Calendar cal = Calendar.getInstance();
				    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				    return sdf.format(cal.getTime());

				}
		
		
		
		// make zip of reports
				public static void zip(String filepath){
				 	try
				 	{
				 		File inFolder=new File(filepath);
				 		File outFolder=new File("Reports.zip");
				 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
				 		BufferedInputStream in = null;
				 		byte[] data  = new byte[1000];
				 		String files[] = inFolder.list();
				 		for (int i=0; i<files.length; i++)
				 		{
				 			in = new BufferedInputStream(new FileInputStream
				 			(inFolder.getPath() + "/" + files[i]), 1000);  
				 			out.putNextEntry(new ZipEntry(files[i])); 
				 			int count;
				 			while((count = in.read(data,0,1000)) != -1)
				 			{
				 				out.write(data, 0, count);
				 			}
				 			out.closeEntry();
			  }
			  out.flush();
			  out.close();
				 	}
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  } 
			  
				 	}



				
		}
		
		// store screenshots

