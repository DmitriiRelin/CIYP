package com.ciyp.utils.downLoader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.StorageReference

fun downLoadWithByte(storageReference: StorageReference, imageView: ImageView, imageUrl: String){
    val imageRefl: StorageReference = storageReference.child(imageUrl)
    val MAXBYTES: Long = 1024*1024
//    imageView.setImageResource(0)
    imageRefl.getBytes(MAXBYTES).addOnSuccessListener(OnSuccessListener {

        val bitmap: Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
        imageView.setImageBitmap(bitmap)


    }).addOnFailureListener(OnFailureListener {

    })
}