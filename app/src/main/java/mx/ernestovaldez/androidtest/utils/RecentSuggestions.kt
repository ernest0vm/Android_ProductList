package mx.ernestovaldez.androidtest.utils

import android.content.Context
import android.content.SharedPreferences

class RecentSuggestions(context: Context) {
    private val PREFS_NAME = "mx.ernestovaldez.sharedpreferences"
    private val SHARED_NAME = "recent_suggestions"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var recents: MutableList<String>
        get() = prefs.getStringSet(SHARED_NAME, setOf<String>())!!.toMutableList()
        set(value) = prefs.edit().putStringSet(SHARED_NAME, value.toSet()).apply()
}
