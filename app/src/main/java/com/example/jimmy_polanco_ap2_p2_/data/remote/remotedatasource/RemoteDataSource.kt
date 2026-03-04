package com.example.jimmy_polanco_ap2_p2_.data.remote.remotedatasource

import com.example.jimmy_polanco_ap2_p2_.data.remote.Api.ApiService
import com.example.jimmy_polanco_ap2_p2_.data.remote.Resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getItems(): Resource<List<Item>> {
        return withContext(Dispatchers.IO) {
            try {

                val response = emptyList<Item>()
                Resource.Success(response)
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "Error desconocido")
            }
        }
    }
}