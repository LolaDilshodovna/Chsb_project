package com.jeff_skillrill.shopping_android_application

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeff_skillrill.shopping_android_application.adapter.CartAdapter
import com.jeff_skillrill.shopping_android_application.databinding.ActivityCardBinding
import com.jeff_skillrill.shopping_android_application.model.Book

class CardActivity : AppCompatActivity() {
    lateinit var binding:ActivityCardBinding
    lateinit var adapter: CartAdapter
    var total: Double = 60.20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityCardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        var sneakers = intent.getSerializableExtra("cart_sneakers") as MutableList<Book>
        var addedToCartSneakers = sneakers.filter { it.isAddedToCart }.toMutableList()

        binding.back.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            for (i in 0 until sneakers.size) {
                if (!addedToCartSneakers.contains(sneakers[i])) {
                    sneakers[i].isAddedToCart = false
                }
            }
            intent.putExtra("added", sneakers as java.io.Serializable)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        adapter = CartAdapter(this, addedToCartSneakers, object: CartAdapter.MyInterface {
            @SuppressLint("SetTextI18n")
            override fun myInterfaceFunction(subTotal: Double) {
                total = subTotal + 60.20

                binding.totalCost.text = total.toString()
                binding.subTotal.text = subTotal.toString()
            }

            override fun update() {
                binding.itemCount.visibility = getVisibility(addedToCartSneakers, this@CardActivity)
                binding.bottomPrices.visibility = getVisibility(addedToCartSneakers, this@CardActivity)
                binding.rv.visibility = getVisibility(addedToCartSneakers, this@CardActivity)

                binding.emptyString.visibility = if (getVisibility(addedToCartSneakers, this@CardActivity) != View.VISIBLE) View.VISIBLE else View.GONE
            }
        })

        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = adapter

        binding.itemCount.text = "${addedToCartSneakers.size} ${getString(R.string.item)}"

        binding.itemCount.visibility = getVisibility(addedToCartSneakers, this@CardActivity)
        binding.bottomPrices.visibility = getVisibility(addedToCartSneakers, this@CardActivity)
        binding.rv.visibility = getVisibility(addedToCartSneakers, this@CardActivity)

        binding.emptyString.visibility = if (getVisibility(addedToCartSneakers, this@CardActivity) != View.VISIBLE) View.VISIBLE else View.GONE

    }

    private fun getVisibility(addedToCartSneakers: MutableList<Book>, context: Context): Int {
        return if (addedToCartSneakers.isNotEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}


