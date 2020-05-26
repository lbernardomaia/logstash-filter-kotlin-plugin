# Logstash Kotlin Filter Plugin

This Kotlin plugin is based on the Java Filter Plugin available here:
 
https://www.elastic.co/guide/en/logstash/current/java-filter-plugin.html
 
### To build the plugin

```
./gradlew build gem
```

### To install

```
bin/logstash-plugin install --no-verify --local /path/to/logstash-filter-kotlin_filter_plugin-0.0.1.gem
```

### To test

Default Conf:
```
bin/logstash -f /path/to/kotlin_filter_default_plugin.conf
```
Custom Conf: 
```
bin/logstash -f /path/to/kotlin_filter_custom_plugin.conf
```