package com.example.josh_s8066611finalassignment.data.model

import java.io.Serializable

data class EntityItem (
     val title: String,
     val author: String,
     val genre: String,
     val publicationYear: Int,
     val description: String,
     val coverUrl: String? = null
 ) : Serializable


