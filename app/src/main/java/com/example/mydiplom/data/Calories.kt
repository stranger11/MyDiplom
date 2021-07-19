package com.example.mydiplom.data

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//import kotlinx.android.parcel.Parcelize


@Entity(tableName = "calories_table")
data class Calories (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "cal_id")
        val calId: Int?,
        @ColumnInfo(name = "day_calories")
        var dayCalories: Double?
)

@Entity(tableName = "water_table")
data class Water (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "wat_id")
        val watId: Int?,
        @ColumnInfo(name = "day_water")
        var dayWater: Int?
        )


@Entity(tableName = "pushUps_table")
data class PushUps (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pushUp_id")
        val pushUpId: Int?,
        @ColumnInfo(name = "day_pushUps")
        var dayPushUp: Int?
        )


@Entity(tableName = "squats_table")
data class Squats (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "sqId")
        val sqId: Int?,
        @ColumnInfo(name = "day_squats")
        var daySquats: Int?
        )


@Entity(tableName = "press_table")
data class Press (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pressId")
        val pressId: Int?,
        @ColumnInfo(name = "day_press")
        var dayPress: Int?
        )


@Entity(tableName = "run_table")
data class Run (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "runId")
        val runId: Int?,
        @ColumnInfo(name = "distance")
        val distance: Int?
        )


@Entity(tableName = "day_results_table")
data class DayResults (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "drId")
        val drId: Int,
        @ColumnInfo(name = "date")
        var date: String,
        @ColumnInfo(name = "day_water")
        var dayWater: Int,
        @ColumnInfo(name = "day_calories")
        var dayCalories: Double,
        @ColumnInfo(name = "day_pushUps")
        var dayPushUps: Int,
        @ColumnInfo(name = "day_squats")
        var daySquats: Int,
        @ColumnInfo(name = "day_press")
        var dayPress: Int,
        @ColumnInfo(name = "day_run")
        var dayRun: Int
        )


@Entity(tableName = "allRuns_table")
data class AllRuns(
        var img: Bitmap? = null,
        var timestamp: Long = 0L,
        var avgSpeedInKMH: Float = 0f,
        var distanceInMeters: Int = 0,
        var timeInMillis: Long = 0L,
        var caloriesBurned: Int = 0
) {
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null
}




