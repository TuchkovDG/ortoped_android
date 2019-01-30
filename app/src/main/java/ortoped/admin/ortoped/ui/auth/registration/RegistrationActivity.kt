package ortoped.admin.ortoped.ui.auth.registration

import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.ui.main.MainActivity

class RegistrationActivity : BaseActivity(), RegistrationContract.View {

    private lateinit var presenter: RegistrationContract.Presenter

    override fun layoutId(): Int {
        return R.layout.activity_registration
    }

    override fun initViewsAndData() {
        presenter = RegistrationPresenterImpl(this)
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
        button_registration.setOnClickListener {
            et_name.error = null
            et_address.error = null
            et_email.error = null
            et_password.error = null
            if (check()) {
                presenter.registration(
                        et_name.text.toString(),
                        et_address.text.toString(),
                        et_email.text.toString(),
                        et_password.text.toString()
                )
            }
        }
    }

    private fun check(): Boolean {
        when {
            et_name.text.toString().isEmpty() -> {
                et_name.error = getString(R.string.field_is_empty)
                return false
            }
            et_address.text.toString().isEmpty() -> {
                et_address.error = getString(R.string.field_is_empty)
                return false
            }
            et_email.text.toString().isEmpty() -> {
                et_email.error = getString(R.string.field_is_empty)
                return false
            }
            et_password.text.toString().isEmpty() -> {
                et_password.error = getString(R.string.field_is_empty)
                return false
            }
            else -> {
                return true
            }
        }
    }
}