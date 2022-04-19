package id.smartech.myquran.ui

import android.os.Bundle
import id.smartech.myquran.R
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityOnboardBinding
import id.smartech.myquran.ui.main.MainActivity

class OnboardActivity : BaseActivity<ActivityOnboardBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_onboard
        super.onCreate(savedInstanceState)

        bind.btnStart.setOnClickListener {
            intents<MainActivity>(this)
            this.finish()
        }
    }
}