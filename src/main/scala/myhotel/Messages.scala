package myhotel

sealed trait HotelMessage

case class ReservaRequest(name: String, roomType: String, nights: Int) extends HotelMessage
case class ReservaConfirmation(name: String, roomType: String, nights: Int) extends HotelMessage
case class ReservaError(message: String) extends HotelMessage
