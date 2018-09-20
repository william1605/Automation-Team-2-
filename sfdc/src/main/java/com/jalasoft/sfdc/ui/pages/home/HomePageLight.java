package com.jalasoft.sfdc.ui.pages.home;

import com.jalasoft.sfdc.ui.pages.profiles.ProfilePage;
import com.jalasoft.sfdc.ui.pages.profiles.ProfilePageLight;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePageLight extends HomePage {

    @FindBy(xpath = "//*[@class='slds-icon-waffle']")
    private WebElement userProfileName;

    @FindBy(xpath = "//span[contains(text(), 'Go Mobile')]")
    private WebElement goMobiloeLbl;

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(goMobiloeLbl));
    }
}
