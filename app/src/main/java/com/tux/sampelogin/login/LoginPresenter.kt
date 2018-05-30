package com.tux.sampelogin.login

import android.support.v4.util.PatternsCompat
import java.util.regex.Pattern

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(mail: String, pwd: String) {
        view.resetAllError()
        if (!isValidEmail(mail)) {
            view.showEmailInvalid()
            return
        }
        if (!isValidPassword(pwd)) {
            view.showPasswordError()
            return
        }

        view.loginSuccess()
    }

    private fun isValidPassword(pwd: String): Boolean {
        return Pattern.compile(PASSWORD_REGEX)
                .matcher(pwd)
                .matches()
    }

    private fun isValidEmail(mail: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(mail).matches()
    }
}