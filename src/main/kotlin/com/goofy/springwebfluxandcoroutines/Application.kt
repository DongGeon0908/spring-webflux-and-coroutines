package com.goofy.springwebfluxandcoroutines

import com.goofy.springwebfluxandcoroutines.common.extension.Zone
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.core.env.Environment
import java.util.*

@SpringBootApplication
class Application(
    private val buildProperties: BuildProperties,
    private val environment: Environment,
) : ApplicationListener<ApplicationReadyEvent> {
    private val logger = KotlinLogging.logger { }

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val application = buildProperties.name
        val profiles = environment.activeProfiles.contentToString()
        logger.info { "$application applicationReady, profiles=$profiles" }
    }
}

fun main(args: Array<String>) {
    /** Initialize jvm level configuration */
    init()
    runApplication<Application>(*args)
}

fun init() {
    /** Setting the Default TimeZone */
    TimeZone.setDefault(TimeZone.getTimeZone(Zone.KST))
}
