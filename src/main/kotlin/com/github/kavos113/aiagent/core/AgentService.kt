package com.github.kavos113.aiagent.core

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.github.kavos113.aiagent.MyBundle
import com.github.kavos113.aiagent.api.ApiHandler
import com.github.kavos113.aiagent.api.ApiHandlerOptions
import com.github.kavos113.aiagent.api.ApiParam

@Service(Service.Level.PROJECT)
class AgentService(project: Project) {

    val apiHandler = ApiHandler.getHandler(
        ApiHandlerOptions.OpenRouterHandlerOptions(
            apiKey = "sss"
        )
    )

    init {
        thisLogger().info(MyBundle.message("projectService", project.name))
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    fun getRandomNumber() = (1..100).random()

    fun createApiRequest(param: ApiParam) {
        val response = apiHandler.sendRequest(param)
        thisLogger().info("API Response: $response")
    }
}
