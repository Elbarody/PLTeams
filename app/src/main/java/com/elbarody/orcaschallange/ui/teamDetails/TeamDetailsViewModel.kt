package com.elbarody.orcaschallange.ui.teamDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elbarody.orcaschallange.data.locale.room.doa.TeamsDoa
import com.elbarody.orcaschallange.data.model.TeamEntityItem
import com.elbarody.orcaschallange.data.services.FixtureAPI
import com.elbarody.orcaschallange.ui.base.BaseViewModel
import com.elbarody.orcaschallange.utils.ioThread
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeamDetailsViewModel(
    private val api: FixtureAPI,
    private val teamsDoa: TeamsDoa
) :
    BaseViewModel() {


    private var listener: OnTeamWebsiteClickListener? = null

    private val _nameTeam = MutableLiveData<String>()
    val nameTeam: LiveData<String> get() = _nameTeam

    private val _shotNameTeam = MutableLiveData<String>()
    val shotNameTeam: LiveData<String> get() = _shotNameTeam

    private val _colorTeam = MutableLiveData<String>()
    val colorTeam: LiveData<String> get() = _colorTeam

    private val _crestUrl = MutableLiveData<String>()
    val crestUrl: LiveData<String?> get() = _crestUrl

    private val _phoneTeam = MutableLiveData<String>()
    val phoneTeam: LiveData<String> get() = _phoneTeam

    private val _venueNameTeam = MutableLiveData<String>()
    val venueNameTeam: LiveData<String> get() = _venueNameTeam

    private val _foundTeam = MutableLiveData<String>()
    val foundTeam: LiveData<String> get() = _foundTeam

    private val _addressTeam = MutableLiveData<String>()
    val addressTeam: LiveData<String> get() = _addressTeam

    private val _website = MutableLiveData<String>()
    val website: LiveData<String> get() = _website

    private val _emailTeam = MutableLiveData<String>()
    val emailTeam: LiveData<String> get() = _emailTeam

    var teamId = 0
    val adapter: PlayerAdapter = PlayerAdapter()
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorMassage: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchTeamDetails(listener: OnTeamWebsiteClickListener, id: Int) {
        launch {
            teamId = id
            this.listener = listener
            teamsDoa.getOneTeam(teamId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { loadingVisibility.value = true }
                .doOnSubscribe { loadingVisibility.value = false }
                .subscribe(
                    { onFetchingListSuccess(it as TeamEntityItem) },
                    { fetchTeamDetailsFromApi(teamId) })
        }
    }

    private fun fetchTeamDetailsFromApi(teamId: Int) {
        launch {
            api.getSpicialTeam(teamId = teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { loadingVisibility.value = true }
                .doOnSubscribe { loadingVisibility.value = false }
                .subscribe(
                    {
                        onFetchingListSuccess(it)
                        ioThread { teamsDoa.insertOneTime(it as TeamEntityItem) }
                    },
                    { errorMassage.value = true })
        }

    }

    private fun onFetchingListSuccess(teamDetails: TeamEntityItem) {
        teamDetails.squad?.let { adapter.addTeams(it) }

        _nameTeam.value = teamDetails.name
        _shotNameTeam.value = teamDetails.shortName
        _colorTeam.value = teamDetails.clubColors
        _crestUrl.value = teamDetails.crestUrl
        _phoneTeam.value = teamDetails.phone
        _website.value = teamDetails.website
        _foundTeam.value = teamDetails.founded.toString()
        _venueNameTeam.value = teamDetails.venue
        _addressTeam.value = teamDetails.address
        _emailTeam.value = teamDetails.email
    }

    fun onClickWebsite() {
        listener!!.onClickWebsite(website.value!!)
    }
}