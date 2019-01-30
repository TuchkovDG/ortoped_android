package ortoped.admin.ortoped.ui.auth

import kotlinx.android.synthetic.main.activity_login_or_registration.*
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.ui.auth.login.LoginActivity
import ortoped.admin.ortoped.ui.auth.registration.RegistrationActivity
import ortoped.admin.ortoped.ui.base.BaseActivity

class LoginOrRegistrationActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_login_or_registration
    }

    override fun initViewsAndData() {
        initButtonListener()
    }

    private fun initButtonListener() {
        button_login.setOnClickListener {
            startActivity<LoginActivity>()
        }
        button_registration.setOnClickListener {
            startActivity<RegistrationActivity>()
        }
    }
}