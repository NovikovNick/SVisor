package ru.nick.webapp.integration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest extends Assert{

	private static final String CHROME_DRIVER_PATH = "D:\\chromedriver.exe";
	private static String ADMIN_MAIN = "http://127.0.0.1:8080/SVisor/pages/admin/main.xhtml";
	private static String STUDENT_MAIN = "http://127.0.0.1:8080/SVisor/pages/student/main.xhtml";
	private static String TEACHER_MAIN = "http://127.0.0.1:8080/SVisor/pages/teacher/main.xhtml";

	private static ChromeDriverService service;
	public static WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(CHROME_DRIVER_PATH))
				.usingAnyFreePort().build();
		service.start();

		ChromeOptions option = new ChromeOptions();
		//option.addArguments("--window-size=1000,540");
		option.addArguments("--enable-kiosk-mode");
		option.addArguments("--no-experiments");
		
		driver = new ChromeDriver(service, option);
	}

	// @Ignore
	@Test
	public void adminPages() throws InterruptedException {
		Assert.assertEquals(ADMIN_MAIN, authorization("admin", "admin"));
		
		toNavigateLink("discipline");
		inputText("title", "Discipline");
		proofInsert("table:discipline");
		
		toNavigateLink("degree");
		inputText("fullDegree", "fullDegree");
		inputText("reducDegree", "reducDegree");
		proofInsert("table:academicDegree");
		
		toNavigateLink("title");
		inputText("fullTitle", "fullTitle");
		inputText("reducTitle", "reducTitle");
		proofInsert("table:academicTitle");
		
		toNavigateLink("speciality");
		inputText("idSpec", "22022012");
		inputText("titleSpec", "специальность");
		proofInsert("table:speciality");
		
		toNavigateLink("teacher");
		inputText("idTeacher", "1130030028");
		inputText("fstNameTeacher", "Иван");
		inputText("sndNameTeacher", "Иванович");
		inputText("surnameTeacher", "Иванов");
		inputText("loginTeacher", "qwerty");
		inputText("passwordTeacher", "1234");
		inputText("innTeacher", "86848642168");
		inputText("pensionInsuranceTeacher", "124143622");
		selectOne("select_degree", 1);
		selectOne("select_title", 1);
		selectMany("select_discipline", 2, 4, 8, 13, 16, 21, 28);
		selectMany("select_group", 1);	
		teacherProof();
		
		toNavigateLink("group");
		inputText("titleGroup", "ЖПЦ-15");
		inputText("courseGroup", "1");
		selectOne("spec", 1);
		proofInsert("table:group");
		
		toNavigateLink("main");
		
		Assert.assertTrue(logout());
	}

	/**
	 * 
	 */
	private void teacherProof() {
		driver.findElement(By.id("form:submit")).click();
		//TODO:refactoring html, аnd check
		List<WebElement> del = driver.findElements(By.cssSelector(".ico del"));
		del.get(del.size()-1).click();
	}

	private void selectMany(String string, int... count) {
		WebElement select = driver.findElement(By.id("form:"+string));
		select.click();
		for (int i = 0; i < count.length; i++) {
			select.findElements(By.cssSelector("input[type=checkbox]")).get(count[i]).click();
		}
		
	}

	/**
	 * 
	 */
	private void crud(String page, String table, Map<String, String> param) {
		toNavigateLink(page);		
		for (Entry<String, String> entry : param.entrySet()) {
			inputText(entry.getKey(), entry.getValue());
		}
		proofInsert(table);
	}

	/**
	 * @param table
	 */
	private void proofInsert(String table) {
		teacherProof();
		
		List<WebElement> d = driver.findElements(By.id(table).tagName("tr"));
		WebElement tr = d.get(d.size()-1);
		List<WebElement> txt = d.get(d.size()-1).findElements(By.tagName("textarea"));
		
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < txt.size(); i++) {
			String string = txt.get(i).getText();
			list.add(string);
			txt.get(i).clear();
			txt.get(i).sendKeys(string+i);
			
		}
		tr.findElement(By.className("edit")).click();
		
		driver.navigate().refresh();
		List<WebElement> d2 = driver.findElements(By.id(table).tagName("tr"));
		WebElement tr2 = d2.get(d2.size()-1);
		List<WebElement> txt2 = d2.get(d2.size()-1).findElements(By.tagName("textarea"));
		
		for (int i = 0; i < txt2.size(); i++) {
			assertEquals(list.get(i)+i, txt2.get(i).getText());
		}
		tr2.findElement(By.className("del")).click();
	}

	/**
	 * @param page
	 */
	private void toNavigateLink(String page) {
		WebElement link = driver.findElement(By.id(page));
		link.click();
	}

	
	private void inputText(String key, String value) {
		WebElement el = driver.findElement(By.id("form:"+key));
		el.clear();
		el.sendKeys(value);
	}

	public void selectOne(String idPrefix, int count) {
		WebElement select = driver.findElement(By.id("form:"+idPrefix));
		select.click();
		select.findElements(By.tagName("option")).get(count).click();
	}
	
	
	
	
	
	
	
	
	
	
	
	@Ignore
	@Test
	public void teacherPages() {
		Assert.assertEquals(TEACHER_MAIN, authorization("test", "test"));
		visitNavigationLink();
		Assert.assertTrue(logout());
	}

	@Ignore
	@Test
	public void studentPages() {
		Assert.assertEquals(STUDENT_MAIN, authorization("nick", "nick"));
		visitNavigationLink();
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
			System.out.println(title + " ~ "
					+ (System.currentTimeMillis() - start) + "ms");
		}
	}

	/**
	 * Метод производит авторизацию
	 * 
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
	 * 
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

	@AfterClass
	public static void createAndStopService() {
		driver.quit();
		service.stop();
	}
	

}
