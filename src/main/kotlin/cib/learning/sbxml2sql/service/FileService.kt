package cib.learning.sbxml2sql.service


class FileService {}
/*    lateinit var FILE_PATTERN:String
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
/*
    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    fun fileWritingMessageHandler(): MessageHandler? {
        return FileHandler(OUTPUT_LOCATION)
    }

 */