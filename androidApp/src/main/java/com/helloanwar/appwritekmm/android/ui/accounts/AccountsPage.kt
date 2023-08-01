package com.helloanwar.appwritekmm.android.ui.accounts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountsPage(
    viewModel: AccountsViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    val response by viewModel.response.observeAsState()
    val error by viewModel.error.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        error?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(16.dp))
        }

        response?.let {
            Text(text = it)
            Spacer(modifier = Modifier.height(16.dp))
        }

        TextField(value = email, onValueChange = {
            email = it
        }, label = {
            Text(text = "Email")
        }, modifier = Modifier.fillMaxWidth())
        TextField(value = password, onValueChange = {
            password = it
        }, label = {
            Text(text = "Password")
        }, modifier = Modifier.fillMaxWidth())
        TextField(value = name, onValueChange = {
            name = it
        }, label = {
            Text(text = "Name")
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.onLogin(email, password)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Login")
        }
        Button(onClick = {
            viewModel.onSignup(email, password, name)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Signup")
        }
        Button(onClick = {
//                         viewModel.oAuthLogin()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "oAuth")
        }
        Button(onClick = {
            viewModel.getUser()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Get user")
        }
        Button(onClick = {
            viewModel.logout()
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Logout")
        }
    }
}