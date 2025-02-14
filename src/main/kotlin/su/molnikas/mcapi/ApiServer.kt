package su.molnikas.mcapi

import io.javalin.Javalin
import io.javalin.http.Context
import su.molnikas.mcapi.controllers.StatusController
import su.molnikas.mcapi.controllers.WhitelistController
import su.molnikas.mcapi.services.StatusService
import su.molnikas.mcapi.services.WhitelistService

class UnauthorizedException : RuntimeException()

class ApiServer(private val config: ConfigManager) {
    private var app: Javalin? = null
    private val whitelistController = WhitelistController(WhitelistService())
    private val statusController = StatusController(StatusService())

    fun start() {
        val port = config.getPort()
        app = Javalin.create()
            .before { ctx -> checkApiKey(ctx) }
            .start(port)

        app?.exception(UnauthorizedException::class.java) { _, ctx ->
            ctx.status(401).json(mapOf("error" to "Unauthorized"))
        }

        app?.apply {
            get("/api/whitelist", whitelistController::getWhitelist)
        }
        app?.apply {
            post("/api/whitelist", whitelistController::toggleWhitelist)
        }
        app?.apply {
            get("/api/status", statusController::getServerStatus)
        }
    }

    fun stop() {
        app?.stop()
    }

    private fun checkApiKey(ctx: Context) {
        val providedKey = ctx.header("X-API-Key")

        if (providedKey == null || providedKey != config.getApiKey()) {
            throw UnauthorizedException()
        }
    }
}