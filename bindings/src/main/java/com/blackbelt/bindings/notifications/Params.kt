package com.blackbelt.bindings.notifications

import android.os.Bundle

interface Params {
    fun getInt(key : String) : Int
    fun putInt(key : String, value : Int)

    fun getBoolean(key: String) : Boolean
    fun putBoolean(key: String, value: Boolean)

    fun getDouble(key: String) : Double
    fun putDouble(key: String, value: Double)

    fun getString(key: String) : String
    fun putString(key: String, value: String)
    fun toBundle(): Bundle

    fun putFloat(key: String, value: Float)
    fun getFloat(key: String): Float
}