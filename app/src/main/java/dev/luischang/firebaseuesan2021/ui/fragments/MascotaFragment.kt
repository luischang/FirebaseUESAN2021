package dev.luischang.firebaseuesan2021.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luischang.firebaseuesan2021.R
import dev.luischang.firebaseuesan2021.ui.fragments.adapter.MascotaAdapter
import dev.luischang.firebaseuesan2021.ui.fragments.client.MascotaClient
import dev.luischang.firebaseuesan2021.ui.fragments.model.MascotaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MascotaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mascota, container, false)

        val rvMascota: RecyclerView = view.findViewById(R.id.rvMascota)
        rvMascota.layoutManager = LinearLayoutManager(requireActivity())

        val call : Call<List<MascotaModel>> =  MascotaClient.retrofitService.listarMascota()

        call.enqueue(object: Callback<List<MascotaModel>>{
            override fun onResponse(
                call: Call<List<MascotaModel>>,
                response: Response<List<MascotaModel>>
            ) {
                Log.d("onResponse",response.body().toString())
                rvMascota.adapter = MascotaAdapter(response.body()!!)
            }

            override fun onFailure(call: Call<List<MascotaModel>>, t: Throwable) {
               Log.w("Onfailure Retrofit","Ocurrió un error en Listar la mascota");
            }
        })

        return view
    }
}