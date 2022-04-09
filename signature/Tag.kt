package signature

object Tag {

    private val border = "88"
    private val borderIndent = " ".repeat(2)

    fun print(_name: String, _status: String) {
        val name = Font(_name, Fonts.ROMAN)
        val status = Font(_status, Fonts.MEDIUM)

        if (name.length > status.length) {
            printHorizontalBorder(name)

            name.transformedString.forEach {
                println(border + borderIndent + it + borderIndent + border)
            }

            printAtCenter(status, name)
            printHorizontalBorder(name)
        } else {
            printHorizontalBorder(status)
            printAtCenter(name, status)

            status.transformedString.forEach {
                println(border + borderIndent + it + borderIndent + border)
            }

            printHorizontalBorder(status)
        }

    }

    private fun printHorizontalBorder(string: Font) {
        val tagTotalLength = string.length + border.length * 2 + borderIndent.length * 2

        println("8".repeat(tagTotalLength))
    }

    private fun printAtCenter(string: Font, inRelationTo: Font) {
        val totalSpaceInsideTag = inRelationTo.length + borderIndent.length * 2
        val indentsTotalLength = totalSpaceInsideTag - string.length
        val isIndentsTotalLengthEven = indentsTotalLength % 2 == 0

        val indent = " ".repeat(indentsTotalLength / 2)

        string.transformedString.forEach {
            println(border + indent + it + indent + if (!isIndentsTotalLengthEven) " " else {""} + border)
        }
    }
}