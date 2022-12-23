import java.util.function.Predicate

val cardinals = listOf(
    Pair(-1,1), // northwest
    Pair(0,1), // north
    Pair(1,1), // northeast
    Pair(1,0), // east
    Pair(-1,0), // west
    Pair(-1,-1), // southwest
    Pair(0,-1), // south
    Pair(1,-1) // southeast
)

var moveOrder = mutableListOf(
    Predicate<Pair<Int,Int>>  { it.second == 1 }, // north
    Predicate<Pair<Int,Int>> { it.second == -1 }, // south
    Predicate<Pair<Int,Int>> { it.first == -1 }, // west
    Predicate<Pair<Int,Int>> { it.first == 1 } // east
)

fun main() {

    var elfPositions = readInput("../inputs/input23")
        .mapIndexed { yCoordinate, list ->
            list.mapIndexed { xCoordinate, char ->
                if (char == '#') Pair(xCoordinate, -yCoordinate) else Pair(100,100) }
        }
        .flatten()
        .filter { it.first != 100 && it.second != 100 }

    fun move(xPosition: Int, yPosition: Int) : Pair<Int,Int> {
        val positionsToCheck = cardinals.map { Pair(it.first + xPosition, it.second + yPosition) }

        if ( !positionsToCheck.any { elfPositions.contains(it) } ) {
            return Pair(xPosition, yPosition)
        }
        else {
            for (direction in moveOrder)
            {
                val oneDirectionCheck = cardinals.filter { direction.test(it) }.map { Pair(it.first + xPosition, it.second + yPosition) }
                if ( !oneDirectionCheck.any { elfPositions.contains(it) } )
                {
                    val towards = cardinals.first { direction.test(it) && (it.first == 0 || it.second == 0) }
                    return Pair(xPosition + towards.first, yPosition + towards.second)
                }
            }
        }
        return Pair(xPosition, yPosition)
    }

    for (round in 1 until 1000)
    {
        val proposedMoves = elfPositions.associateWith { oldPosition -> move(oldPosition.first, oldPosition.second) }
        val bannedMoves = proposedMoves.values.groupingBy { it }.eachCount().filter { it.value > 1 }.toList().toMutableList().map { it.first }

        val nextElfPositions = elfPositions.map { position ->
            if (!bannedMoves.contains(proposedMoves[position]!!))
                proposedMoves[position]!!
            else
                position
        }.toMutableList()

        // part 2. could be checked if all proposed moves are banned
        if (nextElfPositions == elfPositions) {
            println("Elf positions have stabilized after $round rounds")
            break
        }
        else
            elfPositions = nextElfPositions

        val fp = moveOrder.first()
        moveOrder = moveOrder.drop(1).toMutableList()
        moveOrder.add(fp)
    }

    val minX = elfPositions.minBy { it.first }.first
    val minY = elfPositions.minBy { it.second }.second
    val maxX = elfPositions.maxBy { it.first }.first
    val maxY = elfPositions.maxBy { it.second }.second

    println(((maxX + 1 - minX ) * (maxY + 1 - minY )) - elfPositions.size)



}


/**
 * part 1 : 2416 too low
 */
