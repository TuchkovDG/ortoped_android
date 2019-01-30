package ortoped.admin.ortoped.util

import android.content.Context
import com.google.gson.Gson
import ortoped.admin.ortoped.App
import ortoped.admin.ortoped.model.UserModel

object SPUtil {

    private val gson = Gson()

    private const val PreferenceName = "SPUtil"

    fun getUserModel(): UserModel? {
        return gson.fromJson(
                App.instance.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
                        .getString(Constants.KEY_USER_MODEL, ""), UserModel::class.java)
    }

    fun saveUserModel(user: UserModel?) {
        App.instance.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE)
                .edit().putString(Constants.KEY_USER_MODEL, gson.toJson(user)).apply()
    }
}