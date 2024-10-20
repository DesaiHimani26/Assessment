import { BasePage } from './BasePage';

export class HomePage extends BasePage {
    constructor() {
        super();  // Inherit methods from BasePage
    }

    visitHomePage() {
        this.visit('/', 'Energieleverancier Eneco - duurzame energie van iedereen');
        return this;
    }

    enterAddressData(postcode, houseNumber) {
        this.inputByName('postalCode', postcode);
        this.inputByName('houseNumber', houseNumber);
        return this;
    }

    clickButton(buttonText) {
        this.clickFieldByLabel(buttonText);
        return this;
    }

    verifyExactAddress(addressText) {
        this.verifyTextExists(addressText);
        return this;
    }
}
