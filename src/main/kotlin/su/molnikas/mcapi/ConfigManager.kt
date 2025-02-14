package su.molnikas.mcapi

import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.yaml.YamlConfigurationLoader
import java.io.File

class ConfigManager(private val dataFolder: File) {
    private lateinit var config: ConfigurationNode

    fun load() {
        val configFile = File(dataFolder, "config.yml")

        if(!configFile.exists()) {
            configFile.parentFile.mkdirs()
            configFile.writeText("""
                api-port: 25566
                api-key: "your-api-key"
            """.trimIndent())
        }

        val loader = YamlConfigurationLoader.builder().path(configFile.toPath()).build()
        config = loader.load()
    }

    fun getPort(): Int = config.node("api-port").int
    fun getApiKey(): String = config.node("api-key").string ?: "default-key"
}