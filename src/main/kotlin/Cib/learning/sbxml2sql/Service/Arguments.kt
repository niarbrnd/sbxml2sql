package Cib.learning.sbxml2sql.Service

import java.util.*


class Arguments {
    fun printHello() {
            System.out.println("Hi there!")
    }
    fun gets(args: Array<String>): MutableMap<String, String> {
        if (args.size < 6) {
            println("Usage: java -jar file.jar -config <path to config> -xmlin <path to in xml> -xmlout <path to out xml>")
        }
        val options: MutableMap<String, String> = HashMap()
        var i = 0
        while (i < args.size) {
            if (args[i].get(0) == '-') {
                System.out.println("Found dash with command " +
                        args[i] + " and value " + args[i + 1]);
                options[args[i]] = args[i + 1]
            } else {
                println("Parametr is bad " + args[i])
            }
            i++
        }
        return options
    }
}