package com.example.gnomes.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/rrafols/mobile_test/master/"

//Crea el adaptador para hacer parcear el Json a kotlin object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/* Crea la instancia de retrofit y le agrega la instancia de moshi para que tranforme el Json en objetos.
* Tambien se le pasa el url base del api de los nodos*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

// interfaz para definir las llamadas al pi y lo que reciben
interface GnomeApiService {

    @GET("data.json")
    fun getGnomes():
            Deferred<GnomeListModel>
}
// Crea una instancia Del ApiService con los metodos definidos
object GnomeApi {
    val retrofitService: GnomeApiService by lazy { retrofit.create(GnomeApiService::class.java) }
}