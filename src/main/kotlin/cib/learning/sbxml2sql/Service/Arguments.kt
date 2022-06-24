package cib.learning.sbxml2sql.Service

import java.util.*


class Arguments {
    fun printHello() {

            System.out.println("Hi there!")

        // `return Unit` or `return` is optional
    }
    fun gets(args: Array<String>) {
        if (args.size < 6) {
            System.out.println("Usage: java -jar file.jar -config <path to config> -xmlin <path to in xml> -xmlout <path to out xml>")
        }
    }
}