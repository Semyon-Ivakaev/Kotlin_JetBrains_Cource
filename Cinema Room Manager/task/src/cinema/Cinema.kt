package cinema

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    println("Enter the number of rows:")
    val rows = scanner.nextInt()
    println("Enter the number of seats in each row:")
    val seats = scanner.nextInt()
    val cinemaArray = createArray(rows, seats)

    var currentIncome = 0


    val allSeats = rows * seats

    var price = 0
    var firstRows = 0
    var secondRows = 0

    if (allSeats > 60) {
        firstRows = if (rows % 2 == 0) {
            secondRows = (rows / 2) * seats
            (rows / 2) * seats
        } else {
            secondRows = (rows / 2 + 1) * seats
            (rows / 2) * seats
        }
        price = firstRows * 10 + secondRows * 8
    } else {
        price = allSeats * 10
    }

    while (true) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readLine()!!.toInt()) {
            1 -> printArray(cinemaArray)
            2 -> currentIncome = buyTicket(scanner, rows, seats, allSeats, firstRows, cinemaArray, currentIncome)
            3 -> showStatistics(cinemaArray, allSeats, price, currentIncome)
            0 -> break
        }
    }
}

fun showStatistics(cinemaArray: MutableList<CharArray>, allSeats: Int, price: Int, currentIncome: Int) {
    val purchasedTickets = getPurchasedTickets(cinemaArray)
    val percentageTickets = getPercentageTickets(allSeats, purchasedTickets)

    println()
    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: " + String.format("%.2f", percentageTickets) + "%")
    println("Current income: $$currentIncome")
    println("Total income: $$price")
}


fun getPurchasedTickets(cinemaArray: MutableList<CharArray>): Int {
    var count = 0
    for (i in cinemaArray) {
        for (j in i) {
            if (j == 'B') {
                count++
            }
        }
    }
    return count
}

fun getPercentageTickets(allSeats: Int, purchasedTickets: Int): Double = purchasedTickets * 1.0 / allSeats * 100

fun buyTicket(scanner: Scanner, rows: Int, seats: Int, allSeats: Int, firstRows: Int, cinemaArray: MutableList<CharArray>,
              currentIncome: Int): Int {
    var income = 0

    var row: Int
    var seat: Int

    do {
        println()
        println("Enter a row number:")
        row = scanner.nextInt()
        println("Enter a seat number in that row:")
        seat = scanner.nextInt()
        println()
    } while (!checkSpace(cinemaArray, row, seat, rows, seats))

    if (allSeats < 60 || (row - 1) * seats + seat <= firstRows) {
        println("Ticket price: $10")
        income += 10
    } else {
        println("Ticket price: $8")
        income += 8
    }

    editArray(cinemaArray, row, seat)
    return currentIncome + income  // из за того, что переменная currentIncome не глобальная
}

fun checkSpace(cinemaArray: MutableList<CharArray>, row: Int, seat: Int, rows: Int, seats: Int): Boolean {
    if (row > rows || seat > seats) {
        println("Wrong input!")
    } else if (cinemaArray[row][seat] != 'S') {
        println("That ticket has already been purchased!")
    } else return true
    return false
}

fun createArray(rows: Int, seats: Int): MutableList<CharArray> {
    var cinemaArray = mutableListOf<CharArray>()
    for (i in 0..rows) {
        cinemaArray.add(i, CharArray(seats + 1))
    }

    for (i in 0 until cinemaArray.size) {
        for (j in 0 until cinemaArray.get(i).size) {
            if (i == 0) {
                if (j == 0) {
                    cinemaArray.get(i)[j] = ' '
                } else {
                    cinemaArray.get(i)[j] = ' ' + (16 + j)
                }
            } else if (j == 0) {
                cinemaArray.get(i)[j] = ' ' + (16 + i)
            } else {
                cinemaArray.get(i)[j] = 'S'
            }
        }
    }

    return cinemaArray
}

fun editArray(cinemaArray: MutableList<CharArray>, row: Int, seat: Int) {
    cinemaArray[row][seat] = 'B'
}

fun printArray(cinemaArray: MutableList<CharArray>) {
    println()
    println("Cinema:")
    for (i in 0 until cinemaArray.size) {
        println(cinemaArray.get(i).joinToString(" "))
    }
    println()
}