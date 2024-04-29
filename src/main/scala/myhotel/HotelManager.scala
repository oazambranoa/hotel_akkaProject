package myhotel

import scala.concurrent.Future

class HotelManager {

  private var roomAvailability: Map[String, Int] = Map("Sencilla" -> 10, "Doble" -> 10, "Suite" -> 10)

  def process(message: HotelMessage): Future[HotelMessage] = message match {
    case ReservaRequest(name, roomType, nights) =>

      roomAvailability.get(roomType) match {
        case Some(count) if count > 0 =>

          roomAvailability = roomAvailability.updated(roomType, count - 1)
          Future.successful(ReservaConfirmation(name, roomType, nights))
        case _ =>
          Future.successful(ReservaError(s"No hay habitaciones disponibles de tipo $roomType"))
      }
    case _ => Future.failed(new Exception("Error"))
  }

  def getRoomAvailability: Map[String, Int] = roomAvailability

  def printRoomAvailibility(): Unit = {
    getRoomAvailability.foreach { case (roomType, count) =>
      println(s"Habitaciones de tipo $roomType: $count")
    }
  }
}
