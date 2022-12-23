import java.util.Stack

fun main() {


    fun part2(input: List<String>): String {
        val stackIndex = input.indexOfFirst { it.isEmpty() }
        val stackInput = input.subList(0, stackIndex-1)
        val numStacks = input.subList(stackIndex-1, stackIndex)
            .map { it.replace("  ", "")
                .trim()
                .replace(" ", ",") }[0]
            .split(",")
            .map { it.toInt() }
            .last()

        val mutableList = mutableListOf<Stack<Char>>()
        val stack = stackInput.reversed()
            .asSequence()
            .map { it.replace("] ", "]")
                .replace("]", "")
                .replace("[","") }
            .toMutableList()

        for (i in 0 until numStacks) {
            mutableList.add(Stack())
        }

        for (i in 0 until stack.size) {
            for (j in 0 until stack[i].length) {
                mutableList[j].push(stack[i][j])
            }
        }

         mutableList.forEach { it.removeAll {it2 -> it2 == ' '} }

        val moves = input.subList(stackIndex+1, input.size)
            .map { it.split(" ") }
            .map { Triple(it[1].toInt(), it[3].toInt(), it[5].toInt() ) }


        for (i in moves.indices) {
            val (num, from, to) = moves[i]
            val fromStack = mutableList[from-1]
            val toStack = mutableList[to-1]
            val order = mutableListOf<Char>()
            for (j in 0 until num) {
                order.add(fromStack.pop())
            }
            order.reversed()
                .toMutableList()
                .forEach { toStack.push(it) }

        }

        return mutableList.map { it.peek().toString() }
            .joinToString { it }
            .replace(", ", "")
    }

    fun part1(input: List<String>): String {

        val stackIndex = input.indexOfFirst { it.isEmpty() }
        val stackInput = input.subList(0, stackIndex - 1)
        val numStacks = input.subList(stackIndex - 1, stackIndex)
            .map {
                it.replace("  ", "")
                    .trim()
                    .replace(" ", ",")
            }[0]
            .split(",")
            .map { it.toInt() }.last()

        val mutableList = mutableListOf<Stack<Char>>()
        val stack = stackInput.reversed()
            .asSequence()
            .map {
                it.replace("] ", "]")
                    .replace("]", "")
                    .replace("[", "")
            }
            .toMutableList()

        for (i in 0 until numStacks) {
            mutableList.add(Stack())
        }

        for (i in 0 until stack.size) {
            for (j in 0 until stack[i].length) {
                mutableList[j].push(stack[i][j])
            }
        }

        mutableList.forEach { it.removeAll {it2 -> it2 == ' '} }

        val moves = input.subList(stackIndex + 1, input.size)
            .map { it.split(" ") }
            .map { Triple(it[1].toInt(), it[3].toInt(), it[5].toInt()) }

        for (i in moves.indices) {
            val (num, from, to) = moves[i]
            val fromStack = mutableList[from - 1]
            val toStack = mutableList[to - 1]
            for (j in 0 until num) {
                toStack.push(fromStack.pop())
            }
        }
        return mutableList.map { it.peek().toString() }
            .joinToString { it }
            .replace(", ", "")

    }

    val input = readInput("../inputs/input5")
    val less =  parseDay05Input(input)
    println(part1(less))
    println(part2(less))
}
