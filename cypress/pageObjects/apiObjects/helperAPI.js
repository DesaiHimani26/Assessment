import { UsersAPI } from "./UsersAPI";

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


export const fetchPaginatedUsers = (pageNumber, itemsPerPage) => {
    const qs = {
        page:pageNumber,
        per_page: itemsPerPage,
    }
    return usersAPI.getUsers(qs)
    .then((response) => {
        expect(response.status).to.equal(200);
        return response.body;
    });
};

export const validatePagination = (firstPageUsers, secondPageUsers, itemsPerPage) => {
    expect(firstPageUsers).to.have.length(itemsPerPage);
    expect(secondPageUsers).to.have.length(itemsPerPage);

    // Check for No Common users between pages
    const firstPageIds = firstPageUsers.map((user) => user.id);
    const secondPageIds = secondPageUsers.map((user) => user.id);

    secondPageIds.forEach((id) => {
        expect(firstPageIds).to.not.include(id);
    });
};