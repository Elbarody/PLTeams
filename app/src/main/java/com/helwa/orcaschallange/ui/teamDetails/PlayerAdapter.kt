package com.helwa.orcaschallange.ui.teamDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helwa.orcaschallange.data.model.Player
import com.helwa.orcaschallange.databinding.ItemListPlayerBinding

class PlayerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val player = ArrayList<Any>()
    var recyclerView: RecyclerView? = null

    fun addTeams(player: List<Player>) {
        this.player.clear()
        var scrollToPosition = 0

        this.player.addAll(player)

        notifyDataSetChanged()

        recyclerView?.scrollToPosition(scrollToPosition)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerHolder(
            ItemListPlayerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlayerHolder -> {
                val player = player[position] as Player
                holder.bind(player)
            }
        }
    }

    inner class PlayerHolder(private val mBinding: ItemListPlayerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        private val viewModel = PlayerItemViewModel()
        fun bind(player: Player) {
            viewModel.bind(player)
            mBinding.viewModel = viewModel

        }
    }
}