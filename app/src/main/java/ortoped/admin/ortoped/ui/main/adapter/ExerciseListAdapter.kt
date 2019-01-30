package ortoped.admin.ortoped.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_list_main.view.*
import org.jetbrains.anko.imageResource
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.model.ExerciseModel

class ExerciseListAdapter(private val context: Context,
                          private var exercises: ArrayList<ExerciseModel>,
                          private var listener: MainListActivityListener) : RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_main, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(exercises[position])
        if (position == exercises.size - 1) {
            holder.itemView.view_bottom_line.visibility = View.INVISIBLE
        } else {
            holder.itemView.view_bottom_line.visibility = View.VISIBLE
        }
        holder.itemView.setOnClickListener { listener.onClickToLockerItem(position) }
    }

    override fun getItemCount() = exercises.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var view: View = itemView

        fun bind(exercise: ExerciseModel) {
            view.tv_title.text = exercise.title
            if (exercise.status == 1) {
                view.iv_status.imageResource = R.drawable.ic_check_black_24dp
            } else {
                view.iv_status.imageResource = R.drawable.ic_close_black_24dp
            }
        }
    }

    interface MainListActivityListener {

        fun onClickToLockerItem(position: Int)
    }

    fun updateExercises(exercises: ArrayList<ExerciseModel>) {
        this.exercises = exercises
        notifyDataSetChanged()
    }
}