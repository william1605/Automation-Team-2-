package com.jalasoft.sfdc.ui.pages.contacts;

import com.jalasoft.sfdc.entities.Contact;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Contact form Page type light class.
 *
 * @author Yerel Hurtado
 * @since 09/10/2018
 */
public class ContactFormLight extends ContactForm {

    @FindBy(xpath = "//*[@class='lastName compoundBLRadius compoundBRRadius form-element__row input']")
    private WebElement lastNameTxtBox;

    @FindBy(css = "input.firstName")
    private WebElement firstNameTxtBox;

    //@FindBy(xpath = "//span[text()='Last Name']/parent::label/following-sibling::input")
    //private WebElement lastNameTxt;

    @FindBy(xpath = "//span[text()='Title']/parent::label/following-sibling::input")
    private WebElement titleTxtBox;

    @FindBy(xpath = "//span[text()='Email']/parent::label/following-sibling::input")
    private WebElement emailTxtBox;

    @FindBy(xpath = "//span[text()='Phone']/parent::label/following-sibling::input")
    private WebElement phoneTxtBox;

    @FindBy(xpath = "//span[text()='Other Street']/parent::label/following-sibling::TextArea")
    private WebElement streetTxtBox;

    @FindBy(xpath = "//span[text()='Other City']/parent::label/following-sibling::input")
    private WebElement cityTxtBox;

    @FindBy(xpath = "//span[text()='Other State/Province']/parent::label/following-sibling::input")
    private WebElement stateTxtBox;

    @FindBy(xpath = "//span[text()='Other Country']/parent::label/following-sibling::input")
    private WebElement countryTxtBox;

    @FindBy(css = "button[title|='Save']")
    private WebElement saveBtn;

    /**
     * fill all fields of contact form.
     *
     * @param contact - last name of the contact.
     * @return contact details type light.
     */
    @Override
    public ContactDetails fillContactForm(Contact contact){
        driverTools.setInputField(lastNameTxtBox, contact.getLastName());
        driverTools.setInputField(firstNameTxtBox, contact.getFirstName());
        driverTools.setInputField(phoneTxtBox, contact.getHomePhone());
        driverTools.setInputField(titleTxtBox, contact.getTitle());
        driverTools.setInputField(emailTxtBox, contact.getEmail());
        driverTools.setInputField(streetTxtBox, contact.getOtherStreet());
        driverTools.setInputField(cityTxtBox, contact.getOtherCity());
        driverTools.setInputField(stateTxtBox, contact.getOtherState());
        driverTools.setInputField(countryTxtBox, contact.getOtherCountry());
        driverTools.clickElement(saveBtn);
        return new ContactDetailsLight();
    }

    /**
     *
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
    }
}
