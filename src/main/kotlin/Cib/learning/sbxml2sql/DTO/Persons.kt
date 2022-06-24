package Cib.learning.sbxml2sql.DTO

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "Persons")
@XmlAccessorType(XmlAccessType.FIELD)
data class Persons(
        @XmlElement(name = "Person")
        private var People: MutableCollection<Person>
){constructor():this(mutableListOf())}
