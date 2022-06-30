package Cib.learning.sbxml2sql.Adapters

import Cib.learning.sbxml2sql.DTO.Persons
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.config.EnableIntegration
import org.springframework.messaging.Message
import java.io.File

//@Configuration
//@EnableIntegration
class XmlActivator {
   /* @ServiceActivator(inputChannel = "fileInputChannel")
    fun adjustRevenue(order: Message<*>): Message<File> {
        println("Processing file")
        println("Done processing file")
        return order
    }

    */
}
