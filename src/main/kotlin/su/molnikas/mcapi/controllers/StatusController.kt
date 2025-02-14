package su.molnikas.mcapi.controllers

import io.javalin.http.Context
import su.molnikas.mcapi.services.StatusService

class StatusController(private val statusService: StatusService) {
    fun getServerStatus(ctx: Context) {
        ctx.json(statusService.getServerStatus())
    }
}