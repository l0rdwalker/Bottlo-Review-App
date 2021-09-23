package com.example.liquerreviewattempttwo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class shopFronts(var Name: String, var Address: String, var LastVisited: String,var Review: String, var Stars:Int, var Picture: String):
    Parcelable {}