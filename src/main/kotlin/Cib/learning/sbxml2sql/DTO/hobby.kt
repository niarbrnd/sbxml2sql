package Cib.learning.sbxml2sql.DTO

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "hobby")
@XmlAccessorType(XmlAccessType.FIELD)
data class hobby(
        @XmlElement
        private var complexity: Int,
        @XmlElement
        private var hobby_name: String){
        constructor():this (0,"")
}
