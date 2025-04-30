package com.github.kavos113.aiagent.api

sealed class ApiModel {
    data class AnthropicModel(val model: com.anthropic.models.messages.Model): ApiModel()
    data class OpenAiModel(val model: com.openai.models.models.Model): ApiModel()
    data class OpenRouterModel(val model: String): ApiModel()
}