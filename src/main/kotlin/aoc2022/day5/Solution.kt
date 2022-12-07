package aoc2022.day5

import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

private const val FILENAME = "day5.txt"
private val input = aoc2022.utils.inputFile(FILENAME)

enum class Instruction {
    Move, From, To
}

fun parseMatrix(): List<LinkedList<String>> {
    val reg = Regex("""(\[\w]\s?)|(\s\s\s\s?)""")
    val res = input.useLines {
        it.takeWhile { s -> s.first() == '[' }
            .map { s ->
                reg.findAll(s)
                    .map(MatchResult::value)
                    .map { st -> st.trim(' ', '[', ']') }
                    .toList()
            }
            .toList()
    }
    return res.rotateMatrix()
}

fun parseInstructions(): List<HashMap<Instruction, Int>> {
    input.useLines {
        return it.filter(String::isNotEmpty)
            .dropWhile { s -> s.first() != 'm' }
            .map(String::parseInstruction)
            .toList()
    }
}

fun List<List<String>>.rotateMatrix(): List<LinkedList<String>> {
    val arr = Array(this[0].count()) { _ -> LinkedList<String>() }
    this.forEach {
        arr.forEachIndexed { j, ls ->
            val s = it[j]
            if (s.isNotEmpty()) ls.push(s)
        }
    }
    val l = arr.map {
        LinkedList(it.reversed())
    }
    // l.forEach { it.forEach(::println) }
    return l
}

fun String.parseInstruction(): HashMap<Instruction, Int> {
    val inst = this
        .split(' ')
        .filterIndexed { i, _ -> (i % 2) != 0 }
        .map(String::toInt)
    val m = HashMap<Instruction, Int>()
    m[Instruction.Move] = inst[0]
    m[Instruction.From] = inst[1] - 1
    m[Instruction.To] = inst[2] - 1
    return m
}

fun solve(part: Int): String {
    val m = parseMatrix()
    val inst = parseInstructions()

    inst.forEach {
        val from: Int = it[Instruction.From] ?: -1
        val to: Int = it[Instruction.To] ?: -1
        val move: Int = it[Instruction.Move] ?: -1
        // println("$from $to $move")
        if (part == 1) {
            repeat(move) { _ -> m[from].moveTo(m[to]) }
        } else if (part == 2) {
            m[from].moveTo9001(m[to], move)
        } else {
            throw Exception("Invalid part number")
        }
    }

    return m.map(List<String>::first)
        .reduce(String::plus)
}

fun <T> LinkedList<T>.moveTo(targetList: LinkedList<T>) {
    targetList.push(this.pop())
}

fun <T> LinkedList<T>.moveTo9001(targetList: LinkedList<T>, n: Int) {
    val l = LinkedList<T>()
    repeat(n) { l.push(this.pop()) }
    repeat(l.size) { targetList.push(l.pop()) }
}