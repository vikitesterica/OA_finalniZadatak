package main.pom_files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends Page{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // selektori

    protected By naslovStrane = By.xpath("//h1[text()='Formular za registraciju']");

    protected By poljeIme = By.id("customer[first_name]");
    protected By prezime = By.id("customer[last_name]");
    protected By email = By.id("customer[email]");
    protected By emailConfirmation = By.id("customer[confirm_email]");
    protected By sifra = By.id("customer[password]");
    protected By ponovljenaSifra = By.id("customer[confirm_password]");
    protected By mobilni = By.id("customer[mobile]");
    protected By datumRodjenja = By.id("datepicker");
    protected By datePicker = By.id("ui-datepicker-div");
    protected By checkboxPrihvatiUslove = By.id("accept_terms");
    protected By dugmeRegistracija = By.name("Submit");
    protected By poljeDanUmjesecu = By.linkText("1");
    protected By messageRegistered = By.xpath("//div[@class='wrapped']/p[1]");

    // metode

    public RegisterPage waitForTitle(WebDriver driver){
        int timeout = 10;
        waitForElement(driver, naslovStrane, timeout);
        return this;
    }

    public RegisterPage populateName(String text){
        populateField(driver,poljeIme,text);
        return this;
    }

    public RegisterPage populateSurname(String text){
        populateField(driver,prezime,text);
        return this;
    }

    public RegisterPage populateEmail(String text){
        populateField(driver,email,text);
        return this;
    }

    public RegisterPage confirmEmail(String text){
        populateField(driver,emailConfirmation,text);
        return this;
    }

    public RegisterPage populatePassword(String text){
        populateField(driver,sifra,text);
        return this;
    }

    public RegisterPage confirmPassword(String text){
        populateField(driver,ponovljenaSifra,text);
        return this;
    }

    public RegisterPage populateNumber(String text){
        populateField(driver,mobilni,text);
        return this;
    }

    public RegisterPage populateDateOfBirth(String text){
        clickOnElement(datumRodjenja);
        waitForElement(driver,datePicker,5);
        clickOnElement(poljeDanUmjesecu);
        return this;
    }

    public RegisterPage acceptTerms(){
        clickOnElement(checkboxPrihvatiUslove);
        return this;
    }

    public RegisterPage waitForCaptchaToBeSolved(){
        waitForElementToBeClickable(driver,dugmeRegistracija,120);
        return this;
    }

    public RegisterPage clickToRegister(){
        clickOnElement(dugmeRegistracija);
        return this;
    }

    public String getTextFromSuccessfulRegisterMessage(){
        String text = getTextFromElement(driver, messageRegistered);
        return text;
    }
}
