package ortoped.admin.ortoped.ui.auth.registration

import ortoped.admin.ortoped.ui.base.BasePresenter

class RegistrationContract {

    interface View {

        fun showResponseOk()

        fun showResponseError(text: String)
    }

    interface Presenter : BasePresenter {

        fun registration(name: String,
                         address: String,
                         email: String,
                         password: String)
    }
}