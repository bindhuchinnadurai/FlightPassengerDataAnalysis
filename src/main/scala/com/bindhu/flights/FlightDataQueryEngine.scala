package com.bindhu.flights

/**
 * Responsible for executing queries on the Flights Dara
 * @param flightDataReader
 * @author Bindhu
 */

class FlightDataQueryEngine(val flightDataReader: FlightDataReader) {

  val flightJourneySeq = flightDataReader.readFlightJourney();
  val passengerMap = flightDataReader.readPassengerData();

  /**
   * Class MapOps[K, V] with function mapTheValues of Map[K,V]
   * @param map
   * @tparam K
   * @tparam V
   */
  implicit class MapOps[K, V](map: Map[K, V]) {
    def mapTheValues[NewV](fn: V => NewV) = map.map { case (k, v) => k -> fn(v) }
  }

  /**
   * Function responsible to calculate the total number of flights each month
   * @return
   */
  def getTotalNumberOfFlightsGroupedByMonth = {
    def extractMonth(flightJourney: FlightJourney): String = {
      val date = flightJourney.date.split("-")
      date(1)
    }
     flightJourneySeq.groupBy(extractMonth).mapTheValues(_.distinctBy(_.flightId).size).toList
  }

  /**
   * Function responsible to calculate the total number of flights each month
   * @return
   */
  def getFrequentFlyersGroupedByPassengerId  = {
    def countDescending[T] (t:(T, Int)) = -t._2

      flightJourneySeq.groupBy(_.passengerId).mapTheValues(_.length).toSeq.sortBy(countDescending).take(100).map({
      case(passengerId, freq) =>  (passengerId -> freq.toString, passengerMap.getOrElse(passengerId, ""))
    }).toList
  }

  /**
   * Protected case class to store the countries that each passenger has visited
   * @param id
   * @param countries
   */
  protected [flights] case class PassengerToCountries(id: String, countries: List[String])

  /**
   * Function responsible to get from/to locations for each passenger
   * @param flightJourney
   * @return
   */
  def getPassengerCountriesList(flightJourney: FlightJourney) =
    PassengerToCountries(flightJourney.passengerId, List(flightJourney.from, flightJourney.to).filterNot(_ == "uk").distinct)
  /**
   * Function responsible for getting the max number of international journeys that a passenger has taken
   * @return
   */
  def getMaxInternationalJourneysByPassengerId = {
    flightJourneySeq.map(getPassengerCountriesList).groupBy(_.id).map {
      case(id, passengerByCountries) => id -> passengerByCountries.flatMap(_.countries)
    }.map{ case(passengerId, countries) =>  passengerId -> countries.size}.toList
  }

  /**
   * Protected case class to store the passengers travelled in the same flight
   * @param id
   * @param countries
   */
  protected [flights] case class FlightToPassengers(id: String, passengers: List[String])

  /**
   * Function responsible to calculate the number of occurrences of a tuple in a list
   */
  protected [flights] def getElementOccurrences[A](list:List[A]):Map[A, Int] = {
    list.groupBy(elem => elem).map(elem => (elem._1, elem._2.length))
  }

  /**
   * Function responsible to get the list of Passenger Ids for each flight
   * @param flightJourney
   * @return
   */
  protected [flights] def getFlightToPassengerList(flightJourney: FlightJourney) =
    FlightToPassengers(flightJourney.flightId, List(flightJourney.passengerId))

  /**
   * Function responsible to get the passengers who have travelled more than 3 times in the same flight
   * @return the passengers who have been on the same flight more than 3 times
   */
  def getMaxJourneysTogetherByPassengerId = {

    var passengersList = List.empty[Seq[String]]
    flightJourneySeq.map(getFlightToPassengerList).groupBy(_.id).map {
      case(id, passengersByFlight) => id -> passengersByFlight.flatMap(_.passengers).distinct.sorted
    }.map({ case(flightId, passengers) =>  passengers.combinations(2).toList}).map( passengers =>  passengersList ++= passengers)

    getElementOccurrences(passengersList).filter(_._2 > 3).toList
  }
}
