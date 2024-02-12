package com.example.list.data.repository

import com.example.list.data.remote.ItemsApi
import com.example.list.domain.model.Item
import com.example.list.domain.repository.ItemsRepository
import java.util.concurrent.CancellationException
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(private val itemsApi: ItemsApi) : ItemsRepository {
    override suspend fun getItems(): List<Item> {
        try {
            return itemsApi.getItems()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}