package aoc.Day3

import java.io.File
import kotlin.math.*

fun main(){
    val lines = File("input/day03example.txt").readLines()

    println(">")
    //println(part1(lines))
    println(part2(lines))
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

fun part2(lines:List<String>): String {
    var result: Long = 0
    for (line in lines) {
        var l = line
        while (l.length > 12) {
            val lowestDigit = l.split("").filterNot { it.isEmpty() }.min().single()
            val position = l.indexOfLast{ it == lowestDigit }
            l = l.removeRange(position, position+1)
        }
        println(l)
        result += l.toLong()
    }
    return result.toString()
}