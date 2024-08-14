package framework.driver

import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import java.io.File
import java.net.Socket
import java.time.Duration

class AppiumService {
    private lateinit var service: AppiumDriverLocalService
    private val startPort = 4723
    private val endPort = 4753

    fun startAppiumServer(): AppiumDriverLocalService {

        for (port in startPort..endPort) {
            if (isPortAvailable(port)) {
                service = AppiumServiceBuilder()
                    .withIPAddress("127.0.0.1")
                    .usingPort(port)
                    .withTimeout(Duration.ofSeconds(40))
                    .withArgument({ "--allow-insecure" }, "chromedriver_autodownload")
                    .withLogFile(File("./src/test/resources/appium.log"))
                    .build()
                service.start()
                println("Server is running: ${service.isRunning}")
                return service
            }
        }
        throw RuntimeException("No available port found in the range ${startPort}-${endPort}")
    }

    private fun isPortAvailable(port: Int): Boolean {
        var socket: Socket? = null
        try {
            socket = Socket("localhost", port)
        } catch (e: Exception) {
            return true
        } finally {
            socket?.close()
        }
        return false
    }

    fun stopAppiumServer() {
        service.stop()
        println("Server is running: ${service.isRunning}")
    }
}