package Cib.learning.sbxml2sql.DTO

import lombok.Getter
import java.util.*
import javax.xml.bind.annotation.*
import kotlin.collections.ArrayList

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
//@Getter
data class Person(
        private var id: Int = 0,
        @field:XmlElement
        var name: String? ,
        @field:XmlElement
        var birthday: Date?,
        @XmlElementWrapper(name = "hobbies")
        @field:XmlElement(name = "hobby")
        var hobbies: List<hobby>?
) {constructor():this(0,"",null, null)}