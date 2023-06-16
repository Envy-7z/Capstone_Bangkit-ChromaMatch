package com.bangkit.capstonebangkit.vo

data class ResourceVar<out T>(val status: Status, val data: T?, val variant: T?, val store : T?, val message: String?) {
    companion object {
        fun <T> success(data: T?, variant: T?, store: T?): ResourceVar<T> {
            return ResourceVar(Status.SUCCESS, data, variant, store, null)
        }

        fun <T> error(msg: String, data: T?, variant: T?, store: T?): ResourceVar<T> {
            return ResourceVar(Status.ERROR, data, variant, store, msg)
        }

        fun <T> empty(msg: String, data: T?, variant: T?, store: T?): ResourceVar<T> {
            return ResourceVar(Status.EMPTY, data,variant, store, msg)
        }

        fun <T> loading(data: T?, variant: T?, store: T?): ResourceVar<T> {
            return ResourceVar(Status.LOADING, data, variant, store,null)
        }

        fun <T> disconnected(data: T?, variant: T?, store: T?): ResourceVar<T> {
            return ResourceVar(Status.DISCONNECTED, data,variant, store, "Connection not available")
        }
    }
}