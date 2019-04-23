package com.egabruskiy.tasktimer.mvp.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class Task (var id: String = "",
                 var taskName: String = "",
                 var timeDayPlan: Long = 0,
                 var currentProgress: Long = 0,
                 var allProgress: Long = 0,
                 var creationDate: Long = 0,
                 var lastDate: Long = 0
//                 var lastButOneDate: Long = 0,
//                 var lastButTwoDate: Long = 0,
//                 var lastButThreeDate: Long = 0
                     ) : Parcelable {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false
        return true
    }

    override fun hashCode() = taskName.hashCode()


    override fun describeContents(): Int {
        return 0
    }



}

