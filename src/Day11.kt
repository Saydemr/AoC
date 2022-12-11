import kotlin.math.floor

open class Monkey {
    var id: Int = 0
    var items: MutableList<Int> = mutableListOf()
    var operator: String = ""
    var operand : String = ""
    var test : Int = 0
    var passAction: Int = 0
    var failAction: Int = 0
    var numInspected: Int = 0

    @Override
    override fun toString(): String {
        return "Monkey(id=$id, items=$items, operator='$operator', operand='$operand', test=$test, passAction=$passAction, failAction=$failAction, numInspected=$numInspected)"
    }

}

fun readMonkey(monkeyInfo: MutableList<String>): Monkey {
    val monkey = Monkey()
    monkey.id = monkeyInfo[0].trim()
        .replace("Monkey ", "")
        .replace(":","")
        .toInt()
    monkey.items = monkeyInfo[1].trim()
        .replace("Starting items: ", "")
        .split(", ").map { it.trim().toInt() }.toMutableList()
    val operation = monkeyInfo[2]
        .trim()
        .replace("Operation: new = old ", "")
        .split(" ")
    monkey.operator = operation[0]
    monkey.operand = operation[1]
    monkey.test = monkeyInfo[3]
        .trim()
        .replace("Test: divisible by ", "")
        .toInt()

    monkey.passAction = monkeyInfo[4].trim()
        .replace("If true: throw to monkey ", "")
        .toInt()
    monkey.failAction = monkeyInfo[5]
        .trim()
        .replace("If false: throw to monkey ", "")
        .toInt()

    return monkey
}


fun worryLevelCalculator(worryLevel: Int, operator: String, operand: String): Int {

    var operandInt = if (operand ==  "old") worryLevel else operand.toInt()
    return when (operator) {
        "+" -> floor(((worryLevel) + (operandInt  ) ) / 3.0 ).toInt()
        "-" -> floor(((worryLevel) - (operandInt  ))  / 3.0 ).toInt()
        "*" -> floor(((worryLevel) * (operandInt ))  / 3.0 ).toInt()
        else -> worryLevel
    }
}

fun main() {

    val monkeys = mutableListOf<Monkey>()

    readInput("../inputs/input11")
        .chunked(7)
        .forEach {
            monkeys.add(readMonkey(it.toMutableList()))
        }

    for (i in 0..19)
    {
        monkeys.forEach { monkey ->
            monkey.items.forEach {
                val worryLevel = worryLevelCalculator(it, monkey.operator, monkey.operand)
                if (worryLevel % monkey.test == 0) {
                    monkeys[monkey.passAction].items.add(worryLevel)
                } else {
                    monkeys[monkey.failAction].items.add(worryLevel)
                }
                monkey.numInspected += 1
            }
            monkey.items.clear()
        }
    }

    monkeys.map { it.numInspected }
        .sorted()
        .takeLast(2)
        .reduce { acc, i -> acc * i }
        .let { println(it) }



}
