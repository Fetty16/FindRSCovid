package com.example.findrs.model.provinsi

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ModelProvinsiResult {
    @SerializedName("provinces")
    lateinit var provinces: List<ModelProvinsi>

    inner class ModelProvinsi : Serializable {
        @SerializedName("id")
        lateinit var id: String

        @SerializedName("name")
        lateinit var name: String
    }

}