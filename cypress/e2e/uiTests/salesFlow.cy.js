import { HomePage } from "../../pageObjects/uiObjects/homePage";
import { OfferPage } from "../../pageObjects/uiObjects/OfferPage";
import { QuestionsPage } from "../../pageObjects/uiObjects/QuestionsPage"
import { completeQuestionsFlow, completeOfferFlow, completeCustomerData } from "../../pageObjects/uiObjects/HelperUI";
import { CustomerDetailsPage } from "../../pageObjects/uiObjects/CustomerDetailsPage";

const homePage = new HomePage();
const customerDetailsPage = new CustomerDetailsPage();
const offerPage = new OfferPage();
const questionsPage = new QuestionsPage();
let data;

before('Load fixture', () => {
    cy.fixture('uiTestData').then((dataFromFixture) => {
        data = dataFromFixture;
    });
});


describe('Add from Inventory, Checkout and Confirm', () => {

    before(() => {
        homePage.visitHomePage('Energieleverancier Eneco - duurzame energie van iedereen')
            .clickButton('Accepteren');
    })

    it('Sales Flow', () => {
        homePage
            .enterAddressData(data.postcode, data.houseNumber)
            .verifyExactAddress(data.exactAddress)
            .clickButton("Bereken je maandbedrag");

        completeQuestionsFlow(questionsPage, data.questions);

        completeOfferFlow(offerPage, data.offerData);

        completeCustomerData(customerDetailsPage, data);
       
        cy.intercept('POST', 'https://api-digital.enecogroup.com/dxpweb/public/v3/nl/eneco/products/offerproducts').as('offerProductsAPI');

        customerDetailsPage.selectOption('Controleer je bestelling');

        
        cy.wait('@offerProductsAPI').then((result) => {
            expect(result.response.statusCode).to.eq(200);

            const responseData = result.response.body.data;
            const usages = responseData.usages;

            // Validate API Response for electricityNormal, electricityLow, and gas
            expect(usages.electricityNormal).to.eq(data.questions.consumptionData.normalConsumption);  // Replace 1200 with the expected value from your test data
            expect(usages.electricityLow).to.eq(data.questions.consumptionData.offPeakConsumption);     // Replace with expected value
            expect(usages.gas).to.eq(data.questions.consumptionData.gasConsumption);   
        });

        cy.getByDataLabel('Aanmelden en maandelijks betalen').should('be.visible');

    })

})