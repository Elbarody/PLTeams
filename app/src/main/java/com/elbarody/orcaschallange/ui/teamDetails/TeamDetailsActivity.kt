package com.elbarody.orcaschallange.ui.teamDetails

import android.content.Intent
import android.net.Uri
import android.view.View.OnClickListener
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.elbarody.orcaschallange.R
import com.elbarody.orcaschallange.databinding.ActivityTeamDetailsBinding
import com.elbarody.orcaschallange.ui.base.BaseActivity
import org.koin.android.ext.android.inject

class TeamDetailsActivity :
    BaseActivity<ActivityTeamDetailsBinding>(R.layout.activity_team_details),
    OnTeamWebsiteClickListener {

    override fun onClickWebsite(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private val viewModel: TeamDetailsViewModel by inject()
    private var errorSnackbar: Snackbar? = null
    private var teamId: Int = 0

    var errorClickListener = OnClickListener {
        viewModel.fetchTeamDetails(listener = this, id = teamId)
    }

    override fun initViews() {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this

        teamId = intent.getIntExtra("teamId", 0)

        viewModel.errorMassage.observe(this, Observer { errorMessage ->
            if (errorMessage) showError(R.string.cant_load_data) else hideError()
        })

        viewModel.fetchTeamDetails(listener = this, id = teamId)

    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(mBinding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, errorClickListener)
        errorSnackbar?.show()
    }


}
