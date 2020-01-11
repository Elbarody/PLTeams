package com.elbarody.orcaschallange.ui.fixture

import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.elbarody.orcaschallange.ui.base.BaseActivity
import com.elbarody.orcaschallange.R
import com.elbarody.orcaschallange.databinding.ActivityFixtureBinding
import org.koin.android.ext.android.inject

class AllTeamsActivity : BaseActivity<ActivityFixtureBinding>(R.layout.activity_fixture) {

    private val viewModel: AllTeamsViewModel by inject()
    private var errorSnackbar : Snackbar? = null
    private val errorClickListener = View.OnClickListener { viewModel.fetchTeams() }

    override fun initViews() {
        mBinding.fixtureVM = viewModel
        mBinding.lifecycleOwner = this

        viewModel.errorMassage.observe(this, Observer { errorMessage ->
            if (errorMessage) showError(R.string.cant_load_data) else hideError() })

        viewModel.isFavouriteChose.observe(this, Observer {
            if (it) {
                viewModel.getFavourites()
            } else {
                viewModel.getAllTeams()
            }
        })
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
