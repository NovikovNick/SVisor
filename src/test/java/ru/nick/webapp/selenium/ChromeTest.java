package ru.nick.webapp.selenium;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest {

	
	private static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
	private static String ADMIN_MAIN = "http://127.0.0.1:8080/SVisor/pages/admin/main.xhtml";
	private static String STUDENT_MAIN = "http://127.0.0.1:8080/SVisor/pages/student/main.xhtml";
	private static String TEACHER_MAIN = "http://127.0.0.1:8080/SVisor/pages/teacher/main.xhtml";
	
	private static ChromeDriverService service;
	public static WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(CHROME_DRIVER_PATH)).usingAnyFreePort()
				.build();
		service.start();
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1000,540");
		driver = new ChromeDriver(service, option);
	}

	@Before
	public void setUp() {
		
	}
	
	
	@Test
	public void adminPages() throws InterruptedException {
		Assert.assertEquals(ADMIN_MAIN, authorization("admin", "admin"));
		
		
		String[] links = new String[]{"main", "degree", "title", "speciality", "teacher", "group", "discipline"	};
		
		for (int i = 0; i < links.length; i++) {
			WebElement link = driver.findElement(By.id(links[i]));
			link.click();
		}
				
		
		Assert.assertTrue(logout());	
	}
	
	@Test
	public void teacherPages() {
		Assert.assertEquals(TEACHER_MAIN, authorization("test", "test"));
		visitNavigationLink();
		Assert.assertTrue(logout());
	}
	
	@Test
	public void studentPages() {
		Assert.assertEquals(STUDENT_MAIN, authorization("nick", "nick"));
		//visitNavigationLink();
		Assert.assertTrue(logout());
	}

	/**
	 * Пробегает по ссылкам навигатора
	 */
	private void visitNavigationLink() {
		for (int i = 0; i < 10; i++) {
			List<WebElement> a = driver.findElement(By.className("navigation"))
					.findElements(By.cssSelector("a"));
			if (a.size() <= i)
				break;
			String title = a.get(i).getText();
			long start = System.currentTimeMillis();
			a.get(i).click();
			System.out.println(title + " ~ " + (System.currentTimeMillis() - start) + "ms");
		}
	}

	/**
	 * Метод производит авторизацию
	 * @param login 
	 * @param password 
	 * @return возвращает URL страницы, после авторизации
	 */
	private String authorization(String login, String password) {
		driver.get("http://127.0.0.1:8080/SVisor/pages/");
		WebElement j_username = driver.findElement(By.name("j_idt11:j_username"));
		WebElement j_password = driver.findElement(By.name("j_idt11:j_password"));
		WebElement submit = driver.findElement(By.name("j_idt11:j_idt18"));
		j_username.sendKeys(login);
		j_password.sendKeys(password);
		submit.click();
		return driver.getCurrentUrl();
	}

	/**
	 * Метод производит логаут
	 * @return true если логаут удачен
	 */
	private boolean logout() {
		WebElement logout;
		try {
			logout = driver.findElement(By.id("logout"));
		} catch (NoSuchElementException e) {
			return false;
		}
		logout.click();
		return true;
		
	}
	
	@After
	public void tearDown() {
		
	}

	@AfterClass
	public static void createAndStopService() {
		driver.quit();
		service.stop();
	}
}
