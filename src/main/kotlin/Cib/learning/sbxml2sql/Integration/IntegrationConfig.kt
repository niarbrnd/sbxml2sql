package Cib.learning.sbxml2sql.Integration

import Cib.learning.sbxml2sql.DTO.Persons
import Cib.learning.sbxml2sql.Service.Xml
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.*
import org.springframework.integration.file.FileReadingMessageSource
import org.springframework.integration.file.FileWritingMessageHandler
import org.springframework.messaging.MessageChannel
import java.io.File

@Configuration
@EnableIntegration
class IntegrationConfig {
    @Bean
    fun fileInputChannel(): MessageChannel? {
        return DirectChannel()
    }
    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller= [Poller(fixedDelay = "1000")])
    fun fileReadingMessageSource():FileReadingMessageSource{
        val frms:FileReadingMessageSource= FileReadingMessageSource()
        frms.setDirectory(File("c:\\share\\in"))
        return frms
    }
//    @Bean()
//    @ServiceActivator (inputChannel = "fileInputChannel")
    fun fileWritingMessageHandler():FileWritingMessageHandler {
        val fwmh: FileWritingMessageHandler = FileWritingMessageHandler(File("c:\\share\\out"))
        fwmh.setAutoCreateDirectory(true)
        fwmh.setExpectReply(false)
        return fwmh
    }

    @Bean
    fun fileReadingFlow00(): IntegrationFlow = inputFlow()
    fun inputFlow()= IntegrationFlows.from("fileInputChannel")
        //.handle(::fileWritingMessageHandler)
        .channel("nullChannel")
        .get()
}