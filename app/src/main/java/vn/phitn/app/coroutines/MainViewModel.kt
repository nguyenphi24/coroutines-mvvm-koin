package vn.phitn.app.coroutines

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import vn.phitn.app.coroutines.model.Cat
import vn.phitn.app.coroutines.repo.CatRepository
import kotlin.coroutines.CoroutineContext

/*
* Created by phitn on 6/19/2020
*/
class MainViewModel(private val catRepository: CatRepository) : ViewModel(), CoroutineScope {
    private val job = Job()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val catsList = MutableLiveData<List<Cat>>()
    val showError = SingleLiveEvent<String>()

    fun loadCats() {
        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { catRepository.getCatList() }
            showLoading.value = false
            when (result){
                is UseCaseResult.Success -> catsList.value = result.data
                is UseCaseResult.Error -> showError.value = result.exception.message
            }
        }
    }
}