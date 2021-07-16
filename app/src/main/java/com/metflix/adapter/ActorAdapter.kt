package com.metflix.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.metflix.R
import com.metflix.domain.entity.Actor
import com.metflix.databinding.ItemActorBinding

class ActorAdapter : ListAdapter<Actor, ActorAdapter.ViewHolder>(ProductDiffCallback) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemActorBinding>(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor: Actor = getItem(position)
        holder.binding?.let {
            it.actor = actor
        }
    }
    private object ProductDiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
            return oldItem == newItem
        }
    }
}

