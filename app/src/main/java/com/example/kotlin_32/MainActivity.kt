package com.example.kotlin_32

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Application", "Before creating")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Application", "Created")

        val textField = findViewById<EditText>(R.id.textField)
        val but = findViewById<Button>(R.id.button)
        val radPhone = findViewById<RadioButton>(R.id.phone)
        val radWeb = findViewById<RadioButton>(R.id.web)
        val radMap = findViewById<RadioButton>(R.id.map)
        Log.d("Application", "Values created")
        //82222222222

        //showMap("46.414382,10.013988")
        //showWebPage("cat")

        Log.d("Application", "Function completed")

        but.setOnClickListener {
            val text = textField.text.toString()
            when {
                radPhone.isChecked -> {
                    Log.d("Application", "Switch entered into phoneCall")
                    phoneCall(text)
                }
                radWeb.isChecked -> {
                    Log.d("Application", "Switch entered into showWebPage")
                    showWebPage(text)
                }
                radMap.isChecked -> {
                    Log.d("Application", "Switch entered into showMap")
                    showMap(text)
                }
                else -> {
                    Log.d("Application", "Switch entered into decide")
                    decide(text)
                }
            }
        }

    }
    private fun phoneCall(phoneNumber: String){
        Log.d("Application", "Function entered, text = $phoneNumber")
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        Log.d("Application", "Intent created")
        startActivity(intent)
        Log.d("Application", "Application started")
    }

    private fun showWebPage(webAddress: String){
        Log.d("Application", "Function entered, text = $webAddress")
        val intent = Intent(Intent.ACTION_WEB_SEARCH)
        intent.putExtra(SearchManager.QUERY, webAddress)
        Log.d("Application", "Intent created")
        startActivity(intent)
        Log.d("Application", "Application started")
    }

    private fun showMap(address: String){
        Log.d("Application", "Function entered, text = $address")
        val place = Uri.parse("geo:$address")
        Log.d("Application", "Place created")
        val intent = Intent(Intent.ACTION_VIEW, place)
        Log.d("Application", "Intent created")
        intent.setPackage("com.google.android.apps.maps")
        Log.d("Application", "Package added")
        startActivity(intent)
        Log.d("Application", "Application started")
    }

    private fun decide(text: String){
        var intText = text.toLongOrNull()
        Log.d("Application", "decide started, text = $intText")
        if (intText != null) {
            Log.d("Application", "not null!!!")
            if (intText >= 10000000000 && intText <= 99999999999){
                Log.d("Application", "decide phone")
                phoneCall(text)
            }
            else if(isCoordinate(text)){
                Log.d("Application", "decide map")
                showMap(text)
            }
            else {
                Log.d("Application", "decide web")
                showWebPage(text)
            }
        }
        Log.d("Application", "null?")
    }
}

private fun isCoordinate(text: String): Boolean {
    val pos = text.indexOf(',')
    if (pos != null){
        val firstCoordinate = text.substringBefore(',')
        val secondCoordinate = text.substringAfterLast(',')
        return firstCoordinate.toIntOrNull() != null && secondCoordinate.toIntOrNull() != null &&
                (firstCoordinate.toInt() >= -180 && firstCoordinate.toInt() <= 180) &&
                (secondCoordinate.toInt() >= -180 && secondCoordinate.toInt() <= 180)
    }
    return false
}