package com.tlk.currencyconverter.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.io.IOException

fun <T1, T2> performDataLoad(
    loadFromDb: suspend () -> T1,
    networkCall: suspend () -> T2,
    saveCallResult: suspend (T2) -> Unit,
    shouldFetch: (data: T1?) -> Boolean
): LiveData<Resource<T1>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        try {
            var data = loadFromDb()
            if (shouldFetch(data)) {
                val response = networkCall()
                saveCallResult(response!!)
                data = loadFromDb()
            }
            emit(Resource.success(data))
        } catch (exception: IOException) {
            emit(Resource.error(exception.message, loadFromDb()))
        }
    }
}