package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? = null
    private var ageInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(findViewById(R.id.defaultToolBar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.txtSelectedDate)
        ageInMinutes = findViewById(R.id.txtAgeInMinutes)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, year, month, dayOfMonth ->
                //Toast.makeText(this, "$dayOfMonth/${month+1}/$year", Toast.LENGTH_LONG).show()

                val selectedDate2 = "$dayOfMonth/${month+1}/$year"
                selectedDate?.text = selectedDate2

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate2)
                date?.let {
                    val selectedDateInMinutes = date.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        println(differenceInMinutes)
                        ageInMinutes?.text = differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}