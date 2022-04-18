package com.mexcelle.thoughtifydemo.util

import android.content.Context
import android.content.SharedPreferences


class SharedPref(context: Context) {

    private val PREFS_FILENAME = "com.mexcelle.everywhendemo"
    private val USER_NAME = "username"
    private val USER_EMAILID = "emailid"
    private val USER_AUTHTOKEN = "authtoken"
    private val USER_ENTITYNAME = "entityname"
    private val USER_COMPANYNAME = "companyname"
    private val USER_IMAGE_URL = "imageurl"
    private val USER_DATA = "userdata"
    private val USER_ID = "userid"




    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    //0 For Private Mode
    //private val preferences: SharedPreferences = context.getSharedPreferences(Context.MODE_PRIVATE)

    var initUsername: String?
        get() = preferences.getString(USER_NAME, "1")
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    var initEmailid: String?
        get() = preferences.getString(USER_EMAILID, "1")
        set(value) = preferences.edit().putString(USER_EMAILID, value).apply()


    var initAuthToken: String?
        get() = preferences.getString(USER_AUTHTOKEN, "1")
        set(value) = preferences.edit().putString(USER_AUTHTOKEN, value).apply()

    var initUserImageUrl: String?
        get() = preferences.getString(USER_IMAGE_URL, "1")
        set(value) = preferences.edit().putString(USER_IMAGE_URL, value).apply()


    var isUserDataAdded: Boolean
        get() = preferences.getBoolean(USER_DATA, false)
        set(value) = preferences.edit().putBoolean(USER_DATA, value).apply()

    var initUserId: String?
        get() = preferences.getString(USER_ID, "1")
        set(value) = preferences.edit().putString(USER_ID, value).apply()


}
