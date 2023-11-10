import kotlin.random.Random

class Train(var capacity: Int, var direction: String) {
    fun sendTrain() {
        println("Поезд $direction, состоящий из $capacity вагонов отправлен ")
        for (i in 1..capacity) {
            val wagonCapacity = Random.nextInt(10, 51)
            val passengers = Random.nextInt(0, wagonCapacity + 1)
            println("Вагон $i: Вместимость - $wagonCapacity, Пассажиров - $passengers")
        }
    }
}

class TicketSeller {
    fun sellTickets() {
        val passengerCount = Random.nextInt(5, 202)
        println("Продано билетов: $passengerCount")
    }
}

class DirectionCreator(val cities: List<String>) {
    fun createDirection() {
        val city1 = cities.random()
        var city2 = cities.random()
        while (city2 == city1) {
            city2 = cities.random()
        }
        println("Направление создано: $city1 - $city2")
    }
}

fun main() {
    val cities = listOf("Бийск", "Барнаул", "Новосибирск", "Омск", "Томск", "Кемерово", "Ангарск",
        "Красноярск", "Иркутск", "Улан-Удэ", "Чита", "Хабаровск", "Владивосток", "Сургут", "Тюмень")
    val ticketSeller = TicketSeller()
    val directionCreator = DirectionCreator(cities)
    var exit = false
    var trainCreated = false
    var train: Train? = null
    while (!exit) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("EXIT. Закончить работу")
        val choice = readLine()
        when (choice) {
            "1" -> directionCreator.createDirection()
            "2" -> ticketSeller.sellTickets()
            "3" -> {
                if (!trainCreated) {
                    val capacity = Random.nextInt(5, 26)
                    train = Train(capacity, "")
                    println("Создан поезд с вместимостью вагонов: $capacity")
                    trainCreated = true
                } else {
                    println("Поезд уже сформирован")
                }
            }
            "4" -> {
                if (trainCreated) {
                    println("Введите направление поезда:")
                    val trainDirection = readLine() ?: ""
                    train?.direction = trainDirection
                    train?.sendTrain()
                    trainCreated = false
                } else {
                    println("Сначала сформируйте поезд")
                }
            }
            "EXIT" -> exit = true
            else -> println("Неправильный выбор. Попробуйте снова.")
        }
    }
}
