package ortoped.admin.ortoped.ui.main

import ortoped.admin.ortoped.model.ExerciseModel
import ortoped.admin.ortoped.ui.base.BasePresenter

interface MainContract {

    interface View {

        fun updateAllExercises(exercises: ArrayList<ExerciseModel>)

        fun setRefreshing(boolean: Boolean)
    }

    interface Presenter : BasePresenter {

        fun getAllExercises()

        fun getNewData()
    }
}