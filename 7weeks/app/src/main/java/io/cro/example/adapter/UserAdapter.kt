package io.cro.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.cro.example.R
import io.cro.example.UserProfile
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private var dataSet = mutableListOf<UserProfile>()

    fun updateItem(dataSet: List<UserProfile>) {
        this.dataSet = dataSet as MutableList<UserProfile>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}

class UserViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bindTo(data: UserProfile) {
        itemView.nameTextView.text = data.login
    }
}