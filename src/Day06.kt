package aoc.Day6

import java.io.File
import kotlin.collections.ArrayList
import kotlin.math.*

fun main() {
    val mathProblems1 = getInput1("input/day06example.txt")
    val mathProblems2 = getInput2("input/day06example.txt")

    println(">")
//    println(part1(mathProblems1))
    println(part2(mathProblems2))
}

data class MathProblem(val numbers: ArrayList<Long>, val symbol: String)

fun getInput1(filepath: String): ArrayList<MathProblem> {
    val mathProblems = ArrayList<MathProblem>()
    var lines = File(filepath).readLines()
    val symbols = lines.last().split(" ").filterNot { it.isEmpty() }
    lines = lines.dropLast(1)
    val columnCount = symbols.count()
    val columns = ArrayList<ArrayList<Long>>(columnCount)
    for (i in 0..columnCount - 1) {
        columns.add(ArrayList<Long>())
    }
    var i: Int
    for (line in lines) {
        i = 0
        for (value in line.split(" ").filterNot { it.isEmpty() }) {
            columns[i].add(value.toLong())
            i += 1
        }
    }

    for (i in columns.indices) {
        mathProblems.add(MathProblem(columns[i], symbols[i]))
    }

    return mathProblems
}

fun getInput2(filepath: String): ArrayList<MathProblem> {
    val mathProblems = ArrayList<MathProblem>()
    var lines = File(filepath).readLines()
    val symbols = lines.last().split(" ").filterNot { it.isEmpty() }
    lines = lines.dropLast(1)
    for (line in lines) {
        for (value in line.split("")) {
            println(value)
        }
    }
    return mathProblems
}

fun part1(mathProblems: ArrayList<MathProblem>): String {
    var result: Long = 0

    for (m in mathProblems) {
        if (m.symbol == "*") {
            result += m.numbers.reduce { acc, n ->  acc * n }
        }
        if (m.symbol == "+") {
            result += m.numbers.reduce { acc, n ->  acc + n }
        }
    }

    return result.toString()
}

fun part2(mathProblems: ArrayList<MathProblem>): String {
    return part1(mathProblems)
}