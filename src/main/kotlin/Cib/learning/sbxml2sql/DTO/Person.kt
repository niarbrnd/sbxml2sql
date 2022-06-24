package Cib.learning.sbxml2sql.DTO

import java.util.*
import javax.xml.bind.annotation.*

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
data class Person(
        private var id: Int = 0,
        @XmlElement
        private val name: String? ,
        @XmlElement
        private val birthday: Date?,
        @XmlElementWrapper(name = "hobbies")
        @XmlElement(name = "hobby")
        val Hobbies: MutableCollection<hobby>
){constructor():this(0,"",null,mutableListOf()) }