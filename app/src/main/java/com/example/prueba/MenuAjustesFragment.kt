package com.example.prueba

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_ajustes.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_menu_juego.*
import kotlinx.android.synthetic.main.menu_ajustes_fragment.*
import org.w3c.dom.Document
import java.io.File
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

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
        menuAjustes.background = background.getBackground()
        CambiarColor.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#0d15ff"),
                Color.parseColor("#54FFD4"),
                Color.parseColor("#0d15ff")
            )
        )
        CambiarColor2.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#ff3358"),
                Color.parseColor("#BAF4FF"),
                Color.parseColor("#ff3358")
            )
        )
        CambiarColor3.background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(
                Color.parseColor("#ff0a0a"),
                Color.parseColor("#f6ff45"),
                Color.parseColor("#ff0a0a")
            )
        )
        regresarInicio.setOnClickListener(){
            activity?.navigateUpTo(Intent(activity, MainActivity::class.java))
            activity?.finish();
            println("goasfdlaksjfdlaskdfaldksfalsdf")
        }

        view.findViewById<Button>(R.id.CambiarColor).setOnClickListener(){
//************************************************************************
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#0d15ff"),
                    Color.parseColor("#54FFD4"),
                    Color.parseColor("#0d15ff")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable);
            menuAjustes.background = gradientDrawable

            //root.background = gradientDrawable
            //root2?.background = gradientDrawable

            //firstFragment.background = gradientDrawable;


//****************************************************************************
//--------------------------------------------------------------
            /*
            val fXmlFile = File("drawable/gradiente.xml")
            println(fXmlFile)

            val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
            val doc: Document = dBuilder.parse(fXmlFile)

            doc.documentElement.normalize()

            println(doc.documentElement.nodeName)

            val aux = resources.getXml(R.drawable.gradiente);
             */
//--------------------------------------------------------------

        }

        CambiarColor2.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#ff3358"),
                    Color.parseColor("#BAF4FF"),
                    Color.parseColor("#ff3358")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable);
            menuAjustes.background = gradientDrawable

        }

        CambiarColor3.setOnClickListener(){
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#ff0a0a"),
                    Color.parseColor("#f6ff45"),
                    Color.parseColor("#ff0a0a")
                )
            )
            gradientDrawable.cornerRadius = 0f
            background.setBackground(gradientDrawable);
            menuAjustes.background = gradientDrawable
        }

        ColorAceptar.setOnClickListener(){
            if(color1.text != null && color.text != null ){
                var c1 = 0
                var c2 = 0
                try {
                    c1 = Color.parseColor(color1.text.toString())
                } catch (e: Exception) {
                    color1.setText("color invalido")
                }
                try {
                    c2 = Color.parseColor(color.text.toString())
                } catch (e: Exception){
                    color.setText("color invalido")
                }
                println(c1 + c2)
                if(c1 != 0 && c2 != 0){
                    val gradientDrawable = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT,
                        intArrayOf(
                            Color.parseColor(color1.text.toString()),
                            Color.parseColor(color.text.toString()),
                            Color.parseColor(color1.text.toString())
                        )
                    )
                    gradientDrawable.cornerRadius = 0f
                    background.setBackground(gradientDrawable);
                    menuAjustes.background = gradientDrawable
                }

            }
        }
    }
}