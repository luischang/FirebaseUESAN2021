package dev.luischang.firebaseuesan2021.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import dev.luischang.firebaseuesan2021.R
import dev.luischang.firebaseuesan2021.ui.fragments.adapter.CourseAdapter
import dev.luischang.firebaseuesan2021.ui.fragments.model.CourseModel


class ListadoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_listado, container, false)

        val db = FirebaseFirestore.getInstance()
        val lstCourses: ArrayList<CourseModel> = ArrayList()
        val rvCourse: RecyclerView = view.findViewById(R.id.rvCourse)

        db.collection("courses")
            .addSnapshotListener { snapshots,e->
                if(e!=null)
                {
                    Log.w("Firebase Warning","error",e)
                    return@addSnapshotListener
                }
                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED -> {
                            lstCourses.add(
                                CourseModel(dc.document.data["description"].toString(),
                                            dc.document.data["score"].toString()))
                                rvCourse.adapter = CourseAdapter(lstCourses)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            lstCourses.add(
                                CourseModel(dc.document.data["description"].toString(),
                                    dc.document.data["score"].toString()))
                            rvCourse.adapter = CourseAdapter(lstCourses)

                        }
                        DocumentChange.Type.REMOVED -> {
                            Log.w("Firebase Warning","REMOVED")
                        }
                    }
                }
                rvCourse.layoutManager = LinearLayoutManager(requireContext())
            }
        return view
    }
}