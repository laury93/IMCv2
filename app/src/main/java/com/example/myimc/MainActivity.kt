package com.example.myimc

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myimcv2.R

import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calcular.setOnClickListener{calculateImc()}
    }

    private fun calculateImc(){

        if(et_altura.text.toString().isEmpty() || et_peso.text.toString().isEmpty()) {
            Toast.makeText(
                this,
                "Los campos Peso y Altura no deben estar vacios para realizar el cálculo.",
                Toast.LENGTH_SHORT
            ).show()
        }else{
            val altura: Double = et_altura.text.toString().toDouble()/100
            val peso: Double = et_peso.text.toString().toDouble()
            val alturax2: Double = altura*altura
            var imc: Double = peso/alturax2
            tv_imc.text = String.format("%.2f",imc)
            var hombre: Boolean = false
            var mujer: Boolean = false
            hombre = rb_hombre.isChecked
            mujer = rb_mujer.isChecked

            if(hombre == true ){
                if(imc < 18.5){
                    tv_estado.text = "Peso inferior al normal"
                }else if(imc in 18.5..24.9){
                    tv_estado.text = "Normal"
                }else if(imc in 25.0..29.9){
                    tv_estado.text = "Sobrepeso"
                }else if(imc > 30.0){
                    tv_estado.text = "Obesidad"
                }
            }else if(mujer == true){
                if(imc < 18.5){
                    tv_estado.text = "Peso inferior al normal"
                }else if(imc in 18.5..23.9){
                    tv_estado.text = "Normal"
                }else if(imc in 25.0..28.9){
                    tv_estado.text = "Sobrepeso"
                }else if(imc > 29.0){
                    tv_estado.text = "Obesidad"
                }
            }

        }

        if (fileList().contains("notas.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("notas.txt"))
                val br = BufferedReader(archivo)
                var linea = br.readLine()
                val todo = StringBuilder()
                while (linea != null) {
                    todo.append(linea + "\n")
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                et_peso.setText(todo)
            } catch (e: IOException) {
            }
        }

        try {
            val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
            archivo.write(et_altura.text.toString())
            archivo.flush()
            archivo.close()
        } catch (e: IOException) {
        }
        Toast.makeText(this, "Los datos fueron grabados",Toast.LENGTH_SHORT).show()
        finish()
    }








}