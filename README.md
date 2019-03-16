# job-exchange
Matching engine for a job exchange

The project has test coverage with unit-tests as well as integration-tests.
unit-tests located under src/test
integration-tests located under src/itest

The integration-tests are specified using Gherkin in src/itest/resources/job-matches.feature

To run all the tests, run: mvn clean verify

The implementaion is based on below premises:
1) Inactive worker should not be macthed to any job.
2) A worker can start a job only on his/her available days.
3) Worker Cerificate is matched to required job cerificates for exact match.
4) Worker skill is matched to job title for exact match.
5) JobSearchAddress conatins worker's location, Job.Location is job's location. If distance between the two
is more than JobSearchAddress.maxJobDistance, then match fails

To run standalone application:
