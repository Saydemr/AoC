import kotlin.math.abs
import kotlin.system.exitProcess

val manhattanDistance = { x1 :Long, y1: Long, x2: Long, y2: Long ->
    abs(x1 - x2) + abs(y1 - y2)
}

fun main() {


    val row =  2_000_000L
    val banned = mutableSetOf<Pair<Long,Long>>()
    val beacons = mutableSetOf<Pair<Long,Long>>()

    val sensorBeaconList = readInput("../inputs/input15")
        .map { it.trim().split("x=", "y=", " ", ",", ":") }
        .map { it.filter {  it2 -> it2.trim(); val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex(); regex.matches(it2) } }
        .map { ; it.map { it2 -> it2.toLong() } }
        .map {
            val cDistance = manhattanDistance(it[0], it[1], it[2], it[3])
            beacons.add(Pair(it[2],it[3]))
            val yDiff = abs(row - it[1])
            val leftRightSpan = abs(cDistance - yDiff)
            // it0 x1, it1 y1,
            // it2 x2, it3 y2
            if ( cDistance >= yDiff) {
                banned.addAll((it[0] - leftRightSpan  ..it[0] + leftRightSpan)
                    .map { it2 -> Pair(it2, row) })
            }
            it// yeah man I'm just gonna leave this here. Best coding practice ever.
        }

    banned.removeAll(beacons)
    // println(banned.size)

    // part2
    // just more badder KEKW

    val pointsToCheck = mutableSetOf<Pair<Long,Long>>()
    sensorBeaconList.map { Triple(it[0], it[1], manhattanDistance(it[0], it[1], it[2], it[3])) }
        .forEach{
            val xPlus = it.first + it.third
            val xMinus = it.first - it.third
            val yPlus = it.second + it.third
            val yMinus = it.second - it.third

            // from topTop to leftLeft
            val allX = (xMinus - 1 .. it.first).toList()
            val allY = (it.second .. yPlus + 1).toList()
            // zip them together
            val beforeCheck = allX.zip(allY).filter { point ->
                val values = sensorBeaconList.map { it2 -> Triple(it2[0], it2[1], manhattanDistance(it2[0], it2[1], it2[2], it2[3])) };
                values.filter { it3 -> manhattanDistance(it3.first, it3.second, point.first, point.second) == it3.third + 1 }.size > 3
                        && point.first in 0L..4_000_000L
                        && point.second in 0L..4_000_000L
            }
            pointsToCheck.addAll(beforeCheck)

            val allX2 = (it.first .. xPlus + 1).toList()
            val allY2 = (it.second .. yPlus + 1).toList()
            // zip them together
            val beforeCheck2 = allX2.zip(allY2).filter { point ->
                val values = sensorBeaconList.map { it2 -> Triple(it2[0], it2[1], manhattanDistance(it2[0], it2[1], it2[2], it2[3])) };
                values.filter { it3 -> manhattanDistance(it3.first, it3.second, point.first, point.second) == it3.third + 1 }.size > 3
                        && point.first in 0L..4_000_000L
                        && point.second in 0L..4_000_000L
            }

            pointsToCheck.addAll(beforeCheck2)


            // from bottomBottom to leftLeft
            val allX3 = (xMinus - 1 .. it.first).toList()
            val allY3 = (yMinus - 1 .. it.second).toList()
            // zip them together

            val beforeCheck3 = allX3.zip(allY3).filter { point ->
                val values = sensorBeaconList.map { it2 -> Triple(it2[0], it2[1], manhattanDistance(it2[0], it2[1], it2[2], it2[3])) };
                values.filter { it3 -> manhattanDistance(it3.first, it3.second, point.first, point.second) == it3.third + 1 }.size > 3
                        && point.first in 0L..4_000_000L
                        && point.second in 0L..4_000_000L
            }

            pointsToCheck.addAll(beforeCheck3)


            // from bottomBottom to rightRight
            val allX4 = (it.first .. xPlus + 1).toList()
            val allY4 = (yMinus - 1 .. it.second).toList()
            // zip them together

            val beforeCheck4 = allX4.zip(allY4).filter { point ->
                val values = sensorBeaconList.map { it2 -> Triple(it2[0], it2[1], manhattanDistance(it2[0], it2[1], it2[2], it2[3])) };
                values.filter { it3 -> manhattanDistance(it3.first, it3.second, point.first, point.second) == it3.third + 1 }.size > 3
                        && point.first in 0L..4_000_000L
                        && point.second in 0L..4_000_000L
            }

            pointsToCheck.addAll(beforeCheck4)

        }


    pointsToCheck.forEach {
        println(it.first * 4_000_000L + it.second)
    }

    /**
     * This is hilarious. I'm just gonna leave this here. I will update this after the competition is over.
     */
}
