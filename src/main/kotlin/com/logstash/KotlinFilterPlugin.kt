package com.logstash

import co.elastic.logstash.api.*

@LogstashPlugin(name = "kotlin_filter_plugin")
class KotlinFilterPlugin @JvmOverloads constructor(
        private val filterId: String,
        private val config: Configuration,
        private val context: Context,
        private var sourceField: String = config.get(SOURCE_CONFIG)
) : Filter {

    companion object {
        val SOURCE_CONFIG : PluginConfigSpec<String> = PluginConfigSpec.stringSetting("source", "message")
    }

    override fun filter(events: Collection<Event>, matchListener: FilterMatchListener): Collection<Event>? {
        events.forEach { event ->
            with(event.getField(sourceField)){
                if (this is String){
                    event.setField(sourceField, reversed())
                    matchListener.filterMatched(event)
                }
            }
        }

        return events
    }

    override fun configSchema() = listOf(SOURCE_CONFIG)

    override fun getId() = filterId
}
