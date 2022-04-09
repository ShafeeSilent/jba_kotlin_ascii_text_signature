package signature

// enums
private const val NAME = 0
private const val STATUS = 1

fun main() {


    val name = ask(NAME)
    val status = ask(STATUS)

    Tag.print(name, status)

}

private fun ask(ENUM: Int): String {

    val prompt = if (ENUM == NAME) "Enter name and surname: " else "Enter person's status: "

    while (true) {
        print(prompt)
        val input = readLine()!!

        if (input.replace(" ", "").isEmpty()) {
            println("The line shouldn't be empty or composed of spaces only. Try again. \n")
            continue
        }

        if (!isComposedOfEnglishLettersOnly(input)) {
            println("Only [A-Z], [a-z] and spaces are allowed. Try again. \n")
            continue
        }

        return if (ENUM == NAME) deleteExtraSpaces(input) else input
    }
}

private fun isComposedOfEnglishLettersOnly(string: String): Boolean {
    for (ch in string) {
        if (!(ch in 'a'..'z' || ch in 'A'..'Z' || ch == ' ')) return false
    }

    return true
}

private fun deleteExtraSpaces(string: String): String {
    val list = string.split(" ").toMutableList()

    while (list.contains("")) {
        list.remove("")
    }

    return list.joinToString(" ")
}
