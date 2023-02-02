package com.example.pdp_academy_task6_2_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp_academy_task6_2_2.databinding.ItemMoviesBinding
import com.example.pdp_academy_task6_2_2.models.MovieData

class MovieAdapter (
    private var list: List<MovieData>,
    val onItemEdit : (MovieData, Int) ->Unit,
    val onItemClick : (Int) ->Unit,
    val onItemDelete : (MovieData, Int) ->Unit
):RecyclerView.Adapter<MovieAdapter.VH>(){

    class VH(val binding : ItemMoviesBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.movieName.text = list[position].movieName
        holder.binding.movieAuthor.text=list[position].movieAuthor
        holder.binding.movieDate.text=list[position].movieDate

        holder.binding.editBtn.setOnClickListener(View.OnClickListener {
            onItemEdit.invoke(list[position], position)
        })
        holder.binding.deleteBtn.setOnClickListener(View.OnClickListener {
            onItemDelete.invoke(list[position], position)
        })
        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClick.invoke(position)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}