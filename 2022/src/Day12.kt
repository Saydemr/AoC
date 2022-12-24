package src

import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.min

fun main() {

    val mountain = readInput("input12")
        .map { it.trim().split("") }
        .map { it.filter { it2 -> it2 != "" } }
        .map { it.map { it2 -> it2.first() }.toMutableList() }
        .toMutableList()


    val reach = MutableList(mountain.size) { MutableList(mountain[0].size) { POSITIVE_INFINITY } }

    val firstDim = mountain.flatten().indexOf('S') / mountain[0].size
    val secondDim = mountain.flatten().indexOf('S') % mountain[0].size

    mountain[firstDim][secondDim] = 'a'

    println(firstDim)
    println(secondDim)

    val endX =  mountain.flatten().indexOf('E') / mountain[0].size
    val endY = mountain.flatten().indexOf('E') % mountain[0].size

    mountain[endX][endY] = 'z'

    val directions = listOf(Pair(1,0), Pair(-1,0), Pair(0,1), Pair(0,-1))

    fun notNeedGear(pos1: Pair<Int,Int>, pos2: Pair<Int,Int>): Boolean {
        if (pos2.first < 0 || pos2.first >= mountain.size || pos2.second < 0 || pos2.second >= mountain[0].size)
            return false
        val first = mountain[pos1.first][pos1.second]
        val second = mountain[pos2.first][pos2.second]

        return when {
            second - first < 2 -> true
            else -> false
        }
    }

    val visitedNodes = mutableSetOf<Pair<Int,Int>>()
    visitedNodes.add(Pair(firstDim, secondDim))
    reach[firstDim][secondDim] = 0.0


    // part 2
    visitedNodes.addAll(mountain.mapIndexed { index, list ->
        list.mapIndexed { index2, c ->
            if (c == 'a') {
                reach[index][index2] = 0.0;
                listOf(Pair(index, index2))
            }
            else
                listOf()
        }
    }.flatten().flatten())

    fun move() {
        val nextNodes = mutableSetOf<Pair<Int,Int>>()
        for (node in visitedNodes) {
            for (direction in directions) {
                if (!visitedNodes.contains(Pair(node.first + direction.first, node.second + direction.second)) && notNeedGear(node, Pair(node.first + direction.first, node.second + direction.second))) {
                    nextNodes.add(Pair(node.first + direction.first, node.second + direction.second))
                    reach[node.first + direction.first][node.second + direction.second] = min(reach[node.first][node.second] + 1, reach[node.first + direction.first][node.second + direction.second])
                    if (reach[endX][endY] != POSITIVE_INFINITY)
                        return
                }
            }
        }
        if (nextNodes.isNotEmpty()) {
            visitedNodes.addAll(nextNodes)
            move()
        }
    }
    move()
    println(reach[endX][endY])
}