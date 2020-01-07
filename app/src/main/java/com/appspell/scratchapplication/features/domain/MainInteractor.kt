package com.appspell.scratchapplication.features.domain

import com.appspell.scratchapplication.features.data.MainRepository
import com.appspell.scratchapplication.features.presentation.MarsProperty
import io.reactivex.Observable
import javax.inject.Inject

interface MainInteractor {

    fun fetch(): Observable<MutableList<MarsProperty>>
}

class MainInteractorImpl @Inject constructor(private val repository: MainRepository) :
    MainInteractor {

    override fun fetch() = repository.fetch()
        .map { it.asMarsProperty() }
        .toList()
        .toObservable()

    private fun MarsPropertyDO.asMarsProperty() = MarsProperty(
        id = id.hashCode(),
        image = imgSrc,
        price = "${price.toLong()}$"
    )
}