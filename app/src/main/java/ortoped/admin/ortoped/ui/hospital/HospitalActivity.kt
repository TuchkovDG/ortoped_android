package ortoped.admin.ortoped.ui.hospital

import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_hospital.*
import org.jetbrains.anko.startActivity
import ortoped.admin.ortoped.R
import ortoped.admin.ortoped.model.HospitalModel
import ortoped.admin.ortoped.ui.base.BaseActivity
import ortoped.admin.ortoped.ui.hospital.adapter.HospitalListAdapter
import ortoped.admin.ortoped.ui.settings.SettingActivity

class HospitalActivity : BaseActivity() {

    private lateinit var adapter: HospitalListAdapter

    override fun layoutId(): Int {
        return R.layout.activity_hospital
    }

    override fun initViewsAndData() {
        initOnClickListener()
        initListAdapter()
    }

    private fun initOnClickListener() {
        iv_hospital_setting.setOnClickListener {
            startActivity<SettingActivity>()
        }
    }

    private fun initListAdapter() {
        val list: ArrayList<HospitalModel> = ArrayList()
        list.add(HospitalModel("Address1"))
        list.add(HospitalModel("Address2"))
        list.add(HospitalModel("Address3"))
        list.add(HospitalModel("Address4"))
        list.add(HospitalModel("Address5"))
        list.add(HospitalModel("Address6"))
        adapter = HospitalListAdapter(this, list)
        rv_hospital_data.layoutManager = LinearLayoutManager(this)
        rv_hospital_data.adapter = adapter
    }
}