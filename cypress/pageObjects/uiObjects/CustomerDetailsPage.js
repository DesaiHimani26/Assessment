import { BasePage } from './BasePage';
const { faker } = require('@faker-js/faker');

export class CustomerDetailsPage extends BasePage {
    constructor() {
        super();  
    }

    enterAddressDetails(){
        this.inputByName("firstName", faker.person.firstName());
        this.inputByName("initials",'T.A');
        this.inputByName("surname", faker.person.lastName());
        this.inputByName("day", faker.date.birthdate().getDate());
        this.inputByName("month",faker.date.birthdate().getMonth());
        this.inputByName("year",'1990')
        return this;
    }
    
    enterContactDetails(){
        this.inputByName("phoneNumber", '0123456789');
        this.inputByName("emailAddress", faker.internet.exampleEmail());
        return this;
    }

    verifyPageUrl() {
        this.verifyUrlContains('duurzame-energie/bestellen2/je-gegevens/persoonlijke-gegevens/');
        return this;
    }
}
