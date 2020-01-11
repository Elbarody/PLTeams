package com.elbarody.orcaschallange.ui.fixture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.elbarody.orcaschallange.data.model.TeamItem
import com.elbarody.orcaschallange.ui.base.BaseViewModel

class TeamItemViewModel : BaseViewModel() {

    private var listener: OnItemClickListener? = null


    private var idTeam : Int = 0

    private val _nameTeam = MutableLiveData<String>()
    val nameTeam: LiveData<String> get() = _nameTeam

    private val _shotNameTeam = MutableLiveData<String>()
    val shotNameTeam: LiveData<String> get() = _shotNameTeam

    private val _colorTeam = MutableLiveData<String>()
    val colorTeam: LiveData<String> get() = _colorTeam

    private val _crestUrl = MutableLiveData<String>()
    val crestUrl: LiveData<String> get() = _crestUrl

    val isFavourite = MutableLiveData(false)

    fun bind(
        teamItem: TeamItem,
        listener: OnItemClickListener
    ) {
        this.listener = listener
        idTeam = teamItem.id!!
        _nameTeam.value = teamItem.name
        _shotNameTeam.value = teamItem.shortName
        _colorTeam.value = teamItem.clubColors
        _crestUrl.value = teamItem.crestUrl
        isFavourite.value = teamItem.isFavourite
    }

    fun onItemClick() {
        listener!!.onItemClicked(teamId =idTeam)
    }
}