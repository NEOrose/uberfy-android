package br.com.neorose.uberfy.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.neorose.uberfy.R
import br.com.neorose.uberfy.UberfyApplication
import com.uber.sdk.android.core.auth.AccessTokenManager
import com.uber.sdk.android.core.auth.AuthenticationError
import com.uber.sdk.android.core.auth.LoginCallback
import com.uber.sdk.android.core.auth.LoginManager
import com.uber.sdk.core.auth.AccessToken
import com.uber.sdk.rides.client.SessionConfiguration
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LoginCallback {
    val TAG = MainActivity::class.java.simpleName

    lateinit var loginManager: LoginManager

    @Inject lateinit var uberSessionConfiguration: SessionConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        UberfyApplication.graph.inject(this)
    }

    override fun onStart() {
        super.onStart()

        val accessTokenManager =  AccessTokenManager(this)

        loginManager = LoginManager(accessTokenManager,
                this,
                uberSessionConfiguration,
                21)

        loginManager.login(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i(TAG, String.format("onActivityResult requestCode:[%s] resultCode [%s]", requestCode, resultCode))

        loginManager.onActivityResult(this, requestCode, resultCode, data)
    }

    override fun onLoginError(error: AuthenticationError) {
        Log.e(TAG, error.name)
        Log.e(TAG, error.toStandardString())
    }

    override fun onLoginSuccess(accessToken: AccessToken) {
//            loadProfileInfo();
    }

    override fun onAuthorizationCodeReceived(authorizationCode: String) {
        Log.i(TAG, "Logged in: $authorizationCode")
    }

    override fun onLoginCancel() {
        Log.i(TAG, "onLoginCancel")
    }
}
