package com.example.colorapp

import com.example.colorapp.model.ColorEntry
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID

class ColorRepository {
    private val realm: Realm
//    private val firebaseDb = FirebaseDatabase.getInstance().reference.child("colors")

    init {
        val config = RealmConfiguration.Builder(schema = setOf(ColorEntry::class))
            .build()
        realm = Realm.open(config)
    }

    fun getAllColors(): Flow<List<ColorEntry>> =
        realm.query(ColorEntry::class).asFlow().map { it.list }

    fun getUnsyncedCount(): Flow<Int> =
        realm.query(ColorEntry::class)
            .query("isSynced == false")
            .asFlow()
            .map { it.list.size }

    suspend fun addColor(color: String, timestamp: Long) {
        realm.write {
            copyToRealm(ColorEntry().apply {
                id = UUID.randomUUID().toString()
                this.color = color
                this.timestamp = timestamp
                this.isSynced = false
            })
        }
    }
}