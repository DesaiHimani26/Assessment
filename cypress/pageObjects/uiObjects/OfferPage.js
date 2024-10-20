import { BasePage } from './BasePage';

export class OfferPage extends BasePage {
    constructor() {
        super();  
    }

    verifyPageUrl() {
        this.verifyUrlContains('duurzame-energie/bestellen2/productkeuze/looptijd/');
        return this;
    }

    verifyOfferCalculationPage() {
        this.verifyUrlContains('duurzame-energie/bestellen2/overzicht/');
        return this;
    }

    verifyOfferStartDatePage(){
        this.verifyUrlContains('duurzame-energie/bestellen2/je-gegevens/start-leverdatum/');
        return this;
    }
}
