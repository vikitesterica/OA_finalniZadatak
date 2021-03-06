package main.pom_files;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page{

    // konstruktor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    protected By linkPrijava = By.xpath("//*[@class='user-wraper']");
    protected By tooltipLogin = By.id("login_dropdown");
    protected By linkRegistracija = By.xpath("//div[@class='register-links-wrap']/a");
    protected By linkKategorijeProizvoda = By.xpath("//div[@class='category-menu js-category-menu']");
    protected By linkPadajuciMeniProizvod(int index) {
        String locatorText = "(//a[@class='first-level-link'])["+index+"]";
    return By.xpath(locatorText);
    }
    protected By linkPadajuciMeniSviProizvodi = By.xpath("//a[@class='first-level-link']");
    protected By linkPadajuciMeniProizvodPremuTekstuLinka(String textLinka) {
        String locatorText = "//a[@class='first-level-link'][@href='https://www.tehnomanija.rs/"+textLinka+"']";
        return By.xpath(locatorText);
    }



    //metode

    public HomePage hoverPrijava (WebDriver driver){
        Actions hover = new Actions(driver);
        WebElement we = driver.findElement(linkPrijava);
        hover.moveToElement(we).perform();
        return this;
    }
    public HomePage hoverKategorijeProizvoda (){
        hoverElement(driver, linkKategorijeProizvoda);
        implicitWaitInSeconds(driver,3);
        return this;
    }

    public HomePage waitForTooltip(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipLogin));
        return this;
    }

    public HomePage clickToRegister() {
        driver.findElement(linkRegistracija).click();
        return this;
    }

    public String getItemTextFromDropdown(int index) {
        String text = getTextFromElement(driver,linkPadajuciMeniProizvod(index));
        return text;
    }

    public int getNumberOfElementsInDropdownKategorijeProizvoda(){
        int count = getNumberOfElementsUsingXpath(linkPadajuciMeniSviProizvodi);
        return count;
    }

    public HomePage hoverStavkaFromDropdown(String stavka){
        hoverElement(driver, linkPadajuciMeniProizvodPremuTekstuLinka(stavka));
        return this;
    }

    public String getTextColorForStavka(String stavka){
       String boja = getTextColor(driver, linkPadajuciMeniProizvodPremuTekstuLinka(stavka));
    return boja;
    }

    public HomePage clickStavkaFromDropdown(String stavka){
        clickOnElement(driver, linkPadajuciMeniProizvodPremuTekstuLinka(stavka));
        return this;
    }
}
