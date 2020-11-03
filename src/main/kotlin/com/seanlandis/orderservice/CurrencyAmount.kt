package com.seanlandis.orderservice

// This class is mainly to show that I'm aware of the issues around representing money as a float/double
// This could be achieved with BigDecimal as well.
data class CurrencyAmount(val dollar: Int, val cent: Int) {
    operator fun plus(summand: CurrencyAmount): CurrencyAmount {
        val valueInPennies = 100 * (dollar + summand.dollar) + cent + summand.cent
        return CurrencyAmount(valueInPennies / 100, valueInPennies % 100 )
    }

    operator fun times(multiplicand: Int): CurrencyAmount {
        val valueInPennies = multiplicand * ((100 * dollar) + cent)
        return CurrencyAmount(valueInPennies / 100, valueInPennies % 100 )
    }

    override fun toString(): String {
        return String.format("$%d.%02d", dollar, cent)
    }
}