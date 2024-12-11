package martinusTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.junit.Test;



public class overenieTest {

	public static void main(String[] args) throws InterruptedException {
		// Nastav cestu k ChromeDriver
		System.setProperty("webdriver.chrome.chromedriver",
				"C:\\Users\\vasko\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		// Spusti WebDriver
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Čaká max 10 sekúnd na prvky

		// 1. Otvor stránku Martinus.sk
		driver.get("https://www.martinus.sk/");
		// 1*. vypne pop up okno
		driver.findElement(By.id("CybotCookiebotDialogBodyButtonDecline")).click();
		
		// 2. Nájde vyhľadávacie pole a zadá názov knihy
		WebElement searchField = driver.findElement(By.id("search-in-header")); // Vyhľadávacie pole
		searchField.sendKeys("Hobit");
		searchField.submit();
		
		// 3. Počkaj, kým sa zobrazí zoznam výsledkov a klikni na prvý výsledok
		WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".link--product")));
		firstResult.click();

		// 3. Kliknem na hodnotenia
		WebElement hodnotenieElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".link.ml-tiny")));
        String hodnotenieText = hodnotenieElement.getText(); // "1273 hodnotení"

        // Extrahovanie počtu hodnotení ako číslo
        String pocetHodnoteniString = hodnotenieText.split(" ")[0];
        int pocetHodnoteni = Integer.parseInt(pocetHodnoteniString);

        // Overenie, že počet hodnotení je väčší ako 0
        Assert.assertTrue(pocetHodnoteni > 0, "Počet hodnotení je nulový!");
        
        System.out.println("Minimalne 1 recenzia existuje.");
		

	}

}
