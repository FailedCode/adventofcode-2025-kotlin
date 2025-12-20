package aoc.Day11

import java.io.File

fun main(){
    val devices = getInput("input/day11.txt")

    println(">")
    println(part1(devices))
//    println(part2(lines))
}

data class Device(val name:String, val outputs: MutableSet<Device>)

fun getInput(filepath: String): MutableMap<String, Device> {
    val lines = File(filepath).readLines()
    val devices = hashMapOf<String, Device>()  // mutableMapOf<String, Device>()
    val uniqNames = mutableSetOf<String>()
    val regex = Regex("(.+): (.+)")
    var i = 0
    for (line in lines) {
        val match = regex.find(line)
        val name = match?.groups[1]?.value ?: ""
        uniqNames.add(name)
        if (!devices.contains(name)) {
            println("add device $name")
            devices[name] = Device(name, mutableSetOf())
        }
        val device = devices.getValue(name)
        val deviceNames = match?.groups[2]?.value ?: ""
        for (n in deviceNames.split(" ")) {
            uniqNames.add(n)
            if (!devices.contains(n)) {
                println("add device $n")
                val d = Device(n, mutableSetOf())
                devices[n] = d
                println("add output(1) $name -> $n")
                device.outputs.add(d)
            } else {
                println("add output(2) $name -> $n")
                // kotlin will hang here @ 408 / 613 lines read and devices.getValue won't finish
                device.outputs.add(devices.getValue(n))
            }
        }
        i += 1
        println("${i} / ${lines.count()}")
    }

    return devices
}

fun part1(devices:MutableMap<String, Device>): String {
    println("devices: ${devices.count()}")
//    return ""
    return recursiveWalkDevices(devices.getValue("you"), devices.getValue("out")).toString()
}

fun recursiveWalkDevices(currentDevice: Device, exitDevice: Device): Long {
    if (currentDevice.outputs.isEmpty()) {
        return 0
    }
    if (currentDevice.outputs.contains(exitDevice)) {
        return 1
    }
    var i:Long = 0
    for (device in currentDevice.outputs) {
        i += recursiveWalkDevices(device, exitDevice)
    }
    return i
}

fun part2(devices:MutableMap<String, Device>): String {
    var result: Long = 0
    return result.toString()
}