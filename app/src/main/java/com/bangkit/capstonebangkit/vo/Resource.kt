package com.bangkit.capstonebangkit.vo

import com.bangkit.capstonebangkit.vo.Status.*

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> empty(msg: String, data: T?): Resource<T> {
            return Resource(EMPTY, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }

        fun <T> disconnected(data: T?): Resource<T> {
            return Resource(DISCONNECTED, data, "Connection not available")
        }
    }
}