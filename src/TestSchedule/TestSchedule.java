package TestSchedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import TestLogin.TestDriver;

public class TestSchedule extends TestDriver{
	ChromeDriver driver= getDriver();
	@Test
	public void InValidWeek() {
		driver.get("http://localhost:8080/schedule");
		//chọn tuần
		Select dropweek = new Select(driver.findElement(By.id("select_subject")));
		dropweek.selectByVisibleText("Từ ngày [ 2021/01/11 ] đến ngày trước ngày [ 2021/01/18 ] - Tuần 2");
		driver.findElement(By.xpath("/html/body/main/form/section/input")).click();
		//lấy text hiện thị ở bảng
		String tablename=driver.findElement(By.xpath("/html/body/main/section/h6/b")).getText();
		//láy số lượng môn học trong bảng
		List<WebElement> subject= driver.findElements(By.cssSelector("#subject article"));
		int num=subject.size();
		assertEquals("Không có kíp học trong tuần", tablename);
		assertEquals(0, num);
	}
	@Test
	public void ValidWeek() {
		driver.get("http://localhost:8080/schedule");
		Select dropweek = new Select(driver.findElement(By.id("select_subject")));
		dropweek.selectByVisibleText("Từ ngày [ 2021/02/22 ] đến ngày trước ngày [ 2021/03/01 ] - Tuần 8");
		driver.findElement(By.xpath("/html/body/main/form/section/input")).click();
		//lấy text hiện thị ở đầu bảng
		String tablename=driver.findElement(By.xpath("/html/body/main/section/h6/b")).getText();
		//láy số lượng môn học trong bảng
		List<WebElement> subject= driver.findElements(By.cssSelector("#subject article"));
		int num=subject.size();
		assertEquals("Danh sách kíp học trong tuần:", tablename);
		assertNotEquals(0, num);
	}
}
