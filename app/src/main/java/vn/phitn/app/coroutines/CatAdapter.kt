package vn.phitn.app.coroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cat.view.*
import vn.phitn.app.coroutines.model.Cat
import kotlin.properties.Delegates

/*
* Created by phitn on 6/19/2020
*/
class CatAdapter : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    // Our data list is going to be notified when we assign a new list of data to it
    private var catList: List<Cat> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        // Verify if position exists in list
        if (position != RecyclerView.NO_POSITION) {
            val cat: Cat = catList[position]
            holder.bind(cat)
        }
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    // Update recyclerView's data
    fun updateData(newCatsList: List<Cat>) {
        catList = newCatsList
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Cat) {
            Glide.with(itemView.context)
                .load(cat.imageUrl)
                .centerCrop()
                .into(itemView.itemCatImageView)
        }
    }
}