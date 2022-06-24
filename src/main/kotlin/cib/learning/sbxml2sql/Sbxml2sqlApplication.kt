package cib.learning.sbxml2sql

import cib.learning.sbxml2sql.Service.Arguments
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Sbxml2sqlApplication

fun main(args: Array<String>) {
    runApplication<Sbxml2sqlApplication>(*args)
    Arguments().printHello()

    /*  System.out.println("Start app");
        for(int i = 0; i<args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }*/
    val options = Arguments().gets(args)
    //System.out.println(options.toString())
}
