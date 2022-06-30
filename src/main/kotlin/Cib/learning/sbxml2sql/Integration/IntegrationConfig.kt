package Cib.learning.sbxml2sql.Integration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.annotation.Transformer
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.file.FileReadingMessageSource
import org.springframework.integration.file.FileWritingMessageHandler
import java.io.File

@Configuration
@EnableIntegration
class IntegrationConfig {
    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller= [Poller(fixedDelay = "1000")])
    fun fileReadingMessageSource():FileReadingMessageSource{
        val frms:FileReadingMessageSource= FileReadingMessageSource()
        frms.setDirectory(File("c:\\share\\in"))
        return frms
    }
    @Bean
    @ServiceActivator (inputChannel = "fileInputChannel")
    fun fileWritingMessageHandler():FileWritingMessageHandler {
        val fwmh: FileWritingMessageHandler = FileWritingMessageHandler(File("c:\\share\\out"))
        fwmh.setAutoCreateDirectory(true)
        fwmh.setExpectReply(false)
        return fwmh
    }
}