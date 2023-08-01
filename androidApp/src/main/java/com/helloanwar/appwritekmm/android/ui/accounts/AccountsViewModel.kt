package com.helloanwar.appwritekmm.android.ui.accounts

import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helloanwar.appwritekmm.android.utils.Client.client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.extensions.toJson
import io.appwrite.services.Account
import kotlinx.coroutines.launch

class AccountsViewModel : ViewModel() {

    private val _error = MutableLiveData<String>().apply {
        value = null
    }
    val error: LiveData<String> = _error

    private val _response = MutableLiveData<String>().apply {
        value = null
    }
    val response: LiveData<String> = _response

    private val accountService by lazy {
        Account(client)
    }

    fun onLogin(email: String, password: String) {
        viewModelScope.launch {
            try {
                val session =
                    accountService.createEmailSession(email, password)
                _response.postValue(session.toJson())
            } catch (e: AppwriteException) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            }
        }
    }

    fun onSignup(email: String, password: String, name: String) {
        viewModelScope.launch {
            try {
                val user =
                    accountService.create(email.toString(), password.toString(), name.toString())
                _response.postValue(user.toJson())
            } catch (e: AppwriteException) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            }
        }

    }

    fun oAuthLogin(activity: ComponentActivity) {
        viewModelScope.launch {
            try {
                accountService.createOAuth2Session(
                    activity,
                    "facebook",
                    "appwrite-callback-6070749e6acd4://demo.appwrite.io/auth/oauth2/success",
                    "appwrite-callback-6070749e6acd4://demo.appwrite.io/auth/oauth2/failure"
                )
            } catch (e: Exception) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            } catch (e: AppwriteException) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            println("getting user => ")
            try {
                val account = accountService.get()
                _response.postValue(account.toJson())
            } catch (e: AppwriteException) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                val result = accountService.deleteSession("current")
                _response.postValue(result.toJson())
            } catch (e: AppwriteException) {
                _error.postValue(e.message.toString())
                e.printStackTrace()
            }
        }
    }
}