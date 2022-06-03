package com.liontimer.application.pro.data.preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private final val preferenceKey = "PREFERENCE"

    var sharedPreference: SharedPreferences = context.getSharedPreferences(preferenceKey, Activity.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreference.edit()

    val PRIVACY_ACCEPTED = "PRIVACY_ACCEPTED"

    fun getPrivacyAccepted(): Boolean{
        return sharedPreference.getBoolean(PRIVACY_ACCEPTED, false) ?: false
    }

    fun setPrivacyAccepted(value: Boolean){
        editor.putBoolean(PRIVACY_ACCEPTED, value)
        editor.commit()
    }
}