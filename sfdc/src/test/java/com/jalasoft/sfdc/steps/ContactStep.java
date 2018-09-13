package com.jalasoft.sfdc.steps;


import com.jalasoft.sfdc.entities.Contact;
import com.jalasoft.sfdc.ui.PageFactory;
import com.jalasoft.sfdc.ui.pages.allappspage.AllAppsPage;
import com.jalasoft.sfdc.ui.pages.contacts.ContactDetails;
import com.jalasoft.sfdc.ui.pages.home.HomePage;
import com.jalasoft.sfdc.ui.pages.contacts.ContactForm;
import com.jalasoft.sfdc.ui.pages.contacts.ContactListPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class ContactStep {
    private HomePage homePage;
    private AllAppsPage allAppsPage;
    private ContactListPage contactListPage;
    private ContactForm contactForm;
    private ContactDetails contactDetails;
    private Contact contact;


    /*private ContactStep (Contact contact) {
        this.contact = contact;
    }*/


    @When("^I go to Contact page$")
    public void iGoToContactPage() throws Throwable {
        homePage = PageFactory.getHomePage();
        allAppsPage = homePage.topMenu.goToAllPages();
        contactListPage = allAppsPage.goToContact();
    }

    @And("^I click on button New$")
    public void iClickOnButtonNew() throws Throwable {
        contactForm = contactListPage.goToContactNewForm();
    }


    @And("^I fill all the following information")
    public void iFillAllTheFollowingInformation(List<Contact> contactList) {
//        this.contact.setAccountName(contactList.get(0).getAccountName());
        this.contact = contactList.get(0);
        contactDetails = contactForm.fillContactForm(contact);
    }


    @Then("^I should see the contact created correctly$")
    public void iShouldSeeTheContactCreatedCorrectly() throws Throwable {
        contactDetails.getValidateContact();
        assertEquals(contact.getFullName(), contactDetails.getContactNameLbl());
        //assertEquals(contact.getLastName(), contactDetails.getLastNameTextBox());
        assertEquals(contact.getHomePhone(), contactDetails.getPhoneTextBox());
        //assertEquals(contact.getFirstName(), contactDetails.getFirstNameTextBox());
        assertEquals(contact.getEmail(), contactDetails.getEmailTextBox());
        //assertEquals(contact.getOtherStreet(), contactDetails.getOtherStreetTextBox());
        //assertEquals(contact.getOtherCity(), contactDetails.getOtherCityTextBox());
        //assertEquals(contact.getOtherState(), contactDetails.getOtherStateTextBox());
    }
}
