import { UsersAPI } from "../../pageObjects/apiObjects/UsersAPI";
const { faker } = require('@faker-js/faker');
const usersAPI = new UsersAPI();

describe('User Registration, Login & CRUD operations', () => {

    const createRandomUser = () => {
        const user = usersAPI.generateFakeUser(); // Generate a random user
        return usersAPI.createUser(user).then((response) => {
            expect(response.status).to.eq(201);
            return response.body.id; // Return the created user ID
        });
    };


    it('Verify User Endpoint - POST', () => {
        const user = usersAPI.generateFakeUser();
        usersAPI.createUser(user).then((response) => {
            expect(response.status).to.eq(201);
            expect(response.body).to.have.property('id');
            expect(response.body.name).to.eq(user.name);
        });

    });

    it('Verify User Endpoint - GET', () => {
        createRandomUser().then((userId) => {
            usersAPI.getUser(userId).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body).to.have.property('id', userId);
            });
        });
    })

    it('Verify User Endpoint - PUT', () => {
        createRandomUser().then((userId) => {
            const updatedUser = {
                name: faker.person.fullName(),
            };

            usersAPI.updateUser(userId, updatedUser).then((response) => {
                expect(response.status).to.eq(200);
                expect(response.body).to.have.property('name', updatedUser.name);
            });
        });
    })

    it('Verify User Endpoint - DELETE', () => {
        createRandomUser().then((userId) => {
            usersAPI.deleteUser(userId).then((response) => {
                expect(response.status).to.eq(204);
            });
        });
    });

    it('Verify Non-exsitent User - 404', () => {
        const nonExistentUserId = 123456; // Arbitrary non-existent user ID
        usersAPI.getUser(nonExistentUserId).then((response) => {
            expect(response.status).to.eq(404); // Expect 404 Not Found
        });
    });
});

