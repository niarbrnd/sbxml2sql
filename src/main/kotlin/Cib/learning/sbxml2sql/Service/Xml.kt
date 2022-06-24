package Cib.learning.sbxml2sql.Service

import Cib.learning.sbxml2sql.DTO.Persons
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException
import javax.xml.bind.Marshaller

class Xml {
    @Throws(JAXBException::class, FileNotFoundException::class)
    fun getPerson(pathtoxml: String?): Persons {
        var pers = Persons()
        val filexml: FileReader
        try {
            println("FileReader "+pathtoxml)
            filexml =FileReader(pathtoxml)

        } catch (e: FileNotFoundException) {
            println("file xml $pathtoxml not found")
            e.printStackTrace()
            return pers
        }
        println("file read")
        try {
            val context: JAXBContext = JAXBContext.newInstance(Persons::class.java)
            pers = context.createUnmarshaller()
                .unmarshal(filexml) as Persons
        } catch (e: JAXBException) {
            println("JAXB Failed")
            e.printStackTrace()
        }
        return pers
    }

    @Throws(IOException::class)
    fun exportPersontoFile(pers: Persons?, pathexport: String): Boolean {
        val filexml: FileWriter
        filexml = try {
            FileWriter(pathexport)
        } catch (e: FileNotFoundException) {
            println("file xml $pathexport not write")
            e.printStackTrace()
            return false
        }
        try {
            val context: JAXBContext = JAXBContext.newInstance(Persons::class.java)
            //Create Marshaller
            val jaxbMarshaller: Marshaller = context.createMarshaller()
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, java.lang.Boolean.TRUE)
            jaxbMarshaller.marshal(pers, filexml)
            println("Export XML export to $pathexport")
        } catch (e: JAXBException) {
            println("Export XML Failed")
            e.printStackTrace()
        }
        return false
    }
}