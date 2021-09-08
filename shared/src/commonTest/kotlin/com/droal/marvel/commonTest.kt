package com.droal.marvel

import com.droal.marvel.api.MarvelAPI
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(MarvelAPI().greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}