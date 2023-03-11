package com.jeff_skillrill.shopping_android_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeff_skillrill.shopping_android_application.databinding.ActivityBookDetailBinding
import com.jeff_skillrill.shopping_android_application.model.Book

class BookDetail : AppCompatActivity() {
    lateinit var binding: ActivityBookDetailBinding
    lateinit var sneakers: MutableList<Book>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("updateSneakers", sneakers as java.io.Serializable)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        sneakers = intent.getSerializableExtra("sneakers") as MutableList<Book>
        var index: Int = intent.getIntExtra("index", 0)
        var sneaker = sneakers[index]

        binding.sneakerImg.setImageResource(sneaker.img)
        binding.sneakerName.text = sneaker.name
        binding.sneakerPrice.text = "$ ${sneaker.price}"

        if (sneakers[index].isFavourite) {
            binding.addToFavBtn.setImageResource(R.drawable.fav)
        } else {
            binding.addToFavBtn.setImageResource(R.drawable.fav_border)
        }

        binding.addToFavBtn.setOnClickListener {
            sneakers[index].isFavourite = !sneakers[index].isFavourite

            if (sneakers[index].isFavourite) {
                binding.addToFavBtn.setImageResource(R.drawable.fav)
            } else {
                binding.addToFavBtn.setImageResource(R.drawable.fav_border)
            }

        }

        binding.addToCartBtn.setOnClickListener {
            sneakers[index].isAddedToCart = true
        }
    }
}