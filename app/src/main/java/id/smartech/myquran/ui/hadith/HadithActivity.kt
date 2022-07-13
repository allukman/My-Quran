package id.smartech.myquran.ui.hadith

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.smartech.myquran.R
import id.smartech.myquran.base.BaseActivity
import id.smartech.myquran.databinding.ActivityHadithBinding

class HadithActivity : BaseActivity<ActivityHadithBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setLayout = R.layout.activity_hadith
        super.onCreate(savedInstanceState)
    }
}