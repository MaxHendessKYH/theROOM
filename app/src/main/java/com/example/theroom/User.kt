package com.example.theroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
// I databas alla @ bestämmer va tablename och columns kommer heta
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "score")
    val score: Double,
    @ColumnInfo(name = "isHuman")
    val isHuman: Boolean
)