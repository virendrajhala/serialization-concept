package org.fretron.practice.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Serializable
data class User(var id : Int, var name : String, var email : String, var age : Int)

fun main(){


    val u1 = User(1,"Mark Stonemason","mark@gmail.com",23)
    val u2 = User(2,"John Wick","wick.john@gmail.com",24)
    val u3 = User(3,"Phantom","phantom@gmail.com",25)


    val jsonList = listOf(u1,u2,u3)


    println("-------- Serialization ----------")

    val jsonString = Json.encodeToString(u1)

    val serializedUserList = Json.encodeToString(jsonList)
    val fos = FileOutputStream("abc.txt")
    val oos = ObjectOutputStream(fos)
    // oos.writeObject(u1)
    // write User object to abc.txt file
    Json.encodeToStream(u1,oos)

    println(jsonString)
    println(serializedUserList)

    println("-------- Deserialization ----------")

    val fis = FileInputStream("abc.txt")
    val ois = ObjectInputStream(fis)
   // val user:User = ois.readObject() as User
    // read User object from abc.txt file
    val user = Json.decodeFromStream<User>(ois)
    println(user)

    val deserializedUser = Json.decodeFromString<User>(jsonString)
    println(deserializedUser)

    val deserializedUserList = Json.decodeFromString<List<User>>(serializedUserList)
    println(deserializedUserList)
}