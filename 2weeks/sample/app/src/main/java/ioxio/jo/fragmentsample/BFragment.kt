package ioxio.jo.fragmentsample

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_b.*

class BFragment : Fragment() {

    var bundle:Bundle? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Remind", "BFragment-onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Remind", "BFragment-onCreate")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d("Remind", "BFragment-onCreateView")
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Remind", "BFragment-onActivityCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("Remind", "BFragment-onViewStateRestored")

        bFragmentOk.setOnClickListener {
            bFragmentSaveText.text = bFragmentEdit.text.toString()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Remind", "BFragment-onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Remind", "BFragment-onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Remind", "BFragment-onPause")

        bundle?.let {
            bFragmentSaveText.text = it.getString("text")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Remind", "BFragment-onSaveInstanceState")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Remind", "BFragment-onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Remind", "BFragment-onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Remind", "BFragment-onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("Remind", "BFragment-onDetach")
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                BFragment()
    }
}
