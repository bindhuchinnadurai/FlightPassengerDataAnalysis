import com.bindhu.flights.{FlightDataQueryEngine, FlightJourney, PassengerFlightJourneyReader}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should
import org.scalatest.BeforeAndAfter

/**
 * Test functions
 */
class FlightDataQueryEngineTest extends AnyFunSuite with should.Matchers with BeforeAndAfter{
  var queryEngine: FlightDataQueryEngine = _

  before {
    val passengerData = new PassengerFlightJourneyReader ("flightData_sample.csv", "passengers_sample.csv")
    queryEngine = new FlightDataQueryEngine(passengerData)
  }

  test("NumberOfYearsInAYearNotGreaterThan12") {
    queryEngine.getTotalNumberOfFlightsGroupedByMonth.size shouldBe 12
  }

  test("NumberOfGrequentFlyerNotGreaterThan10") {
    queryEngine.getFrequentFlyersGroupedByPassengerId.size should be <= 10
  }

  test("UKNotInPassengerJourneyList") {
    queryEngine.getPassengerCountriesList(new FlightJourney("48", "0", "cg", "uk", "01-01-2017")).countries should not contain "uk"
  }

  test("MaxNumberOfJourneyTogetherGreaterThan3") {
    queryEngine.getMaxJourneysTogetherByPassengerId.collect(_._2) should not contain 3
  }
}
