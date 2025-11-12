package com.seuusuario.appdoacao

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val category: String? = null,
    val userId: String? = null
)
