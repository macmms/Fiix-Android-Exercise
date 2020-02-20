package fiix.challenge.fiixexercise.kotlinsample.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fiix.challenge.fiixexercise.R

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount()= 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    /*ViewHolder implementation with companion object for easy initialisation*/

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(position: Int) {

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.list_item_home, parent, false)

                return ViewHolder(view)
            }
        }
    }
}