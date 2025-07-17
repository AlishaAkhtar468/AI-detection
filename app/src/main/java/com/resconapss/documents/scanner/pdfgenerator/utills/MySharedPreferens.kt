package com.resconapss.documents.scanner.pdfgenerator.utills

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class MySharedPreferens(context: Context) {
    private val sharedPrefs: SharedPreferences
    private val prefsEditor: SharedPreferences.Editor

    init {
        sharedPrefs = context.getSharedPreferences(
            APP_SHARED_PREFS,
            Activity.MODE_PRIVATE
        )
        prefsEditor = sharedPrefs.edit()
    }

    fun getfirsttime(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun savefirstitme(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun setcountinue(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun getcountinue(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }
    fun setgdrPref(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun getgdrPref(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun set_cross(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun get_cross(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }


    fun setlockcompass(key: String?, state: Boolean?) {

        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun getlockCompass(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }
    fun set_compass_dialer(key: String?, state: Boolean?) {

        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun get_compass_dialer(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun setLockBubble(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun getlockBubble(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun setLockInclino(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun get_lock_inclino(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun save_leveler_mode(key: String?, option: Int) {
        prefsEditor.putInt(key, option)
        prefsEditor.commit()
    }

    fun get_leveler_modePrefs(key: String?): Int {
        return sharedPrefs.getInt(key, 0)
    }


    fun getLastLatPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "longitude")
    }

    fun getLastLongiPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "latitude")
    }

    fun saveLastLati(key: String?, latitude: String?) {
        prefsEditor.putString(key, latitude)
        prefsEditor.commit()
    }

    fun saveLastLongi(key: String?, longitude: String?) {
        prefsEditor.putString(key, longitude)
        prefsEditor.commit()
    }


    fun save_sateenamePrefs(key: String?, satName: String?) {
        prefsEditor.putString(key, satName)
        prefsEditor.commit()
    }

    fun saveAzimuthPrefs(key: String?, azimuth: String?) {
        prefsEditor.putString(key, azimuth)
        prefsEditor.commit()
    }

    fun saveCompassPrefs(key: String?, compass: String?) {
        prefsEditor.putString(key, compass)
        prefsEditor.commit()
    }

    fun saveElevationPrefs(key: String?, elevation: String?) {
        prefsEditor.putString(key, elevation)
        prefsEditor.commit()
    }

    fun save_lnbskewPrefs(key: String?, lnbskew: String?) {
        prefsEditor.putString(key, lnbskew)
        prefsEditor.commit()
    }


    fun get_satenamePrefs(key: String?): String? {
        return sharedPrefs.getString(key, "SatName")
    }

    fun getAzimuthPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "azimuth")
    }

    fun getCompassPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "compass")
    }

    fun getElevationPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "elevation")
    }

    fun getLnbSkewPrefs(key: String?): String? {
        return sharedPrefs.getString(key, "lnbskew")
    }
    fun save_latilongi(key: String?, state: Boolean?) {
        prefsEditor.putBoolean(key, state!!)
        prefsEditor.commit()
    }

    fun get_latilongi(key: String?): Boolean {
        return sharedPrefs.getBoolean(key, true)
    }

    fun save_dialer(key: String?, option: Int) {
        prefsEditor.putInt(key, option)
        prefsEditor.commit()
    }

    fun get_dialer(key: String?): Int {
        return sharedPrefs.getInt(key, 0)
    }


    companion object {
        private const val APP_SHARED_PREFS = "Location"
    }
}