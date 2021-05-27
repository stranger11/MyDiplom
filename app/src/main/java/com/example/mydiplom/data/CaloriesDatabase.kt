package com.example.mydiplom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities =
[Calories::class,
    Water::class,
    PushUps::class,
    Squats::class,
    Press::class,
    Run::class,
    AllRuns::class,
    DayResults::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class CaloriesDatabase : RoomDatabase() {

    abstract fun caloriesDao(): CaloriesDao

    companion object {
        private var inst: CaloriesDatabase? = null

        fun getDatabase(context: Context): CaloriesDatabase {
            val tempInstance = inst
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CaloriesDatabase::class.java,
                    "task_database"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        GlobalScope.launch {
                            inst?.caloriesDao()?.addCalories(PREPOPULATE_DATA_CALORIES)
                            inst?.caloriesDao()?.addWater(PREPOPULATE_DATA_WATER)
                            inst?.caloriesDao()?.addPushUps(PREPOPULATE_DATA_PUSH_UPS)
                            inst?.caloriesDao()?.addSquats(PREPOPULATE_DATA_SQUATS)
                            inst?.caloriesDao()?.addPress(PREPOPULATE_DATA_PRESS)
                            inst?.caloriesDao()?.addRun(PREPOPULATE_DATA_RUN)
                        }
                    }
                }).build()
                inst = instance
                return instance
            }
        }
        val PREPOPULATE_DATA_CALORIES = Calories(0, 0.0)
        val PREPOPULATE_DATA_WATER = Water(0, 0)
        val PREPOPULATE_DATA_PUSH_UPS = PushUps(0, 0)
        val PREPOPULATE_DATA_SQUATS = Squats(0,0)
        val PREPOPULATE_DATA_PRESS = Press(0, 0)
        val PREPOPULATE_DATA_RUN = Run(0, 0)
    }
}