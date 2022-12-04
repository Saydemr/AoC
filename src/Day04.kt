fun main() {
    fun part1(input: List<String>): Int {

        val out = input.flatMap { it.split(",") }
            .asSequence()
            .map { it.split("-") }
            .map { it[0].toInt()..it[1].toInt() }
            .chunked(2)
            .map { (it[0] intersect it[1]).containsAll(it[0].toList()) || (it[0] intersect it[1]).containsAll(it[1].toList()) }
            .filter { it }
            .count()

        return out
    }

    fun part2(input: List<String>): Int {

        val out = input.flatMap { it.split(",") }
            .asSequence()
            .map { it.split("-") }
            .map { it[0].toInt()..it[1].toInt() }
            .chunked(2)
            .map { it[0].toList().any { iter -> iter in it[1] } || it[1].toList().any { iter -> iter in it[0] } }
            .filter { it }
            .count()

        return out
    }



    val input = readInput("../input4")
    println(part1(input))
    println(part2(input))
}
