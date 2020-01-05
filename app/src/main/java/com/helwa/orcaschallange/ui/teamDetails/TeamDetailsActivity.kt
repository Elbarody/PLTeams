package com.helwa.orcaschallange.ui.teamDetails

import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.helwa.orcaschallange.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import com.helwa.orcaschallange.R
import com.helwa.orcaschallange.databinding.ActivityTeamDetailsBinding
import org.koin.android.ext.android.inject

class TeamDetailsActivity :
    BaseActivity<ActivityTeamDetailsBinding>(R.layout.activity_team_details) {

    private val viewModel: TeamDetailsViewModel by inject()
    private var errorSnackbar: Snackbar? = null


    override fun initViews() {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this

        viewModel.errorMassage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.fetchTeamDetails(intent.getIntExtra("teamId",0))

    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(mBinding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }


}
