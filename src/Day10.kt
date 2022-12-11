import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sqrt

fun main() {

    fun part1(input: List<String>): Int {
        var register = 1
        var registerNext = 0
        val values = mutableListOf<Int>()
        input.map { it.trim().split(" ") }
            .forEach {
                register += registerNext
                values.add(register)
                registerNext = if (it[0].trim() == "addx") it[1].toInt() else 0
                if (it[0].trim() == "addx") values.add(register)
            }

        register += registerNext
        values.add(register)
        
        val sequence = (0..220)
            .map { if ((it - 19) % 40 == 0) it+1 else 0 }

        return  sequence.zip(values)
            .sumOf { it.first * it.second }
    }

    val input = readInput("../inputs/input10")
    println(part1(input))


}
