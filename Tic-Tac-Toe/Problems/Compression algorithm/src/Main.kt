fun main() {
    // write your code here
    val str = readLine()!!

    var count = 0

    for (i in str.indices) {
        when {
            str.length == 1 -> print("${str[i]}1")
            i == str.length - 1 -> if (str[i] != str[i - 1]) {
                print("${str[i]}1")
            } else {
                count++
                print("${str[i]}$count")
            }
            str[i] == str[i + 1] -> count++
            str[i] != str[i + 1] -> {
                count++
                print("${str[i]}$count")
                count = 0
            }
        }
    }
}