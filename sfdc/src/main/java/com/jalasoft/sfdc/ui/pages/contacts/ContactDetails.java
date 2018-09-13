package com.jalasoft.sfdc.ui.pages.contacts;

import com.jalasoft.sfdc.ui.BasePage;

public abstract class ContactDetails extends BasePage {
    public abstract String getContactNameLbl();
    public abstract void getValidateContact();
    public abstract ContactForm goToEditContactNewForm();
    public abstract String getPhoneTextBox();
    public abstract String getFirstNameTextBox();
    public abstract String getLastNameTextBox();
    public abstract String getEmailTextBox();
    public abstract String getOtherStreetTextBox();
    public abstract String getOtherCityTextBox();
    public abstract String getOtherStateTextBox();

}
