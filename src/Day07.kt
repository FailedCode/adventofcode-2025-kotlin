package aoc.Day7

import java.io.File
import kotlin.math.*
import kotlin.text.split

fun main() {
    val manifold = getInput("input/day07.txt")

    println(">")
    println(part1(manifold))
//    println(part2(manifold))
}

data class Manifold(val grid: ArrayList<ArrayList<Int>>, val startpos: Pair<Int, Int>)
data class Ray(var y: Int, var x: Int)

// kotlin enums are useless.
enum class Types(val t: Int) {
    EMPTY(0), SPLITTER(1), START(2)
}

fun getInput(filepath: String): Manifold {
    var grid = ArrayList<ArrayList<Int>>()
    var startpos: Pair<Int, Int> = Pair(0, 0)
    val lines = File(filepath).readLines()
    var y = 0
    var x = 0
    for (line in lines) {
        grid.add(ArrayList<Int>())
        var values = line
            .replace(".", "0")
            .replace("^", "1")
            .replace("S", "2")
            .split("")
            .filterNot { it.isEmpty() }
        x = 0
        for (v in values) {
            if (v == "2") {
                startpos = Pair(y, x)
                grid[y].add(0) // ???
                continue
            }
            grid[y].add(v.toInt())
            x += 1
        }
        y += 1
    }
    return Manifold(grid, startpos)
}

fun part1(manifold: Manifold): String {
    var result: Long = 0
    val max_y = manifold.grid.size - 1
    var rays: MutableMap<String, Ray> = mutableMapOf()
    var raysNext: MutableMap<String, Ray> = mutableMapOf()
    rays["${manifold.startpos.first},${manifold.startpos.second}"] =
        Ray(manifold.startpos.first, manifold.startpos.second)

    while (!rays.isEmpty()) {
        for (ray in rays.values) {
            if (manifold.grid[ray.y + 1][ray.x] == 1) {
                // split event!
                result += 1
                raysNext["${ray.y + 1},${ray.x + 1}"] = Ray(ray.y + 1, ray.x + 1)
                raysNext["${ray.y + 1},${ray.x - 1}"] = Ray(ray.y + 1, ray.x - 1)
            } else {
                ray.y += 1
                if (ray.y < max_y) {
                    raysNext["${ray.y},${ray.x}"] = Ray(ray.y, ray.x)
                }
            }
        }
        rays = raysNext
        raysNext = mutableMapOf()

//        for (y in manifold.grid.indices) {
//            for (x in manifold.grid[y].indices) {
//                var symbol = " "
//                if (manifold.grid[y][x] == 1) symbol = "^"
//                if (rays.contains("$y,$x")) symbol = "|"
//                print(symbol)
//            }
//            println()
//        }
    }


    return result.toString()
}


fun part2(manifold: Manifold): String {
    var result: Long = 0
    return result.toString()
}