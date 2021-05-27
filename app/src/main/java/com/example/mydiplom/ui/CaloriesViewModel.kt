 package com.example.mydiplom.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.mydiplom.data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CaloriesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CaloriesRepository

    private var _readCalorie : MutableLiveData<Double> = MutableLiveData<Double>()
    var readCalorie: LiveData<Double> = _readCalorie

    private var _readWater: MutableLiveData<Int> = MutableLiveData<Int>()
    var readWater: LiveData<Int> = _readWater

    private var _readPushUps: MutableLiveData<Int> = MutableLiveData<Int>()
    var readPushUps: LiveData<Int> = _readPushUps

    private var _readSquats: MutableLiveData<Int> = MutableLiveData()
    var readSquats: LiveData<Int> = _readSquats

    private var _readPress: MutableLiveData<Int> = MutableLiveData()
    var readPress: LiveData<Int> = _readPress

    var readDayResults: LiveData<List<DayResults>>

    private var _readRun: MutableLiveData<Int> = MutableLiveData<Int>()
    var readRun: LiveData<Int> = _readRun

    init {
        val dao = CaloriesDatabase.getDatabase(application).caloriesDao()
        repository = CaloriesRepository(dao)
        readCalorie = repository.calorie
        readWater = repository.water
        readPushUps = repository.pushUps
        readSquats = repository.squats
        readPress = repository.press
        readRun = repository.readRun
        readDayResults = repository.readDayResults
    }

    fun updateCalories(int : Int) {
        _readCalorie.value = repository.calorie.value
        _readCalorie.value = _readCalorie.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val cal = Calories(0, _readCalorie.value)
            repository.updateCalories(cal)
        }
    }

    fun updateCaloriesToZero() {
        _readCalorie.value = repository.calorie.value
        _readCalorie.value = _readCalorie.value?.minus(_readCalorie.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val cal = Calories(0, _readCalorie.value)
            repository.updateCalories(cal)
        }
    }

    fun updateWater(int: Int) {
        _readWater.value = repository.water.value
        _readWater.value = readWater.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val wat = Water(0, _readWater.value)
            repository.updateWater(wat)
        }
    }

    fun updateWaterToZero() {
        _readWater.value = repository.water.value
        _readWater.value = readWater.value?.minus(_readWater.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val wat = Water(0, _readWater.value)
            repository.updateWater(wat)
        }
    }


    fun updatePushUps(int: Int) {
        _readPushUps.value = repository.pushUps.value
        _readPushUps.value = readPushUps.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val pushUps = PushUps(0, _readPushUps.value)
            repository.updatePushUps(pushUps)
        }
    }

    fun updatePushUpsToZero() {
        _readPushUps.value = repository.pushUps.value
        _readPushUps.value = readPushUps.value?.minus(readPushUps.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val pushUps = PushUps(0, _readPushUps.value)
            repository.updatePushUps(pushUps)
        }
    }

    fun updateSquats(int: Int) {
        _readSquats.value = repository.squats.value
        _readSquats.value = readSquats.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val squats = Squats(0, _readSquats.value)
            repository.updateSquats(squats)
        }
    }

    fun updateSquatsToZero() {
        _readSquats.value = repository.squats.value
        _readSquats.value = readSquats.value?.minus(readSquats.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val squats = Squats(0, _readSquats.value)
            repository.updateSquats(squats)
        }
    }

    fun updatePress(int: Int) {
        _readPress.value = repository.press.value
        _readPress.value = readPress.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val press = Press(0, _readPress.value)
            repository.updatePress(press)
        }
    }

    fun updatePressToZero() {
        _readPress.value = repository.press.value
        _readPress.value = readPress.value?.minus(_readPress.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val press = Press(0, _readPress.value)
            repository.updatePress(press)
        }
    }


    fun updateRun(int: Int) {
        _readRun.value = repository.readRun.value
        _readRun.value = readRun.value?.plus(int)
        viewModelScope.launch(Dispatchers.IO) {
            val run = Run(0, _readRun.value)
            repository.updateRun(run)
        }
    }

    fun updateRunToZero() {
        _readRun.value = repository.readRun.value
        _readRun.value = readRun.value?.minus(_readRun.value!!)
        viewModelScope.launch(Dispatchers.IO) {
            val run = Run(0, _readRun.value)
            repository.updateRun(run)
        }
    }

    fun addDayResults(dayResults: DayResults) {
        viewModelScope.launch(Dispatchers.IO){
            repository.addDayResults(dayResults)
        }
    }

    val runsSortedByDate = repository.getAllRunsSortedByDate()

    fun insertRun(run: AllRuns) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRun(run)
    }

}