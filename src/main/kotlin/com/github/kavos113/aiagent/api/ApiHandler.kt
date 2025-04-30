package com.github.kavos113.aiagent.api

interface ApiHandler {
    fun sendRequest(param: ApiParam): ApiResponse
}