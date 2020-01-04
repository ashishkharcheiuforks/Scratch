package com.appspell.scratchapplication.features.data

import com.appspell.scratchapplication.features.domain.MarsPropertyDO
import io.reactivex.Observable
import javax.inject.Inject

interface MainRepository {
    fun fetch(): Observable<MarsPropertyDO>
}

class MainRepositoryImpl @Inject constructor(private val api: MarsApi) : MainRepository {

    override fun fetch(): Observable<MarsPropertyDO> =
        api.fetchRealestate()
            .flattenAsObservable { it }
            .map { it.asMarsPropertyDO() }

    private fun MarsPropertyDTO.asMarsPropertyDO() = MarsPropertyDO(
        id = id,
        imgSrc = imgSrc,
        type = type,
        price = price
    )
}