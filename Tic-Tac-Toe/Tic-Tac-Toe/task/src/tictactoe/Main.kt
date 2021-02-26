package tictactoe

import java.util.*
import kotlin.math.abs

fun main() {
    var stepCount = 0
    val squad = createSquad()
    editSquad(squad, "_________")
    showSquad(squad)
    do {
        print("Enter the coordinates: ")
        val (x, y)  = readLine()!!.split(" ")

        when {
            !checkIsDigit(x, y) -> println("You should enter numbers!")
            !checkRange(x.toInt(), y.toInt()) -> println("Coordinates should be from 1 to 3!")
            !checkCell(x.toInt(), y.toInt(), squad) -> println("This cell is occupied! Choose another one!")
            else -> {
                stepCount++
                if (stepCount % 2 != 0)  {
                    stepPlayer(x.toInt(), y.toInt(), squad, 'X')
                } else stepPlayer(x.toInt(), y.toInt(), squad, 'O')
                showSquad(squad)
                if (!checkWinner(squad)) break
            }
        }
    } while (true)
}

fun checkWinner(squad: MutableList<CharArray>): Boolean {
    when {
        !countsImpossible(squad) || (rowWinner(squad, 'X') && rowWinner(squad, 'O')
                || (diagonalWinner(squad, 'X') && diagonalWinner(squad, 'O'))) -> {
            println("Impossible")
            return true
        }
        cellWinner(squad, 'X') || rowWinner(squad, 'X')
                || diagonalWinner(squad, 'X') -> {
            println("X wins")
            return false
        }
        cellWinner(squad, 'O') || rowWinner(squad, 'O')
                || diagonalWinner(squad, 'O') -> {
            println("O wins")
            return false
        }
        gameNotWinner(squad) -> {
            println("Draw")
            return false
        }
        !gameNotWinner(squad) -> return true
    }
    return false
}

fun countsImpossible(squad: MutableList<CharArray>): Boolean {
    var countX = 0
    var countO = 0
    for (i in 0 until squad.size) {
        for (j in 0 until squad.get(i).size) {
            if (squad[i][j] == 'X') countX++
            else if (squad[i][j] == 'O') countO++
        }
    }
    return (abs(countX - countO) in 0..1)
}

fun gameNotWinner(squad: MutableList<CharArray>): Boolean {
    var count = 0
    for (i in 0 until squad.size) {
        for (j in 0 until squad.get(i).size) {
            if (squad[i][j] == '_') count++
        }
    }
    return count == 0
}

fun diagonalWinner(squad: MutableList<CharArray>, ch: Char): Boolean {
    return (squad[0][0] == ch && squad[1][1] == ch && squad[2][2] == ch
        || squad[2][0] == ch && squad[1][1] == ch && squad[0][2] == ch)
}

fun rowWinner(squad: MutableList<CharArray>, ch: Char): Boolean {
    return (squad[0][0] == ch && squad[1][0] == ch && squad[2][0] == ch
            || squad[0][1] == ch && squad[1][1] == ch && squad[2][1] == ch
            || squad[0][2] == ch && squad[1][2] == ch && squad[2][2] == ch)
}

fun cellWinner(squad: MutableList<CharArray>, ch: Char): Boolean {
    var count = 0
    for (i in 0 until squad.size) {
        for (j in 0 until squad.get(i).size) {
            if (squad[i][j] == ch) count++
        }
        if (count == 3) return true
        count = 0
    }
    return false
}

fun createSquad(): MutableList<CharArray> {
    val squad = mutableListOf<CharArray>()

    for (i in 0..2) {
        squad.add(i, CharArray(3))
    }

    for (i in 0 until squad.size) {
        for (j in 0 until squad.get(i).size) {
            squad[i][j] = '_'
        }
    }
    return squad
}

fun editSquad(squad: MutableList<CharArray>, str: String) {
    var countStr = 0
    for (i in 0 until squad.size) {
        for (j in 0 until squad.get(i).size) {
            squad[i][j] = str[countStr]
            countStr++
        }
    }
}

fun stepPlayer(stepX: Int, stepY: Int, squad: MutableList<CharArray>, ch: Char) {
    squad[stepX - 1][stepY - 1] = ch
}

fun checkCell(stepX: Int, stepY: Int, squad: MutableList<CharArray>): Boolean {
    return squad[stepX - 1][stepY - 1] == '_'
}

fun checkIsDigit(x: String, y: String): Boolean {
    return when {
        (x != "" && y != "") && (x.first().isDigit() && y.first().isDigit()) -> true
        else -> false
    }
}

fun checkRange(stepX: Int, stepY: Int): Boolean {
    return stepX in 1..3 && stepY in 1..3
}


fun showSquad(squad: MutableList<CharArray>) {
    println("---------")
    for (i in squad) {
        print("| ")
        print(i.joinToString(" "))
        println(" |")
    }
    println("---------")
}