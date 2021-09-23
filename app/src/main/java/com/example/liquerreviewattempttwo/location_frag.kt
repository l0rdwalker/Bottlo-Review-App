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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        StoreInformation = args?.getParcelable<shopFronts>("params")
    }

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode){
                AppCompatActivity.RESULT_OK -> {
                    val data = result.data
                    val TempObject = data?.getParcelableExtra<shopFronts>("ReturnedObject")
                    StoreInformation = TempObject
                    Log.i(StoreInformation?.Stars.toString(),StoreInformation?.Stars.toString())

                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater!!.inflate(R.layout.fragment_location_frag, container, false)
        val name = view.findViewById<TextView>(R.id.Title)
        name.setText(StoreInformation?.Name)

        val Address = view.findViewById<TextView>(R.id.Address)
        Address.setText(StoreInformation?.Address)

        val Image = view.findViewById<ImageView>(R.id.PreviewImage)
        val resourceId = this.resources.getIdentifier(StoreInformation?.Picture.toString(), "drawable", activity?.getBaseContext()
            ?.getPackageName()
        )
        Image.setImageResource(resourceId)

        val Button = view?.findViewById<Button>(R.id.MoreInfoBTN)
        Button?.setOnClickListener {
            val intent = Intent(activity,detailed_activity::class.java).apply {
                putExtra("StoreInfo", StoreInformation)
            }
            startForResult.launch((intent))
        }

        StoreInformation?.let { StarSelect(it.Stars,view) }
        Log.i("onCreateView","onCreateView")
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
        StoreInformation?.Stars = StarNum
    }
}
