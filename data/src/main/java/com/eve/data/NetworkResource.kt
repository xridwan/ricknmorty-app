package com.eve.data

import com.eve.data.remote.network.ApiResponse
import com.eve.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = callRequest().first()) {
            is ApiResponse.Success -> {
                resultResponse(apiResponse.data)
                emit(Resource.Success(resultResponse(apiResponse.data)))
            }
            is ApiResponse.Empty -> {
                onFetchFailed()
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage, null))
            }
        }
    }

    protected abstract suspend fun callRequest(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun resultResponse(data: RequestType): ResultType

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected open fun onFetchFailed() {}

    fun asFlow(): Flow<Resource<ResultType>> = result
}