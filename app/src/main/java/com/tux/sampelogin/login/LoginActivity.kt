package com.tux.sampelogin.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tux.sampelogin.MainActivity
import com.tux.sampelogin.R
import com.tux.sampelogin.SignupActivity
import kotlinx.android.synthetic.main.activity_login.*

//  ^                 # start-of-string
//  (?=.*[0-9])       # a digit must occu
// r at least once
//  (?=.*[a-z])       # a lower case letter must occur at least once
//  (?=.*[A-Z])       # an upper case letter must occur at least once
//  (?=.*[@#$%^&+=])  # a special character must occur at least once
//  (?=\S+$)          # no whitespace allowed in the entire string
//  .{8,}             # anything, at least eight places though
//  $                 # end-of-string
const val PASSWORD_REGEX: String = "^(?=\\S+\$).{8,}\$"

class LoginActivity : AppCompatActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(this)

        login_btn.setOnClickListener {
            val pwd = password.text.toString()
            val mail = email.text.toString()
            presenter.login(mail, pwd)
        }

        register.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    fun test() {
        println("feature a")
    }

    override fun loginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showEmailInvalid() {
        textInputEmail.error = getString(R.string.invalid_email)
    }

    override fun showPasswordError() {
        textInputPassword.error = getString(R.string.invalid_password)
    }

    override fun resetAllError() {
        textInputEmail.error = null
        textInputPassword.error = null
    }
}
