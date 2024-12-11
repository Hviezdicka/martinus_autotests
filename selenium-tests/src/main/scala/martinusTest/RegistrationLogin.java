package martinusTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationLogin {

	public static void main(String[] args) throws InterruptedException {
		// Overiť proces registrácie
		
//		Prejdi na registračný formulár.
//		Vyplň všetky povinné údaje (meno, e-mail, heslo, atď.).
//		Klikni na tlačidlo "Registrovať".
//		Over, že sa používateľ dostane na uvítaciu stránku alebo dostane potvrdzujúci e-mail.
		
		// Nastav cestu k ChromeDriver
		System.setProperty("webdriver.chrome.chromedriver",
				"C:\\Users\\vasko\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		// Spusti WebDriver
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Čaká max 10 sekúnd na prvky

		// 1. Otvor stránku Martinus.sk
		driver.get("https://www.martinus.sk/");
		
		driver.findElement(By.id("CybotCookiebotDialogBodyButtonDecline")).click();
		WebElement panacik = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".svg-inline--fa.fa-circle-user.fa-xl.header-user__icon")));
		panacik.click();

		WebElement emailik = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
		emailik.sendKeys("radka.hrivnakova1+1@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn--primary.mj-next-button.mj-login-email.login-email.login-button")).click();
		Thread.sleep(1000);
//		driver.findElement(By.name("name")).sendKeys("radka");
//		driver.findElement(By.name("surname")).sendKeys("hrivnakova");
//		driver.findElement(By.name("password")).sendKeys("1234abcd");
//		driver.findElement(By.cssSelector(".mj-checkbox-tac.form-check-input")).click();
//		driver.findElement(By.cssSelector(".btn.btn--primary")).click();
		driver.findElement(By.cssSelector("input[name=\"heslo\"]")).sendKeys("1234abcd");
		driver.findElement(By.cssSelector("button[data-form-target=\"submitButton\"]")).click();
		
	}

}
