package kiwiTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class SearchButton {

	public static void clickOnMonthAndDay(String month, String day, WebDriver driver, WebDriverWait wait)
			throws InterruptedException {
		boolean found = false;
		while (true) {
			List<WebElement> currentMonths = driver
					.findElements(By.xpath("//button[@data-test='DatepickerMonthButton']"));
			for (WebElement webElement : currentMonths) {
				// System.out.println(webElement.getText());
				if (webElement.getText().equals(month)) {
					found = true;
				}
			}
			if (found)
				break;
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-test='CalendarMoveNext']")))
					.click();
		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='w-full not-last:me-400' and contains(., '" + month
						+ "')]//div[@data-test='DayDateTypography' and text()='" + day + "']")))
				.click();

	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.chromedriver",
				"C:\\Users\\vasko\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		// Spusti WebDriver
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		try {
			driver.get("https://www.kiwi.com/");

			driver.findElement(By.cssSelector(".orbit-button-primitive-content")).click();
			WebElement firstResult = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("p.orbit-text.font-bold.text-white-foreground")));
			firstResult.click();

			WebElement firstPlace = driver.findElement(By.cssSelector("input[data-test=\"SearchField-input\"]"));
			firstPlace.sendKeys("Bratislava");

			Thread.sleep(2000);

			List<WebElement> options = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(
					"div.flex-1.overflow-hidden.text-ellipsis.whitespace-nowrap.font-medium.leading-normal")));

			for (WebElement option : options) {
				if (option.getText().contains("Bratislava")) {
					option.click();
					break;
				}
			}

			Thread.sleep(3000);

			WebElement secondPlace = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("input[placeholder=\"City, airport or place\"]")));
			secondPlace.sendKeys("Bangkok");

			Thread.sleep(2000);

			List<WebElement> options2 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(
					"div.flex-1.overflow-hidden.text-ellipsis.whitespace-nowrap.font-medium.leading-normal")));

			for (WebElement option2 : options2) {
				if (option2.getText().contains("Bangkok")) {
					option2.click();
					break;
				}
			}
			// click on departure date
			wait.until(ExpectedConditions.elementToBeClickable(By.name("search-outboundDate"))).click();

			Thread.sleep(3000);

			clickOnMonthAndDay("March 2025", "4", driver, wait);
			clickOnMonthAndDay("April 2025", "3", driver, wait);

			// click on set dates
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[contains(@class, 'orbit-button-primitive-content') and contains(text(), 'Set dates')]")))
					.click();

			// click on search button
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'Search')]"))).click();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}