package ortoped.admin.ortoped.ui.auth.login

import ortoped.admin.ortoped.model.UserModel
import ortoped.admin.ortoped.util.SPUtil

class LoginPresenterImpl(var view: LoginContract.View?) : LoginContract.Presenter {

    var serverOk: Boolean = false

    override fun login(password: String) {
        if (serverOk) {
            SPUtil.saveUserModel(UserModel("", true))
            view?.showResponseOk()
        } else {
            serverOk = true
            view?.showResponseError("")
        }
    }

    override fun onDestroy() {
        view = null
    }
}