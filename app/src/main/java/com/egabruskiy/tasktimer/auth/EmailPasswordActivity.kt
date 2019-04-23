package com.egabruskiy.tasktimer.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import com.egabruskiy.tasktimer.MainActivity
import com.egabruskiy.tasktimer.R
import com.egabruskiy.tasktimer.auth.BaseActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_emailpassword.detail
import kotlinx.android.synthetic.main.activity_emailpassword.emailCreateAccountButton
import kotlinx.android.synthetic.main.activity_emailpassword.emailPasswordButtons
import kotlinx.android.synthetic.main.activity_emailpassword.emailPasswordFields
import kotlinx.android.synthetic.main.activity_emailpassword.emailSignInButton
import kotlinx.android.synthetic.main.activity_emailpassword.fieldEmail
import kotlinx.android.synthetic.main.activity_emailpassword.fieldPassword
import kotlinx.android.synthetic.main.activity_emailpassword.signOutButton
import kotlinx.android.synthetic.main.activity_emailpassword.signedInButtons
import kotlinx.android.synthetic.main.activity_emailpassword.status
import kotlinx.android.synthetic.main.activity_emailpassword.verifyEmailButton

class EmailPasswordActivity : BaseActivity(), View.OnClickListener {

        private lateinit var auth: FirebaseAuth

        public override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_emailpassword)

        emailSignInButton.setOnClickListener(this)
        emailCreateAccountButton.setOnClickListener(this)
        signOutButton.setOnClickListener(this)
        verifyEmailButton.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
        }

        public override fun onStart() {
           super.onStart()
          val currentUser = auth.currentUser
                  updateUI(currentUser)
}

        private fun createAccount(email: String, password: String) {
                Log.d(TAG, "createAccount:$email")
                if (!validateForm()) {
                        return
                }

                showProgressDialog()

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {

                                        Log.d(TAG, "createUserWithEmail:success")
                                        val user = auth.currentUser
                                        sendEmailVerification()
                                        updateUI(user)
                                } else {
                                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                        Toast.makeText(baseContext, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show()
                                        updateUI(null)
                                }
                                hideProgressDialog()
                        }
        }

        private fun signIn(email: String, password: String) {
                Log.d(TAG, "signIn:$email")
                if (!validateForm()) {
                        return
                }
                if (auth.currentUser != null) {
                        val intent = Intent(this, MainActivity::class.java)
                                        startActivity(intent)
                }
                showProgressDialog()

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                        Log.d(TAG, "signInWithEmail:success")
                                        val user = auth.currentUser
                                        updateUI(user)
                                } else {
                                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                                        Toast.makeText(baseContext, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show()
                                        updateUI(null)
                                }
                                if (!task.isSuccessful) {
                                        status.setText(R.string.auth_failed)
                                }
                                hideProgressDialog()
                        }
        }

        private fun signOut() {
                auth.signOut()
                updateUI(null)
        }

        private fun sendEmailVerification() {
                verifyEmailButton.isEnabled = false

                val user = auth.currentUser
                user?.sendEmailVerification()
                        ?.addOnCompleteListener(this) { task ->

                                verifyEmailButton.isEnabled = true

                                if (task.isSuccessful) {
                                        Toast.makeText(baseContext,
                                                "Verification email sent to ${user.email} ",
                                                Toast.LENGTH_SHORT).show()
                                } else {
                                        Log.e(TAG, "sendEmailVerification", task.exception)
                                        Toast.makeText(baseContext,
                                                "Failed to send verification email.",
                                                Toast.LENGTH_SHORT).show()
                                }
                        }
        }

        private fun validateForm(): Boolean {
                var valid = true

                val email = fieldEmail.text.toString()
                if (TextUtils.isEmpty(email)) {
                        fieldEmail.error = "Required."
                        valid = false
                } else {
                        fieldEmail.error = null
                }

                val password = fieldPassword.text.toString()
                if (TextUtils.isEmpty(password)) {
                        fieldPassword.error = "Required."
                        valid = false
                } else {
                        fieldPassword.error = null
                }

                return valid
        }

        private fun updateUI(user: FirebaseUser?) {
                hideProgressDialog()
                if (user != null) {
                        if (!user.isEmailVerified){
                                status.text = ("Email User:" + user.email)
                                detail.text = (getString(R.string.verify_email_and_sign_in))
                                emailCreateAccountButton.visibility = View.GONE
                                emailPasswordFields.visibility = View.GONE
                                emailSignInButton.visibility = View.VISIBLE
                                verifyEmailButton.visibility= View.VISIBLE

                                verifyEmailButton.isEnabled = !user.isEmailVerified
                        } else {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                        }

                } else {
                        status.setText(R.string.signed_out)
                        detail.text = null

                        emailPasswordButtons.visibility = View.VISIBLE
                        emailPasswordFields.visibility = View.VISIBLE
                        signedInButtons.visibility = View.GONE
                }
        }

        override fun onClick(v: View) {
//                val i =
                when (v.id) {
                        R.id.emailCreateAccountButton -> createAccount(fieldEmail.text.toString(), fieldPassword.text.toString())
                        R.id.emailSignInButton -> signIn(fieldEmail.text.toString(), fieldPassword.text.toString())
                        R.id.signOutButton -> signOut()
                        R.id.verifyEmailButton -> sendEmailVerification()
                }
        }

        companion object {
                private const val TAG = "EmailPassword"
        }
        }
