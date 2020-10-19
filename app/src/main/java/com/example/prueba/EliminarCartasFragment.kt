package com.example.prueba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_agregar_carta.*
import kotlinx.android.synthetic.main.fragment_eliminar_carta.*


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarCarta.newInstance] factory method to

*/
class EliminarCartasFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_carta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EliminarCartas.background = background.getBackground()



    }

}