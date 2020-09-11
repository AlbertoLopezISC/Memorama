package com.example.prueba

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.acercade.*

class Ajustes : AppCompatActivity() {
    var estado = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent.getStringExtra("fragmento") == "acerca_de"){
            setContentView(R.layout.acercade)
            acerca_de.background = background.getBackground()
        } else {
            setContentView(R.layout.activity_ajustes)
        }

//        setSupportActionBar(findViewById(R.id.toolbar))

        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Boton posible para ayuda", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}