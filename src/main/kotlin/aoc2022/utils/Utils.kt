package aoc2022.utils

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.pathString

fun inputFile(name: String): File {
    val basePath = Path("E:", "Projects", "aoc", "2022").pathString
    val input = Path(basePath, name).pathString
    return File(input)
}

fun <T> T.trace(): T {
    println(this)
    return this
}