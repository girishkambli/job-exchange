# job-exchange
Matching engine for a job exchange

The project has test coverage with unit-tests as well as integration-tests.

Unit-tests are located under src/test
Integration-tests are located under src/itest

The integration-tests are specified using Gherkin/Cucumber in file src/itest/resources/job-matches.feature

To run all the tests, run: mvn clean verify

The implementaion is based on below premises:
1) Inactive worker should not be macthed to any job.
2) A worker can start a job only on his/her available days.
3) Worker Certificate is matched to required job cerificates for exact match.
4) Worker skill is matched to job title for exact match.
5) JobSearchAddress conatins worker's location, Job.Location is the job's location. If distance between the two locations
is more than JobSearchAddress.maxJobDistance, then match fails

To run standalone application, run: mvn spring-boot:run

Port: 8082
Endpoints:
1) /workers : POST to upload workers
              DELETE to remove all workers
2) /jobs : POST to upload jobs
           DELETE to remove all jobs
3) /matches/{workerId} : GET to find job matches for the worker            
              
