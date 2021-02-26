fun main() {
    // write your code here
    val str: ArrayList<String> = readLine()!!.split("") as ArrayList<String>
    str.remove("")
    str.remove("")
    var firstCount = 0
    var secondCount = 0

    for (i in str.indices) {
        if (i < str.size / 2) {
            firstCount += str[i].toInt()
        } else secondCount += str[i].toInt()
    }

    println(if (firstCount == secondCount) "YES" else "NO")
}