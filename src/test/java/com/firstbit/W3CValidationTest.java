package com.firstbit;
import com.firstbit.page.W3CValidatorPage;
import com.firstbit.utils.ScreenshotUtil;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class W3CValidationTest {
    private FirefoxDriver driver;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
    }

    @Test
    public void validateMultipleUrls() {
        String[] urls = {
                "https://www.gmrwebteam.com/",
                "https://www.gmrwebteam.com/contact-us",
                "https://www.gmrwebteam.com/case-studies",
                "https://www.gmrwebteam.com/case-study/plaza-dental-group"
        };

        W3CValidatorPage validatorPage = new W3CValidatorPage(driver);

        for (String url : urls) {
            try {
                System.out.println(" Validating: " + url);
                validatorPage.openValidatorFor(url);

                int errors = validatorPage.getErrorCount();
                int infos = validatorPage.getInfoCount();

                String safeFileName = validatorPage.getSafeFileName(url);
                ScreenshotUtil.takeFullPageScreenshot(driver, safeFileName);

                System.out.println(" Screenshot saved for: " + url);
                System.out.println(" Errors: " + errors);
                System.out.println(" Info: " + infos);
                System.out.println("--------------------------------------------------");

            } catch (Exception e) {
                System.out.println(" Error validating: " + url);
                System.out.println("Details: " + e.getMessage());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
