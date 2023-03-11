package com.jeff_skillrill.shopping_android_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeff_skillrill.shopping_android_application.R
import com.jeff_skillrill.shopping_android_application.model.Book

class CartAdapter(
    var context: Context,
    var books: MutableList<Book>,
    var myInterface: MyInterface
) : RecyclerView.Adapter<CartAdapter.CartHolder>() {
    interface MyInterface {
        fun myInterfaceFunction(subTotal: Double)
        fun update()
    }

    class CartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.imgCart)
        val name: TextView = itemView.findViewById(R.id.nameCart)
        val price: TextView = itemView.findViewById(R.id.priceCart)
        val count: TextView = itemView.findViewById(R.id.countCart)
        val plusBtn: ImageView = itemView.findViewById(R.id.plusCart)
        val minusBtn: ImageView = itemView.findViewById(R.id.minusCart)
        val sub: TextView = itemView.findViewById(R.id.subTotalPrice)
        val deleteBtn: TextView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        var subTotal = 0.0
        var book: Book = books[position]

        holder.img.setImageResource(book.img)
        holder.name.text = book.name
        holder.price.text = "$${book.price}"

        holder.plusBtn.setOnClickListener {
            book.count++
            book.subTotal = book.count * book.price
            notifyItemChanged(position)
        }

        holder.minusBtn.setOnClickListener {
            if (book.count > 1) {
                book.count--
            }
            book.subTotal = book.count * book.price
            notifyItemChanged(position)
        }

        for (i in 0 until books.size) {
            subTotal += books[i].subTotal
        }

        myInterface.myInterfaceFunction(subTotal)

        holder.count.text = book.count.toString()
        holder.sub.text = "$${book.subTotal}"

        holder.deleteBtn.setOnClickListener {
            books.removeAt(position)
            if (books.size==0){
                myInterface.update()
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

}