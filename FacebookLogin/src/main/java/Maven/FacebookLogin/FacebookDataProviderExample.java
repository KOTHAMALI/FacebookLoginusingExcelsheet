   package Maven.FacebookLogin;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;



	public class FacebookDataProviderExample {

		 private WebDriver driver=null;
		    
		    @BeforeClass
		    public void beforeClass() {
		    	

		        System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver.exe");
		       driver = new ChromeDriver();
		    	
//		    	 System.setProperty("webdriver.gecko.driver", "C://Selenium//geckodriver.exe");
//		         driver = new FirefoxDriver();
		         driver.get("http://facebook.com");
		         driver.manage().window().maximize();
		       //  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		     
		    }
		    
		    @AfterClass
		    public void afterClass() throws InterruptedException {
		    	
		       driver.close();
		    }
		    
		    @Test(dataProvider="invalid-login-data")
		    public void sampleLoginTest(String username, String password) throws Exception {
		    	
		        driver.findElement(By.id("email")).sendKeys(username);
		        driver.findElement(By.id("pass")).sendKeys(password);
		        driver.findElement(By.id("loginbutton")).click();
		        // You will verify the error message here then go back Landing 
		        Thread.sleep(1000);
		        driver.navigate().back();
		        driver.findElement(By.id("email")).clear();
		    }
		    
		    @DataProvider(name="invalid-login-data")
		    public Object[][] dpInavlidLogin() throws InterruptedException {
		    	ExcelDataConfig config = new ExcelDataConfig("C:\\Selenium\\ExcelSheet.xlsx");
		    	int rows = config.getRowCount(0);
		    
		    	Object[][]data=new Object[rows][2];
		    	for(int i=0;i<rows;i++){
		    		data[i][0]=config.getData(0, i, 0);
		    		data[i][1] = config.getData(0, i, 1);
		    		Thread.sleep(1000);
		    	}
		    	return data;
		        }    
		    }
