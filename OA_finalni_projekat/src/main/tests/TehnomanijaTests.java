package main.tests;

import main.pom_files.HomePage;
import main.pom_files.ItShopPage;
import main.pom_files.LaptopsPage;
import main.pom_files.RegisterPage;
import main.selenium_core.DriverManager;
import main.selenium_core.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TehnomanijaTests {

    String URL = "https://www.tehnomanija.rs/";
    int waitForCaptcha = 120;
/*
    @BeforeMethod
    public void setup(){
        DriverManager driverManager;
        WebDriver driver = null;

        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }*/
    /**
     * I
     * 1. Navigate to https://www.tehnomanija.rs/
     * 2. Hover over "Prijava"
     * 3. Click register
     * 4. Fill the form "Formular za registraciju"
     * (This includes the extra details fields and Captcha; If needed you can complete the capcha manually)
     * 5. Click "Registracija" button
     * 6 Verify that the user is registered message is shown.
     * */

    @Test
    public void registracijaKorisnika(){
        WebDriver driver = null;

        String ime = "Testerica";
        String prezime = "Testerovic";
        String email = "Viktorija.krivokapic25+1@gmail.com";
        String lozinka = "Test123!@#";
        String brojTelefona = "0123456789";
        String datumRodjenja = "01.01.1970";
        String expectedText = "Uspešno ste se registrovali.";
        String errorMesssage = "Ocekivani i posmatrani text nisu identicni.";


        DriverManager driverManager;

        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        HomePage hp = new HomePage(driver);
        hp.hoverPrijava(driver)
                .waitForTooltip()
                .clickToRegister();

        RegisterPage rp = new RegisterPage(driver);
        rp.waitForTitle(driver)
                .acceptCookies();
        rp.populateName(ime)
                .populateSurname(prezime)
                .populateEmail(email)
                .confirmEmail(email)
                .populatePassword(lozinka)
                .confirmPassword(lozinka)
                .populateNumber(brojTelefona)
                .populateDateOfBirth(datumRodjenja)
                .acceptTerms();


        // cekaj 'waitForCaptcha' sekundi dok korisnik ne klikne captcha
        System.out.println("Imate "+waitForCaptcha+" sekundi da rijesite kepcu!");

        //rp.waitForCaptchaToBeSolved();

        //driver.manage().timeouts().implicitlyWait(waitForCaptcha, TimeUnit.SECONDS);

        rp.clickToRegister();

        String actualText = rp.getTextFromSuccessfulRegisterMessage();

        assert actualText==expectedText: errorMesssage;

    }


    /**
     * II
     * 1. Navigate to https://www.tehnomanija.rs/
     * 2. Hover over "Kategorije proizvoda"
     * 3. Verify that all the menu items are displayed (text and icons)
     * 4. Hover over "IT SHOP"
     * 5. Verify that the color of the text is yellow.
     * 6. Click "IT SHOP"
     * 7. Verify that you are on the correct page by checking the URL
     * 8. Click on "Laptopovi" image
     * 9. Verify that you are on the correct page by checking the URL
     * 10. Click on "Laptop racunari" image
     * 11. Verify that you are on the correct page by checking the URL
     * 12. In the filter on the left side of the screen, check the "Honor" in "Robna marka"
     * 13. Verify that only "Honor" products are shown.
     * 14. Sort products by price descending.
     * 15. Verify that the products are sorted by price descending.**/

    @Test
    public void filterAndSortCheck(){

        String[] dropdownMeniStavke = new String[]{"CELEBRITY STORE - NOVO",
                "IGRAČKE" ,"BELA TEHNIKA","MALI KUĆNI APARATI","KLIMA UREĐAJI I GREJANJE",
                "TV I VIDEO","IT SHOP", "TELEFONIJA", "LEPOTA I ZDRAVLJE", "FOTO-APARATI I KAMERE",
                "AUDIO", "AUTO TEHNIKA", "SVE ZA KUĆU", "LIFESTYLE"};

        String dobijeniTekstStavke = null;
        String ocekivaniTekstStavke = null;
        String expectedTekstColor = "rgba(255, 203, 5, 1)";
        String stavka1 = "it-shop";
        String actualUrl = null;
        String expectedUrl = URL+stavka1;
        String linkTekst = "Laptopovi";
        String expectedUrl2 = URL+stavka1+"/"+linkTekst.toLowerCase();
        String linkTekst2 = "Laptop računari";
        String linkTekst2Formatted = linkTekst2.replace(" ","-").replace("č","c").toLowerCase();
        String expectedUrl3 = URL+stavka1+"/"+linkTekst2Formatted;
        String producer = "Honor";
        String sortItem = "Ceni opadajuće";
        String errorForDescendingPriceFailure = "Cijene nisu sortirane po opadajucem redosledu! Ocekivano je da je %s1 vece ili jednako od %s2";

        WebDriver driver = null;
        DriverManager driverManager;

        driverManager = DriverManagerFactory.getDriverManager("CHROME");
        driver = driverManager.getWebDriver();

        /** 1. Navigate to https://www.tehnomanija.rs/ */
        driver.get(URL);
        /***********************************************/

        HomePage hp = new HomePage(driver);
        hp.implicitWaitInSeconds(driver,5);

        /** 2. Hover over "Kategorije proizvoda" */
        hp.hoverKategorijeProizvoda();
        /***********************************************/

        /** 3. Verify that all the menu items are displayed (text and icons) */
        int brojStavki = hp.getNumberOfElementsInDropdownKategorijeProizvoda();

        for (int i = 1; i<=brojStavki;i++){
            dobijeniTekstStavke = hp.getItemTextFromDropdown(i);
            ocekivaniTekstStavke = dropdownMeniStavke[i-1];
            String errorText = "stavka sa dropdown menija ne odgovara ocekivanom rezultatu. Ocekivano: >>>"+ocekivaniTekstStavke+"<<<. Dobijeno: >>>"+dobijeniTekstStavke+"<<<";
            Assert.assertTrue(dobijeniTekstStavke.contentEquals(ocekivaniTekstStavke));
            //assert dobijeniTekstStavke == ocekivaniTekstStavke:errorText;
        }
        /***********************************************/

        /** 4. Hover over "IT SHOP" */
        hp.hoverStavkaFromDropdown(stavka1);
        /***********************************************/

        /** 5. Verify that the color of the text is yellow. */
        String actualTextColor = hp.getTextColorForStavka(stavka1);
        hp.implicitWaitInSeconds(driver,5);
        //assert expectedTekstColor == actualTextColor:"boje se nisu slozile!";

        Assert.assertTrue(actualTextColor.contentEquals(expectedTekstColor));
        /***********************************************/


        /** 6. Click "IT SHOP" */
        hp.clickStavkaFromDropdown(stavka1);
        /***********************************************/
        hp.implicitWaitInSeconds(driver,5);

        /** 7. Verify that you are on the correct page by checking the URL */
        actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl,actualUrl);
        //assert actualUrl==expectedUrl:"Dobijeni i ocekivani url se razlikuju. Dobijeni: "+actualUrl+", ocekivani: "+expectedUrl;
        /***********************************************/


        /** 8. Click on "Laptopovi" image */
        ItShopPage itShopPage = new ItShopPage(driver);
        itShopPage.clickOnImageLink(linkTekst);

        /***********************************************/
        hp.implicitWaitInSeconds(driver,5);
        /** 9. Verify that you are on the correct page by checking the URL */
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl2,actualUrl);
        /***********************************************/

        /** 10. Click on "Laptop racunari" image */
        itShopPage.clickOnImageLink(linkTekst2);
        /***********************************************/

        hp.implicitWaitInSeconds(driver,5);

        /** 11. Verify that you are on the correct page by checking the URL */
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl3,actualUrl);
        /***********************************************/

        hp.implicitWaitInSeconds(driver,5);
        LaptopsPage laptopsPage = new LaptopsPage(driver);

        /** 12. In the filter on the left side of the screen, check the "Honor" in "Robna marka" */
        laptopsPage.acceptCookies();
        laptopsPage.filterByProducer(producer);
        /***********************************************/
        hp.implicitWaitInSeconds(driver,5);
        /** 13. Verify that only "Honor" products are shown. */
        int numberOfProducts = laptopsPage.getNumberOfProducts();
        String currentDescriptionText = null;
        for (int i = 1; i<= numberOfProducts; i++){
        currentDescriptionText = laptopsPage.getProductDescriptionTextByIndex(i);
            System.out.println(currentDescriptionText);
            Assert.assertTrue(currentDescriptionText.contains(producer));
        }
        /***********************************************/
        hp.implicitWaitInSeconds(driver,5);
        /** 14. Sort products by price descending. */
        laptopsPage.sortByText(sortItem);
        /***********************************************/


        /** 15. Verify that the products are sorted by price descending. */
        String currentPricePrecendent = null;
        String currentPriceSucceder = null;
        for (int i = 1; i < numberOfProducts; i++){
            currentPricePrecendent = laptopsPage.getProductPrice(i);
            currentPriceSucceder = laptopsPage.getProductPrice(i+1);

            currentPricePrecendent = currentPricePrecendent.split(" ")[0];
            currentPriceSucceder = currentPriceSucceder.split(" ")[0];
;
            currentPricePrecendent = currentPricePrecendent.replaceAll("\\.","");
            currentPriceSucceder = currentPriceSucceder.replaceAll("\\.","");

            int iCurrentPricePrecendent = Integer.parseInt(currentPricePrecendent);
            int iCurrentPriceSucceder = Integer.parseInt(currentPriceSucceder);

            assert iCurrentPricePrecendent>=iCurrentPriceSucceder:String.format(errorForDescendingPriceFailure, currentPricePrecendent,currentPriceSucceder);
            System.out.println(currentDescriptionText);
            Assert.assertTrue(currentDescriptionText.contains(producer));
        }
        /***********************************************/


    }
}
