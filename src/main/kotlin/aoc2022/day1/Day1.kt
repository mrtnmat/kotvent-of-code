package aoc2022.day1

import java.io.File
import kotlin.io.*

fun parseInput(): List<UInt> {
    return File("input/day1")
        .readText().split("\n\n")
        .map { it.split("\n")
            .map { it.toUInt() } }
        .map { it.reduce { acc, v -> acc + v } }
}

fun solvePart1(): UInt {
    return parseInput()
        .reduce { acc, v -> if (v > acc) v else acc }
}

fun solvePart2(): UInt {
    return parseInput()
        .sortedDescending()
        .subList(0, 3)
        .reduce { acc, v -> acc + v }
}
