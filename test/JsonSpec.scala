package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json
import controllers.JsonFmtListWrapper

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class JsonSpec extends Specification {
  case class TestNumber(typ: String, value: Int)
  implicit val testNumberFmt = Json.format[TestNumber]

  "JsonFmtListWrapper should" should {
    "Wrap a list and unwrap it" in {
      running(FakeApplication()) {
        val lwrapped = JsonFmtListWrapper("12", "numbers", List(TestNumber("number", 1), TestNumber("number", 2)))
        val jsonLwid = Json.toJson(lwrapped)
        lwrapped must beEqualTo(jsonLwid.validate[JsonFmtListWrapper[TestNumber]].fold(invalid = x => x, valid = x => x))
      }
    }
  }
}