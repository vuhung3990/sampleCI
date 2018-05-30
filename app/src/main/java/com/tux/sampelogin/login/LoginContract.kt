package com.tux.sampelogin.login

interface LoginContract {
    interface View {
        fun showEmailInvalid()
        fun loginSuccess()
        fun resetAllError()
        fun showPasswordError()
    }

    interface Presenter {
        fun login(mail: String, pwd: String)
    }
}