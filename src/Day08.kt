package aoc.Day8

import java.io.File
import kotlin.math.*

fun main(){
    val points = getInput("input/day08example.txt")

    println(">")
    println(part1(points))
//    println(part2(lines))
}

data class Point ( val id:Int, val x:Int, val y:Int, val z:Int )

fun getInput(filepath: String): ArrayList<Point> {
    var points: ArrayList<Point> = ArrayList()
    val lines = File(filepath).readLines()
    val regex = Regex("(\\d+),(\\d+),(\\d+)")
    var i:Int = 0
    for (line in lines ) {
        val match = regex.find(line)
        val x:Int = match?.groups[1]?.value?.toInt() ?: 0
        val y:Int = match?.groups[2]?.value?.toInt() ?: 0
        val z:Int = match?.groups[3]?.value?.toInt() ?: 0
        points.add(Point(i,x,y,z))
        i += 1
    }
    return points
}

fun distance(pointA:Point, pointB:Point): Double {
    val dx = abs(pointA.x - pointB.x).toDouble()
    val dy = abs(pointA.y - pointB.y).toDouble()
    val dz = abs(pointA.z - pointB.z).toDouble()
    return sqrt(dx*dx + dy*dy + dz*dz)
}

fun part1(points:ArrayList<Point>): String {
    var result: Long = 1
    var closePairs: MutableMap<Double, Pair<Point, Point>> = mutableMapOf()
    var circuits: ArrayList<MutableSet<Point>> = ArrayList()

    for (p1 in points) {
        for (p2 in points) {
            if (p1.id == p2.id) {
                continue;
            }
            val d = distance(p1, p2)
            closePairs[d] = Pair(p1, p2)
        }
    }

    val connectionsMax = 10
    var connectionsMade:Int = 0
    for (pair in closePairs.toSortedMap()) {

        var isInCircuit = false
        for (c in circuits) {
            if (c.contains(pair.value.first) && !c.contains(pair.value.second)) {
                c.add(pair.value.second)
                isInCircuit = true
            }
            if (c.contains(pair.value.second) && !c.contains(pair.value.first)) {
                c.add(pair.value.first)
                isInCircuit = true
            }
        }
        if (!isInCircuit) {
            circuits.add(mutableSetOf(pair.value.first, pair.value.second))
        }
        connectionsMade+=1

        if (connectionsMade >= connectionsMax) {
            break
        }
    }

    for (c in circuits.sortedBy{ it.count() }.reversed().subList(0, 3)) {
        println( c.count() )
        println( c )
        result *= c.count()
    }

    return result.toString()
}

fun part2(points:ArrayList<Point>): String {
    var result: Long = 0
    return result.toString()
}