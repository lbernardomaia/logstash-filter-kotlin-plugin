package com.logstash

import co.elastic.logstash.api.Configuration
import co.elastic.logstash.api.Event
import co.elastic.logstash.api.FilterMatchListener
import org.junit.jupiter.api.Test
import org.logstash.plugins.ConfigurationImpl
import org.logstash.plugins.ContextImpl
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

class KotlinFilterPluginTest {

    @Test
    fun `When content is abcdef, Then return fedcba`() {
        val sourceField = "foo"
        val config: Configuration = ConfigurationImpl(mapOf<String, Any>(Pair("source", sourceField)))
        val filter = KotlinFilterPlugin("test-id", config, ContextImpl(null, null))

        val e: Event = org.logstash.Event().apply {
            setField(sourceField, "abcdef")
        }

        filter.filter(mutableListOf(e), TestMatchListener())

        assertEquals("fedcba", e.getField(sourceField))
    }

    private class TestMatchListener : FilterMatchListener {
        private val matchCount = AtomicInteger(0)

        override fun filterMatched(event: Event) {
            matchCount.incrementAndGet()
        }

        fun getMatchCount(): Int {
            return matchCount.get()
        }
    }
}