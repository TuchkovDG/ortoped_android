package ortoped.admin.ortoped.ui.hospital.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_hospital.view.*
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.model.HospitalModel

class HospitalListAdapter(private val context: Context,
                          private var hospitals: ArrayList<HospitalModel>) : RecyclerView.Adapter<HospitalListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_hospital, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(hospitals[position])
        if (position == hospitals.size - 1) {
            holder.itemView.view_bottom_line.visibility = View.INVISIBLE
        } else {
            holder.itemView.view_bottom_line.visibility = View.VISIBLE
        }
    }

    override fun getItemCount() = hospitals.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bind(hospital: HospitalModel) {
            view.tv_title.text = hospital.address
        }
    }
}