package com.example.filmsandseries.util

data class ResultWrapper<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> succcess(data: T?): ResultWrapper<T> {
            return ResultWrapper(Status.SUCCESS, data, null)
        }

        fun <T> loading(data: T?): ResultWrapper<T> {
            return ResultWrapper(Status.LOADING, data, null)
        }

        fun <T> error(msg: String, data: T?): ResultWrapper<T> {
            return ResultWrapper(Status.ERROR, data, msg)
        }

    }
}


enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}