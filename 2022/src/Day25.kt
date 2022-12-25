package src

val snafu = mapOf("=" to -2, "-" to -1, "1" to 1, "2" to 2, "0" to 0)
val snafuBack = mapOf(-2 to "=", -1 to "-", 1 to "1", 2 to "2", 0 to "0")

fun main() {

    fun encoder(number: String): Long {

        // powers of five in reverse order
        val powersOf5 =
            generateSequence { 5 }
                .map { it.toLong() }
                .scan(1L) { acc, i -> acc * i }
                .take(number.length)
                .toList()
                .reversed()

        return number
            .map { snafu[it.toString()]!! }
            .mapIndexed { index, i -> i * powersOf5[index] }
            .sum()
    }

    fun decoder(number: Long): String {

        return if (number == 0L) ""
        else {
            if (number % 5 == 0L || number % 5 == 1L || number % 5 == 2L) {
                decoder(number / 5) + snafuBack[(number % 5).toInt()]!!
            } else {
                decoder(number / 5 + 1) + snafuBack[(number % 5 - 5).toInt()]!!
            }
        }
    }

    val input = readInput("input25")
        .map { it.trim() }
        .sumOf { encoder(it) }
        .also { println(it) }

    println(decoder(input))

}
