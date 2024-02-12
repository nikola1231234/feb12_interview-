package com.example.list.data.remote

import com.example.list.domain.model.Item
import retrofit2.http.GET

interface ItemsApi {
    @GET("exoplayer/api/programmes.json")
    suspend fun getItems(): List<Item>
}