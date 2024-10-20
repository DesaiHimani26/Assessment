import { BasePage } from './BasePage';

export class QuestionsPage extends BasePage {
    constructor() {
        super();  
    }

    verifyPageUrl() {
        this.verifyUrlContains('duurzame-energie/bestellen2/energiekeuze/');
        return this;
    }

    enterConsumptionData(data) {
        this.enterAnswerFor("Stroom normaal verbruik per jaar", data.normalConsumption);
        this.enterAnswerFor("Stroom dal verbruik per jaar", data.offPeakConsumption);
        this.enterAnswerFor("Gasverbruik per jaar", data.gasConsumption);
        return this;
    }
}
