package com.example.list.domain.repository

import com.example.list.domain.model.Item

interface ItemsRepository {
    suspend fun getItems(): List<Item>
}