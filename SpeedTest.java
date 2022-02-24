package ibmarketing.automatedqa.website.validation.Speed;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestName;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class SpeedTest {
	WebDriver driver = null;
	static ExtentTest test;
	static ExtentReports report;





	@Rule
	public ErrorCollector collector = new ErrorCollector();
	public static TestName name = new TestName();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\daigg\\Desktop\\selenium\\chromedriver_win32_90\\chromedriver.exe");
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		// String nameofCurrMethod = name.getMethodName();
		//test = report.startTest(name.getMethodName());
	}


	@Before
	public void setUp() throws Exception {
		System.out.println("--------Starting Test and updateing XLS Date Column--------");



	}

	@After
	public void tearDown() throws Exception {
		System.out.println("--------Test Finished--------");
		driver.quit();
	}

	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
	}


	public int TestSpeed(String page) {
		WebDriver driver = null;

		System.out.println("--------fetching Next Page--------");
		String homePage = page;
		//   String url = "";
		//  HttpURLConnection huc = null;
		//   int respCode = 200;
		//   int linkNum = 0;

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);

		long start = System.currentTimeMillis();

		driver.get(homePage);

		long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		//  long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);
		int totalTimeInt = (int)totalTime;
		System.out.println("Total Time for page load - "+totalTimeInt); 

		if(totalTimeInt > 3000){
			System.out.println("The time to load " + homePage + " is " + totalTimeInt + " Miliseconds");
			collector.addError(new Throwable("The time to load " + homePage + " is " + totalTimeInt + " Miliseconds"));
			test.log(LogStatus.FAIL, "The time to load " + homePage + " is " + totalTimeInt + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The time to load " + homePage + " is " + totalTimeInt + " Miliseconds");
			test.log(LogStatus.PASS, "The time to load " + homePage + " is " + totalTimeInt + " Miliseconds");
		}
		driver.quit();
		return totalTimeInt;




	}

	public int AverageTime(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver();
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("en/index.php?f=")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public int AverageTimeMerril(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("https://www.merrilledge.com")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public int AverageTimeEtrade(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("us.etrade.com")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains("webchatrequest")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}


	public int AverageTimeFidelity(String page) {


		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver();
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("www.fidelity.com")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains("chat.fidelity.com")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains(".pdf")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public int AverageTimeSchwab(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("https://www.schwab.com")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains("webchatrequest")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains(".pdf")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public int AverageTimeRobinhood(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");



			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("robinhood")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains("webchatrequest")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains(".pdf")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public int AverageTimeTDAmeritrade(String page) {


		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
		int linkNum = 0;
		String url = "";



		driver.get(page);
		System.out.println("--------Collecting links--------");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		linkNum = links.size();
		System.out.println("There are " + linkNum + " Links present");
		Iterator<WebElement> it = links.iterator();
		int Denom = 0;
		int totalTime = 0;
		int i = 0;
		while(it.hasNext() && i < 50){

			url = it.next().getAttribute("href");

			System.out.println(url);

			if(url == null || url.isEmpty()){
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			if(!url.contains("https://www.tdameritrade.com/")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}

			if(url.contains("webchatrequest")){
				System.out.println("URL belongs to another domain, skipping it.");
				continue;
			}


			int A = TestSpeed(url);

			i++;
			System.out.println(i);
			Denom++;
			totalTime = totalTime + A;



		}
		System.out.println("Denominator = " + Denom );
		int EntityAvg = totalTime / Denom;

		return EntityAvg;
	}

	public void WriteToXLS(int rowNum, int time) throws IOException {

		//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		//get current date time with Date()
		// Date date = new Date();

		// Now format the date
		// String date1= dateFormat.format(date);

		FileInputStream fsIP= new FileInputStream(new File("C:\\Users\\daigg\\OneDrive\\Documents\\IBKR WFH\\WebsiteSpeed.xlsx")); //Read the spreadsheet that needs to be updated
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);      
		XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		Cell cell = null; // declare a Cell object

		XSSFRow setRow = worksheet.getRow(rowNum);
		int colCount2 = setRow.getLastCellNum();
		System.out.println("The number of columns in the row is "+ colCount2);
		cell = worksheet.getRow(rowNum).createCell(colCount2);   // Access the second cell in second row to update the value

		cell = worksheet.getRow(rowNum).getCell(colCount2);

		cell.setCellValue(time);  // Get current cell value value and overwrite the value

		fsIP.close(); //Close the InputStream

		FileOutputStream output_file =new FileOutputStream(new File("C:\\Users\\daigg\\OneDrive\\Documents\\IBKR WFH\\WebsiteSpeed.xlsx"));  //Open FileOutputStream to write updates

		wb.write(output_file); //write changes

		output_file.close();  //close the stream  
		System.out.println("Excel Chart has been Updated");


		/* 
	//Set new column to todays date in top row
	sheet = workbook.getSheet("WebsiteSpeedGraph");
    row = sheet.getRow(1);
    int colCount = row.getLastCellNum() + 1;
    cell = row.createCell(colCount);
    cell.setCellValue(date1);

    //Set newest column for result to value from test
    XSSFRow setRow = sheet.getRow(rowNum);
    int colCount2 = setRow.getLastCellNum() + 1;
    cell = setRow.createCell(colCount2);
    cell.setCellValue(time);

    workbook.write(fos);
    workbook.close();
		 */
	}

	public void UpdateDateXLS() throws IOException {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1= dateFormat.format(date);

		FileInputStream fsIP= new FileInputStream(new File("C:\\Users\\daigg\\OneDrive\\Documents\\IBKR WFH\\WebsiteSpeed.xlsx")); //Read the spreadsheet that needs to be updated
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);      
		XSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.

		Cell cell = null; // declare a Cell object

		XSSFRow setRow = worksheet.getRow(0);
		int colCount2 = setRow.getLastCellNum();
		System.out.println("The number of columns in the row is "+ colCount2);
		cell = worksheet.getRow(0).createCell(colCount2);   // Access the second cell in second row to update the value

		cell = worksheet.getRow(0).getCell(colCount2);

		cell.setCellValue(date1);  // Get current cell value value and overwrite the value

		fsIP.close(); //Close the InputStream

		FileOutputStream output_file =new FileOutputStream(new File("C:\\Users\\daigg\\OneDrive\\Documents\\IBKR WFH\\WebsiteSpeed.xlsx"));  //Open FileOutputStream to write updates

		wb.write(output_file); //write changes

		output_file.close();  //close the stream  
		System.out.println("Excel Chart has been Updated");
	}


	@Test 
	public void IBLLC() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.com/en/home.php");
		UpdateDateXLS();
		WriteToXLS(1, Average);

		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_CAN() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.ca/en/home.php");

		WriteToXLS(2, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_UK() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.co.uk/en/home.php");

		WriteToXLS(3, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_EU() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.eu/en/home.php");

		WriteToXLS(4, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_IE() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.ie/en/home.php");

		WriteToXLS(5, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_HU() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.hu/en/home.php");

		WriteToXLS(6, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_AU() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.com.au/en/home.php");

		WriteToXLS(7, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_HK() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.com.hk/en/home.php");

		WriteToXLS(8, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_IN() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.co.in/en/home.php");

		WriteToXLS(9, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_JP() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.co.jp/en/home.php");

		WriteToXLS(10, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void IB_SG() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTime("https://www.interactivebrokers.com.sg/en/home.php");

		WriteToXLS(11, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void MerrilEdge() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTimeMerril("https://www.merrilledge.com");

		WriteToXLS(12, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void ETrade() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTimeEtrade("https://us.etrade.com/home");

		WriteToXLS(13, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	/*	@Test
		public void Fidelity() {
			String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
			test = report.startTest(nameofCurrMethod);
			int Average = AverageTimeFidelity("https://www.fidelity.com");

			 if(Average > 3000){
                 System.out.println("The average time to load a page is " + Average + " Miliseconds");
                 collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
                 test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
                // Assert.fail(url+" is a broken link");
             }
             else{
                 System.out.println("The average time to load a page is " + Average + " Miliseconds");
                 test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
             } 
		}
	 */
	@Test
	public void Schwab() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTimeSchwab("https://www.schwab.com/");

		WriteToXLS(14, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void Robinhood() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTimeRobinhood("https://learn.robinhood.com/library/");

		WriteToXLS(15, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}

	@Test
	public void TDAmeritrade() throws IOException {
		String nameofCurrMethod = new Throwable().getStackTrace()[0].getMethodName();
		test = report.startTest(nameofCurrMethod);
		int Average = AverageTimeTDAmeritrade("https://www.tdameritrade.com/home.page");

		WriteToXLS(16, Average);
		if(Average > 3000){
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			collector.addError(new Throwable("The average time to load a page is " + Average + " Miliseconds"));
			test.log(LogStatus.FAIL, "The average time to load a page is " + Average + " Miliseconds");
			// Assert.fail(url+" is a broken link");
		}
		else{
			System.out.println("The average time to load a page is " + Average + " Miliseconds");
			test.log(LogStatus.PASS, "The average time to load a page is " + Average + " Miliseconds");
		} 
	}


}
