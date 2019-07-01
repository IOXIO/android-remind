package ioxio.jo.fragmentsample.communication

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ioxio.jo.fragmentsample.R
import kotlinx.android.synthetic.main.fragment_first_communication.*

class FirstCommunicationFragment : Fragment() {

    private var count = 0
    private var listener: OnCommunicationListener? = null

    interface OnCommunicationListener {
        fun onButtonClick(count: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCommunicationListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_communication, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button.setOnClickListener {
            listener?.onButtonClick(count++)
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                FirstCommunicationFragment()
    }
}
