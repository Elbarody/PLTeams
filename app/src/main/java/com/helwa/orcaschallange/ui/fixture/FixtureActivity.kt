package com.helwa.orcaschallange.ui.fixture

import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.helwa.orcaschallange.ui.base.BaseActivity
import com.helwa.orcaschallange.R
import com.helwa.orcaschallange.databinding.ActivityFixtureBinding
import org.koin.android.ext.android.inject

class FixtureActivity : BaseActivity<ActivityFixtureBinding>(R.layout.activity_fixture) {

    private val viewModel: FixtureViewModel by inject()
    private var errorSnackbar : Snackbar? = null

    override fun initViews() {
        mBinding.fixtureVM = viewModel
        mBinding.lifecycleOwner = this

        viewModel.errorMassage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError() })

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
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

}
