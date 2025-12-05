package aoc.Day2

import java.io.File
import kotlin.math.*

fun main(){
    val lines = File("input/day02.txt").readLines()
    val ranges = lines[0].split(",")

    println(">")
    println(part1(ranges))
    println(part2(ranges))
}

fun part1(ranges:List<String>): String {
    var result: Long = 0
    val regex = Regex("(\\d+)-(\\d+)")
    for (range in ranges) {
        val match = regex.find(range)
        val min_value: Long = match?.groups[1]?.value?.toLong() ?: 0
        val max_value: Long = match?.groups[2]?.value?.toLong() ?: 0
        var n = min_value
        while (n <= max_value) {
            if (is_invalid_part1(n)) {
                result += n
            }
            n += 1
        }
    }
    return result.toString()
}

// The first half is the same as the seconds half
fun is_invalid_part1(number:Long) : Boolean {
    val id = number.toString()
    if ( id.length % 2 != 0) {
        return false
    }
    val half = id.length / 2
    val first_part = id.substring(0, half)
    val second_part = id.substring(half)
    return first_part == second_part
}

fun part2(ranges:List<String>): String {
    var result: Long = 0
    val regex = Regex("(\\d+)-(\\d+)")
    for (range in ranges) {
        val match = regex.find(range)
        val min_value: Long = match?.groups[1]?.value?.toLong() ?: 0
        val max_value: Long = match?.groups[2]?.value?.toLong() ?: 0
        var n = min_value
        while (n <= max_value) {
            if (is_invalid_part2(n)) {
                result += n
            }
            n += 1
        }
    }
    return result.toString()
}

fun is_invalid_part2(number:Long) : Boolean {
    val id = number.toString()
    var n = 1
    // once we testet more than half, the number can't repeat
    while (n <= id.length / 2) {
        // If it is impossible to form a multiple with the substring,
        // no need to test further
        // 1234 => id.length = 4
        // n = 3
        // => 4 % 3 > 0
        if (id.length % n != 0) {
            n += 1
           continue
        }

        val part = id.substring(0, n)
        val factor = id.length / n
        val test = part.repeat(factor)
        if (test == id) {
            return true
        }

        n += 1
    }

    return false
}