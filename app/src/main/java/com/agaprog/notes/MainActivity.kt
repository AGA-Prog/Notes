package layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agaprog.notes.config.ConfigService
import com.agaprog.notes.databinding.ActivityMainBinding
import com.agaprog.notes.views.listmanagement.JsonActivity
import com.agaprog.notes.list.ListActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.agaprog.notes.storage.StorageTypes

class MainActivity : AppCompatActivity() {
    private lateinit var _binding:ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val config = ConfigService(this)
        val defaultList: String? = null

        when (config.getSelectedStorage()) {
            StorageTypes.FIREBASE -> {
                // TODO startActivity(Intent(this, LoginActivity::class.java))
                defaultList = config.getDefaultFirebaseNode()
            }
            else -> {
                defaultList = config.getDefaultFileName()
            }
        }

        if (defaultList.isNullOrBlank()) {
            startActivity(Intent(this, JsonActivity::class.java))
        } else {
            startActivity(Intent(this, ListActivity::class.java))
        }
    }
        val database = Firebase.database("https://agaprog-notes-default-rtdb.europe-west1.firebasedatabase.app/")
        val reference = database.getReference("message")
        reference.setValue("Hello World")
    }
}