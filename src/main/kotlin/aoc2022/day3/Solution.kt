package aoc2022.day3

private const val FILENAME = "day3.txt"

fun parseInput(): List<String> {
    return aoc2022.utils.inputFile(FILENAME)
        .readLines()
}

fun solvePart1(): Int {
    return parseInput()
        .asSequence()
        .map{ it.chunked(it.length / 2) }
        .map(Iterable<String>::commonChars)
        .map(Set<Char>::first)
        .map(Char::charPriority)
        .sum()
}

fun solvePart2(): Int {
    return parseInput()
        .asSequence()
        .chunked(3)
        .map(List<String>::commonChars)
        .map(Set<Char>::first)
        .map(Char::charPriority)
        .sum()
}

fun Iterable<String>.commonChars(): Set<Char> {
    return this
        .map(String::toSet)
        .reduce(Set<Char>::intersect)
}

fun Char.charPriority(): Int {
    return (if (this.isLowerCase()) this - 'a' else this - 'A' + 26) + 1
}
