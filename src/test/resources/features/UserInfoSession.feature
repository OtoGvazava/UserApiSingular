Feature: User Info API Session (/info)

  Scenario: Retrieve user information with a valid session
    Given a valid authorization token
    When they make a GET request to info endpoint
    Then they should receive a response with status code 200

  Scenario: Retrieve user information with an invalid session
    Given a invalid authorization token
    When they make a GET request to info endpoint
    Then they should receive a response with status code 401