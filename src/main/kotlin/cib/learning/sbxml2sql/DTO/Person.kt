package cib.learning.sbxml2sql.DTO

import java.util.*

data class Person(
        private var id: Int = 0,
private val name: String? = null,
private val birthday: Date? = null,
private val Hobbies: List<hobby>? = null
)