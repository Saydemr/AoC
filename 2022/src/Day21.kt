package src

class NewMonkey {
    var id: String = ""
    var hasYelled: Boolean = false
    var operation: String = ""
    var firstOperand: String = ""
    var secondOperand: String = ""
    var firstLong: Long = Long.MIN_VALUE
    var secondLong: Long = Long.MIN_VALUE
    var result: Long = Long.MIN_VALUE

    constructor(id: String, operation: String, firstOperand: String, secondOperand: String) {
        this.id = id
        this.operation = operation
        this.hasYelled = false
        this.firstOperand = firstOperand
        this.secondOperand = secondOperand
    }

    constructor(id: String, result: Long) {
        this.id = id
        this.hasYelled = true
        this.result = result
    }

    @Override
    override fun toString(): String {
        return "src.NewMonkey(id='$id', hasYelled=$hasYelled, operation='$operation', firstOperand='$firstOperand', secondOperand='$secondOperand', firstLong=$firstLong, secondLong=$secondLong, result=$result)"
    }
}

fun main() {
    val monkeys = mutableListOf<NewMonkey>()
    readInput("input21").forEach {
        val monkeyInfo = it.split(" ", ":").filter { it2 -> it2 != "" }
        println(monkeyInfo)
        if (monkeyInfo.size == 2) {
            monkeys.add(NewMonkey(monkeyInfo[0], monkeyInfo[1].toLong()))
        } else {
            val id = monkeyInfo[0]
            val operation = monkeyInfo[2]
            val firstOperand = monkeyInfo[1]
            val secondOperand = monkeyInfo[3]
            monkeys.add(NewMonkey(id, operation, firstOperand, secondOperand))
        }
    }

    var monkeysToYell = monkeys.filter { !it.hasYelled }
    while (monkeysToYell.isNotEmpty()) {
        val yelledMonkeys = monkeys.filter { it.hasYelled }
        monkeysToYell = monkeys.filter { !it.hasYelled }
        monkeysToYell.forEach { monkey ->
            if (yelledMonkeys.any { monkey.firstOperand == it.id }) {
                monkey.firstLong = yelledMonkeys.first { monkey.firstOperand == it.id }.result
            }
            if (yelledMonkeys.any { monkey.secondOperand == it.id }) {
                monkey.secondLong = yelledMonkeys.first { monkey.secondOperand == it.id }.result
            }

            if (monkey.firstLong != Long.MIN_VALUE && monkey.secondLong != Long.MIN_VALUE) {
                monkey.result = when (monkey.operation) {
                    "+" -> monkey.firstLong + monkey.secondLong
                    "-" -> monkey.firstLong - monkey.secondLong
                    "*" -> monkey.firstLong * monkey.secondLong
                    "/" -> monkey.firstLong / monkey.secondLong
                    else -> throw Exception("Unknown operation: ${monkey.operation}")
                }
                monkey.hasYelled = true
            }
        }
    }
    println(monkeys.first { it.id == "root" }.result)
}
