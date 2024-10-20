import { APIClient } from "../../support/utils/api_v1.js";
import * as ENDPOINTS from "../../support/endpoints.js";
const { faker } = require('@faker-js/faker');



export class PostsAPI {

    constructor() {
        this.token = Cypress.env('ACCESS_TOKEN');
        this.header = {
            Authorization: `Bearer ${this.token}`,
        };
    }

    // Generate fake Post data for given User Id
    generateFakePost(userId, id = 0) {
        return {
            id: id,
            user_id: userId,
            title: faker.finance.transactionType(),
            body: faker.finance.transactionDescription(),
        };
    }


    createPost(body) {
        return APIClient.postRequest(ENDPOINTS.postsEndpoint, body, this.header)
    }

    getPosts(postId) {
        return APIClient.getRequest(ENDPOINTS.postsEndpoint + `/${postId}`, this.header)
    }

    updatePosts(postId, newBody) {
        return APIClient.putRequest(ENDPOINTS.postsEndpoint + `/${postId}`, newBody, this.header)
    }

    deletePosts(postId) {
        return APIClient.deleteRequest(ENDPOINTS.postsEndpoint + `/${postId}`, this.header)
    }

}
