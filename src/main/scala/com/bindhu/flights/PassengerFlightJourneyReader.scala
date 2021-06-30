package com.bindhu.flights

import scala.io.Source

/**
 * Implementation of com.bindhu.flights.FlightDataReader responsible for reading data from csv files
 *
 * @param fileName The name of the CSV file to be read.
 * @author Bindhu
 */

class PassengerFlightJourneyReader(val flightJourneyFileName: String, val passengerFileName : String) extends App with FlightDataReader {

  /**
   *  Responsible for reading flightData_sample.csv
   *  @return A[[Seq]] containing Flight trips taken by each passenger
   */
  override def readFlightJourney(): Seq[FlightJourney] = {
    for {
      line <- Source.fromResource(flightJourneyFileName).getLines().drop(1).toVector
      values = line.split(",").map(_.trim)
    } yield FlightJourney(values(0), values(1), values(2), values(3), values(4))
  }

  /**
   *  Responsible for reading passengers_sample.csv
   *  @return A[[Map]] containing passenger data(ID and Name)
   */
  override def readPassengerData(): Map[String, String] = {

    val passengerList = for {
      line <- Source.fromResource(passengerFileName).getLines().drop(1).toVector
      values = line.split(",").map(_.trim)
    } yield (values(0), values(2) + "," + values(1))

    val passengerMap =  passengerList.toMap
    passengerMap
  }
}