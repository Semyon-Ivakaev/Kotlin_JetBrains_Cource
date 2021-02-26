fun main() {
    // write your code here
    val url = readLine()!!

    // parameters = {pass=12345, port=8080, cookie=, host=localhost}
    val parameters = url.split('?')[1].split('&')
    var password: String? = null
    for (el in parameters) {
        val keyValue = el.split('=')
        when {
            keyValue[1].isEmpty() -> println("${keyValue[0]} : not found")
            keyValue[0] == "pass" -> {
                println("${keyValue[0]} : ${keyValue[1]}")
                password = keyValue[1]
            }
            else -> println("${keyValue[0]} : ${keyValue[1]}")
        }
    }
    if (password != null) {
        println("password : $password")
    }
}