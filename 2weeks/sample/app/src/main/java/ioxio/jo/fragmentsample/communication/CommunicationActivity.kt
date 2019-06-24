package ioxio.jo.fragmentsample.communication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ioxio.jo.fragmentsample.R

class CommunicationActivity : AppCompatActivity(), FirstCommunicationFragment.OnCommunicationListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communication)

        supportFragmentManager.beginTransaction()
                .replace(R.id.firstContainer, FirstCommunicationFragment.newInstance())
                .commit()

        supportFragmentManager.beginTransaction()
                .replace(R.id.secondContainer, SecondCommunicationFragment.newInstance())
                .commit()

    }

    override fun onButtonClick(count: Int) {
        val fragment = supportFragmentManager.findFragmentById(R.id.secondContainer) as? SecondCommunicationFragment
        if(fragment != null) {
            fragment.editCount(count)
        }
    }
}
