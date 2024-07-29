@UITest
Feature: Trello UI Test Scenarios

  Background: Front End Test for Trello
    Given user is on Trello login page
    When user enters valid username and password to login
      | email                    | password  |
      | desai.himani@yahoo.co.in | Trello@26 |
    Given User click Create to create New Board
    And User creates board with Board unique title

  @TC01
  Scenario: Create, Close & Delete New Board
    Then New board is created with title
    When User closes current board
    Then Message "This board is closed. Reopen the board to make changes." is displayed
    And User deletes current board
    Then User verifies board is deleted

  @TC02
  Scenario: Card Creation and Move to Other List
    When User create lists "To Do"
    Then User adds cards in list
      | Test Data creation   |
      | Ready for Automation |
      | Test Metrics         |
    And User create another lists "Doing"
    And User moving the old list task to new list "Doing"
      | Test Data creation |
    

  @TC03
  Scenario Outline: List Creation & archival
    When User create lists "<listTitle>"
    And User Archives list "<listTitle>"
    Then User verify "<listTitle>" list is Archived
    Examples:
      | listTitle |
      | List1     |
      | List2  |