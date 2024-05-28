package com.example.languagetranslatorapp

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.languagetranslatorapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var srcTxt: TextInputEditText
    private lateinit var translationTxt: TextView
    private lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        showAlert()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.savebtn.setOnClickListener(){
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

        spinner1 = binding.spinner1
        spinner2 = binding.spinner2
        srcTxt = binding.Edtsrce
        translationTxt = binding.txtTrans
        saveButton = binding.savebtn

        var Sp1 = findViewById(R.id.spinner1) as Spinner
        var Sp2 = findViewById(R.id.spinner2) as Spinner
        var SrcTxt = findViewById(R.id.Edtsrce) as TextInputEditText
        var Translation = findViewById(R.id.txt_trans) as TextView
        var speakButton = findViewById(R.id.speakButton) as Button

        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)

        val languages = arrayOf(
            "English",
            "Spanish",
            "French",
            "German",
            "Chinese",
            "Japanese",
            "Korean",
            "Indonesian",
            "Arabic",
            "Tagalog"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.setSelection(0)
        spinner2.setSelection(0)

        // Set default text in srcTxt
        srcTxt.setText("I love you")

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                translateILoveYou()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        saveButton.setOnClickListener{
            val translation = translationTxt.text.toString()
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("translation", translation)
            startActivity(intent)
        }
    }


    private fun translateILoveYou() {
        val translations = mapOf(
            "English" to "I love you",
            "Spanish" to "Te quiero",
            "French" to "Je t'aime",
            "German" to "Ich liebe dich",
            "Chinese" to "我爱你 | Wǒ ài nǐ",
            "Japanese" to "愛してる | Itoshi teru",
            "Korean" to "사랑해 | saranghae",
            "Indonesian" to "Aku cinta kamu",
            "Arabic" to "'uhibuk | أحبك",
            "Tagalog" to "Mahal kita"
        )

        val selectedLanguage = spinner2.selectedItem.toString()
        val translation = translations[selectedLanguage]
        translationTxt.text = translation ?: "Translation not found"
    }


    private fun showAlert() {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("About the App")
            .setMessage("This App translates I Love You's in different languages. If there's a person who tells you he/she loves you in every universe, they can also tell you I love you In every Languages. This App discover how to express your love. luvTranslate is your perfect companion to express love in multiple languages. Whether you're learning a new language, traveling abroad, or simply curious about how to say 'I Love You' in various languages, our app provides quick and accurate translations at your fingertips.")
            .setNegativeButton("Close") {
                    dialog,which->
                dialog.dismiss()
            }
        val alertDialog: AlertDialog =builder.create()
        alertDialog.show()
    }
}





