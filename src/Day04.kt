package aoc.Day4

import java.io.File

fun main() {
    val input = loadInput("input/day04.txt")

    println(">")
    println(part1(input))
//    println(part2(lines))
}

fun loadInput(filepath: String): ArrayList<List<Int>> {
    val grid: ArrayList<List<Int>> = arrayListOf<List<Int>>()
    val lines: List<String> = File(filepath).readLines()
    for (line in lines) {
        grid.add(
            line
            .trim()
            .replace(".", "0")
            .replace("@", "1")
            .split("")
            .filterNot { it.isEmpty() }
            .map { it.toInt() })
    }
    return grid
}

fun part1(grid: ArrayList<List<Int>>): String {
    val enableGridDraw = false
    var result: Long = 0
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] > 0) {
                if (countNeighbours(x, y, grid) < 4) {
                    if (enableGridDraw) print("X")
                    result += 1
                } else {
                    if (enableGridDraw) print("@")
                }
            } else {
                if (enableGridDraw) print(" ")
            }
        }
        if (enableGridDraw) println()
    }
    return result.toString()
}

/**
 * | -1-1 | 0-1 | +1-1 |
 * | -10  | 00  | +10  |
 * | -1+1 | 0+1 | +1+1 |
 */
fun countNeighbours(x: Int, y: Int, grid: ArrayList<List<Int>>): Int {
    return (grid.getOrNull(y+1)?.getOrNull(x) ?: 0) +
        (grid.getOrNull(y+1)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x-1) ?: 0) +
        (grid.getOrNull(y)?.getOrNull(x-1) ?: 0) +
        (grid.getOrNull(y+1)?.getOrNull(x-1) ?: 0)
}
