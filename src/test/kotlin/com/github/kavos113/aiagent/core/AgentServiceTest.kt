package com.github.kavos113.aiagent.core

import com.intellij.openapi.components.service
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class AgentServiceTest : BasePlatformTestCase() {

    private lateinit var agentService: AgentService

    override fun setUp() {
        super.setUp()
        agentService = project.service()
    }

    override fun tearDown() {
        super.tearDown()
    }

    fun testUseTool() {
        val xmlContent = """
            <list_files>
                <path>/path/to/directory</path>
                <recursive>true</recursive>
            </list_files>
        """.trimIndent()

        agentService.useTool(xmlContent)
    }
}