package com.agaprog.notes.views.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.agaprog.notes.R
import com.agaprog.notes.databinding.ActivityLoginBinding
import com.agaprog.notes.storage.firebase.FirebaseService
import com.agaprog.notes.menu.MenuHandler
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityLoginBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //    initFirebase()
    }

    val firebaseService = FirebaseService(this)

    var resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            try {
                var credential =
                    firebaseService.oneTapClient.getSignInCredentialFromIntent(result.data)
            } catch (e: ApiException) {
                Log.d("Error", e.message.toString())
            }
        }
        else {
            Log.d("Debug", "CODE", + result.resultCode)
        }
    }
    resutLauncher.launch(this.intent)

    // Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var menuHandler = MenuHandler(this,"login")
        menuHandler.itemHandler(item)
        if (menuHandler.intent != null) {
            startActivity(menuHandler.intent)
        }
        return super.onOptionsItemSelected(item)
    }


    // Firebase

    private fun initFirebase(){
       val firebaseService = FirebaseService()
    }

}