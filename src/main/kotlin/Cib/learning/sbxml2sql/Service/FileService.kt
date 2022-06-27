package Cib.learning.sbxml2sql.Service

import Cib.learning.sbxml2sql.Adapters.FilePoller
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.InboundChannelAdapter
import org.springframework.integration.annotation.Poller
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageSource
import org.springframework.integration.file.filters.SimplePatternFileListFilter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import java.io.File
import java.util.logging.FileHandler


class FileService {
    lateinit var FILE_PATTERN:String
    lateinit var OUTPUT_LOCATION:String
    lateinit var directory:File
    @Bean
    fun fileChannel(): MessageChannel? {
        return DirectChannel()
    }

    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = [Poller(fixedDelay = "1000")])
    fun fileReadingMessageSource(): MessageSource<File?>? {
        val filter = SimplePatternFileListFilter(FILE_PATTERN)
        lateinit var fp:FilePoller
        fp.get(directory, filter)
        return fp
    }

    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    fun fileWritingMessageHandler(): MessageHandler? {
        return FileHandler(OUTPUT_LOCATION)
    }
}