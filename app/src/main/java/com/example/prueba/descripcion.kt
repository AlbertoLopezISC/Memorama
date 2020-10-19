package com.example.prueba

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Descripcion(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    var descrip: String,
    val ruta: String


)