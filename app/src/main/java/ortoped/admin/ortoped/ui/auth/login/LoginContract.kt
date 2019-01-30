package ortoped.admin.ortoped.ui.auth.login

import ortoped.admin.ortoped.ui.base.BasePresenter

interface LoginContract {

    interface View {

        fun showResponseOk()

        fun showResponseError(text: String)
    }

    interface Presenter : BasePresenter {

        fun login(password: String)
    }
}