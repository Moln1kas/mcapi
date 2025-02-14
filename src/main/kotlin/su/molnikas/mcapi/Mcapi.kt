package su.molnikas.mcapi

import org.bukkit.plugin.java.JavaPlugin

class Mcapi : JavaPlugin() {
    private lateinit var configManager: ConfigManager
    private lateinit var apiServer: ApiServer

    companion object {
        lateinit var plugin: Mcapi
            private set
    }

    override fun onEnable() {
        plugin = this

        configManager = ConfigManager(dataFolder)
        configManager.load()

        apiServer = ApiServer(configManager)
        apiServer.start()
    }

    override fun onDisable() {
        apiServer.stop()
    }
}
