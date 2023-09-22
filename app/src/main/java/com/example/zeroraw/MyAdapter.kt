package com.example.zeroraw

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class MyAdapter (val itemlist: ArrayList<recylerarray>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        val tvAdress: TextView = itemView.findViewById(R.id.address)
        val tvPrice: TextView = itemView.findViewById(R.id.price)
        val image: ImageView = itemView.findViewById(R.id.imageView)


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview, parent, false)

        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return itemlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvAdress.text = itemlist[position].address
        holder.tvPrice.text = itemlist[position].price
//        holder.image.setImageBitmap(bitmap)
        val auth: FirebaseAuth = FirebaseAuth.getInstance()

        val localFile: File = File.createTempFile("tempfile", ".jpg")
        val storageReference = FirebaseStorage.getInstance().getReference(auth.currentUser!!.uid+"/"+itemlist[position].address+"image1")
        storageReference.getMetadata().addOnSuccessListener {
            // The file exists
            storageReference.getFile(localFile).addOnSuccessListener {
                // The file has been downloaded successfully
                val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                holder.image.setImageBitmap(bitmap)
            }
                .addOnFailureListener {
                    // An error occurred while downloading the file
                }
        }
            .addOnFailureListener {
                // The file does not exist
            }
    }
    }

