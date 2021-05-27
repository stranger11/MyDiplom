package com.example.mydiplom.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CaloriesRepository(private val dao: CaloriesDao) {

    val calorie: LiveData<Double> = dao.readCalorie()

    val water: LiveData<Int> = dao.readWater()

    val pushUps: LiveData<Int> = dao.readPushUp()

    val squats: LiveData<Int> = dao.readSquats()

    val press: LiveData<Int> = dao.readPress()

    val readDayResults: LiveData<List<DayResults>> = dao.readDayResults()

    val readRun: LiveData<Int> = dao.readRun()


    fun updateCalories(calories: Calories) {
        dao.updateCalories(calories)
    }

    fun updateWater(water: Water) {
        dao.updateWater(water)
    }

    fun updatePushUps(pushUps: PushUps) {
        dao.updatePushUps(pushUps)
    }

    fun updateSquats(squats: Squats) {
        dao.updateSquats(squats)
    }

    fun updatePress(press: Press) {
        dao.updatePress(press)
    }

    fun updateRun(run: Run) {
        dao.updateRun(run)
    }

    fun addDayResults(dayResults: DayResults) {
        dao.addDayResults(dayResults)
    }

    suspend fun insertRun(run: AllRuns) = dao.insertRun(run)

    suspend fun deleteRun(run: AllRuns) = dao.insertRun(run)

    fun getAllRunsSortedByDate() = dao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = dao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByTimeInMillis() = dao.getAllRunsSortedByTimeInMillis()

    fun getAllRunsSortedByAvgSpeed() = dao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByCaloriesBurned() = dao.getAllRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = dao.getTotalAvgSpeed()

    fun getTotalDistance() = dao.getTotalDistance()

    fun getTotalCaloriesBurned() = dao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = dao.getTotalTimeInMillis()
}