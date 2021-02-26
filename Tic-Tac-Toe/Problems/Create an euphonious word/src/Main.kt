fun main() {
    // write your code here
    val vowels = arrayOf('A', 'E', 'I', 'O', 'U', 'Y')
    val consonants = arrayOf('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
        'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z')

    val str = readLine()!!.toUpperCase()
    var countVowels = 0
    var countConsonants = 0
    var count = 0

    for (i in str) {
        if (i in vowels) {
            countVowels++
            countConsonants = 0
        } else {
            countConsonants++
            countVowels = 0
        }
        if (countVowels == 3 || countConsonants == 3) {
            count++
            countVowels = 1
            countConsonants = 1
        }
    }

    println(count)

/*    for (i in str) {
        count++
        if (count == str.length) break
        when (i) {
            in vowels -> {
                if (countVowels >= 3 && countConsonants != 0) continue
                countVowels++
                if (countConsonants < 3) countConsonants = 0
            }
            in consonants -> {
                if (countConsonants >= 3 && countVowels != 0) continue
                countConsonants++
                if (countVowels < 3) countVowels = 0
            }
        }
    }*/
/*
    println(when {
        countVowels >= 3 -> if (countVowels % 3 != 0) {
            countVowels / 3 + 1
        } else countVowels / 3
        countConsonants >= 3 -> if (countConsonants % 3 != 0) {
            countConsonants / 3 + 1
        } else countConsonants / 3
        else -> 0
    })*/
}