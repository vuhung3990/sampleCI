package com.tux.sampelogin

import com.tux.sampelogin.login.LoginContract
import com.tux.sampelogin.login.LoginPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LoginTest {
    private lateinit var view: LoginContract.View
    private lateinit var presenter: LoginContract.Presenter

    @Before
    fun setUp() {
        view = Mockito.mock(LoginContract.View::class.java)
        presenter = LoginPresenter(view)
    }

    @Test
    fun case_invalid_email() {
        val mail = "example@email"
        val pwd = "123143254353"
        presenter.login(mail, pwd)
        Mockito.verify(view).showEmailInvalid()
    }

    @Test
    fun case_invalid_password() {
        val mail = "djksja@ka.cc"
        val pwd = "1231 43254353"
        presenter.login(mail, pwd)
        Mockito.verify(view).showPasswordError()
    }

    @Test
    fun case_login_success() {
        val mail = "djksja@ka.cc"
        val pwd = "123143254353"
        presenter.login(mail, pwd)
        Mockito.verify(view).loginSuccess()
    }
}
