/*
 * @(#)ProductsFormLight.java
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
package com.jalasoft.sfdc.ui.pages.products;

import com.jalasoft.sfdc.entities.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class that has the form to create and update the information of a product of the light skin.
 *
 * @author William Claros Revollo
 * @since 9/11/2018
 */
public class ProductsFormLight extends ProductsForm {

    @FindBy(xpath = "//*[@aria-required='true']")
    private WebElement productNameTxtBox;

    @FindBy(xpath = "//span[text()='Product Code']/parent::label/following-sibling::input")
    private WebElement productCodeTxtBox;

    @FindBy(css = ".textarea")
    private WebElement descriptionTxtBox;

    @FindBy(css = ".uiInput--checkbox input")
    private WebElement activeChkBox;

    @FindBy(xpath = "//button[@title ='Save']")
    private WebElement saveBtn;

    /**
     * Method that waits until the page element is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
    }

    /**
     * Method that performs the setting of the information of the fields of the form.
     *
     * @param product value of the field to be set.
     */
    @Override
    public ProductsDetailPage setFormProduct(Product product) {
        driverTools.setInputField(productNameTxtBox, product.getProductName());
        driverTools.setInputField(productCodeTxtBox, product.getProductCode());
        driverTools.setInputField(descriptionTxtBox, product.getProductDescription());
        if(product.getStatusActive().equalsIgnoreCase("true")){
            driverTools.selectChkBox(activeChkBox);
        }
        driverTools.clickElement(saveBtn);
        return new ProductsDetailPageLight();
    }
}
