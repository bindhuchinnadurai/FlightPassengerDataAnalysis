# Journey inferences from flights and passenger data

*** Version 1.0.0 ***

Given the flights and passenger data in the form of a csv, the projects aims at reading and querying the data for the following use cases.

- ![image](https://user-images.githubusercontent.com/39429634/123937614-4029d980-d98e-11eb-9c3d-b63b71dc1da3.png)
- ![image](https://user-images.githubusercontent.com/39429634/123937690-559f0380-d98e-11eb-8ed5-3817d407a65c.png)
- ![image](https://user-images.githubusercontent.com/39429634/123937841-7cf5d080-d98e-11eb-8e99-dd1656b2ec5e.png)
- ![image](https://user-images.githubusercontent.com/39429634/123937876-87b06580-d98e-11eb-9d76-e41b65eae77d.png)

The code is written in Scala. This is my first attempt in writting scala code. So any suggestions would be greatly appreciated.


## License and copyright
    Bindhu Chinnadurai
  
## Code responsibility
- src
  -  main
    - scala
      - com.bindhu.flights
        - FlightApplication.scala /** To generate the output for the 4 usecases listed above */
        - FlightDataReader.scala /** Trait with functions declared to read data from csv and load to collections */
        - PassengerFlightJourneyReader.scala /** Implementation of FlightDataReader trait */
        - FlightDataQueryEngine.scala /** Implementation of the the 4 usecases listed above*/
  - test
   - scala
    -  FlightDataQueryEngineTest.scala /** Test the loading of csv into collections using a subset of data - *_sample.csv **/   
    -  FlightDataQueryEngineTest.scala /** Test cases to test each of the 4 usecases listed above using a subset of data - *_sample.csv **/

Note - Please increase the java heap memory for usecase 4 as the solution makes use of combinations.



