package com.helwa.orcaschallange.ui.teamDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.helwa.orcaschallange.ui.base.BaseViewModel
import com.helwa.orcaschallange.data.model.Player

class PlayerItemViewModel : BaseViewModel() {



    private var idPlayer : Int = 0

    private val _namePlayer = MutableLiveData<String>()
    val namePlayer: LiveData<String> get() = _namePlayer

    private val _nationalityPlayer = MutableLiveData<String>()
    val nationalityPlayer: LiveData<String> get() = _nationalityPlayer

    private val _positionPlayer = MutableLiveData<String>()
    val positionPlayer: LiveData<String> get() = _positionPlayer


    val isFavourite = MutableLiveData(false)

    fun bind(
        player: Player
    ) {
        idPlayer = player.id!!
        _namePlayer.value = player.name
        _nationalityPlayer.value = player.nationality
        _positionPlayer.value = player.position
    }

}