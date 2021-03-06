/*
 * @(#)PriceBookDetailPageLight.java
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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Class that has the Price Book detail page of the classic light.
 *
 * @author William Claros Revollo
 * @since 9/16/2018
 */
public class PriceBookDetailPageLight  extends PriceBookDetailPage{

    @FindBy(xpath = "//span[text()='Price Book Name']/parent::div/following-sibling::div/child::span/child::span[@class='uiOutputText']")
    private WebElement priceBookNameTxt;

    @FindBy(xpath = "//span[text()='Description']/parent::div/following-sibling::div/child::span/child::span[@class='uiOutputText']")
    private WebElement priceBookDescriptionTxt;

    /**
     * Method that waits until the page element is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(priceBookNameTxt));
    }

    /**
     * Method that is responsible for obtaining text of a WebElement.
     *
     * @return returns the text of the WebElement.
     */
    @Override
    public String getPriceBookNameTxt() {
        return priceBookNameTxt.getText();
    }

    /**
     * Method that is responsible for obtaining text of a WebElement.
     *
     * @return returns the text of the WebElement.
     */
    @Override
    public String getPriceBookDescriptionTxt() {
        return priceBookDescriptionTxt.getText();
    }
}
