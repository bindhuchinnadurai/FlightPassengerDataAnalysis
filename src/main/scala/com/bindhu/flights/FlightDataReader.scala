package com.bindhu.flights

/**
 * Trait responsible for loading the csv files - passengers_sample.csv, flightdata.csv
 * @author Bindhu
 */

trait FlightDataReader {
  /**
   *
   * @return A[[Seq]] containing Flight trips taken by each passenger
   */
  def readFlightJourney(): Seq[FlightJourney]
  /**
   *
   * @return A[[Map]] containing passenger data(ID and Name)
   */
  def readPassengerData(): Map[String, String]
}

/**
 * Flight Journey Record
 * @param passengerId
 * @param flightId
 * @param from
 * @param to
 * @param date
 */
case class FlightJourney(passengerId: String, flightId: String, from: String, to: String, date: String)