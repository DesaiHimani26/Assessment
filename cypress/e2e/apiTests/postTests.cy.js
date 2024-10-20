import { createRandomUser } from '../../pageObjects/apiObjects/helperAPI';
import { PostsAPI } from "../../pageObjects/apiObjects/PostsAPI";

const postsAPI = new PostsAPI();

describe('Posts API', () => {

    const createPostForUser = (userId) => {
        const postBody = postsAPI.generateFakePost(userId);
        return postsAPI.createPost(postBody).then((response) => {
            expect(response.status).to.eq(201);
            expect(response.body).to.have.property('id');
            return response.body;
        });
    };

    it('Verify Posts Endpoint - POST', () => {
        createRandomUser().then(({ id }) => {
            createPostForUser(id);
        });
    });

    it('Verify Posts Endpoint - GET', () => {
        createRandomUser().then(({ id }) => {
            createPostForUser(id).then((post) => {
                postsAPI.getPosts(post.id).then((response) => {
                    expect(response.status).to.eq(200);
                    expect(response.body).to.have.property('id', post.id);
                    expect(response.body).to.have.property('user_id', id);
                });
            });
        });
    });

    it('Verify Posts Endpoint - PUT', () => {
        createRandomUser().then(({ id }) => {
            createPostForUser(id).then((post) => {
                const updatedPost = postsAPI.generateFakePost(id, post.id);
                postsAPI.updatePosts(post.id, updatedPost).then((response) => {
                    expect(response.status).to.eq(200);
                    expect(response.body).to.have.property('id', post.id);
                    expect(response.body).to.have.property('user_id', id);
                    expect(response.body).to.have.property('title', updatedPost.title);
                    expect(response.body).to.have.property('body', updatedPost.body);
                });
            });
        });
    });

    it('Verify Posts Endpoint - DELETE', () => {
        createRandomUser().then(({ id }) => {
            createPostForUser(id).then((post) => {
                postsAPI.deletePosts(post.id).then((response) => {
                    expect(response.status).to.eq(204);
                });
            });
        });
    });

});
