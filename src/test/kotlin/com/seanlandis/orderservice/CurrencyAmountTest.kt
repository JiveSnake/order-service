package com.seanlandis.orderservice

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CurrencyAmountTest {
    @Test
    fun givenTwoCurrencyAmounts_whenSumOfCentOver100_thenReturnCorrectCurrencyAmount() {
        val result = CurrencyAmount(1, 90) + CurrencyAmount(0, 10)
        assertEquals(CurrencyAmount(2, 0), result)
    }

    @Test
    fun givenCurrencyAmount_whenCentIsLessThan10_thenReturnsProperlyFormattedString() {
        val result = CurrencyAmount(10, 1).toString()
        assertEquals("$10.01", result)
    }
}
