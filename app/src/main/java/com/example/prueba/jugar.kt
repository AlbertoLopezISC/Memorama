package com.example.prueba

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_jugar.*
import java.util.concurrent.Delayed


class jugar : AppCompatActivity() {
    var retraso:Delayed? =null

    var cartaSeleccionadaUno  :Button? = null     //con estas variables guardamos los botones seleccionados
    var cartaSeleccionadaDos : Button? = null     //******

    var cartaUnoPos :String = ""
    var cartaDosPos : String = "version 1.2"
    var cartaUno: Int = -1   //Con estas variables checamos si son cartas iguales
    var cartaDos: Int = -1   //**************************************************
    var puntuacion: Int = 0
    var baraja_numeros = arrayListOf<Int>()  //array que contiene los numeros con los cuales compararemos si son las mismas cartas
    var tamano_baraja :Int = 0
    var baraja_botones = arrayListOf<Button>()

    var rnds: Int = 0  //variable para generar numeros aleatorios
    var repetidas = 0 //variable para generar los numeros aleatorios



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar)
        jugarFragment.background = background.getBackground()

        val tapa:  Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.tapa2, null)
        val drawable: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.sol, null)
        val imagen1: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.barco, null)
        val imagen2: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.barco_de_papel, null)
        val imagen3: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.pajaro_carpintero, null)
        val imagen4: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.pajaro_carpintero_colorido, null)
        val imagen5: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.nube, null)

        //capturando lo que nos llego de parametro
        val objetIntent: Intent= intent
        tamano_baraja = objetIntent.getIntExtra("tamaño",12)
        var puntos = objetIntent.getIntExtra("puntos", 0)
        //Boton para salir del juego
        Boton_salida.setOnClickListener {
            //Lineas de codigo para llamar a la actividad principal pero sin liberar memoria
            /*val intento1 = Intent(this, MainActivity::class.java)
            startActivity(intento1)*/
            //Ahora se termina la acctividad, regresando a la anterior. Esto permite liberar memoria
            finish()
        }
        //**********
        //Guardamos todos los botones en un array
        var totalBotones= arrayListOf<Button>(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button10,
            button11,
            button12
        )

        //***************

        //Barajeando las cartas
        // for para generar los numeros aleatorios para las posiciones de las cartas
        for(i in 0..tamano_baraja-1) {
            do {
                repetidas = 0
                rnds = (0..( tamano_baraja / 2 )-1).random()

                for (index in baraja_numeros) {
                    if (index == rnds)
                        repetidas++
                }
                if (repetidas < 2)
                    baraja_numeros.add(rnds)
            }while(repetidas>1)
        } //Fin del for*************
        //fin de barajear **********

        //Comprobando en la terminal que se hayan generado los numeros correctamente
        var tamaño=baraja_numeros.size
        println("el tamaño de la baraja es  de $tamaño")
        for (numero in baraja_numeros)
            println(numero)
        //fin de la comprobacion***************



        //Mostrando el numero de cartas segun la dificultad seleccionada
        for((index,carta) in totalBotones.withIndex())
        {
            if(index < tamano_baraja){
                carta.setContentDescription("carta número ${index + 1}")
                carta.setTag("carta ${index + 1}")
                println(carta.getTag())
                baraja_botones.add(carta)
            }else {
                carta.setVisibility(View.INVISIBLE)
            }


        }
        //******************



        //Realizando cambios al Boton  cuando se da click
        for((index,boton) in baraja_botones.withIndex()){
            boton.setOnClickListener {

                if(cartaUno == -1) { //en caso de ser la primera carta que se escogió
                    cartaUno = baraja_numeros[index]    // Guardamos que es lo que esconde esa carta
                    cartaSeleccionadaUno=boton
                    cartaSeleccionadaUno?.setBackground(drawable) //Cambiamos la imagen (volteamos la carta)
                    cartaUnoPos=boton.getContentDescription().toString()
                    boton.setContentDescription("Sol") //cambiamos la descripcion
                } //fin del if**********
                else if(boton != cartaSeleccionadaUno  && cartaDos == -1) { //en caso de ser la segunda carta que se escogió


                    cartaDos = baraja_numeros[index]    // Guardamos que es lo que esconde esa carta
                    cartaSeleccionadaDos=boton
                    cartaSeleccionadaDos?.setBackground(drawable) //Cambiamos la imagen (volteamos la carta)
                    cartaDosPos=boton.getContentDescription().toString()
                    boton.setContentDescription("Sol")


                    if(cartaUno == cartaDos  ) { //En caso de que hayan sido las mismas

                        //empieza retardo
                        cartaSeleccionadaDos?.setContentDescription("Acertaste!!")

                        val dandler = Handler() // Comienzo del retardo
                        dandler.postDelayed(Runnable { // Do something after 5s = 5000ms
                            cartaSeleccionadaUno?.setVisibility(View.INVISIBLE)   //En caso de que sean las mismas cartas, procedemos a borrar estas
                            cartaSeleccionadaDos?.setVisibility(View.INVISIBLE)
                            puntuacion += 5
                            textoPuntuacion?.setText("Puntuación: $puntuacion")
                            textoPuntuacion.requestFocus()
                            cartaUno = -1
                            cartaDos = -1
                        }, 3000) //Fin del retardo

                        // cartaSeleccionadaUno?.setEnabled(false)               // setEnabled
                        // cartaSeleccionadaDos?.setEnabled(false)               //

                    } else { //En caso de que NO sea el mismo
                        val handler = Handler()
                        cartaSeleccionadaDos?.setContentDescription("fallaste!!")
                        handler.postDelayed(Runnable { // Do something after 5s = 5000ms inicio del retardo
                            if(puntuacion >= puntos){
                                puntuacion -= puntos
                            } else {
                                puntuacion = 0;
                            }


                            textoPuntuacion?.setText("Puntuación: $puntuacion")
                            //Regresamos las cartas a como estaban antes de voltearlas
                            cartaSeleccionadaDos?.setBackground(tapa)
                            cartaSeleccionadaUno?.setBackground(tapa)
                            cartaSeleccionadaDos?.setContentDescription(cartaDosPos)
                            cartaSeleccionadaUno?.setContentDescription(cartaUnoPos)
                            cartaUno = -1
                            cartaDos = -1
                        }, 3000)   //fin del retardo


                    }  //fin del else de cuando no sean las mismas cartas

                    //Esto debe pasar indistintamente de que sean correctas o no las cartas
                    println("vuelve a intentar WEY : $cartaUno y $cartaDos")

                    cartaSeleccionadaUno?.setTag("hola")






                } //fin del else if de ser la segunda carta escogida

            } //fin de la funcion OnClickListener***

        }//fin del for *********



    } //fin de la funcion OnCreate






} //fin de la clase