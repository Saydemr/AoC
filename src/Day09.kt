import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sqrt

fun main() {

    val lookup = mapOf("L" to Pair(-1,0), "R" to Pair(1,0), "U" to Pair(0,1), "D" to Pair(0,-1))
    val visitedCells = mutableSetOf<Pair<Int,Int>>()
    visitedCells.add(Pair(0,0))
    var currentPos = Pair(0,0)


    val input = readInput("../inputs/input9")
        .map { it.trim().split(" ") }
        .map { it[0].repeat(it[1].toInt()) }
        .joinToString(separator = "") { it }
        .windowed(2)
        .map { it.map { it2 -> lookup[it2.toString()]!! }
            .reduce { acc, pair -> Pair(acc.first + pair.first, acc.second + pair.second) } }
        .forEach {
            if (it.first == 0 && it.second == 0)
            else if ( hypot(it.first.toDouble(), it.second.toDouble()) == sqrt(5.0))
                currentPos = Pair(currentPos.first + it.first, currentPos.second + it.second / 2)
            else if ( hypot(it.first.toDouble(), it.second.toDouble()) == sqrt(2.0))
                currentPos = Pair(currentPos.first + it.first, currentPos.second + it.second)

            print(currentPos)
            println(hypot(it.first.toDouble(), it.second.toDouble()))
            visitedCells.add(currentPos)
        }


    println(visitedCells.size)
    println(input)

}
