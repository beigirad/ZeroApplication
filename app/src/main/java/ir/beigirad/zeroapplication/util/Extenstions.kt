package ir.beigirad.zeroapplication.util

/**
 * Created by farhad-mbp on 2/2/18.
 */
val String.containDigit: Boolean
    get() {
        iterator().forEach {
            if (!it.isDigit())
                return false
        }
        return true
    }

val String.isMobileNumberFormat: Boolean
    get() = matches(Regex("09\\w{9}"))

val String.toPriceFormat: String
    get() = String.format("%,d", toIntOrNull() ?: 0)
