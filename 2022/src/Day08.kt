package src

fun main() {

    val visibility = mutableListOf<MutableList<Boolean>>()
    val scenicScores = mutableListOf<Int>()
    val input = readInput("input8")
        .map { it.trim() }
        .map { it.chunked(1)
            .map { it2 -> it2.toInt() } }

    fun isVisibleFromDirection(x: Int, y: Int, direction: String): Boolean {
        return if (direction == "left") input[x].take(y).map { it < input[x][y] }.filter { it }.count { true } == y
        else if (direction == "right") input[x].drop(y+1).map { it < input[x][y] }.filter { it }.count { true } == input[x].size - y - 1
        else {
            val column = input.map { it[y] }
            if (direction == "up") column.take(x).map { it < input[x][y] }.filter { it }.count { true } == x
            else column.drop(x+1).map { it < input[x][y] }.filter { it }.count { true } == column.size - x - 1
        }
    }

    fun scenicScore(x: Int, y: Int): Int {
        val left = if (input[x].take(y).indexOfLast { it >= input[x][y] } < 0) y else input[x].take(y).reversed().indexOfFirst { it >= input[x][y] } + 1
        val right = if (input[x].drop(y+1).indexOfFirst { it >= input[x][y] } < 0) input[x].size - y - 1 else input[x].drop(y+1).indexOfFirst { it >= input[x][y] } + 1
        val column = input.map { it[y] }
        val up = if(column.take(x).indexOfLast { it >= input[x][y] } < 0) x else column.take(x).reversed().indexOfFirst { it >= input[x][y] } + 1
        val down = if (column.drop(x+1).indexOfFirst { it >= input[x][y] } < 0) column.size - x - 1 else  column.drop(x+1).indexOfFirst { it >= input[x][y] } + 1
        return left * right * up * down
    }

    fun isVisible(x: Int, y: Int): Boolean {
        return if (x <= 0 || y <= 0 || y >= input[0].size - 1 || x >= input.size - 1) true
        else (isVisibleFromDirection(x,y,"up") ||
                isVisibleFromDirection(x,y,"down") ||
                isVisibleFromDirection(x,y,"left") ||
                isVisibleFromDirection(x,y,"right"))
    }

    fun part1(input: List<List<Int>>): Int {
        for (i in input.indices)
        {
            visibility.add(mutableListOf())
            for (j in 0 until input[i].size)
                visibility[i].add(isVisible(i,j))
        }
        return visibility.flatten().count{ it }
    }

    fun part2 (input: List<List<Int>>): Int {
        for (i in input.indices) {
            for (j in 0 until input[i].size)
                scenicScores.add(scenicScore(i, j))
        }
        return scenicScores.max()
    }

    println(part1(input))
    println(part2(input))
}
