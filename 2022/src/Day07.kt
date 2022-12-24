package src

import java.util.*

fun main() {

    // directory name, with Pair<Size, Path>
    val directories = mutableMapOf<String, Pair<Int, String>>()
    val currentDir = Stack<String>()
    val getPath = currentDir.joinToString(separator = "/") { "" }

    fun createDirectories(input: List<List<String>>) {
        println(input)
        input.forEach { inOut ->
            val inputCommand = inOut[0]


            if (inputCommand == "ls") {
                inOut.drop(1)
                    .map { it.trim().split(" ") }
                    .forEach { it2 ->
                        if (it2[0] == "dir")
                            directories[it2[1]] = Pair(0, getPath)
                        else
                            directories[it2[1]] = Pair(0, getPath)
                    }
            } else if (inputCommand == "cd") {
                if (inOut[1] == "..")
                    currentDir.pop()
                else
                    currentDir.push(inOut[2])
            }
        }
    }

    fun part1(input: List<List<String>>): Int {
        createDirectories(input)

        return 0
    }

    val commands = parseCommandLine("input7")
    println(part1(commands))

}
