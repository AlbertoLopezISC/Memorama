package com.example.prueba

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.menu_ajustes_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuAjustesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_ajustes_fragment, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.CambiarColor).setOnClickListener(){
            var bgShape = menuAjustes.background;
            val res = resources;
            menuAjustes.background = Drawable.createFromXml(res, res.getXml(R.drawable.gradiente));
            println("hola bb");
        }
    }
}