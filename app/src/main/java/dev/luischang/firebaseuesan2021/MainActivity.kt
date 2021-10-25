package dev.luischang.firebaseuesan2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.DocumentType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCurso: TextView = findViewById(R.id.tvCurso)
        val tvNota: TextView = findViewById(R.id.tvNota)
        val db = FirebaseFirestore.getInstance()

        db.collection("courses")
            .addSnapshotListener{snapshots,e->
                if(e!=null){
                    Log.w("Firebase Warning","Listen Error",e)
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED ->{
                            tvCurso.text = dc.document.data["description"].toString()
                            tvNota.text = dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.MODIFIED ->{
                            tvCurso.text = dc.document.data["description"].toString()
                            tvNota.text = dc.document.data["score"].toString()
                        }

                        DocumentChange.Type.REMOVED ->{
                            tvCurso.text = "Curso eliminado"
                            tvNota.text  = "Nota eliminada"
                        }
                    }
                }
            }
    }
}