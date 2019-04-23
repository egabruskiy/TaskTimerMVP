package com.egabruskiy.tasktimer.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.egabruskiy.tasktimer.MainActivity
import com.egabruskiy.tasktimer.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_anonymous_auth.anonymousStatusEmail
import kotlinx.android.synthetic.main.activity_anonymous_auth.anonymousStatusId
import kotlinx.android.synthetic.main.activity_anonymous_auth.buttonAnonymousSignIn


class AnonymousAuthActivity : BaseActivity(){

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_auth)
        auth = FirebaseAuth.getInstance()
        buttonAnonymousSignIn.setOnClickListener{updateUI(auth.currentUser)}
    }

    public override fun onStart() {
        super.onStart()
        signInAnonymously()
    }

    private fun signInAnonymously() {
        showProgressDialog()
        auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInAnonymously:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                    hideProgressDialog()
                }
    }



    private fun updateUI(user: FirebaseUser?) {
        hideProgressDialog()
        val isSignedIn = user != null

        if (isSignedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            anonymousStatusId.setText(R.string.signed_out)
            anonymousStatusEmail.text = null
        }

        buttonAnonymousSignIn.isEnabled = !isSignedIn
    }


    companion object {
        private const val TAG = "AnonymousAuth"
    }
}
