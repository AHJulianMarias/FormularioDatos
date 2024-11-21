package com.example.formulariodatos

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var nombreView: EditText
    lateinit var apellidoView:EditText
    lateinit var checkPaga:CheckBox
    lateinit var botonEnv:Button
    lateinit var textoGeneral: TextView
    lateinit var radioDefensa:RadioButton
    lateinit var radioDelantero:RadioButton
    lateinit var radioCentro:RadioButton
    lateinit var radioPortero:RadioButton
    lateinit var textoRadio: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nombreView = findViewById(R.id.editTextNombre)
        apellidoView = findViewById(R.id.editTextApellidos)
        radioCentro = findViewById(R.id.radioButtonCentro)
        radioDefensa = findViewById(R.id.radioButtonDefensa)
        radioPortero = findViewById(R.id.radioButtonPortero)
        radioDelantero = findViewById(R.id.radioButtonDelantero)
        botonEnv = findViewById(R.id.button)
        checkPaga = findViewById(R.id.checkBox)
        textoGeneral = findViewById(R.id.textViewResultado)
        val seekB = findViewById<SeekBar>(R.id.seekBar)
        val textoSeekB = findViewById<TextView>(R.id.textViewEdadElegida)
        seekB.max = 18
        seekB.min = 14
        textoSeekB.text= seekB.min.toString()
        val listRadios = listOf(radioCentro,radioDefensa,radioPortero,radioDelantero)
        seekB.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textoSeekB.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        })
        botonEnv.setOnClickListener{
            val textoNombre = nombreView.text.toString()
            val textoApellido = apellidoView.text.toString()
            val textoEdad = textoSeekB.text.toString()
            if (textoNombre == ""){
                Toast.makeText(this,"El apartado nombre no puede estar vacio", Toast.LENGTH_LONG).show()
            }else if(textoApellido == ""){
                Toast.makeText(this,"El apartado apellidos no puede estar vacio", Toast.LENGTH_LONG).show()
            }else{
                var marcado = false
                listRadios.forEach{ radio ->
                    if(radio.isChecked){
                        textoRadio = radio.text.toString()
                        marcado = true
                    }
                }
                if (!marcado) textoRadio= "No has marcado posicion"
                val textoCheck: String = if(checkPaga.isChecked) "Si paga seguro" else "No paga seguro"

                textoGeneral.text = getString(
                    R.string.a_os,
                    textoNombre,
                    textoApellido,
                    textoEdad,
                    textoRadio,
                    textoCheck
                ).trimMargin()
            }




        }

    }
}