package com.example.mydiplom.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface CaloriesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCalories(calories: Calories)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateCalories(calories: Calories)

    @Query("SELECT day_calories FROM calories_table")
    fun readCalorie() : LiveData<Double>

    @Query("UPDATE calories_table SET day_calories =:daycalories WHERE cal_id =:id")
    fun update(daycalories : Double, id: Int)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addWater(water : Water)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateWater(water: Water)

    @Query("SELECT day_water FROM water_table")
    fun readWater() : LiveData<Int>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPushUps(pushUps: PushUps)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updatePushUps(pushUps: PushUps)

    @Query("SELECT day_pushUps FROM pushUps_table")
    fun readPushUp() : LiveData<Int>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSquats(squats: Squats)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSquats(squats: Squats)

    @Query("SELECT day_squats FROM squats_table")
    fun readSquats(): LiveData<Int>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPress(press: Press)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updatePress(press: Press)

    @Query("SELECT day_press FROM press_table")
    fun readPress(): LiveData<Int>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addRun(run: Run)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateRun(run: Run)

    @Query("SELECT distance FROM run_table")
    fun readRun(): LiveData<Int>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDayResults(dayResults: DayResults)

    @Query("SELECT * FROM day_results_table ORDER BY drId ASC")
    fun readDayResults() : LiveData<List<DayResults>>



    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertRun(run: AllRuns)

    @Delete
    suspend fun deleteRun(run: AllRuns)



    @Query("SELECT * FROM allRuns_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<AllRuns>>

    @Query("SELECT * FROM allRuns_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<AllRuns>>

    @Query("SELECT * FROM allRuns_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<AllRuns>>

    @Query("SELECT * FROM allRuns_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<AllRuns>>

    @Query("SELECT * FROM allRuns_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<AllRuns>>



    @Query("SELECT SUM(timeInMillis) FROM allRuns_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM allRuns_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM allRuns_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM allRuns_table")
    fun getTotalAvgSpeed(): LiveData<Float>

}