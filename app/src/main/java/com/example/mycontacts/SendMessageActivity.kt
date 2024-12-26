package com.example.mycontacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class SendMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        val phoneNumber = intent.getStringExtra("phoneNumber")
        val textViewPhoneNumber = findViewById<TextView>(R.id.textView_phoneNumber)
        textViewPhoneNumber.text = phoneNumber

        val editTextMessage = findViewById<EditText>(R.id.editText_message)
        val buttonSendMessage = findViewById<Button>(R.id.button_sendMessage)

        buttonSendMessage.setOnClickListener {
            val message = editTextMessage.text.toString()

            if (message.isNotBlank()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.SEND_SMS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    sendSms(phoneNumber!!, message)
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.SEND_SMS),
                        300
                    )
                }
            } else {
                Toast.makeText(this, "Введите сообщение", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendSms(phoneNumber: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "Сообщение отправлено", Toast.LENGTH_SHORT).show()
            finish()
        } catch (e: Exception) {
            Toast.makeText(this, "Ошибка при отправке сообщения", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}