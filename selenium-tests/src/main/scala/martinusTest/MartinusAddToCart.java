package martinusTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class MartinusAddToCart {

	public static void main(String[] args) throws InterruptedException {

		// Nastavi cestu k ChromeDriver
		System.setProperty("webdriver.chrome.chromedriver",
				"C:\\Users\\vasko\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		// Spusti WebDriver
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Čaká max 10 sekúnd na prvky

		// 1. Otvori stránku Martinus.sk
		driver.get("https://www.martinus.sk/");

		driver.findElement(By.id("CybotCookiebotDialogBodyButtonDecline")).click();

		// 2. Nájde vyhľadávacie pole a zadá názov knihy
		WebElement searchField = driver.findElement(By.id("search-in-header")); // Vyhľadávacie pole
		searchField.sendKeys("Hobit");
		searchField.submit();

		// 3. Počka, kým sa zobrazí zoznam výsledkov a klikni na prvý výsledok
		WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".link--product")));
		firstResult.click();

		// 4. Počka, kým sa zobrazí stránka produktu a klikni na tlačidlo "Pridať do
		// košíka"
		WebElement addToCartButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("button.btn.btn--primary.mj-cart-add__button.mb-none.flex-1")));
		addToCartButton.click();

		// 5. Over, či sa kniha pridala do košíka
		WebElement modalWindow = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn--ghost")));
		modalWindow.click();

		WebElement kosik = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".svg-inline--fa.fa-basket-shopping.fa-xl")));
		kosik.click();

		WebElement cartNotification = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title=\"Hobit\"]")));
		System.out.println(cartNotification.getText());
		if (cartNotification.getText().contains("Hobit")) {
			System.out.println("Test prebehol úspešne: Kniha bola pridaná do košíka.");
		} else {
			System.out.println("Test zlyhal: Kniha nebola pridaná do košíka.");
		}

		WebElement kosikVymaz = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".svg-inline--fa.fa-trash-can-xmark.fa-xl")));
		kosikVymaz.click();

		Thread.sleep(2000);
		WebElement blueCircle = driver.findElement(By.cssSelector("div.pill.pill--blue"));
		System.out.println(blueCircle.getText());
		if (blueCircle.getText().contains("0")) {
			System.out.println("Košík je prázdny.");
		} else {
			System.out.println("Košík nie je prázdny.");
		}
		// driver.close();
	}

}
