package com.elbarody.orcaschallange.ui.fixture

import androidx.lifecycle.MutableLiveData
import com.elbarody.orcaschallange.data.locale.room.doa.TeamsDoa
import com.elbarody.orcaschallange.data.model.TeamItem
import com.elbarody.orcaschallange.data.services.FixtureAPI
import com.elbarody.orcaschallange.ui.base.BaseViewModel
import com.elbarody.orcaschallange.utils.ioThread
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllTeamsViewModel(private val api: FixtureAPI, private val teamsDoa: TeamsDoa) :
        BaseViewModel(), OnFavouriteClickListener {

    val adapter: AllTeamsAdapter = AllTeamsAdapter(this)
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMassage: MutableLiveData<Boolean> = MutableLiveData()
    val isFavouriteChose: MutableLiveData<Boolean> = MutableLiveData()

    init {
        fetchTeams()
    }

    fun fetchTeams() {
        launch {
            Observable.fromCallable { teamsDoa.getAllTeams }
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
                    .subscribe({ onFetchingListSuccess(it) }, { errorMassage.value =true })
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
            Observable.fromCallable { teamsDoa.getAllTeams }
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