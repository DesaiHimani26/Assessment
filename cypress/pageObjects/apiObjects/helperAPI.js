import { UsersAPI } from "../../pageObjects/apiObjects/UsersAPI";

const usersAPI = new UsersAPI();


export const createRandomUser = () => {
    const user = usersAPI.generateFakeUser();
    return usersAPI.createUser(user).then((response) => {
        expect(response.status).to.eq(201);
        return { id: response.body.id, user }; 
    });
};


export const verifyUserDetails = (userId, expectedUser,responseBody) => {
    expect(responseBody).to.have.property('id', userId);
    if (expectedUser.name) {
        expect(responseBody).to.have.property('name', expectedUser.name);
    }
};

export const updateUserDetails = (userId, updatedUser) => {
    return usersAPI.updateUser(userId, updatedUser).then((response) => {
        expect(response.status).to.eq(200);
        expect(response.body).to.have.property('name', updatedUser.name);
        return response.body;
    });
};

export const deleteUserAndVerify = (userId) => {
    return usersAPI.deleteUser(userId).then((response) => {
        expect(response.status).to.eq(204);
    });
};
