/*
 * @(#)ProductsSteps.java
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
package com.jalasoft.sfdc.steps;

import com.jalasoft.sfdc.api.APIProduct;
import com.jalasoft.sfdc.entities.Container;
import com.jalasoft.sfdc.entities.Product;
import com.jalasoft.sfdc.ui.PageFactory;
import com.jalasoft.sfdc.ui.pages.allappspage.AllAppsPage;
import com.jalasoft.sfdc.ui.pages.home.HomePage;
import com.jalasoft.sfdc.ui.pages.products.*;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.response.Response;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Class that contains the steps to execute the Product scenarios.
 *
 * @author William Claros Revollo
 * @since 9/11/2018
 */
public class ProductsSteps {

    private AllAppsPage allAppsPage;
    private ProductsListPage productsListPage;
    private HomePage homePage;
    private ProductsForm productsForm;
    private ProductsDetailPage productsDetailPage;
    private ProductAddStandardPrice productAddStandardPrice;
    private ProductAddPriceBooks productAddPriceBooks;
    private Product product;
    private Response response;
    private Container container;
    private Product productAPI;
    private APIProduct apiProduct;

    public ProductsSteps(Container container) {
        this.container = container;
    }

    @And("^I go to Products page$")
    public void iGoToProductsPage() {
        homePage = PageFactory.getHomePage();
        allAppsPage = homePage.topMenu.goToAllPages();
        productsListPage = allAppsPage.goToProductsListPage();
    }

    @And("^I click New Product button$")
    public void iClickNewButtonProducts() {
        productsForm = productsListPage.clickNewBtn();
    }

    @And("^I create a Product with the following information$")
    public void iCreateANewProduct(final List<Product> productList) {
        this.product = productList.get(0);
        apiProduct = new APIProduct(product);
        product.updateName();
        container.setProduct(product);
        productsDetailPage = productsForm.createProduct(product);
    }

    @When("^I click Edit Product button$")
    public void iClickEditButton() {
        productsForm = productsDetailPage.clickEditBtn();
    }

    @And("^I edit the Product information$")
    public void iEditInformationOfAProduct(final List<Product> productListEdit)  {
        this.product = productListEdit.get(0);
        apiProduct = new APIProduct(product);
        productsDetailPage = productsForm.editProduct(product);
    }

    @Then("^the (?:created|edited) product should be displayed in the Product Detail Page$")
    public void theProductInformationCreatedShouldBeDisplayedInTheProductsListPage() {
        assertEquals(product.getProductName(), productsDetailPage.getProductNameTxt());
        assertEquals(product.getProductCode(), productsDetailPage.getProductCodeTxt());
        assertEquals(product.getProductDescription(), productsDetailPage.getProductDescriptionTxt());
        //assertEquals(product.getStatusActive(), String.valueOf(productsDetailPage.getStatusChkBox()));
    }

    @When("^I click Delete Product button$")
    public void iClickDeleteButton() {
        productsListPage = productsDetailPage.clickDeleteBtn();
    }

    @Then("^the Product deleted shouldn't be displayed in the Product Detail Page$")
    public void theProductInformationDeleteShouldnTBeDisplayedInTheProductDetailPage()  {
        assertFalse(productsDetailPage.verifyDeletedProduct(product), "The product wasn't removed correctly");
    }

    @Then("^the Product should be (?:created|updated)$")
    public void theProductShouldBeVerifiedByAPI() {
        apiProduct = new APIProduct(product);
        apiProduct.createProductByAPI();
        productAPI = apiProduct.getProductsValuesByAPI();
        assertEquals(product.getProductName(), productAPI.getProductName());
        assertEquals(product.getProductCode(), productAPI.getProductCode());
        assertEquals(product.getProductDescription(), productAPI.getProductDescription());
    }

    @Then("^the Product should be deleted$")
    public void theProductShouldDeletedByAPI() {
        response = apiProduct.deleteProductByAPI();
        assertTrue(response.asString().contains("entity is deleted"));
    }

    @After(value = "@DeleteProduct", order = 999)
    public void afterProductScenario() {
        apiProduct.deleteProductByAPI();
    }

    @Given("^I have created a product with the following information$")
    public void iHaveCreatedAProductWithTheFollowingInformation(final List<Product> productList) {
        this.product = productList.get(0);
        apiProduct = new APIProduct(product);
        container.setProduct(product);
        response = apiProduct.createProductByAPI();
    }

    @And("^I go by URL to the Product Details page$")
    public void iSelectTheProductByURL()  {
        productsDetailPage = productsListPage.goToTheDetailsPage(product);
    }

    @And("^I add the Product to the Standard Price Book$")
    public void iAddTheProductToTheStandardPriceBook() {
        iGoToProductsPage();
        productsDetailPage = productsListPage.goToTheDetailsPage(product);
        productAddStandardPrice = productsDetailPage.gotoAddStandardPrice();
        productsDetailPage = productAddStandardPrice.goToDetailPage();
        productAddPriceBooks = productsDetailPage.gotoAddPriceBook();
        productsDetailPage = productAddPriceBooks.filldataPriceBook();

    }

}
