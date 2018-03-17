package ir.beigirad.zeroapplication.bases

import android.app.ProgressDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ir.beigirad.app.R
import ir.beigirad.zeroapplication.util.SharedPrefUtils
import ir.beigirad.zeroapplication.widget.CenterToolbar
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


/**
 * Created by farhad-mbp on 7/31/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var parentView: View

    private lateinit var progressDialog: ProgressDialog

    protected lateinit var sharedPref: SharedPrefUtils


    private val menu: Menu? = null


    protected abstract val contentView: Int

    protected abstract val toolbarTitle: CharSequence?

    protected abstract val toolbar: CenterToolbar?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        initVariables()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && isRtl) {
            window.decorView.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }

        initToolbar()
        initUI()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    private var isRtl: Boolean = false


    protected open fun initVariables() {
        parentView = findViewById(android.R.id.content)
        sharedPref = SharedPrefUtils()

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))

    }


    protected open fun initUI() {}


    protected open fun initToolbar(hasBack: Boolean = true) {
        if (toolbar == null)
            return
        toolbar?.title = toolbarTitle
        setSupportActionBar(toolbar)

        if (hasBack) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //        getMenuInflater().inflateActionView(R.menu.cart, menu);

        //        this.menu = menu;
        //        updateCartNumber();

        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        progressDialog.dismiss()
        super.onDestroy()
    }

    protected open fun showProgress() {
        progressDialog.show()
    }

    protected open fun hideProgress() {
        progressDialog.hide()
    }

}

