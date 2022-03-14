package com.example.canliskorapp.util

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    var SHARED_PREFS_FILE_NAME = "userSettings"
    private fun getPrefs(context: Context): SharedPreferences? {
        try {
            return context.getSharedPreferences(
                SHARED_PREFS_FILE_NAME,
                Context.MODE_PRIVATE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    //Save Booleans
    fun save(context: Context?, key: String?, value: Boolean) {
        getPrefs(context!!)!!.edit().putBoolean(key, value).apply()
    }

    fun save(
        context: Context?,
        key: String?,
        value: String
    ) { // uygulama ilk kurulduğunda varsayılan olarak canlı ortam linki gelecek
        try {
            val mCroppedValue: String = value.replace(
                getString(
                    context,
                    "connectedurl",
                    "https://api.tmgrup.com.tr"
                ), ""
            )
            getPrefs(context!!)!!.edit()
                .putString(key, mCroppedValue).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveApiLink(
        context: Context?,
        key: String?,
        value: String?
    ) { // FKR-765 işi kapsamında artık enum'dan değil sharedpref'ten bu değerleri alacak. varsayılan olarak canlı db gelecek. tek değer olduğu için tek replace yapılacak
       getPrefs(context!!)!!.edit().putString(key, value).apply()
    }

    //Integers
    fun save(context: Context?, key: String?, value: Int) {
        getPrefs(context!!)!!.edit().putInt(key, value).apply()
    }

    //Floats
    fun save(context: Context?, key: String?, value: Float) {
       getPrefs(context!!)!!.edit().putFloat(key, value).apply()
    }

    //Longs
    fun save(context: Context?, key: String?, value: Long) {
        getPrefs(context!!)!!.edit().putLong(key, value).apply()
    }

    fun getBoolean(context: Context?, key: String?, defaultValue: Boolean): Boolean {
        return getPrefs(context!!)!!.getBoolean(key, defaultValue)
    }

    fun getString(context: Context?, key: String?): String {
        return getPrefs(context!!)!!.getString(key, "").toString()
    }

    fun getString(context: Context?, key: String?, defaultValue: String?): String {
        return getPrefs(context!!)!!.getString(key, defaultValue).toString()
    }

    fun getInt(context: Context?, key: String?): Int {
        return getPrefs(context!!)!!.getInt(key, 0)
    }

    fun getInt(context: Context?, key: String?, defaultValue: Int): Int {
        return getPrefs(context!!)!!.getInt(key, defaultValue)
    }

    fun getFloat(context: Context?, key: String?): Float {
        return getPrefs(context!!)!!.getFloat(key, 0f)
    }

    fun getFloat(context: Context?, key: String?, defaultValue: Float): Float {
        return getPrefs(context!!)!!.getFloat(key, defaultValue)
    }

    fun getLong(context: Context?, key: String?): Long {
        return getPrefs(context!!)!!.getLong(key, 0)
    }

    fun getLong(context: Context?, key: String?, defaultValue: Long): Long {
        return getPrefs(context!!)!!.getLong(key, defaultValue)
    }

    fun getStringSet(context: Context?, key: String?): Set<String> {
        return getPrefs(context!!)!!.getStringSet(key, null)!!
    }

    fun getStringSet(context: Context?, key: String?, defaultValue: Set<String?>?): Set<String> {
        return getPrefs(context!!)!!.getStringSet(key, defaultValue)!!
    }


    fun Context.savePrefs(name: String? = "custom_value"): SharedPreferences =
        getSharedPreferences(name, Context.MODE_PRIVATE)
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }
    /**
     * puts a value for the given [key].
     */
    operator fun SharedPreferences.set(key: String, value: Any?) = when (value) {
        is String?  -> edit { it.putString(key, value) }
        is Int      -> edit { it.putInt(key, value) }
        is Boolean  -> edit { it.putBoolean(key, value) }
        is Float    -> edit { it.putFloat(key, value) }
        is Long     -> edit { it.putLong(key, value) }
        else        -> throw UnsupportedOperationException("Not yet implemented")
    }
    /**
     * finds a preference based on the given [key].
     * [T] is the type of value
     * @param defaultValue optional defaultValue - will take a default defaultValue if it is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T = when (T::class) {
        String::class   -> getString(key, defaultValue as? String ?: "") as T
        Int::class      -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class  -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class    -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class     -> getLong(key, defaultValue as? Long ?: -1) as T
        else            -> throw UnsupportedOperationException("Not yet implemented")
    }

}