package Cib.learning.sbxml2sql

import Cib.learning.sbxml2sql.DBConnectors.JTpqsl
import Cib.learning.sbxml2sql.DTO.Persons
import Cib.learning.sbxml2sql.Service.Arguments
import Cib.learning.sbxml2sql.Service.Xml
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.integration.config.EnableIntegration
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@SpringBootApplication
@Configuration
@EnableIntegration
//@ImportResource("classpath:beans.xml")
class Sbxml2sqlApplication

fun main(args: Array<String>) {
    runApplication<Sbxml2sqlApplication>(*args)
    Arguments().printHello()
    /*  System.out.println("Start app");
        for(int i = 0; i<args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }*/
    val options:MutableMap<String, String> = Arguments().gets(args)
    println(options.get("-xmlin"))
    /*ResourceBundle a = ResourceBundle.getBundle("config");
        / *System.out.println(options.toString());
        for ( String key : options.keySet() ) {
            System.out.println( key );
            System.out.println(options.get(key));
        }
        System.out.println(options.get("-сonfig"));
        System.out.println(options.keySet().toArray()[0]);
         */
    val resource: ResourceBundle = PropertyResourceBundle(Files.newInputStream(Paths.get(options["-config"]!!)))
    val pers: Persons = Xml().getPerson(options["-xmlin"])
    println(pers)
    //Boolean save = new xml().exportPersontoFile(con.getPersons(),options.get("-xmlout"));
    val conjtemplate = JTpqsl()
    conjtemplate.resource = resource
    conjtemplate.save(pers)
    val save: Boolean = Xml().exportPersontoFile(conjtemplate.getPersons(), options["-xmlout"]!!)
    //System.out.println(conjtemplate.getPersons());
    //System.out.println(conjtemplate.getPersons());
    println("Stop  app")

}
