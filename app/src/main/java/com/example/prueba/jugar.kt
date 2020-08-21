package com.example.prueba

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_jugar.*
import java.util.concurrent.Delayed


class jugar : AppCompatActivity() {
    var retraso:Delayed? =null

    var cartaSeleccionadaUno  :ImageButton? = null     //con estas variables guardamos los botones seleccionados
    var cartaSeleccionadaDos : ImageButton? = null     //******

    var cartaUnoPos :String = ""
    var cartaDosPos : String = "HOla beto"
    var cartaUno: Int = -1   //Con estas variables checamos si son cartas iguales
    var cartaDos: Int = -1   //**************************************************
    var puntuacion: Int = 0
    var baraja_numeros = arrayListOf<Int>()  //array que contiene los numeros con los cuales compararemos si son las mismas cartas
    var tamano_baraja :Int = 0
    var baraja_botones = arrayListOf<ImageButton>()

    var rnds: Int = 0  //variable para generar numeros aleatorios
    var repetidas = 0 //variable para generar los numeros aleatorios



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar)
        val tapa:  Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.tapa2, null)
        val drawable: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.sol, null)

        //capturando lo que nos llego de parametro
        val objetIntent: Intent= intent
        tamano_baraja = objetIntent.getIntExtra("tamaño",12)
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
        var totalBotones= arrayListOf<ImageButton>(
            boton00,
            boton01,
            boton02,
            boton03,
            boton04,
            boton05,
            boton06,
            boton07,
            boton08,
            boton09,
            boton10,
            boton11
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
                    cartaSeleccionadaUno?.setImageDrawable(drawable) //Cambiamos la imagen (volteamos la carta)
                    cartaUnoPos=boton.getContentDescription().toString()
                    boton.setContentDescription("Sol")
                } //fin del if**********
                else if(boton != cartaSeleccionadaUno ) { //en caso de ser la segunda carta que se escogió
                    cartaDos = baraja_numeros[index]    // Guardamos que es lo que esconde esa carta
                    cartaSeleccionadaDos=boton
                    cartaSeleccionadaDos?.setImageDrawable(drawable) //Cambiamos la imagen (volteamos la carta)
                    cartaDosPos=boton.getContentDescription().toString()
                    boton.setContentDescription("Sol")
                    if(cartaUno == cartaDos  ) {

                        //empieza retardo
                        cartaSeleccionadaDos?.setContentDescription("Acertaste!!")
                        cartaSeleccionadaUno?.setVisibility(View.INVISIBLE)   //En caso de que sean las mismas cartas, procedemos a borrar estas
                        cartaSeleccionadaDos?.setVisibility(View.INVISIBLE)   // mismas, para ello setVisibility y
                        // cartaSeleccionadaUno?.setEnabled(false)               // setEnabled
                        // cartaSeleccionadaDos?.setEnabled(false)               //
                        //Fin del retardo
                        val handler = Handler()
                        handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                            puntuacion += 5
                            textoPuntuacion?.setText("Puntuación: $puntuacion")
                            textoPuntuacion?.setContentDescription("Acertaste!!")
                            textoPuntuacion.requestFocus()
                        }, 1000)
                    } else
                        println("vuelve a intentar WEY : $cartaUno y $cartaDos")
                    cartaUno = -1
                    cartaDos = -1
                    cartaSeleccionadaUno?.setTag("hola")




                    val handler = Handler()
                    handler.postDelayed(Runnable { // Do something after 5s = 5000ms
                        if(puntuacion >= 2){
                            puntuacion -= 2
                        } else {
                            puntuacion = 0;
                        }
                        textoPuntuacion?.setText("Puntuación: $puntuacion")
                        //Regresamos las cartas a como estaban antes de voltearlas
                        cartaSeleccionadaDos?.setImageDrawable(tapa)
                        cartaSeleccionadaUno?.setImageDrawable(tapa)
                        cartaSeleccionadaDos?.setContentDescription(cartaDosPos)
                        cartaSeleccionadaUno?.setContentDescription(cartaUnoPos)
                    }, 1000)

                    //***************************



                } // fin del else

            } //fin de la funcion OnClickListener***

        }//fin del for *********



    } //fin de la funcion OnCreate






} //fin de la clase