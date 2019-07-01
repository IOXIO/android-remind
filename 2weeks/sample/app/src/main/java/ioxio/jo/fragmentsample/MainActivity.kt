package ioxio.jo.fragmentsample

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ioxio.jo.fragmentsample.communication.CommunicationActivity
import kotlinx.android.synthetic.main.activity_main.*

const val A_FRAGMENT_TAG = "A_FRAGMENT"
const val B_FRAGMENT_TAG = "B_FRAGMENT"
const val POP_FRAGMENT_TAG = "POP_FRAGMENT"

class MainActivity : AppCompatActivity() {

    private val aFragment by lazy {
        AFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, aFragment, A_FRAGMENT_TAG)
                    .commit()
//                    .commitNow()
//            aFragment.commitTimingCheck()
        }

        button1.setOnClickListener {

            /**
             * case 1. bagic
             */
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, AFragment.newInstance(), A_FRAGMENT_TAG)
//                    .commit()

            /**
             * case 2. addToBackStack
             */
//            val af = supportFragmentManager.findFragmentById(R.id.container) as? AFragment
//            if(af == null) {
//                supportFragmentManager.run {
//                    popBackStack(POP_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                    beginTransaction()
//                            .replace(R.id.container, AFragment.newInstance(), A_FRAGMENT_TAG)
//                            .commit()
//                }
//            }

            /**
             * case 3. add/show/hide
             */
//            val af = supportFragmentManager.findFragmentByTag(A_FRAGMENT_TAG)
//            val bf = supportFragmentManager.findFragmentByTag(B_FRAGMENT_TAG)
//
//            if (af == null) {
//                supportFragmentManager.beginTransaction()
//                        .add(R.id.container, AFragment.newInstance(), A_FRAGMENT_TAG)
//                        .commit()
//            } else {
//                supportFragmentManager.beginTransaction().show(af)
//                        .commit()
//            }
//
//            if (bf != null) {
//                supportFragmentManager.beginTransaction().hide(bf)
//                        .commit()
//            }
        }

        button2.setOnClickListener {

            /**
             * bagic
             */
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, BFragment.newInstance(), B_FRAGMENT_TAG)
//                    .commit()

            /**
             * addToBackStack
             */
//            val bf = supportFragmentManager.findFragmentById(R.id.container) as? BFragment
//            if(bf == null) {
//                supportFragmentManager.run {
//                    popBackStack(POP_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                    beginTransaction()
//                            .replace(R.id.container, BFragment.newInstance(), B_FRAGMENT_TAG)
//                            .addToBackStack(POP_FRAGMENT_TAG)
//                            .commit()
//                }
//            }

            /**
             * add/show/hide
             */
//            val af = supportFragmentManager.findFragmentByTag(A_FRAGMENT_TAG)
//            val bf = supportFragmentManager.findFragmentByTag(B_FRAGMENT_TAG)
//
//            if (bf == null) {
//                supportFragmentManager.beginTransaction()
//                        .add(R.id.container, BFragment.newInstance(), B_FRAGMENT_TAG)
//                        .commit()
//            } else {
//                supportFragmentManager.beginTransaction().show(bf)
//                        .commit()
//            }
//
//            if (af != null) {
//                supportFragmentManager.beginTransaction().hide(af)
//                        .commit()
//            }
        }

        communication.setOnClickListener {
            val intent = Intent(this@MainActivity, CommunicationActivity::class.java)
            startActivity(intent)
        }
    }
}
