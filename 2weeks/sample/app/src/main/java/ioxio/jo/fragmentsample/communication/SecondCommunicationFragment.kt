package ioxio.jo.fragmentsample.communication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ioxio.jo.fragmentsample.R
import kotlinx.android.synthetic.main.fragment_second_communication.*

class SecondCommunicationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_communication, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun editCount(count: Int) {
        text.text = "$count"
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            SecondCommunicationFragment()
    }
}
