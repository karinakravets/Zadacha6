import java.util.*
import kotlin.random.Random

fun main() {
    val cities = listOf("Бийск", "Барнаул", "Новосибирск", "Томск", "Омск", "Красноярск", "Иркутск",
        "Чита", "Пермь", "Екатеринбург", "Казань", "Нижний Новгород", "Москва", "Санкт-Петербург",
        "Владивосток")
    var exit = false

    while (!exit) {
        print("Введите '1' для создания направления, '2' для продажи билетов, '3' для формирования поезда, " +
            "и '4' для отправки поезда: ")
        when (readLine()?.toIntOrNull()) {
            1 -> createDirection(cities)
            2 -> sellTickets()
            3 -> formTrain()
            4 -> sendTrain()
            else -> exit = true
        }
    }
}

fun createDirection(cities: List<String>) {
    val randomCities = cities.shuffled().take(2)
    println("Направление создано: ${randomCities[0]} - ${randomCities[1]}")
}

fun sellTickets() {
    val randomPassengerCount = Random.nextInt(5, 201)
    println("Продано билетов: $randomPassengerCount")
}

fun formTrain() {
    val randomWagonCapacity = Random.nextInt(5, 26)
    val randomPassengerCount = Random.nextInt(5, 201)
    var trainCapacity = 0
    var wagonCount = 0

    while (trainCapacity < randomPassengerCount) {
        trainCapacity += randomWagonCapacity
        wagonCount++
    }

    println("Сформирован поезд из $wagonCount вагонов (вместимость каждого вагона:$randomWagonCapacity)")
    println("Количество пассажиров в каждом вагоне:")
    repeat(wagonCount) {
        println("- Вагон ${it + 1}: ${if ((it + 1) * randomWagonCapacity <= randomPassengerCount)
            randomWagonCapacity else randomPassengerCount % randomWagonCapacity}")
    }
}

fun sendTrain() {
    println("Поезд отправлен")
}