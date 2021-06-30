import com.bindhu.flights.{FlightJourney, PassengerFlightJourneyReader}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should

/**
 * Test Load CSV file
 */
class PassengerFlightJourneyReaderTest extends AnyFunSuite with should.Matchers {
  test("Load CSV file") {
    val passengerData = new PassengerFlightJourneyReader ("flightData_sample.csv", "passengers_sample.csv")

    passengerData.readFlightJourney().size shouldBe 14
    passengerData.readPassengerData().size shouldBe 4

    val journey = passengerData.readFlightJourney()
    journey contains FlightJourney("48", "0", "cg" ,"ir", "2017-01-01")
  }
}
