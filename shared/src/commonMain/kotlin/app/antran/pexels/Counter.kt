package app.antran.pexels

class Counter {
    var value: Int = 0

    fun increase(): Int {
        value += 1
        return value
    }

    fun decrease(): Int {
        if (value == 0) return value
        value -= 1
        return value
    }
}