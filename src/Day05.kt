package aoc.Day5

import java.io.File
import kotlin.math.*

fun main(){
    val (ranges, ids) = getInput("input/day05.txt")

    println(">")
//    println(part1(ranges, ids))
    println(part2(ranges))
}
data class Range(val min:Long, val max:Long)
data class Input(val ranges: ArrayList<Range>, val ids: ArrayList<Long>)

fun getInput(filepath:String): Input {
    var ranges = ArrayList<Range>()
    var ids = ArrayList<Long>()
    val lines = File(filepath).readLines()
    val regex = Regex("(\\d+)-(\\d+)")
    var inputSwitch = true
    for (line in lines) {
        if (inputSwitch) {
            if (line.isEmpty()) {
                inputSwitch = false
                continue
            }
            val match = regex.find(line)
            val min_value: Long = match?.groups[1]?.value?.toLong() ?: 0
            val max_value: Long = match?.groups[2]?.value?.toLong() ?: 0
            ranges.add(Range(min_value, max_value))
        } else {
            ids.add(line.toLong())
        }

    }
    return Input(ranges, ids)
}

fun part1(ranges: ArrayList<Range>, ids: ArrayList<Long>): String {
    var result: Long = 0

    for (id in ids) {
        if (is_in_ranges(ranges, id)) {
            result += 1
        }
    }

    return result.toString()
}

fun is_in_ranges(ranges: ArrayList<Range>, id: Long): Boolean {
    for (range in ranges) {
        if (id < range.min) {
            continue
        }
        if (id > range.max) {
            continue
        }
        return true
    }
    return false
}

/**
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
fun __part2(ranges: ArrayList<Range>): String {
    var ids:MutableSet<Long> = mutableSetOf()
    for (range in ranges) {
        for (i in range.min .. range.max) {
            ids.add(i)
        }
    }
    return ids.count().toString()
}

/**
 * lower an upper bound are about 559454809365675 values to test
 */
fun ___part2(ranges: ArrayList<Range>): String {
    var min_value:Long = ranges[0].min
    var max_value:Long = 0
    for (range in ranges) {
        min_value = min(range.min, min_value)
        max_value = max(range.max, max_value)
    }

    var result: Long = 0
    for (i in min_value .. max_value) {
        if (is_in_ranges(ranges, i)) {
            result += 1
        }
    }

    return result.toString()
}

fun part2(ranges: ArrayList<Range>): String {
    var result: Long = 0
    return result.toString()
}