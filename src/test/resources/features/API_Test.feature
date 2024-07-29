@APITest
Feature: Backend Test to verify Trello API Request

  Background:
    Given POST Request is sent to Trello to create Board with Unique Name

  @BoardCreation
  Scenario: Verify POST Request to Create New Board
    Then Verify Request is successful with response code 200
    And Response body should have correct board name
    And Response Body contains ID

  @BoardDetailsValidation
  Scenario: Verify GET Request for Board and Schema
    When Response Body contains ID
    Given GET Request is sent to get Board Details
    When Verify Request is successful with response code 200
    And Verify Status line should be "HTTP/1.1 200 OK"
    And Verify response header has following details
      | Content-Type                    | Connection |
      | application/json; charset=utf-8 | keep-alive |
    And Verify response time is less than 1000 ms
    And Verify response schema for "board"
    And Response body should have correct board name

  @AccessDeletedBoard
  Scenario: Verify DELETE and PUT Request for Board
    When Response Body contains ID
    Given DELETE Request is sent to delete Board
    When Verify Request is successful with response code 200
    Given PUT Request is sent to update Board Name
    When Verify Request is successful with response code 404





