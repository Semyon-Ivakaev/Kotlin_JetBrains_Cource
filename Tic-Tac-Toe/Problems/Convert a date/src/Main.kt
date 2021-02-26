fun main() {
    // write your code here
    val date = readLine()!!
    val newDate = date.split("-")
    println("${newDate[1]}/${newDate[2]}/${newDate[0]}")
}