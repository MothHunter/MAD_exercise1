import kotlin.random.Random

fun main() {
    val numberString = generateNumberString()
    println("Please enter your guess (4 digit number):")
    gameLoop@ while (true){
        val guess = readLine()
        if (guess == null || guess.length != 4) {
            println("please enter a 4 digit number")
            continue
        }
        if (guess.equals(numberString)) {
            println("You won!")
            return
        }
        var correctPlaces = 0
        var correctNumbers = 0
        for (i in 0..3) {                           // check for non-digit characters and exact matches
            if (!guess[i].isDigit()) {
                println("please only use digits from 0 to 9")
                continue@gameLoop
            } else if (numberString[i] == guess[i]) {
                correctPlaces++
            }
        }
        for (n in 0..3) {
            for (g in 0..3) {
                if (guess[g] == numberString[n]){   // selected digit of guess is present in the number
                    correctNumbers++
                    break                           // break loop, so we don't count duplicate digits from the guess
                }
            }
        }
        println("$correctNumbers:$correctPlaces")
    }
}

// function for generating numbers to guess
fun generateNumberString(): String {
    var number = ""
    outerLoop@ while (number.length < 4)
    {
        val nextDigit = (Random.nextInt(0,9)).digitToChar()
        for (digit in number){
            if(digit == nextDigit) {
                continue@outerLoop
            }
        }
        number += nextDigit
    }
    return number
}