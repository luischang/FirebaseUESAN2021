package dev.luischang.firebaseuesan2021.ui.fragments.interfaces

import dev.luischang.firebaseuesan2021.ui.fragments.model.MascotaModel
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("mascotaperdida.php")
    fun listarMascota(): Call<List<MascotaModel>>
}