package com.bindhu.flights

/**
 * App to display the query results on the console
 */
object FlightApplication extends App {
  /** Read from FlightData.csv and Passengers.csv */
  val flightsDataReader = new PassengerFlightJourneyReader("flightData_sample.csv", "passengers_sample.csv")
  flightsDataReader.readPassengerData()

  /** Get an instance of the FlightDataQueryEngine*/
  val engine = new FlightDataQueryEngine(flightsDataReader);

  /** Print the number of flights each month */
  val usecase_1 = engine.getTotalNumberOfFlightsGroupedByMonth
  println("Month  Number_Of_Flights")
  usecase_1.foreach {
        case (month, noOfFlights) => {
          println(s"$month            $noOfFlights")
          }
       }

  /** Print the 100 most frequent flyers*/
  val usecase_2 = engine.getFrequentFlyersGroupedByPassengerId
  println("Passenger_Id   No_Of_Flights   Passenger_Name")
  usecase_2.foreach {
    case (element, name) => {
      println(s"${element._1}             ${element._2}               $name")
    }
  }

  /**  Greatest number of countries a passenger has been to*/
  val usecase_3 = engine.getMaxInternationalJourneysByPassengerId
  println("Passenger_Id Max_Run")
  usecase_3.foreach {
    case (passengerId, maxRun) => {
      println(s"$passengerId              $maxRun")
    }
  }

  /**  Greatest number of countries a passenger has been to*/
  val usecase_4 = engine.getMaxJourneysTogetherByPassengerId
  println("Passenger_Id1  Passenger_Id2 No_Of_Flights_Together")
  usecase_4.foreach {
      case (passengerId, noOfTimes) => {
        println(s"${passengerId(0)}             ${passengerId(1)}                 $noOfTimes")
      }
  }
}
