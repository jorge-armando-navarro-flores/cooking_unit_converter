package com.example.cooking_unit_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooking_unit_converter.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.convertUnitsButton.setOnClickListener { convertUnits() }
    }

    private fun convertUnits() {
        val stringInTextField = binding.millilitersEditText.text.toString()
        val units = stringInTextField.toDoubleOrNull()
        if (units == null || units == 0.0) {
            displayUnits(0.0)
            return
        }

        var unitConversion = when(binding.unitOptions.checkedRadioButtonId) {
            R.id.option_to_tablespoons -> units / 14.787
            R.id.option_to_cups -> units / 240
            else -> units / 29.574

        }

        if (binding.roundConversionSwitch.isChecked) {
            unitConversion = (unitConversion * 100.0).roundToInt() / 100.0
        }

        displayUnits(unitConversion)

    }

    private fun displayUnits(units : Double){
        val formattedUnits = "$units"
        binding.conversion.text = getString(R.string.conversion, formattedUnits)
    }
}