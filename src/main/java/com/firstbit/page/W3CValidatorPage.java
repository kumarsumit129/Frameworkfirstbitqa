package com.firstbit.page;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
public class W3CValidatorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public W3CValidatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openValidatorFor(String targetUrl) {
        String validatorUrl = "https://validator.w3.org/nu/?doc=" + targetUrl + "&out=html";
        driver.get(validatorUrl);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.error")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.info"))
        ));
    }

    public int getErrorCount() {
        return driver.findElements(By.cssSelector("li.error")).size();
    }

    public int getInfoCount() {
        return driver.findElements(By.cssSelector("li.info")).size();
    }

    public String getSafeFileName(String url) {
        return url.replaceAll("https?://|www\\.|[^a-zA-Z0-9]", "_");
    }
}