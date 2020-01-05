package com.helwa.orcaschallange.ui.fixture

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.helwa.orcaschallange.data.locale.room.doa.TeamsDoa
import com.helwa.orcaschallange.data.model.TeamItem
import com.helwa.orcaschallange.data.services.FixtureAPI
import com.helwa.orcaschallange.ui.base.BaseViewModel
import com.helwa.orcaschallange.utils.ioThread
import com.helwa.orcaschallange.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FixtureViewModel(private val api: FixtureAPI, private val teamsDoa: TeamsDoa) :
        BaseViewModel(), OnFavouriteClickListener {

    val adapter: FixtureAdapter = FixtureAdapter(this)
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMassage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { fetchTeams() }
    val isFavouriteChose: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchTeams()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun fetchTeams() {
        launch {
            Observable.fromCallable { teamsDoa.getAllTeams(limit = 6,offset = 0) }
                    .concatMap { dbMatchesList ->
                        if (dbMatchesList.isEmpty()) {
                            api.getTeams().concatMap { apiMatchesList ->
                                teamsDoa.insertTeams(*apiMatchesList.teams.toTypedArray())
                                Observable.just(apiMatchesList.teams)
                            }
                        } else {
                            Observable.just(dbMatchesList)
                        }
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally { loadingVisibility.value = true }
                    .doOnSubscribe { loadingVisibility.value = false }
                    .subscribe({ onFetchingListSuccess(it) }, { errorMassage.value = R.string.cant_load_data })
        }
    }

    override fun onFavouriteClicked(teamItem: TeamItem) {
        teamItem.isFavourite = teamItem.isFavourite.not()
        ioThread { teamsDoa.insertTeams(teamItem) }
    }

    fun getFavourites() {
        launch {
            Observable.fromCallable { teamsDoa.getFavouriteTeams }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        adapter.addTeams(it)
                    }
        }
    }

    fun getAllTeams() {
        launch {
            Observable.fromCallable { teamsDoa.getAllTeams(limit = 6,offset = 0) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        adapter.addTeams(it)
                    }
        }
    }

    private fun onFetchingListSuccess(teams: List<TeamItem>) {
        adapter.addTeams(teams)
    }
}