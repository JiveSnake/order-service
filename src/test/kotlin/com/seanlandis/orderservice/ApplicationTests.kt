package com.seanlandis.orderservice


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.shell.Shell
import org.springframework.shell.jline.InteractiveShellApplicationRunner
import org.springframework.shell.jline.ScriptShellApplicationRunner

@SpringBootTest(properties = [InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false", ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"])
class ApplicationTests {
    @Autowired
    private val shell: Shell? = null

    @Test
    fun givenAppInitialized_whenSubtotalOfProducts_thenReturnCorrectSubtotal() {
        val result = shell?.evaluate({ "subtotal Apple,Apple,Orange,Apple" })

        assertEquals("$2.05", result)
    }

    @Test
    fun givenAppInitialized_whenSubtotalOfProductsWithDiscounts_thenReturnCorrectSubtotal() {
        val result = shell?.evaluate({ "subtotal Apple,Apple,Orange,Orange,Orange true" })

        assertEquals("$1.10", result)
    }

    @Test
    fun givenAppInitialized_whenOrder_thenReturnNull() {
        val result = shell?.evaluate({ "order Apple,Apple,Orange,Orange,Orange" })

        assertNull(result)
    }

    @Test
    fun givenAppInitialized_whenOrderTooMany_thenReturnNull() {
        val result = shell?.evaluate({ "order Apple,Apple,Apple,Orange,Orange,Orange,Orange" })

        assertNull(result)
    }
}
