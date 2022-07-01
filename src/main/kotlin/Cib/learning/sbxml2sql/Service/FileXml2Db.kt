package Cib.learning.sbxml2sql.Service

import Cib.learning.sbxml2sql.DBConnectors.JTpqsl
import Cib.learning.sbxml2sql.DTO.Persons
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
//@Component
@EnableAutoConfiguration //else get Could not autowire. No beans of 'File' type found. on fun execute
@Service
class FileXml2Db {
    private val log = LoggerFactory.getLogger(FileXml2Db::class.java)
    @ServiceActivator
    fun execute(file: File): Persons {
        val pers: Persons = Xml().getPerson(file.canonicalPath)
        println(pers)
        //Boolean save = new xml().exportPersontoFile(con.getPersons(),options.get("-xmlout"));
//        val conjtemplate = JTpqsl()
//        conjtemplate.resource = resource
//        conjtemplate.save(pers)
//        val save: Boolean = Xml().exportPersontoFile(conjtemplate.getPersons(), options["-xmlout"]!!)
        return pers
    }
}