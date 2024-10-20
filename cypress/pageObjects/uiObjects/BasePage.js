export class BasePage {
    constructor() {
        this.elements = {
            fieldWithLabel: (labelText) => cy.getByDataLabel(labelText),
            inputWithLabel: (labelText) => cy.getInputByLabel(labelText),
            inputWithName: (fieldName) => cy.getInputByName(fieldName),
            buttonWithText: (buttonText) => cy.getByButtonText(buttonText),
        };
    }

    visit(url="/", expectedTitle = null) {
        cy.visit(url);
        if (expectedTitle) {
            cy.title().should('equal', expectedTitle);
        }
        return this;
    }

    enterAnswerFor(fieldName, value) {
        this.elements.inputWithLabel(fieldName).clear().type(value);
        return this;
    }

    clickNext() {
        this.clickFieldByLabel('Volgende');
        return this;
    }

    clickFieldByLabel(labelText) {
        this.elements.fieldWithLabel(labelText).last().click();
        return this;
    }

    inputByName(fieldName, value) {
        this.elements.inputWithName(fieldName).scrollIntoView().clear().type(value);
        return this;
    }

    selectOption(optionText) {
        this.clickFieldByLabel(optionText);
        return this;
    }

    verifyTextExists(text) {
        cy.contains(text).should('exist').and('be.visible');
        return this;
    }

    verifyUrlContains(text) {
        cy.url().should('contain', text);
        return this;
    }

}