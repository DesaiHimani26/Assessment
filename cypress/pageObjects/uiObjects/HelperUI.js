
/**
 * Helper function to complete the questions flow.
 * @param {QuestionsPage} questionsPage - The page object for the Questions page.
 * @param {Object} data - The test data object that contains answers for the questions.
 */
export const completeQuestionsFlow = (questionsPage, data) => {
    questionsPage
        .verifyPageUrl()
        .selectOption(data.energyType)
        .clickNext()
        .selectOption(data.consumptionKnown)
        .clickNext()
        .selectOption(data.smartMeter)
        .enterConsumptionData(data.consumptionData)
        .clickNext()
        .selectOption(data.solarPanel)
        .clickNext()
        .selectOption(data.isMoving)
        .clickNext();
};

/**
 * Helper function to complete the offer flow.
 * @param {OfferPage} offerPage - The page object for the Offer page.
 * @param {Object} data - The test data object that contains offer details.
 */
export const completeOfferFlow = (offerPage, data) => {
    offerPage
        .verifyPageUrl()
        .selectOption(data.contractType)
        .selectOption(data.dynamicContractOption)
        .clickNext()
        .verifyOfferCalculationPage()
        .selectOption('Naar je gegevens')
        .verifyOfferStartDatePage()
        .clickNext()
        .selectOption(data.sameAddress)
        .clickNext();
};


export const completeCustomerData = (customerDetailsPage) => {
    customerDetailsPage
        .verifyPageUrl()
        .selectOption('Mevr.')
        .enterAddressDetails()
        .clickNext()
        .enterContactDetails()
        .selectOption('Controleer je bestelling');
};