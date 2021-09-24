package com.example.liquerreviewattempttwo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class location_frag() : Fragment() {
    var StoreInformation: shopFronts? = null
    var ViewSave: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            StoreInformation = savedInstanceState.getParcelable("Storeinfo")
            StoreInformation?.let { ViewSave?.let { it1 -> StarSelect(it.Stars, it1) } }
        } else {
            val args = arguments
            StoreInformation = args?.getParcelable<shopFronts>("params")
        }
        /*
        For the mostpart, the oncreate method here checks to see if there is a savedinstance and loads in the saved parcelable object it inilizes it.
        If there isn't a savedinstance it will load the object passed through by the main activity at the fragments initial creation.
        */

    }


    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode){
                AppCompatActivity.RESULT_OK -> {
                    val data = result.data
                    val TempObject = data?.getParcelableExtra<shopFronts>("ReturnedObject")
                    StoreInformation = TempObject
                    Log.i(StoreInformation?.Stars.toString(),StoreInformation?.Stars.toString())
                    StoreInformation?.let { ViewSave?.let { it1 -> StarSelect(it.Stars, it1) } }
                }
            }
        }
    /*
The start for result loads in the data bassed back from the detailed activity, It'll then load the changed data (specifically the star rating provided by the user)
*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        /*
The onCreateView loads in the provided view information. It is also here where the majority of the onscreen information relevent to any store location is
substituted into the placeholder widgets. E.g. the store name, address, default star rating ect.
*/

        val view: View = inflater!!.inflate(R.layout.fragment_location_frag, container, false)
        val name = view.findViewById<TextView>(R.id.Title)
        name.setText(StoreInformation?.Name)

        val Address = view.findViewById<TextView>(R.id.Address)
        Address.setText(StoreInformation?.Address)

        val Image = view.findViewById<ImageView>(R.id.PreviewImage)
        val resourceId = this.resources.getIdentifier(StoreInformation?.Picture.toString(), "drawable", activity?.getBaseContext()?.getPackageName())
        Image.setImageResource(resourceId)

        val Button = view?.findViewById<Button>(R.id.MoreInfoBTN)
        Button?.setOnClickListener {
            val intent = Intent(activity,detailed_activity::class.java).apply {
                putExtra("StoreInfo", StoreInformation)
            }
            startForResult.launch((intent))

            /*
When the more infromation button is clicked, a onclick listener saves the store object information into an intent and passes it through to the detailed activity.
*/
        }

        StoreInformation?.let { StarSelect(it.Stars,view) }
        ViewSave = view
        return view
    }

    fun StarSelect(StarNum:Int, view:View, ) {
        for (i in 1..5) {
            if (i <= StarNum) {
                var resourceId = resources.getIdentifier("Star$i", "id", activity?.getPackageName())
                var SetStar = view.findViewById<ImageView>(resourceId)
                SetStar.setImageResource(R.drawable.star)
            } else {
                var resourceId = resources.getIdentifier("Star$i", "id", activity?.getPackageName())
                var SetStar = view.findViewById<ImageView>(resourceId)
                SetStar.setImageResource(R.drawable.nostar)
            }
        }

        /*
This method takes the store object value that relates to the star rating, and ajusts the onscreen display to reflext it. It checks each star and sets any
star with a value less or equal to the set value as being the filled in star, any star with a value greater then the set value is set to display the no star
drawable.
*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("Storeinfo",StoreInformation)
        /*
Saves the current object which contains store information for when the activity lifecyle loops through.
*/
    }
}
