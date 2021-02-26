fun main() {
    // write your code here
    val str = readLine()!!
    println(if (str.length % 2 == 0) {
        "${str.substring(0, str.length / 2 - 1)}${str.substring(str.length / 2 + 1)}"
    } else {
        "${str.substring(0, str.length / 2)}${str.substring(str.length / 2 + 1)}"
    })
}