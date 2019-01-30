package ortoped.admin.ortoped.ui.main

import ortoped.admin.ortoped.bd.HelperExercise
import ortoped.admin.ortoped.model.ExerciseModel

class MainPresenterImpl(var view: MainContract.View?) : MainContract.Presenter {

    var status: Boolean = false

    override fun getAllExercises() {
        view?.updateAllExercises(HelperExercise().getAllExercises())
    }

    override fun getNewData() {
        val list: ArrayList<ExerciseModel> = ArrayList()
        list.add(ExerciseModel(System.currentTimeMillis().toString(), System.currentTimeMillis().toString(), status))
        status = !status
        HelperExercise().addNewExerciseModels(list)
        getAllExercises()
        view?.setRefreshing(false)
    }

    override fun onDestroy() {
        view = null
    }
}