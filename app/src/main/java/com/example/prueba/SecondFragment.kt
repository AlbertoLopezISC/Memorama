package com.example.prueba

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_jugar.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var cont = 0
        var c1 = false
        var c2 = false
        val drawable: Drawable? = androidx.core.content.res.ResourcesCompat.getDrawable(resources, R.drawable.sol, null)
        Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
            textViewInstrucciones.requestFocus(1);
        }, 1000)
        view.findViewById<Button>(R.id.button_regresar).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        view.findViewById<Button>(R.id.button_siguiente).setOnClickListener {
            if(cont == 0){
                button_siguiente.contentDescription = "Muy bien, ahora apareceran una carta tapada" +
                        "en el centro de la pantalla. Presiona una para escuchar cual numero de carta es. Presiona dos veces" +
                        "para destaparla y ver lo que es. Cuando lo hayas hecho, presiona siguiente"
                view.findViewById<TextView>(R.id.textViewInstrucciones).text = "Muy bien, ahora apareceran una carta tapada" +
                        "en el centro de la pantalla. Presiona una para escuchar cual numero de carta es. Presiona dos veces" +
                        "para destaparla y ver lo que es. Cuando lo hayas hecho, presiona siguiente"
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    if(button_siguiente != null){
                        button_siguiente.contentDescription = "Boton siguiente";
                    }
                }, 15000)
                carta1Instrucciones.visibility = View.VISIBLE;
                cont++;
            } else if(cont == 1){
                button_siguiente.contentDescription = "Muy bien, ahora que ya viste que carta es, aparecera" +
                        "la siguiente carta y tendras destaparla de nuevo.";
                view.findViewById<TextView>(R.id.textViewInstrucciones).text = "Muy ahora que ya viste que carta es, aparecera" +
                        "la siguiente carta, y tendras destaparla de nuevo.";
                Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                    if(button_siguiente != null){
                        button_siguiente.contentDescription = "Boton siguiente";
                    }
                }, 10000)
                carta2Instrucciones.visibility = View.VISIBLE;
                cont++;
            }
        }
        view.findViewById<Button>(R.id.carta1Instrucciones).setOnClickListener(){
            carta1Instrucciones.background = drawable;
            carta1Instrucciones.contentDescription = "Carta destapada, Sol brillante al amanecer";
            Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                carta1Instrucciones.requestFocus();
            }, 1000)
            c1 = true;
        }
        carta2Instrucciones.setOnClickListener(){
            carta2Instrucciones.background = drawable;
            carta2Instrucciones.contentDescription = "Carta destapada, Sol brillante al amanecer";
            Handler().postDelayed(Runnable { // Do something after 5s = 5000ms
                view.findViewById<TextView>(R.id.textViewInstrucciones).text = "Excelente, hemos encontrado un par, ahora" +
                        "veremos lo que pasa cuando no encontramos un par";
                carta2Instrucciones.contentDescription = "Excelente, hemos encontrado un par, ahora" +
                        "veremos lo que pasa cuando no encontramos un par";
            }, 2500)
        }
    }
}