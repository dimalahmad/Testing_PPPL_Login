package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page Object Class untuk Halaman Login.
 * Berisi semua elemen dan layanan yang disediakan oleh halaman ini.
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By emailInput = By.name("email");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.xpath("//button[contains(text(), 'Masuk')]");

    /**
     * Constructor untuk LoginPage.
     * @param driver instance WebDriver yang diteruskan dari step definition.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Aksi pada Halaman (Page Actions / Services) ---

    /**
     * Membuka halaman login.
     */
    public void navigateToLoginPage() {
        driver.get("https://simbat.madanateknologi.web.id/login");
    }

    /**
     * Memasukkan alamat email ke dalam kolom email.
     * @param email Alamat email pengguna.
     */
    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    /**
     * Memasukkan password ke dalam kolom password.
     * @param password Password pengguna.
     */
    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    /**
     * Menekan tombol login.
     */
    public void clickLoginButton() {
        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginElement.click();
    }

    /**
     * Metode gabungan untuk melakukan proses login lengkap (Business Flow).
     * Ini menyederhanakan kode di Step Definitions.
     * @param email Alamat email pengguna.
     * @param password Password pengguna.
     */
    public void performLogin(String email, String password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }

    /**
     * Mengambil teks pesan error jika ada.
     * @return String teks pesan error.
     */
    public String getErrorMessage() {
        try {
            // Wait for either error message or successful login
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]")),
                ExpectedConditions.urlContains("/dashboard")
            ));
            
            // If we're still on the login page, get the error message
            if (driver.getCurrentUrl().contains("/login")) {
                WebElement errorMessageElement = driver.findElement(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]"));
                return errorMessageElement.getText();
            }
            return "Login successful";
        } catch (Exception e) {
            return "No error message found";
        }
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert') or contains(@class, 'error') or contains(@class, 'invalid-feedback')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/login");
    }

    public boolean isLoginFormEmpty() {
        WebElement emailElement = driver.findElement(emailInput);
        WebElement passwordElement = driver.findElement(passwordInput);
        return emailElement.getAttribute("value").isEmpty() && 
               passwordElement.getAttribute("value").isEmpty();
    }

    public boolean isLoggedIn() {
        return !driver.getCurrentUrl().contains("/login");
    }
}