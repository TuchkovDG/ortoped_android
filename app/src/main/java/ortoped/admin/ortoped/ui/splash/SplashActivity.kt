package ortoped.admin.ortoped.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.ui.auth.LoginOrRegistrationActivity
import ortoped.admin.ortoped.ui.main.MainActivity
import ortoped.admin.ortoped.util.SPUtil

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        checkAndStartActivity()
    }

    private fun checkAndStartActivity() {
        if (SPUtil.getUserModel() != null) {
            startActivity<MainActivity>()
        } else {
            startActivity<LoginOrRegistrationActivity>()
        }
        finish()
    }
}