package com.appspell.scratchapplication.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.appspell.scratchapplication.features.domain.MainInteractor
import com.appspell.scratchapplication.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class MainViewModel : ViewModel() {
    abstract val items: LiveData<List<MarsProperty>>
    abstract val error: LiveData<String>
    abstract val loading: LiveData<Boolean>
}

class MainViewModelImpl(private val interactor: MainInteractor) : MainViewModel() {

    override val items = SingleLiveEvent<List<MarsProperty>>()
    override val error = SingleLiveEvent<String>()
    override val loading = SingleLiveEvent<Boolean>()

    private val disposable = CompositeDisposable()

    init {
        fetch()
    }

    private fun fetch() {
        interactor.fetch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                loading.value = true
            }
            .doFinally {
                loading.value = false
            }
            .subscribe(
                {
                    items.value = it
                    error.value = null
                },
                {
                    error.value = it.message
                }
            )
            .apply { disposable.add(this) }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
