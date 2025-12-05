package aoc.Day3

import java.io.File
import kotlin.math.*

fun main(){
    val lines = File("input/day03.txt").readLines()

    println(">")
    println(part1(lines))
    //println(part2(lines))
}

fun part1(lines:List<String>): String {
    var result: Long = 0
    for (line in lines) {
        val values = line.split("")
        val highestDigit = values.max()
        val leftmostPosition = line.indexOf(highestDigit)
        if (leftmostPosition == line.length - 1) {
            val rest = line.dropLast(1)
            val secondHighest = rest.split("").max()
            val value = secondHighest + highestDigit
            result += value.toInt()
        } else {
            val rest = line.substring(leftmostPosition+1)
            val secondHighest = rest.split("").max()
            val value = highestDigit + secondHighest
            result += value.toInt()
        }
    }
    return result.toString()
}