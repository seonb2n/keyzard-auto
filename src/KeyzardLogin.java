import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class KeyzardLogin {
    private WebDriver driver;
    private WebElement element;
    private String url;

    private final String ID = "";
    private final String PW = "";

    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:/selenium/driver/chromedriver.exe";

    public KeyzardLogin() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("---start-maximized");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        url = "https://keyzard.org/login";
    }

    public void activateLogin() throws InterruptedException {
        try {
            driver.get(url);
            Thread.sleep(2000);

            //id 입력
            element = driver.findElement(By.id("id"));
            element.sendKeys(ID);

            //pw 입력
            element = driver.findElement(By.id("pw"));
            element.sendKeys(PW);

            element = driver.findElement(By.id("loginBtn"));
            element.click();
            Thread.sleep(2000);

            url = "https://keyzard.org/nb";
            driver.get(url);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void inputLink(String[] links) throws InterruptedException {
        for (int i = 0; i<links.length; i++) {
            element = driver.findElement(By.id("blogUrl"));
            element.sendKeys(links[i]);
            element = driver.findElement(By.id("submit"));
            element.click();
            Thread.sleep(1000);
            driver.switchTo().alert().accept();
        }
    }
}
