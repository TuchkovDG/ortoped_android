package ortoped.admin.ortoped.ui.auth.login

import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.ui.main.MainActivity

class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewsAndData() {
        presenter = LoginPresenterImpl(this)
        initButtonListener()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showResponseOk() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    override fun showResponseError(text: String) {
        toast(text)
    }

    private fun initButtonListener() {
        button_login.setOnClickListener {
            et_email.error = null
            et_password.error = null
            when {
                et_email.text.toString().isEmpty() -> et_email.error = getString(R.string.field_is_empty)
                et_password.text.toString().isEmpty() -> et_password.error = getString(R.string.field_is_empty)
                else -> presenter.login(et_password.text.toString())
            }
        }
    }
}