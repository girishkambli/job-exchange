Feature: Match workers with jobs

  Background:
    Given baseUri is "http://localhost:8082/"
    And the client performs DELETE request on "/workers"
    And the client performs DELETE request on "/jobs"

  Scenario: Inactive worker does not match any job
    Given request body from file "data/01_inactive-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/00_all-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"


  Scenario: Active worker matches jobs
    Given request body from file "data/02_active-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/02_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "1"


  Scenario: Required Certificates do not match with worker's certificates
    Given request body from file "data/03_certs-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/03_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"


  Scenario: Job Title does not match with worker's skills
    Given request body from file "data/04_skills-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/04_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"


  Scenario: Job needs Driving License, worker does not have it, match fails
    Given request body from file "data/05_DL-missing-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/05_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"

  Scenario: Job does not need Driving License, worker does not have it, match passes
    Given request body from file "data/06_DL-missing-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/06_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "1"

  Scenario: Job does not need Driving License, worker has it, match passes
    Given request body from file "data/07_DL-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/07_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "1"


  Scenario: Job outside max distance from worker, match fails
    Given request body from file "data/08_location-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/08_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"


  Scenario: Job start day is not within worker's availability, match fails
    Given request body from file "data/09_no-availability-workers.json"
    And content type is "application/json"
    When the client performs POST request on "/workers"
    Then status code is 200

    And request body from file "data/09_matching-jobs.json"
    And content type is "application/json"
    When the client performs POST request on "/jobs"
    Then status code is 200

    When the client performs GET request on "matches/1"
    Then status code is 200
    And response contains property "count" with value "0"

