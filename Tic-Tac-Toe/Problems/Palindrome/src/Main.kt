fun main() {
    // write your code here
    val str = readLine()!!
    val newStr = str.reversed()
    println(if (str == newStr) "yes" else "no")
}