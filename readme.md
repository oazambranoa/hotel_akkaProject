# MyHotel Project

Este proyecto es una aplicación de consola simple escrita en Scala que simula un sistema de gestión de reservas de hotel.

## Requisitos

- Scala 2.13 o superior
- sbt 1.3.8 o superior

## Funcionalidades

- El usuario puede hacer una reserva de habitación de hotel.
- El usuario puede elegir entre tres tipos de habitaciones: Sencilla, Doble y Suite.
- El sistema verifica la disponibilidad de la habitación y confirma o rechaza la reserva.
- Después de cada reserva, el sistema imprime la disponibilidad de habitaciones restante.
- El sistema permite al usuario hacer múltiples reservas hasta que decida detenerse.

## Cómo ejecutar

1. Clona el repositorio a tu máquina local.
2. Navega hasta el directorio del proyecto.
3. Ejecuta el comando `sbt run` para iniciar la aplicación.

## Estructura del proyecto

El proyecto consta de dos clases principales:

- `HotelManager`: Esta clase gestiona la disponibilidad de las habitaciones y procesa las solicitudes de reserva.
- `Main`: Esta es la clase principal que interactúa con el usuario, recoge los detalles de la reserva y llama a `HotelManager` para procesar la reserva.
