package aoc.Day4

import java.io.File

fun main() {
    val input = loadInput("input/day04.txt")

    println(">")
//    println(part1(input))
    println(part2(input))
}

fun loadInput(filepath: String): ArrayList<MutableList<Int>> {
    val grid = arrayListOf<MutableList<Int>>()
    val lines: List<String> = File(filepath).readLines()
    for (line in lines) {
        grid.add(
            line
            .trim()
            .replace(".", "0")
            .replace("@", "1")
            .map {  it.digitToInt() }
            .toMutableList()
        )
    }
    return grid
}

fun part1(grid: ArrayList<MutableList<Int>>): String {
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

fun part2(grid: ArrayList<MutableList<Int>>): String {
    var currentGrid:ArrayList<MutableList<Int>> = ArrayList(grid)
    var nextGrid:ArrayList<MutableList<Int>> = ArrayList(grid)
    var result: Long = 0
    var changesMade: Boolean
    while (true) {
        changesMade = false
        for (y in currentGrid.indices) {
            for (x in currentGrid[y].indices) {
                if (currentGrid[y][x] > 0) {
                    if (countNeighbours(x, y, currentGrid) < 4) {
                        result += 1
                        nextGrid[y][x] = 0
                        changesMade = true
                    }
                }
            }
        }
        if (changesMade) {
            currentGrid = nextGrid
        } else {
            break;
        }
    }
    return result.toString()
}

/**
 * | -1-1 | 0-1 | +1-1 |
 * | -10  | 00  | +10  |
 * | -1+1 | 0+1 | +1+1 |
 */
fun countNeighbours(x: Int, y: Int, grid: ArrayList<MutableList<Int>>): Int {
    return (grid.getOrNull(y+1)?.getOrNull(x) ?: 0) +
        (grid.getOrNull(y+1)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x+1) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x) ?: 0) +
        (grid.getOrNull(y-1)?.getOrNull(x-1) ?: 0) +
        (grid.getOrNull(y)?.getOrNull(x-1) ?: 0) +
        (grid.getOrNull(y+1)?.getOrNull(x-1) ?: 0)
}
