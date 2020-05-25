package com.logstash

import co.elastic.logstash.api.*

@LogstashPlugin(name = "kotlin_filter_plugin")
class KotlinFilterPlugin(
        private val filterId: String,
        private val config: Configuration,
        private val context: Context
) : Filter {
    private val sourceField: String

    init {
        sourceField = config.get(sourceConfig())
    }

    companion object {
        fun sourceConfig() : PluginConfigSpec<String> {
            return PluginConfigSpec.stringSetting("source", "message")
        }
    }

    override fun filter(events: Collection<Event>, matchListener: FilterMatchListener): Collection<Event>? {
        events.forEach { event ->
            val field = event.getField(sourceField)
            if (field is String) {
                event.setField(sourceField, field.reversed())
                matchListener.filterMatched(event)
            }
        }

        return events
    }

    override fun configSchema(): Collection<PluginConfigSpec<*>> {
        return listOf(sourceConfig())
    }

    override fun getId(): String {
        return filterId
    }
}
