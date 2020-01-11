package com.elbarody.orcaschallange.ui.fixture

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elbarody.orcaschallange.data.model.TeamItem
import com.elbarody.orcaschallange.databinding.TeamItemBinding
import com.elbarody.orcaschallange.ui.teamDetails.TeamDetailsActivity

class AllTeamsAdapter(val onFavouriteClickListener: OnFavouriteClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val teams = ArrayList<Any>()
    var recyclerView: RecyclerView? = null

    fun addTeams(teams: List<TeamItem>) {
        this.teams.clear()
        val scrollToPosition = 0

        this.teams.addAll(teams)

        notifyDataSetChanged()

        recyclerView?.scrollToPosition(scrollToPosition)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FixtureHolder(
            TeamItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FixtureHolder -> {
                val teams = teams[position] as TeamItem
                holder.bind(teams)
            }
        }
    }

    inner class FixtureHolder(private val mBinding: TeamItemBinding) :
        RecyclerView.ViewHolder(mBinding.root), OnItemClickListener {
        override fun onItemClicked(teamId: Int) = mBinding.root.context.startActivity(
            Intent(
                mBinding.root.context,
                TeamDetailsActivity::class.java
            ).putExtra("teamId", teamId)
        )

        private val viewModel = TeamItemViewModel()
        fun bind(teamsItem: TeamItem) {
            viewModel.bind(teamsItem, this)
            mBinding.viewModel = viewModel

            mBinding.btnFavourite.setOnClickListener {
                mBinding.btnFavourite.onClick(it)
                onFavouriteClickListener.onFavouriteClicked(teamsItem)
            }
        }
    }


}