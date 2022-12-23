fun main() {

    val walls = mutableSetOf<Pair<Int,Int>>()
    val sands = mutableSetOf<Pair<Int,Int>>()

    readInput("../inputs/input14")
        .map { it.trim().split("->") }
        .map { it.map { nane -> nane.trim().split(",") }
            .map { list -> list.map { str -> str.toInt() } } }
        .map { it.windowed(2)
            .forEach { pairs ->
                if (pairs[0][0] == pairs[1][0]) {
                    val min = if (pairs[0][1] < pairs[1][1]) pairs[0][1] else pairs[1][1]
                    val max = if (pairs[0][1] < pairs[1][1]) pairs[1][1] else pairs[0][1]
                    walls.addAll(List(max - min + 1) { pairs[0][0] }.zip((min..max)))
                }
                else {
                    val min = if (pairs[0][0] < pairs[1][0]) pairs[0][0] else pairs[1][0]
                    val max = if (pairs[0][0] < pairs[1][0]) pairs[1][0] else pairs[0][0]
                    walls.addAll((min..max).zip(List(max - min + 1) { pairs[0][1] }))
                }
            }
        }


    val height = walls.maxBy { it.second }.second + 2
    val minWidth = walls.minBy { it.first }.first - height - 2
    val maxWidth = walls.maxBy { it.first }.first + height + 2

    // part 2
    walls.addAll((minWidth..maxWidth).zip(List(maxWidth - minWidth + 1) { height }))

    var dfs = false
    val startPos = Pair(500, 0)

    fun dfs(pos: Pair<Int,Int>): Boolean {
        dfs = dfs || pos.second > height || sands.contains(Pair(500,0)) // remove last `or` to get part 1
        if (dfs || sands.contains(pos) || walls.contains(pos))
            return dfs
        if (!(dfs(Pair(pos.first, pos.second + 1))
                    || dfs(Pair(pos.first - 1, pos.second + 1))
                    || dfs(Pair(pos.first + 1, pos.second + 1)))
            )
            sands.add(pos)
        return true
    }
    while (!dfs)
        dfs(startPos)

    println(sands.size)
}
