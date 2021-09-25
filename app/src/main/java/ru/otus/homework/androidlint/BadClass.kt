package ru.otus.homework.androidlint

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BadClass {

    fun case1() {
        GlobalScope.launch {
            delay(1000)
            print("Hello world!!!")
        }
    }
}

class BadViewModel : ViewModel() {

    fun case1() {
        viewModelScope.launch(Dispatchers.Main + SupervisorJob()) {
            delay(1000)
            print("Hello world!!!")
        }
    }

    fun case2() {
        viewModelScope.launch {
            val result = GlobalScope.async {
                delay(1000)
            }
            print(result.await())
        }
    }
}

class Fragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            delay(1000)
            print("Hello world!!!")
        }
    }
}