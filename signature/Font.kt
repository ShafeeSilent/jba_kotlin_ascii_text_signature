package signature

import java.io.File

enum class Fonts(val font: List<String>, val size: Int, val spaceLength: Int) {
    ROMAN(
        font = File("signature/fonts/roman.txt")
            .readLines(),
        size = 10,
        spaceLength = 10,
    ),
    MEDIUM(
        font = File("signature/fonts/medium.txt")
            .readLines(),
        size = 3,
        spaceLength = 5,
    )
}

class Font(val originalString: String, val FONT: Fonts) {
    /**
     * Contains all font as a sequence of lines
     */
    private val fontLines = FONT.font

    /**
     * The original string in the specified font
     */
    val transformedString: List<String>

    /**
     * The length of the string in the specified font
     */
    val length: Int

    init {
        transformedString = applyFont(originalString)
        length = transformedString.first().length
    }

    /**
     * Prints the created string in the specified font
     */
    fun print() {
        transformedString.forEach {
            println(it)
        }
    }

    /**
     * Applies to the given String the font
     */
    private fun applyFont(originalString: String): List<String> {
        val result = MutableList<String>(FONT.size) { "" }
        val transformedSymbolsList = mutableListOf<List<String>>()

        for (ch in originalString) {
            transformedSymbolsList.add(find(ch))
        }

        for (symbolLines in transformedSymbolsList) {
            for (i in symbolLines.indices) {
                result[i] += symbolLines[i]
            }
        }

        return result.toList()
    }

    /**
     * Returns the symbol representation in the specified font
     */
    private fun find(symbol: Char): List<String> {
        val space = " ".repeat(FONT.spaceLength)

        if (symbol == ' ') return List<String>(FONT.size) { space }

        var isFound = false
        var theLineWhereSymbolWasFound: Int = 0
        for (i in fontLines.indices) {
            if (fontLines[i][0] == symbol && fontLines[i][1] == ' ') {
                isFound = true
                theLineWhereSymbolWasFound = i
                break
            }
        }

        if (!isFound) throw Exception()
        else {
            val result = mutableListOf<String>()
            var i = theLineWhereSymbolWasFound + 1
            repeat(FONT.size) {
                result.add(fontLines[i])
                i++
            }

            equalizeLinesInList(result)
            return result.toList()
        }
    }

    /**
     * Makes the mutable String list's all elements equal to the longest String in that list.
     */
    fun equalizeLinesInList(list: MutableList<String>) {
        var longestLineSize = 0
        for (line in list) {
            if (line.length > longestLineSize) longestLineSize = line.length
        }

        for (i in list.indices) {
            list[i] += " ".repeat(longestLineSize - list[i].length)
        }
    }
}