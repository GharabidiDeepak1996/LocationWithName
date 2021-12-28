package com.example.watchdesk


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.watchdesk.databinding.ActivityHomeBinding
import com.example.watchdesk.fragment.CreateIncidentFragment

class HomeActivity : AppCompatActivity() {
    private val  TAG="HomeActivity"
    private val createIncidentFragment=CreateIncidentFragment()

    private lateinit var bindingHomeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingHomeBinding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(bindingHomeBinding.root)

        replaceFragment(createIncidentFragment)

        window.statusBarColor= Color.BLACK
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment!=null){
            val transaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.commit()
        }
    }
}