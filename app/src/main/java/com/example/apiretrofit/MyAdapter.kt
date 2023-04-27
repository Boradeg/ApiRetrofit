package com.example.apiretrofit

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val productArrayList: List<Product>) :RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
      var title:TextView
        var image:ImageView
        var prize:TextView
        init {
            title=itemView.findViewById(R.id.productTitle)
            image=itemView.findViewById(R.id.productImage)
            prize=itemView.findViewById(R.id.productPrize)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(context).inflate(R.layout.each_item,parent,false)
        return MyViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=productArrayList[position]
        holder.title.text=currentItem.title
        val priz=currentItem.price.toString()
        holder.prize.text= "$ $priz"
        Picasso.get().load(currentItem.thumbnail).into(holder.image);
    }
}