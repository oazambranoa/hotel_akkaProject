package myhotel

import akka.actor.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.io.StdIn

object Main extends App{
  implicit val system: ActorSystem = ActorSystem("HotelSystem")
  import system.dispatcher
  implicit val timeout: Timeout = Timeout(5.seconds)

  val hotelManager = new HotelManager

  var continue = true

  while (continue) {
    hotelManager.printRoomAvailibility()

    println("Ingrese su nombre: ")
    val name = StdIn.readLine()

    println("Ingrese el tipo de habitación: ")
    println("1. Sencilla")
    println("2. Doble")
    println("3. Suite")
    val roomType = StdIn.readLine() match {
      case "1" => "Sencilla"
      case "2" => "Doble"
      case "3" => "Suite"
      case _ => println("Opción no válida. Se seleccionará 'Sencilla' por defecto.")
        "Sencilla"
    }

    println("Ingrese el número de noches: ")
    val nights = StdIn.readLine().toInt

    val futureResult = hotelManager.process(ReservaRequest(name, roomType, nights))

    futureResult.onComplete {
      case Success(value) => value match {
        case ReservaConfirmation(name, roomType, nights) =>
          println(s"Reserva confirmada para $name. Tipo de habitación: $roomType, Noches: $nights")
          hotelManager.printRoomAvailibility()
          println("¿Desea hacer otra reserva? (s/n)")
        case ReservaError(message) =>
          println(s"Error al realizar la reserva: $message")
      }
      case Failure(exception) => println(exception.getMessage)
    }
    continue = StdIn.readLine().toLowerCase match {
      case "s" => true
      case _ => false
    }
  }
}