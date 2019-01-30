package ortoped.admin.ortoped.ui.exercise

import android.graphics.Color
import kotlinx.android.synthetic.main.activity_exercise.*
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.bd.HelperExercise
import ortoped.admin.ortoped.model.ExerciseModel
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.ui.settings.SettingActivity

class ExerciseActivity : BaseActivity() {

    private var listExerciseModel: ArrayList<ExerciseModel> = ArrayList()
    private var currentExerciseModel: ExerciseModel? = null

    private var position: Int = 0

    override fun layoutId(): Int {
        return R.layout.activity_exercise
    }

    override fun initViewsAndData() {
        listExerciseModel = HelperExercise().getAllExercises()
        position = intent.getIntExtra("position", 0)
        currentExerciseModel = listExerciseModel[position]
        initOnClickListener()
        setDataInViews()
    }

    private fun initOnClickListener() {
        iv_exercise_setting.setOnClickListener {
            startActivity<SettingActivity>()
        }
        iv_previous.setOnClickListener {
            if (position != 0) {
                position--
                currentExerciseModel = listExerciseModel[position]
                setDataInViews()
            }
        }
        title_done.setOnClickListener {
            if (currentExerciseModel != null) {
                if (currentExerciseModel?.status == 0) {
                    title_done.setTextColor(Color.parseColor("#bebebe"))
                    currentExerciseModel!!.status = 1
                    HelperExercise().addOrUpdateExerciseModel(currentExerciseModel)
                }
            }
        }
        iv_next.setOnClickListener {
            if (position != listExerciseModel.size - 1) {
                position++
                currentExerciseModel = listExerciseModel[position]
                setDataInViews()
            }
        }
    }

    private fun setDataInViews() {
        tv_title.text = currentExerciseModel?.title
        tv_description.text = currentExerciseModel?.description
        if (currentExerciseModel != null) {
            if (currentExerciseModel!!.status == 1) {
                title_done.setTextColor(Color.parseColor("#bebebe"))
            } else {
                title_done.setTextColor(Color.parseColor("#ff000000"))
            }
        }
    }
}