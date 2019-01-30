package ortoped.admin.ortoped.ui.auth.registration

import ortoped.admin.ortoped.model.UserModel
import ortoped.admin.ortoped.util.SPUtil

class RegistrationPresenterImpl(var view: RegistrationContract.View?) : RegistrationContract.Presenter {

    var serverOk: Boolean = false

    override fun registration(name: String, address: String, email: String, password: String) {
        if (serverOk) {
            SPUtil.saveUserModel(UserModel(""))
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