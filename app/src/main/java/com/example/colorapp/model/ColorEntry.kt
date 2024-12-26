package com.example.colorapp.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ColorEntry : RealmObject {
    @PrimaryKey
    var id: String = ""
    var color: String = ""
    var timestamp: Long = 0
    var isSynced: Boolean = false
}

