package src

fun main() {

    var part2vals = mutableListOf<Int>()
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
        part2vals = values
        
        val sequence = (0..220)
            .map { if ((it - 19) % 40 == 0) it+1 else 0 }

        return  sequence.zip(values)
            .sumOf { it.first * it.second }
    }

    fun part2(input: List<Int>): String {
        var resString = ""
        var CRTPosition = 0
        input.forEach {
            if (CRTPosition % 40 == 0) CRTPosition = 0
            resString += if (CRTPosition in it-1 until it+2)
                "#"
            else
                "."
            CRTPosition++
        }

        return resString
    }

    val input = readInput("input10")
    println(part1(input))
    println(part2(part2vals).chunked(40).joinToString("\n"))


}
