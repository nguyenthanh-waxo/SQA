package TestLogin;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLogin extends TestDriver{
	ChromeDriver driver= getDriver();
	// nhập đúng tên đăng nhập và mật khẩu
	@Test
	public void ValidLogin() throws InterruptedException {
		driver.get("http://localhost:8080/login");
		//nhập tên đăng nhập và mật khẩu
		WebElement username= driver.findElement(By.name("username"));
		WebElement password= driver.findElement(By.name("password"));
		username.sendKeys("hong");
		password.sendKeys("1234");
		Thread.sleep(500);
		//nhấn nút đăng nhập
		driver.findElement(By.id("btn_login")).click();
		String title = driver.getTitle();
		String expectedTitle = "Quản lí sinh viên PTIT";
		driver.close();
		assertEquals(expectedTitle, title);
	}
	//nhập sai tên đăng nhập và mật khẩu
	@Test
	public void InValidLogin() {
		driver.get("http://localhost:8080/login");
		WebElement username= driver.findElement(By.name("username"));
		WebElement password= driver.findElement(By.name("password"));
		username.sendKeys("thanh");
		password.sendKeys("123");
		driver.findElement(By.id("btn_login")).click();
		String title = driver.getTitle();
		String expectedTitle ="Đăng nhập QLSV";	
		WebElement message =driver.findElement(By.xpath("/html/body/main/div/div[2]"));	
		String getmessage = message.getAttribute("textContent");
		String expectedmessage ="Tài khoản hoặc mật khẩu không đúng! Xin thử lại!";
		driver.close();
		assertEquals(expectedTitle, title);
		assertEquals(expectedmessage, getmessage);
		
	}

	// nhập mật khẩu =null
	@Test
	public void NullPasswordLogin() {
		driver.get("http://localhost:8080/login");
		WebElement username= driver.findElement(By.name("username"));
		WebElement password= driver.findElement(By.name("password"));
		username.sendKeys("hong");
		password.sendKeys("");
		driver.findElement(By.id("btn_login")).click();
		String title = driver.getTitle();
		String expectedTitle = "Đăng nhập QLSV";
		WebElement message =driver.findElement(By.xpath("/html/body/main/div/div[2]"));	
		String getmessage = message.getAttribute("textContent");
		String expectedmessage ="Không được để trống tên tài khoản hoặc mật khẩu!";
		driver.close();
		assertEquals(expectedTitle, title);
	}
	//nhập tên đăng nhập =null
	@Test
	public void NullUserNameLogin() {
		driver.get("http://localhost:8080/login");
		WebElement username= driver.findElement(By.name("username"));
		WebElement password= driver.findElement(By.name("password"));
		username.sendKeys("");
		password.sendKeys("123");
		driver.findElement(By.id("btn_login")).click();
		String title = driver.getTitle();
		String expectedTitle = "Đăng nhập QLSV";
		WebElement message =driver.findElement(By.xpath("/html/body/main/div/div[2]"));	
		String getmessage = message.getAttribute("textContent");
		String expectedmessage ="Không được để trống tên tài khoản hoặc mật khẩu!";
		driver.close();
		assertEquals(expectedTitle, title);
	}
	//nhập tên đăng nhập và mật khẩu =null
	@Test
	public void NullLogin() {
		driver.get("http://localhost:8080/login");
		WebElement username= driver.findElement(By.name("username"));
		WebElement password= driver.findElement(By.name("password"));
		username.sendKeys("");
		password.sendKeys("");
		driver.findElement(By.id("btn_login")).click();
		String title = driver.getTitle();
		String expectedTitle = "Đăng nhập QLSV";
		WebElement message =driver.findElement(By.xpath("/html/body/main/div/div[2]"));	
		String getmessage = message.getAttribute("textContent");
		String expectedmessage ="Không được để trống tên tài khoản hoặc mật khẩu!";
		driver.close();
		assertEquals(expectedTitle, title);
		}
}
