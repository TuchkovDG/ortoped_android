package ortoped.admin.ortoped.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.model.ExerciseModel
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.ui.exercise.ExerciseActivity
import ortoped.admin.ortoped.ui.hospital.HospitalActivity
import ortoped.admin.ortoped.ui.main.adapter.ExerciseListAdapter
import ortoped.admin.ortoped.ui.settings.SettingActivity
import ortoped.admin.ortoped.util.SPUtil

class MainActivity : BaseActivity(), MainContract.View, ExerciseListAdapter.MainListActivityListener {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var adapter: ExerciseListAdapter

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViewsAndData() {
        presenter = MainPresenterImpl(this)
        initOnClickListener()
        val userModel = SPUtil.getUserModel()
        if (userModel != null && userModel.statusOfHeals) {
            srl_exercise_data.visibility = View.GONE
            ll_fatal_heals.visibility = View.VISIBLE
        }
        initSwipeRefreshLayout()
        initListAdapter()
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllExercises()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onClickToLockerItem(position: Int) {
        startActivity(intentFor<ExerciseActivity>("position" to position))
    }

    override fun updateAllExercises(exercises: ArrayList<ExerciseModel>) {
        adapter.updateExercises(exercises)
    }

    override fun setRefreshing(boolean: Boolean) {
        srl_exercise_data.isRefreshing = boolean
    }

    private fun initSwipeRefreshLayout() {
        srl_exercise_data.setOnRefreshListener {
            presenter.getNewData()
        }
    }

    private fun initListAdapter() {
        adapter = ExerciseListAdapter(this, ArrayList(), this)
        rv_exercise_data.layoutManager = LinearLayoutManager(this)
        rv_exercise_data.adapter = adapter
    }

    private fun initOnClickListener() {
        iv_main_setting.setOnClickListener {
            startActivity<SettingActivity>()
        }
        ll_fatal_heals.setOnClickListener {
            startActivity<HospitalActivity>()
        }
    }
}
