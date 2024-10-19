export class APIClient {

    static getRequest(endpoint, header = null, queries = {}) {
        return cy.api({
            method: 'GET',
            url: endpoint,
            qs: queries,
            headers: header,
            failOnStatusCode: false
        })
            .then((response) => {
                return response;
            })
    }

    static postRequest(endpoint, payload = null, header = null, queries = {}) {
        return cy.api({
            method: 'POST',
            url: endpoint,
            qs: queries,
            headers: header,
            body: payload,
            failOnStatusCode: false
        })
            .then((response) => {
                return response;
            })
    }

    static putRequest(endpoint, payload = null, header = null, queries = {}) {
        return cy.api({
            method: 'PUT',
            url: endpoint,
            qs: queries,
            headers: header,
            body: payload
        })
            .then((response) => {
                return response;
            })
    }

    static deleteRequest(endpoint, header = null, queries = {}) {
        return cy.api({
            method: 'DELETE',
            url: endpoint,
            qs: queries,
            headers: header,
            failOnStatusCode: false
        })
            .then((response) => {
                return response;
            })
    }
}