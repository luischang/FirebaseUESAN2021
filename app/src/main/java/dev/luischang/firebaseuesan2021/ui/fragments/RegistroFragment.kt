package dev.luischang.firebaseuesan2021.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import dev.luischang.firebaseuesan2021.R
import java.util.*


class RegistroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_registro, container, false)

        val txtCourse: EditText = view.findViewById(R.id.txtCourse)
        val txtScore: EditText = view.findViewById(R.id.txtScore)
        val btnSave: Button = view.findViewById(R.id.btnSave)
        val db = FirebaseFirestore.getInstance()

        btnSave.setOnClickListener {
            var curso = txtCourse.text.toString()
            var nota = txtScore.text.toString()

            val nuevoCurso = Course(curso,nota)

            val id: UUID = UUID.randomUUID()

            db.collection("courses")
                .document(id.toString())
                .set(nuevoCurso)
                .addOnSuccessListener {
                    enviarMensaje(view,"Se registró satisfactoriamente...!!")
                }.addOnFailureListener{
                    enviarMensaje(view,"Ocurrió un problema al registrar el curso")
                }
        }
        return view
    }
}
private fun enviarMensaje(vista: View, mensaje: String){
    Snackbar.make(vista,mensaje,Snackbar.LENGTH_LONG).show()
}
data class Course (
    val description: String,
    val score: String)

