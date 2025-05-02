package com.github.kavos113.aiagent.core

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.util.Key
import java.io.File
import java.util.concurrent.CountDownLatch

object Tools {
    enum class ToolName {
        ListFiles,
        ExecuteCommand,
        ReadFile,
        WriteFile,
        AskUser,
        TaskComplete,
    }

    fun listFiles(path: String, recursive: Boolean): String {
        val sb = StringBuilder()

        val dir = File(path)
        if (!dir.exists()) {
            return "Directory does not exist"
        }

        if (!dir.isDirectory) {
            return "Path is not a directory"
        }

        if (recursive) {
            dir.walk().forEach {
                sb.append(it.absolutePath).append("\n")
            }
        } else {
            dir.listFiles()?.forEach {
                sb.append(it.absolutePath).append("\n")
            }
        }

        return sb.toString()
    }

    fun executeCommand(command: String, path: String): String {
        val sb = StringBuilder()

        val commandParts = command.split("\\s+".toRegex())

        val commandLine = GeneralCommandLine(commandParts)
        commandLine.setWorkDirectory(path)
        commandLine.charset = Charsets.UTF_8

        val latch = CountDownLatch(1)

        val processHandler = OSProcessHandler(commandLine)
        processHandler.addProcessListener(object : ProcessAdapter() {
            override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                sb.append(event.text)
                println(event.text)
            }

            override fun processTerminated(event: ProcessEvent) {
                latch.countDown()
            }
        })

        processHandler.startNotify()
        latch.await()

        return "Command Output:\n$sb"
    }

    fun readFile(path: String): String {
        val file = File(path)
        if (!file.exists()) {
            return "File does not exist"
        }

        if (!file.isFile) {
            return "Path is not a file"
        }

        return file.readText()
    }

    fun writeFile(path: String, content: String): String {
        val file = File(path)

        if (!file.exists()) {
            return "File does not exist"
        }

        if (!file.isFile) {
            return "Path is not a file"
        }

        file.writeText(content)
        return "File written successfully"
    }

    fun askUser(question: String): String {
        println(question)
        return "User response"
    }

    fun taskComplete(): String {
        return "Task completed successfully"
    }
}