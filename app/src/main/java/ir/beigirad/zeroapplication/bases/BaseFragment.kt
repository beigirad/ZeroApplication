package ir.beigirad.zeroapplication.bases

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.beigirad.app.R

/**
 * Created by farhad-mbp on 9/12/17.
 */

abstract class BaseFragment : Fragment() {

    protected abstract val childView: Int

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(childView, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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


    override fun onDetach() {
        progressDialog.dismiss()
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