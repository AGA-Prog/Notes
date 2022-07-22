package com.agaprog.notes.config
import android.content.Context
import androidx.preference.PreferenceManager
import com.agaprog.notes.storage.StorageTypes

class ConfigService (val context: Context){
    // Preference Keys

    fun getDefaultFileName():String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(ConfigKeys.DEFAULT_FILE.key.toString(),"")
    }

    fun setDefaultFileName(name:String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putString(ConfigKeys.DEFAULT_FIREBASE_NODE.key, name)
            apply()
        }
    }

    fun getDefaultFirebaseNode():String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getString(ConfigKeys.DEFAULT_FILE.key.toString(),"")
    }

    fun setDefaultFirebaseNode(name:String) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putString(ConfigKeys.DEFAULT_FIREBASE_NODE.key, name)
            apply()
        }
    }

    fun getSelectedStorage(): StorageTypes {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val storage = preferences.getString(
            ConfigKeys.SELECTED_STORAGE.key,
            StorageTypes.LOCAL.toString()
        )
        return StorageTypes.valueOf(storage!!)
    }

    fun setSelectedStorage(selectedStorage:StorageTypes) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        with(preferences.edit()) {
            putString(ConfigKeys.SELECTED_STORAGE.key, selectedStorage.toString())
            apply()
        }
    }
}