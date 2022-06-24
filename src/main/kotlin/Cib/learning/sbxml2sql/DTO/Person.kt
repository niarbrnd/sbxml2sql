package Cib.learning.sbxml2sql.DTO

import java.util.*
import javax.xml.bind.annotation.*
import kotlin.collections.ArrayList

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
data class Person(
        private var id: Int = 0,
        @field:XmlElement
        private val name: String? ,
        @field:XmlElement
        private val birthday: Date?,
        @XmlElementWrapper(name = "hobbies")
        @field:XmlElement(name = "hobby")
        val hobbies: List<hobby>?
){constructor():this(0,null,null,null) }