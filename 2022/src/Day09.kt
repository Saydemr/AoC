package src

import kotlin.math.abs
import kotlin.math.sign


fun correct(vertex: Pair<Int,Int>) : Pair<Int,Int> {
    return if (abs(vertex.first)  > 1 || abs(vertex.second) > 1)
        Pair((vertex.first - sign(vertex.first.toDouble())).toInt(),
            (vertex.second - sign(vertex.second.toDouble())).toInt())
    else
        vertex
}

fun main() {

    val lookup = mapOf(
        "L" to Pair(-1,0),
        "R" to Pair(1,0),
        "U" to Pair(0,1),
        "D" to Pair(0,-1))

    val visitedCells = mutableSetOf<Pair<Int,Int>>()


    fun part1(input: List<List<String>>): Int {

        visitedCells.clear()
        val knotPositions = mutableListOf(
            Pair(0, 0), Pair(0, 0))

        input.forEach {
            val direction = lookup[it[0]]
            val distance = it[1].toInt()

            for (a in 0 until distance) {
                knotPositions[0] = Pair(first = knotPositions[0].first + direction!!.first,
                    second = knotPositions[0].second + direction.second )

                for (index in 1 until knotPositions.size) {
                    val diff = Pair(
                        first = knotPositions[index].first - knotPositions[index - 1].first,
                        second = knotPositions[index].second - knotPositions[index - 1].second
                    )

                    knotPositions[index] = Pair(
                        first = knotPositions[index - 1].first + correct(diff).first,
                        second = knotPositions[index - 1].second + correct(diff).second
                    )
                }
                visitedCells.add(knotPositions.last())
            }
        }
        return visitedCells.size
    }

    fun part2(input: List<List<String>>): Int {

        visitedCells.clear()
        val knotPositions = mutableListOf(
            Pair(0, 0), Pair(0, 0), Pair(0, 0),
            Pair(0, 0), Pair(0, 0), Pair(0, 0),
            Pair(0, 0), Pair(0, 0), Pair(0, 0),
            Pair(0, 0)
        )

        input.forEach {
            val direction = lookup[it[0]]
            val distance = it[1].toInt()

            for (a in 0 until distance) {
                knotPositions[0] = Pair(first = knotPositions[0].first + direction!!.first,
                    second = knotPositions[0].second + direction.second )

                for (index in 1 until knotPositions.size) {
                    val diff = Pair(
                        first = knotPositions[index].first - knotPositions[index - 1].first,
                        second = knotPositions[index].second - knotPositions[index - 1].second
                    )

                    knotPositions[index] = Pair(
                        first = knotPositions[index - 1].first + correct(diff).first,
                        second = knotPositions[index - 1].second + correct(diff).second
                    )
                }
                visitedCells.add(knotPositions.last())
            }
        }
        return visitedCells.size
    }

    val input = readInput("input9")
        .map { it.trim().split(" ") }
        .toList()

    println(part1(input))
    println(part2(input))


}
