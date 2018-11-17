package ir.beigirad.zeroapplication.bases

//
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.network.RequestProvider
import ir.beigirad.zeroapplication.util.Utils

/**
 * Created by farhad-mbp on 9/12/17.
 */

abstract class BaseDialogFragment : DialogFragment() {
    protected var rootView: View? = null

    protected abstract val childView: Int
    protected abstract val toolbarTitle: CharSequence
    protected abstract val toolbar: Toolbar

    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(childView, container, false)
        this.rootView = rootView

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initUI()

    }

    protected open fun initUI() {}

    protected open fun initToolbar() {
        this.toolbar.title = toolbarTitle
        when {
            Utils.isTablet -> this.toolbar.navigationIcon = Utils.getDrawable(context, R.drawable.item_black_cross_icon)
            beFullScreen() -> this.toolbar.navigationIcon = Utils.getDrawable(context, R.drawable.item_back_button)
            else -> this.toolbar.navigationIcon = Utils.getDrawable(context, R.drawable.item_black_cross_icon)
        }


        this.toolbar.setNavigationOnClickListener { dismiss() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (beFullScreen() && !Utils.isTablet)
            setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme_NoActionBar)
        else
            setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_NoActionBar_BaseDialog)


        initVariables()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog.window.setBackgroundDrawable(resources.getDrawable(R.drawable.dialog_background))

        return dialog
    }

    protected open fun beFullScreen(): Boolean {
        return true
    }

    protected open fun normalDetach(): Boolean {
        return false
    }

    protected open fun initVariables() {
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage(getString(R.string.please_wait))
    }


    protected fun requireBaseToolbar(): Boolean {
        return true
    }


    override fun dismiss() {
        super.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onDetach() {
        if (!normalDetach()) {
            progressDialog.dismiss()
            rootView = null
            RequestProvider.instance.cancellAll()
        }
        super.onDetach()
    }


    protected open fun showProgress() {
        progressDialog.show()
    }

    protected open fun hideProgress() {
        progressDialog.hide()
    }
}