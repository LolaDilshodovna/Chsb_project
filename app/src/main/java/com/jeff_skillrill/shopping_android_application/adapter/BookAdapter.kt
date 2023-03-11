package com.jeff_skillrill.shopping_android_application.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jeff_skillrill.shopping_android_application.BookDetail
import com.jeff_skillrill.shopping_android_application.R
import com.jeff_skillrill.shopping_android_application.model.Book

class BookAdapter(var context: Context, var books: MutableList<Book>) : RecyclerView.Adapter<BookAdapter.MyHolder>() {

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val favBtn: ImageView = itemView.findViewById(R.id.favBtn)
        val plusBtn: ConstraintLayout = itemView.findViewById(R.id.plusBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var book: Book = books[position]

        holder.img.setImageResource(book.img)
        holder.name.text = book.name
        holder.price.text = "$ ${book.price}"

        holder.favBtn.setOnClickListener {
            book.isFavourite = !book.isFavourite

            if (book.isFavourite) {
                holder.favBtn.setBackgroundResource(R.drawable.fav)
            }
            else {
                holder.favBtn.setBackgroundResource((R.drawable.fav_border))
            }
        }

        if (book.isFavourite) {
            holder.favBtn.setBackgroundResource(R.drawable.fav)
        }
        else {
            holder.favBtn.setBackgroundResource((R.drawable.fav_border))
        }

        holder.plusBtn.setOnClickListener {
            book.isAddedToCart = true

            Toast.makeText(context, "Savatchaga qo'shildi", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            var intent = Intent(context, BookDetail::class.java)
            intent.putExtra("sneakers", books as java.io.Serializable)
            intent.putExtra("index", position)
            context.startActivity(intent)
        }
    }
}