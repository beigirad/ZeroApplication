package ir.beigirad.zeroapplication.base

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.network.RequestProvider

/**
 * Created by farhad-mbp on 9/12/17.
 */

abstract class BaseFragment : Fragment() {
    protected var rootView: View? = null

    protected abstract val childView: Int

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(childView, container, false)
        this.rootView = rootView

        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    protected open fun initUI() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
    }

    protected open fun initVariables() {
        progressDialog = ProgressDialog(context)
    }


    protected open fun normalDetach(): Boolean {
        return false
    }

    override fun onDetach() {
        if (!normalDetach()) {
            progressDialog.dismiss()
            rootView = null
            RequestProvider.instance.cancellAll()
        }
        super.onDetach()
    }


    protected open fun showProgress(message: String = getString(R.string.please_wait)) {
        progressDialog.setMessage(message)
        progressDialog.show()
    }

    protected open fun hideProgress() {
        progressDialog.hide()
    }
}