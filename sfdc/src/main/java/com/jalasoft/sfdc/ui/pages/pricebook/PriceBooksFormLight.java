/*
 * @(#)PriceBooksFormLight.java
 *
 * Copyright (c) 2018 Jala Foundation.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jala Foundation, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jala Foundation.
 */
package com.jalasoft.sfdc.ui.pages.pricebook;

import com.jalasoft.sfdc.entities.PriceBook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class that has the form to create and update the information of a Price Books of the classic light.
 *
 * @author William Claros Revollo
 * @since 9/16/2018
 */
public class PriceBooksFormLight extends PriceBooksForm {

    @FindBy(xpath = "//*[@aria-required='true']")
    private WebElement nameTxtBox;

    @FindBy(xpath = "//span[text()='Description']/parent::label/following-sibling::input")
    private WebElement descriptionTxtBox;

    @FindBy(css = ".uiInput--checkbox input")
    private WebElement statusChkBox;

    @FindBy(xpath = "//*[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton']")
    private WebElement saveBtn;

    /**
     * Method that waits until the page element is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
    }

    /**
     * Method that performs the setting of information of the fields of the form.
     *
     * @param priceBook value of the field to be set.
     */
    @Override
    public PriceBookDetailPage setFormPriceBook(PriceBook priceBook) {
        driverTools.setInputField(nameTxtBox, priceBook.getPriceBookName());
        driverTools.setInputField(descriptionTxtBox, priceBook.getDescription());
        if(priceBook.getStatus().equalsIgnoreCase("false") || priceBook.getStatus().isEmpty()){
            driverTools.clearChkBox(statusChkBox);
        }else{
            driverTools.selectChkBox(statusChkBox);
        }
        driverTools.clickElement(saveBtn);
        return new PriceBookDetailPageLight();
    }
}
