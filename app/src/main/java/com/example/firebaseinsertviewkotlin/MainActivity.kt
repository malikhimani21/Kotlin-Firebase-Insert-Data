package com.example.firebaseinsertviewkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var editTextEmail: EditText
    lateinit var editTextContact: EditText
    lateinit var ratingBar: RatingBar
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextContact = findViewById(R.id.editTextContact)
        ratingBar = findViewById(R.id.ratingBar)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener() {
            saveInfo()
        }
    }

    private fun saveInfo() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val contact = editTextContact.text.toString().trim()

        if (name.isEmpty()) {
            editTextName.error = "Please enter value"
        }
        if (email.isEmpty()) {
            editTextEmail.error = "Please enter value"
        }
        if (contact.isEmpty()) {
            editTextContact.error = "Please enter value"
        }

        val ref = FirebaseDatabase.getInstance().getReference("user")

        val id = ref.push().key

        val model = Model(id, name, email, contact, ratingBar.numStars)

        ref.child(id!!).setValue(model).addOnCompleteListener {
            Toast.makeText(this, "Data save successfully", Toast.LENGTH_LONG).show()
        }


    }
}