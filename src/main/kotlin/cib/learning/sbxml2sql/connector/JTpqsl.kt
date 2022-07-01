package cib.learning.sbxml2sql.connector

import cib.learning.sbxml2sql.dto.Person
import cib.learning.sbxml2sql.dto.Persons
import cib.learning.sbxml2sql.dto.Hobby
import java.sql.*
import java.text.SimpleDateFormat
import java.util.*

class JTpqsl {
    var resource: ResourceBundle? = null
    private var conn: Connection? = null
    private fun connect(): Boolean {
        try {
            Class.forName("org.postgresql.Driver")
        } catch (e: ClassNotFoundException) {
            println("PostgreSQL JDBC Driver is not found. Include it in your library path ")
            e.printStackTrace()
            return false
        }
        println("PostgreSQL JDBC Driver successfully connected")
        conn = try {
            DriverManager
                .getConnection(
                    resource!!.getString("DB_URL"),
                    resource!!.getString("USER"),
                    resource!!.getString("PASS")
                )
        } catch (e: SQLException) {
            println("Connection Failed")
            e.printStackTrace()
            return false
        }
        if (conn != null) {
            println("You successfully connected to database now")
            return true
        } else {
            println("Failed to make connection to database")
        }
        return false
    }

    fun save(pers: Persons): Boolean {
        connect()
        val stmt: Statement?
        try {
            stmt = conn!!.createStatement()
            stmt.executeUpdate(resource!!.getString("ct"))
        } catch (e: SQLException) {
            println("CREATE TABLE Failed")
            e.printStackTrace()
            return false
        }
        insertPerson(pers.People!!)
        return true
    }

    private fun insertPerson(list: List<Person>) {
        val SQL = ("INSERT INTO person (name,birthday) "
                + "VALUES(?,date(?))")
        try {
            conn!!.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS).use { statement ->
                for (actor in list) {
                    statement.setString(1, actor.name)
                    statement.setString(2, SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(actor.birthday))
                    statement.addBatch()
                    statement.executeBatch()
                    val keyset = statement.generatedKeys
                    if (keyset.next()) {
                        // Retrieve the auto generated key(s).
                        val key = keyset.getInt(1)
                        //System.out.println(key);
                        insertHobby(actor.Hobbies!!, key)
                    }
                }
            }
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }

    private fun insertHobby(list: List<Hobby>, persid: Int) {
        val SQL = ("INSERT INTO hobby (idpers,complexity,hobby_name) "
                + "VALUES(?,?,?)")
        try {
            null.use { connection ->
                DriverManager
                    .getConnection(
                        resource!!.getString("DB_URL"),
                        resource!!.getString("USER"),
                        resource!!.getString("PASS")
                    ).use { conn ->
                        conn.prepareStatement(SQL).use { statement ->
                            var count = 0
                            for (actor in list) {
                                statement.setInt(1, persid)
                                statement.setInt(2, actor.complexity)
                                statement.setString(3, actor.hobby_name)
                                statement.addBatch()
                                count++
                                // execute every 100 rows or less
                                if (count % 100 == 0 || count == list.size) {
                                    statement.executeBatch()
                                }
                            }
                        }
                    }
            }
        } catch (ex: SQLException) {
            println(ex.message)
        }
    }

    private fun getHobby(idpers: Int): List<Hobby> {
        val Hobbies: MutableList<Hobby> = ArrayList<Hobby>()
        try {
            val statement = conn!!.prepareStatement(resource!!.getString("getHobby"))
            statement.setInt(1, idpers)
            val rs = statement.executeQuery()
            // Количество колонок в результирующем запросе
            //val columns = rs.metaData.columnCount
            // Перебор строк с данными
            while (rs.next()) {
                /*for (int i = 1; i <= columns; i++){
                    //System.out.print(rs.getString(i) + "\t");
                }*/
                val h = Hobby()
                h.complexity=rs.getInt("complexity")
                h.hobby_name=rs.getString("hobby_name")
                Hobbies.add(h)
                //System.out.println();
            }
        } catch (e: SQLException) {
            println("CREATE TABLE Failed")
            e.printStackTrace()
            return Hobbies
        }
        //insertPerson(pers.getPersons());
        return Hobbies
    }

    fun getPersons(): Persons {
        val Pers: MutableList<Person> = ArrayList<Person>()
        val P = Persons()
        try {
            val statement = conn!!.prepareStatement(resource!!.getString("getPersons"))
            val rs = statement.executeQuery()
            // Количество колонок в результирующем запросе
            //val columns = rs.metaData.columnCount
            // Перебор строк с данными
            while (rs.next()) {
                /*for (int i = 1; i <= columns; i++){
                    //System.out.print(rs.getString(i) + "\t");
                }*/
                val p = Person()
                p.name=rs.getString("name")
                p.birthday=rs.getDate("birthday")
                p.Hobbies=getHobby(rs.getInt("id"))
                Pers.add(p)
                //System.out.println();
            }
        } catch (e: SQLException) {
            println("CREATE TABLE Failed")
            e.printStackTrace()
            return P
        }
        //insertPerson(pers.getPersons());
        P.People=Pers
        return P
    }
}
