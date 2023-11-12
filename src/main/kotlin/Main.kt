import kotlin.random.Random
data class Wagon(val capacity: Int, val passengers: Int)

val cities = listOf( "Кемерово", "Санкт-Петербург", "Новосибирск", "Москва", "Сочи",
    "Казань", "Челябинск", "Омск", "Адлер", "Ростов-на-Дону",
    "Уфа", "Красноярск", "Пермь", "Воронеж", "Волгоград")
var exit = false
fun main() {
    while (true) {
        println("\nВыберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("Введите 'exit' для выхода из программы")

        val choice = readLine()

        when (choice) {
            "1" -> createDirection()
            "2" -> sellTickets()
            "3" -> formTrain()
            "4" -> sendTrain()
            "exit" -> {
                exit= true
                println("Работа программы завершена.")
                return
            }
            else -> println("Некорректный ввод. Пожалуйста, повторите попытку.")
        }
    }
}

var direction: String? = null
var wagons: MutableList<Wagon>? = null
val passengersTickets = mutableListOf<Int>()

fun createDirection() {
    val City1 = cities[Random.nextInt(cities.size)]
    var City2: String

    do {
        City2 = cities[Random.nextInt(cities.size)]
    } while (City1 == City2)

    direction = "$City1 - $City2"
    println("Направление создано: $direction")
}

fun sellTickets() {
    if (direction.isNullOrEmpty()) {
        println("Сначала создайте направление.")
        return
    }

    val passengersCount = Random.nextInt(5, 202)
    passengersTickets.clear()

    for (i in 1..passengersCount) {
        passengersTickets.add(i)
    }

    println("Продано $passengersCount билетов на направление $direction")
}

fun formTrain() {
    if (passengersTickets.isEmpty()) {
        println("Сначала продайте билеты.")
        return
    }

    wagons = mutableListOf()
    var remainingPassengers = passengersTickets.size

    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(5, 26)
        val passengersInWagon = if (wagonCapacity < remainingPassengers) wagonCapacity else remainingPassengers

        wagons?.add(Wagon(wagonCapacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    println("Поезд успешно сформирован.")
}

fun sendTrain() {
    if (wagons.isNullOrEmpty()) {
        println("Сначала сформируйте поезд.")
        return
    }

    println("Поезд $direction, состоящий из ${wagons!!.size} вагонов, отправлен.")

    for ((index, wagon) in wagons!!.withIndex()) {
        println("Вагон ${index + 1}: Вместимость - ${wagon.capacity}, Пассажиры - ${wagon.passengers}")
    }

    direction = null
    wagons = null
}
