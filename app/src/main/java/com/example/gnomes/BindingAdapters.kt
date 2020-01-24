package com.example.gnomes

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.gnomes.gnomes.GnomeApiStatus
import com.example.gnomes.network.GnomeModel
import com.example.gnomes.gnomes.GnomesGridAdapter

// Adaptador que agrega la lista de Gnomes al GnomesGridAdapter para ser mostrado en le recyclerview
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GnomeModel>?) {
    val adapter = recyclerView.adapter as GnomesGridAdapter
    adapter.submitList(data)
}

/*Obtiene las imagenes de internet usando el Url y las guarda en memoria, tambien muestra un icono
* de carga mientras se descarga la imagen o uno de error*/
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}
//Muestra los iconos de carga y error mientras se hace obtienen los datos del api
@BindingAdapter("gnomeApiStatus")
fun bindStatus(statusImageView: ImageView, status: GnomeApiStatus?) {
    when (status) {
        GnomeApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

        GnomeApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        GnomeApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}