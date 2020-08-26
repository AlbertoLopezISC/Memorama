package com.example.prueba

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_first.*
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

        view.findViewById<Button>(R.id.CambiarColor).setOnClickListener(){
//************************************************************************
            val gradientDrawable = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.parseColor("#0d15ff"),
                    Color.parseColor("#54FFD4"),
                    Color.parseColor("#0d15ff")
                )
            );
            gradientDrawable.cornerRadius = 0f;
            menuAjustes.background = gradientDrawable;

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
    }
}