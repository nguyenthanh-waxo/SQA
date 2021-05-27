package TestRegistersub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestLogin.TestDriver;

public class TestRegistersub extends TestDriver{
	ChromeDriver driver= getDriver();
	// nhap sai ma mon hoc
	@Test
	public void InvalidSearch(){
		driver.get("http://localhost:8080/registersub");
		driver.navigate().refresh();
		String text="int";
		//nhập mã môn
		WebElement code= driver.findElement(By.name("code"));
		code.sendKeys(text);
		//nhấn nut tìm kiếm
		driver.findElement(By.xpath("/html/body/main/form[1]/input[2]")).click();
		
		WebElement input= driver.findElement(By.xpath("/html/body/main/section[1]/h6/b/span"));
		String codeinput=input.getText();
		WebElement message= driver.findElement(By.xpath("/html/body/main/section[1]/h6/div"));
		String errormessage = message.getAttribute("textContent");
		String expectmessage ="Mã môn học không đúng hoặc bạn không có quyền tìm kiếm môn học này";
		driver.close();
		assertEquals(text, codeinput);
		assertEquals(expectmessage, errormessage);
	}
	//Tìm kiếm mon học
	@Test
	public void ValidSearch() {
		driver.get("http://localhost:8080/registersub");
		driver.navigate().refresh();
		String text="INT1314";
		WebElement code= driver.findElement(By.name("code"));
		code.sendKeys(text);
		driver.findElement(By.xpath("/html/body/main/form[1]/input[2]")).click();
		WebElement input= driver.findElement(By.xpath("/html/body/main/section[1]/h6/b/span"));
		String codeinput=input.getText();
		List<WebElement> subject = driver.findElements(By.cssSelector("#registersub tr"));
		int num=subject.size();
		driver.close();
		assertEquals(text, codeinput);
		assertNotEquals(0, num);
		
	}
	//Kiểm tra thêm môn học vào danh sách đăng kí
	@Test
	public void RegistertSubject() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		driver.navigate().refresh();
		List<WebElement> index=driver.findElements(By.cssSelector("#register tr"));
		int before=index.size();
		String text="INT1314";
		driver.findElement(By.name("code")).sendKeys(text);
		WebElement click1=new WebDriverWait(driver, 10).until(driver->driver.findElement(By.xpath("/html/body/main/form[1]/input[2]")));
		click1.click();
		
		WebElement click2=new WebDriverWait(driver,10).until(driver->driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input")));	
		Thread.sleep(2000);
		click2.click();
		driver.findElement(By.xpath("/html/body/main/section[1]/form/input")).click();
		List<WebElement> register= driver.findElements(By.cssSelector("#register tr"));
		int after =register.size();
		
		driver.close();
		assertEquals(before+1, after);
	}
	// kiểm tra trùng lịch
	@Test
	public void SelectInvalidWeek() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		driver.navigate().refresh();
		String text1="INT1314";
		String text2="INT3221";
		WebElement code1= driver.findElement(By.name("code"));
		code1.sendKeys(text1);
		WebElement search1=driver.findElement(By.xpath("/html/body/main/form[1]/input[2]"));
		search1.click();
		WebElement check1=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input"));
		Thread.sleep(1000);
		check1.click();
		WebElement add1=driver.findElement(By.xpath("//*[@id=\"classes_list\"]/form/input"));
		add1.click();
		List<WebElement> register1= driver.findElements(By.cssSelector("#register tr"));
		int num1=register1.size();	
		WebElement code2=driver.findElement(By.name("code"));
		Thread.sleep(1000);
		code2.sendKeys(text2);
		WebElement search2=driver.findElement(By.xpath("/html/body/main/form[1]/input[2]"));
		search2.click();
		WebElement  check2=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input"));
		Thread.sleep(1000);
		check2.click();
		WebElement add2=driver.findElement(By.xpath("//*[@id=\"classes_list\"]/form/input"));
		Thread.sleep(1000);
		add2.click();
		List<WebElement> register2= driver.findElements(By.cssSelector("#register tr"));	
		String errormessage= driver.findElement(By.xpath("/html/body/main/section[2]/form[2]/h7/div")).getAttribute("textContent");
		String expectedmessage ="Môn học bạn vừa đăng kí bị trùng";
		int num2=register2.size();
		driver.close();
		assertEquals(num1, num2);
		assertEquals(errormessage, expectedmessage);
	}
	//kiểm tra thêm 2 nhóm trong cùng 1 môn học
	@Test
	public void SelectTwice() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		List<WebElement> index=driver.findElements(By.cssSelector("#register tr"));
		int before=index.size();
		String text="INT1314";
		driver.findElement(By.name("code")).sendKeys(text);
		WebElement search=driver.findElement(By.xpath("/html/body/main/form[1]/input[2]"));
		search.click();
		WebElement subject1=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input"));
		Thread.sleep(500);
		subject1.click();
		WebElement subject2=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[2]/td[2]/div/input"));
		Thread.sleep(500);
		subject2.click();
		driver.findElement(By.xpath("/html/body/main/section[1]/form/input")).click();
		List<WebElement> register= driver.findElements(By.cssSelector("#register tr"));
		int after =register.size();
		String NMHres=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr/td[6]")).getText();
		String TTHres=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr/td[7]")).getText();
		String NMHsave=driver.findElement(By.xpath("//*[@id=\"register\"]/tr[1]/td[5]")).getText();
		String TTHsave=driver.findElement(By.xpath("//*[@id=\"register\"]/tr[1]/td[6]")).getText();
		driver.close();
		assertEquals(before+1, after);
		assertEquals(NMHres, NMHsave);
		assertEquals(TTHres, TTHsave);
	}
	//xóa môn học đã đăng ký
	@Test
	public void DeleteSubject() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		String text="INT1314";
		//nhập mã môn học
		WebElement code= driver.findElement(By.name("code"));
		code.sendKeys(text);
		//nhấn nút tìm kiếm
		driver.findElement(By.xpath("/html/body/main/form[1]/input[2]")).click();
		//tích vào nhóm của môn học
		WebElement subject=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input"));
		Thread.sleep(500);
		subject.click();
		//lưu vào bảng đăng ký
		driver.findElement(By.xpath("/html/body/main/section[1]/form/input")).click();		
		//lấy số lượng môn sau khi đăng ký ở bảng đăng ký
		List<WebElement> registerbefore= driver.findElements(By.cssSelector("#register tr"));
		int before =registerbefore.size();
		//nhấn nút xóa môn
		WebElement deletebtn=driver.findElement(By.xpath("//*[@id=\"register\"]/tr/td[8]/form/input[2]"));
		Thread.sleep(500);
		deletebtn.click();
		//lấy số lượng môn sau khi xóa
		List<WebElement> registerafter= driver.findElements(By.cssSelector("#register tr"));
		int after =registerafter.size();
		assertEquals(before-1, after);
	}

	//kiểm tra lưu danh sách vào cơ sở dữ liệu không thành công
	@Test
	public void InvalidRegister() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		driver.navigate().refresh();
		String text1="INT1314";
		String text2="INT3221";
		//nhập môn 1
		WebElement code1= driver.findElement(By.name("code"));
		code1.sendKeys(text1);
		WebElement search1=driver.findElement(By.xpath("/html/body/main/form[1]/input[2]"));
		search1.click();
		WebElement check1=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[4]/td[2]/div/input"));
		Thread.sleep(1000);
		check1.click();
		WebElement add1=driver.findElement(By.xpath("//*[@id=\"classes_list\"]/form/input"));
		add1.click();	
		//nhập môn 2
		WebElement code2=driver.findElement(By.name("code"));
		Thread.sleep(1000);
		code2.sendKeys(text2);
		WebElement search2=driver.findElement(By.xpath("/html/body/main/form[1]/input[2]"));
		search2.click();
		WebElement  check2=driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input"));
		Thread.sleep(1000);
		check2.click();
		WebElement add2=driver.findElement(By.xpath("//*[@id=\"classes_list\"]/form/input"));
		Thread.sleep(1000);
		add2.click();
		//nhấn nút lưu vào csdl
		WebElement save=driver.findElement(By.xpath("//*[@id=\"registered_classes\"]/form[1]/input"));
		Thread.sleep(1000);
		save.click();
		String message=driver.findElement(By.xpath("//*[@id=\"registered_classes\"]/form[3]/h7/div")).getText();
		assertEquals("Tổng số tín chỉ đăng kí phải lớn hơn 12", message);
	}
	//đăng ký mon học het slot
	@Test
	public void InvalidSubject() throws InterruptedException {
		driver.get("http://localhost:8080/registersub");
		String text="INT3312";
		driver.findElement(By.name("code")).sendKeys(text);
		WebElement click1=new WebDriverWait(driver, 10).until(driver->driver.findElement(By.xpath("/html/body/main/form[1]/input[2]")));
		click1.click();
		
		WebElement click2=new WebDriverWait(driver,10).until(driver->driver.findElement(By.xpath("//*[@id=\"registersub\"]/tr[1]/td[2]/div/input")));	
		Thread.sleep(2000);
		click2.click();
		driver.findElement(By.xpath("/html/body/main/section[1]/form/input")).click();
		String errormessage=driver.findElement(By.xpath("//*[@id=\"registered_classes\"]/form[4]/h7/div")).getText();
		assertEquals("Môn học INT3312 đã hết slot! Vui lòng thử lại", errormessage);
	}
}
