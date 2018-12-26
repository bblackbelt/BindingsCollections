package com.blackbelt.bindings.notifications

import android.os.Bundle
import android.os.Parcelable

open class BundleParams : Params {

    companion object {
        fun initWithInt(key: String, value: Int): Params {
            val params = BundleParams()
            params.putInt(key, value)
            return params
        }

        fun initWithDouble(key: String, value: Double): Params {
            val params = BundleParams()
            params.putDouble(key, value)
            return params
        }

        fun initWithBoolean(key: String, value: Boolean): Params {
            val params = BundleParams()
            params.putBoolean(key, value)
            return params
        }
    }

    private val params: MutableMap<String, Any> = mutableMapOf()

    override fun getInt(key: String) = params[key] as Int
    override fun putInt(key: String, value: Int) = put(key, value)

    override fun getBoolean(key: String) = params[key] as Boolean
    override fun putBoolean(key: String, value: Boolean) = put(key, value)

    override fun getDouble(key: String) = params[key] as Double
    override fun putDouble(key: String, value: Double) = put(key, value)

    override fun getFloat(key: String) = params[key] as Float
    override fun putFloat(key: String, value: Float) = put(key, value)

    override fun getString(key: String): String = params[key] as String
    override fun putString(key: String, value: String) = put(key, value)

    override fun toBundle() = params.toBundle()

    private fun put(key: String, value: Any) {
        params[key] = value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as BundleParams
        return params == other.params
    }

    override fun hashCode(): Int = params.hashCode()
}

fun Map<String, Any>.toBundle(bundle: Bundle = Bundle()): Bundle = bundle.apply {
    forEach {
        val key = it.key
        val value = it.value
        when (value) {
            is Byte -> putByte(key, value)
            is ByteArray -> putByteArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Float -> putFloat(key, value)
            is FloatArray -> putFloatArray(key, value)
            is Parcelable -> putParcelable(key, value)
            is Short -> putShort(key, value)
            is ShortArray -> putShortArray(key, value)
            is Double -> putDouble(key, value)
            is Boolean -> putBoolean(key, value)
        }
    }
}
