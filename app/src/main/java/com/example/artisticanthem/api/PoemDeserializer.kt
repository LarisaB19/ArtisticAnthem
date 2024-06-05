package com.example.artisticanthem.api

import com.google.gson.*
import java.lang.reflect.Type

class PoemDeserializer : JsonDeserializer<List<PoemResponse>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<PoemResponse> {
        return if (json.isJsonArray) {
            val array = json.asJsonArray
            array.map { context.deserialize<PoemResponse>(it, PoemResponse::class.java) }
        } else {
            listOf(context.deserialize(json, PoemResponse::class.java))
        }
    }
}
