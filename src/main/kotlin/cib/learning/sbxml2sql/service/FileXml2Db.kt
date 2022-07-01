package cib.learning.sbxml2sql.service

import cib.learning.sbxml2sql.dto.Persons
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.File

//@Component
@EnableAutoConfiguration //else get Could not autowire. No beans of 'File' type found. on fun execute
@Service
class FileXml2Db {
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