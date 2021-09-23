package com.example.liquerreviewattempttwo

import android.app.Activity
import android.graphics.Picture
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detailed_activity : AppCompatActivity() {
    var ShopInformation: shopFronts? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_information)

        ShopInformation = intent.getParcelableExtra<shopFronts>("StoreInfo")

        val StoreName = findViewById<TextView>(R.id.Title)
        StoreName.text = ShopInformation?.Name

        val StoreAddress = findViewById<TextView>(R.id.Address)
        StoreAddress.text = ShopInformation?.Address

        val Picture = findViewById<ImageView>(R.id.PreviewImage)
        val resourceId = this.resources.getIdentifier(ShopInformation?.Picture.toString(), "drawable", this.getPackageName())
        Picture.setImageResource(resourceId)

        val Review = findViewById<TextView>(R.id.Description)
        Review.text = ShopInformation?.Review

        var StarOne = findViewById<ImageView>(R.id.Star1).setOnClickListener{
            StarSelect(1)
        }
        var StarTwo = findViewById<ImageView>(R.id.Star2).setOnClickListener{
            StarSelect(2)
        }
        var StarThree = findViewById<ImageView>(R.id.Star3).setOnClickListener{
            StarSelect(3)
        }
        var StarFour = findViewById<ImageView>(R.id.Star4).setOnClickListener{
            StarSelect(4)
        }
        var StarFive = findViewById<ImageView>(R.id.Star5).setOnClickListener{
            StarSelect(5)
        }
        ShopInformation?.let { StarSelect(it.Stars) }
    }

    fun StarSelect(StarNum:Int) {
        for (i in 1..5) {
            if (i <= StarNum) {
                var resourceId = resources.getIdentifier("Star$i", "id", getBaseContext()?.getPackageName())
                var SetStar = findViewById<ImageView>(resourceId)
                SetStar.setImageResource(R.drawable.star)
            } else {
                var resourceId = resources.getIdentifier("Star$i", "id", getBaseContext()?.getPackageName())
                var SetStar = findViewById<ImageView>(resourceId)
                SetStar.setImageResource(R.drawable.nostar)
            }
        }
        ShopInformation?.Stars = StarNum
    }

    override fun onBackPressed() {
        val i = intent.apply {
            putExtra("ReturnedObject", ShopInformation)
        }
        setResult(Activity.RESULT_OK, i)
        super.onBackPressed()
    }
}