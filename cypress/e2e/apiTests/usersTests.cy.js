import { createRandomUser, verifyUserDetails, updateUserDetails, deleteUserAndVerify } from '../../pageObjects/apiObjects/helperAPI';
const { faker } = require('@faker-js/faker');
import { UsersAPI } from "../../pageObjects/apiObjects/UsersAPI";

const usersAPI = new UsersAPI();
describe('Users API', () => {

    it('Verify User Endpoint - POST', () => {
        const user = usersAPI.generateFakeUser();
        usersAPI.createUser(user).then((response) => {
            expect(response.status).to.eq(201);
            verifyUserDetails(response.body.id, user, response.body);
        });
    });

    it('Verify User Endpoint - GET', () => {
        createRandomUser().then(({ id, user }) => {
            usersAPI.getUser(id).then((response) => {
                expect(response.status).to.eq(200);
                verifyUserDetails(id, user, response.body);
            });
        });
    });

    it('Verify User Endpoint - PUT', () => {
        createRandomUser().then(({ id }) => {
            const updatedUser = { name: faker.person.fullName() };
            updateUserDetails(id, updatedUser).then(() => {
                usersAPI.getUser(id).then((response) => {
                    verifyUserDetails(id, updatedUser, response.body);
                });
            });
        });
    });

    it('Verify User Endpoint - DELETE', () => {
        createRandomUser().then(({ id }) => {
            deleteUserAndVerify(id);
        });
    });

    it('Verify RUD for Non-existent User - 404 Not Found', () => {
        const nonExistentUserId = 123456;
        const updatedUser = { name: faker.person.fullName() };

        usersAPI.getUser(nonExistentUserId).then((response) => {
            expect(response.status).to.eq(404);
        });

        usersAPI.updateUser(nonExistentUserId, updatedUser).then((response) => {
            expect(response.status).to.eq(404);
        });

        usersAPI.deleteUser(nonExistentUserId).then((response) => {
            expect(response.status).to.eq(404);
        });
    });

});
