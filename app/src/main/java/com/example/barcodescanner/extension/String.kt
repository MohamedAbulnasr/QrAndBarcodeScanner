package com.example.barcodescanner.extension

fun String.removePrefixIgnoreCase(prefix: String): String {
    return substring(prefix.length)
}

fun String.startsWithIgnoreCase(prefix: String): Boolean {
    return startsWith(prefix, true)
}

fun String.startsWithAnyIgnoreCase(prefixes: List<String>): Boolean {
    prefixes.forEach { prefix ->
        if (startsWith(prefix, true)) {
            return true
        }
    }
    return false
}

fun String.equalsAnyIgnoreCase(others: List<String>): Boolean {
    others.forEach { other ->
        if (equals(other, true)) {
            return true
        }
    }
    return false
}

fun String.containsAll(others: List<String>): Boolean {
    others.forEach { other ->
        if (contains(other).not()) {
            return false
        }
    }
    return true
}

fun List<String?>.joinNotNullOrBlankToStringWithLineSeparator(): String {
    return filter { it.isNullOrBlank().not() }.joinToString("\n")
}

