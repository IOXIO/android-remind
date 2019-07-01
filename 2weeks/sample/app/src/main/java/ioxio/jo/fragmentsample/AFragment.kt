package ioxio.jo.fragmentsample

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment() {

    var saveBundle: Bundle? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Remind", "AFragment-onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Remind", "AFragment-onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("Remind", "AFragment-onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Remind", "AFragment-onActivityCreated")

        aFragmentOk.setOnClickListener {
            aFragmentSaveText.text = aFragmentEdit.text.toString()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Remind", "AFragment-onViewStateRestored")

        val bundle: Bundle? = saveBundle ?: savedInstanceState?.getBundle("saveBundle")

        bundle?.let {
            aFragmentSaveText.text = it.getString("text")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Remind", "AFragment-onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Remind", "AFragment-onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Remind", "AFragment-onPause")

        saveBundle = Bundle().apply {
            putString("text", aFragmentEdit.text.toString())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Remind", "AFragment-onSaveInstanceState")

        outState.putBundle("saveBundle", saveBundle)
    }

    override fun onStop() {
        super.onStop()
        Log.d("Remind", "AFragment-onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Remind", "AFragment-onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Remind", "AFragment-onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Remind", "AFragment-onDetach")
    }

    fun commitTimingCheck() {
        Log.d("Remind", context!!.getString(R.string.hello_blank_fragment))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                AFragment()
    }
}
