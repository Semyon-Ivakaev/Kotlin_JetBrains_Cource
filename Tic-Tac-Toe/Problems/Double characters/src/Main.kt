fun main() {
    // write your code here
    var newStr = ""
    for (i in readLine()!!) {
        newStr += i.toString().repeat(2)
    }
    println(newStr)
}