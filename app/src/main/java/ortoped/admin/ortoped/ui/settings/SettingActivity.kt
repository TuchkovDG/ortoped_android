package ortoped.admin.ortoped.ui.settings

import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.bd.HelperExercise
import ortoped.admin.ortoped.ui.auth.LoginOrRegistrationActivity
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.util.SPUtil

class SettingActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initViewsAndData() {
        val userModel = SPUtil.getUserModel()
        tv_name.text = userModel?.name
        initButtonListener()
    }

    private fun initButtonListener() {
        button_login.setOnClickListener {
            SPUtil.saveUserModel(null)
            HelperExercise().deleteAllExercises()
            startActivity<LoginOrRegistrationActivity>()
            finishAffinity()
        }
    }
}