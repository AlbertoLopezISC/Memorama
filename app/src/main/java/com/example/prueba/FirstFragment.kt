package com.example.prueba

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import java.util.jar.Manifest

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val SELECT_FOTO = 1
    val mImages: Uri? = null
    var path: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstFragment.background = background.getBackground()


        context?.let { ContextCompat.checkSelfPermission(it,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED }

        ContextCompat.checkSelfPermission(context!!,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

        when {
            ContextCompat.checkSelfPermission(
                context!!,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                view.findViewById<Button>(R.id.cartasBoton).setOnClickListener{
                    val intent: Intent = Intent(
                        Intent.ACTION_GET_CONTENT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,mImages)
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intent.setType("image/*")
                    startActivityForResult(Intent.createChooser(intent, "Elige aplicacion"), SELECT_FOTO)

                }
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                val permisos = android.Manifest.permission.READ_EXTERNAL_STORAGE
                activity?.let { ActivityCompat.requestPermissions(it, arrayOf(permisos), 50) }
            }
        }





        view.findViewById<Button>(R.id.button_play).setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_menuJuegoFragment)
        }

        view.findViewById<Button>(R.id.button_instruccions).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
            when(requestCode){
                SELECT_FOTO -> {
                    if (resultCode == RESULT_OK) {
                        //Tomar el nombre de la foto seleccionada
                        val selectedImage: Uri? = data?.data
                        val wholeID = DocumentsContract.getDocumentId(selectedImage)
                        val id = wholeID.split(":".toRegex()).toTypedArray()[1]

                        val column = arrayOf(MediaStore.Images.Media.DATA)
                        val sel = MediaStore.Images.Media._ID + "=?"

                        val cursor: Cursor? = activity?.contentResolver?.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,column, sel, arrayOf(id), null)
                        var filePath = ""
                        val columnIndex = cursor?.getColumnIndex(column[0])
                        if (cursor != null) {
                            if(cursor.moveToFirst())
                                filePath = cursor.getString(columnIndex!!)
                        }
                        this.path = filePath
                        println(filePath)
                        /*bitmap = BitmapFactory.decodeFile(path);  // Crea un mapa de bits y lo asigna a un imagenView
                        imgProducto.setImageBitmap(bitmap);*/
                    }
                }
            }
    }
}