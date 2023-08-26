package com.project.currency.core.utils

data class Resource<out T>(
    val status: Status = Status.IDLE,
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null
) {

    companion object {

        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null, statusCode: Int? = null): Resource<T> {
            return Resource(Status.ERROR, data, message, statusCode)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> idle(data: T? = null): Resource<T> {
            return Resource(Status.IDLE, data, null)
        }
    }

    fun isLoading() : Boolean {
        return status == Status.LOADING
    }

    fun isSuccess() : Boolean{
        return status == Status.SUCCESS
    }

    fun isError() : Boolean{
        return status == Status.ERROR
    }

    fun isIdle() : Boolean{
        return status == Status.IDLE
    }
}