package com.example.liquerreviewattempttwo

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.graphics.drawable.Drawable
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BottleMart = shopFronts("BottleMart","596 Balcombe Rd, Black Rock VIC 3193",
            "22/9/2020","Good for when your on a beach walk. The intear is nice but the prices here are higher than others. ",
            4, "bottlemart")
        val Duncans = shopFronts("Duncans","95 Mentone Parade, Mentone VIC 3194",
            "23/9/2020","Nearby and easy. Prices are average. Intear is fine. ", 2,
        "mentoneduncans")
        val IGAbentleigh  = shopFronts("IGAbentleigh","86-88 Patterson Rd, Bentleigh VIC 3204",
            "24/9/2020","Expensive, far away. Why?", 3,
        "pat")
        val BoozeMate = shopFronts("BoozeMate","3/1-9 Balcombe Rd, Mentone VIC 3194",
            "25/9/2020","Very inconvenient to get to. But they seem to be the only place that sells my favorite flavour of a specific drink for very cheap. Worth it.",
            1, "mentoneliqerclearence")

        val list = listOf<shopFronts>(BottleMart, Duncans, IGAbentleigh,BoozeMate)

        list.forEachIndexed{index, element ->
            supportFragmentManager.beginTransaction().apply {
                var bundle = Bundle()
                bundle.putParcelable("params", element)
                var myObj = location_frag()
                myObj.setArguments(bundle)

                var resourceId = resources.getIdentifier("fragmentContainerView$index", "id", getBaseContext()
                    ?.getPackageName()
                )

                replace(resourceId, myObj)
                commit()
            }
        }




    }
}