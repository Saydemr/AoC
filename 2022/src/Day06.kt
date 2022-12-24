package src

fun main() {

    fun uniqueSubstring(input: String, consecutive: Int): Int {

        val firstUnique = input
            .asSequence()
            .windowed(consecutive,1,partialWindows = true)
            .filter { it.distinct().size == it.size }
            .first()
            .joinToString { it.toString() }
            .replace(", ", "")


        return input.indexOf(firstUnique) + consecutive

    }


    val input = readInput("input6")
    println(uniqueSubstring(input[0],4))
    println(uniqueSubstring(input[0],14))
}
