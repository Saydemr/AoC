package src

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("2022/inputs","$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun readUntilEmptyLine(input: List<String>): List<String> {
    val result = mutableListOf<String>()
    for (line in input) {
        if (line.isEmpty()) {
            break
        }
        result.add(line)
    }
    return result
}

fun parseDay05Input(input: List<String>): List<String> {
    /**
     * Parse the data in the following form
     * three consecutive spaces correspond to an empty item
     * one space is a separator
     */
    return input.map { it.replace("    ", " [ ]") }

}

fun parseCommandLine(name: String): List<List<String>> {
    return File("src", "$name.txt")
        .readText(Charsets.UTF_8)
        .split("$ ")
        .map { it.trim().split("\n") }
}