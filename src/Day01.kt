package aoc.Day1

import java.io.File
import kotlin.math.*

fun main(){
    val lines = File("input/day01.txt").readLines()

    println(">")
    println(part1(lines))
    println(part2(lines))
}

fun part1(lines:List<String>): Int {
    val max_val = 100
    var dial = 50
    var number_of_zeros = 0
    for (line in lines) {
        val n = line
            .replace("R", "")
            .replace("L", "-")
            .toInt()
        dial = (dial + n + max_val) % max_val
        if (dial == 0) {
            number_of_zeros += 1
        }
    }
    return number_of_zeros
}

fun part2(lines:List<String>): Int {
    val max_val = 100
    var dial = 50
    var number_of_zeros = 0
    for (line in lines) {
        var n = line
            .replace("R", "")
            .replace("L", "-")
            .toInt()
        val change = sign(n.toDouble()).toInt()
        val add = sign(-n.toDouble()).toInt()
        while (abs(n) > 0) {
            dial = (dial + change + max_val) % max_val
            if (dial == 0) {
                number_of_zeros += 1
            }
            n += add
        }
    }
    return number_of_zeros
}