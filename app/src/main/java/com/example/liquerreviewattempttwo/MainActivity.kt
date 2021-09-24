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

        /*
            The above code is responsible for creating the objects which will eventually be passed into view fragments.
         */

        val list = listOf<shopFronts>(BottleMart, Duncans, IGAbentleigh,BoozeMate)

            /*
            I've put these location data classes into a non-mutable list of objects. Becasue the application doesn't support
            adding new locations into the system I thought that useing a mutable list was uncessary. Therefore, I didn't. True story.
            */

        if (savedInstanceState == null) {

            /*
            I encounted an issue that everytime the application cylced through its various states it would factory reset all fragment objects and their data.
            So on a screens rotation the star rating bar would reset back to its default state. This is becasue everything the main activity re-ran its
            oncreate function it would reinitilize all the fragment data. This was annoying, as I had designed fragments to savce their instancechange data and
            reinitalize irrespective of the main activity. To fix this, I made it so the fragment manager would only create new fragment objects when
            a savedinstance state is not detected. This allowed fragment data to retain themselves through orientation changes.
             */


            list.forEachIndexed { index, element ->
                supportFragmentManager.beginTransaction().apply {

                    var bundle = Bundle()
                    bundle.putParcelable("params", element)
                    var myObj = location_frag()
                    myObj.setArguments(bundle)

                    var resourceId = resources.getIdentifier(
                        "fragmentContainerView$index", "id", getBaseContext()
                            ?.getPackageName()
                    )

                    replace(resourceId, myObj)
                    commit()

                    /*
                    While the use of fragments was not outlined within the project specifiycation document, I though their implermentation made sense for this application.
                    Online, one of the usecases of fragments are they they allow you to create reuseable UI elements. As this project has four structually identiical
                    onscreen elements to display location data I thought their implermentation made sense. However, this ended up causeing alot of headaces. So,
                    neadless to say I have regrets. More information reguarding this can be foud in my GAP report.
                 */

                    /*
                    Location information (stored as objects) are saved as a bundle which is then passed into the myObj fragment object. This allows each fragment
                    to have access to the data is needs to display the relevent onscreen information.
                    */
                }
            }
        }

    }


}