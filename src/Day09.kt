package aoc.Day9

import java.io.File
import kotlin.math.*

fun main(){
    val points = getInput("input/day09.txt")

    println(">")
    println(part1(points))
//    println(part2(points))
}

fun getInput(filepath: String): ArrayList<Pair<Int, Int>> {
    val lines = File(filepath).readLines()
    val points: ArrayList<Pair<Int, Int>> = ArrayList()
    val regex = Regex("(\\d+),(\\d+)")
    for (line in lines ) {
        val match = regex.find(line)
        val x: Int = match?.groups[1]?.value?.toInt() ?: 0
        val y: Int = match?.groups[2]?.value?.toInt() ?: 0
        points.add(Pair(x, y))
    }
    return points
}

fun rectSize(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Long {
    val dx = abs(p1.first - p2.first + 1).toLong()
    val dy = abs(p1.second - p2.second + 1).toLong()
    return dx * dy
}

fun part1(points:ArrayList<Pair<Int, Int>>): String {
    var size:Long = 0

    for (p1 in points) {
        for (p2 in points) {
            if (p1 == p2) {
                continue
            }
            size = max(size, rectSize(p1, p2))
        }
    }

    return size.toString()
}

fun part2(points:ArrayList<Pair<Int, Int>>): String {
    var result: Long = 0
    return result.toString()
}