import { APIClient } from "../../support/utils/api_v1.js";
import * as ENDPOINTS from "../../support/endpoints.js";
const { faker } = require('@faker-js/faker');



export class UsersAPI {

    constructor() {
        this.token =  Cypress.env('ACCESS_TOKEN');
        console.log("Loaded Token:", this.token);
        this.header = {
            Authorization: `Bearer ${this.token}`,
          };
      }
    
      
      // Generate fake user data
      generateFakeUser() {
        return {
          name: faker.person.fullName(),
          gender: faker.helpers.arrayElement(['male', 'female']),
          email: faker.internet.email(),
          status: faker.helpers.arrayElement(['active', 'inactive']),
        };
      }
    

    createUser(body) {
        return APIClient.postRequest(ENDPOINTS.usersEndpoint,body,this.header)
    }

    getUser(userId){
        return APIClient.getRequest(ENDPOINTS.usersEndpoint+`/${userId}`,this.header)
    }

    getUsers(qs){
        return APIClient.getRequest(ENDPOINTS.usersEndpoint,this.header,qs)
    }

    updateUser(userId, body){
        return APIClient.putRequest(ENDPOINTS.usersEndpoint+`/${userId}`,body,this.header)
    }

    deleteUser(userId){
        return APIClient.deleteRequest(ENDPOINTS.usersEndpoint+`/${userId}`,this.header)
    }

}
