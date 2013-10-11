package controllers

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class JsonFmtListWrapper[T](id: String, typ: String, data: List[T])

object JsonFmtListWrapper {
  implicit def listWrapperFormat[T : Format]: Format[JsonFmtListWrapper[T]] = (
    (__ \ "id").format[String] and
      (__ \ "typ").format[String] and
      (__ \ "data").format[List[T]]
    )(JsonFmtListWrapper.apply, unlift(JsonFmtListWrapper.unapply))
}

