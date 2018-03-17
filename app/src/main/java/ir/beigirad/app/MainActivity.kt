package ir.beigirad.app

import ir.beigirad.zeroapplication.bases.BaseActivity
import ir.beigirad.zeroapplication.widget.CenterToolbar
import kotlinx.android.synthetic.main.app_bar.*

/**
 * Created by farhad-mbp on 3/18/18.
 */
class MainActivity : BaseActivity() {
    override val contentView: Int
        get() = R.layout.activity_main
    override val toolbarTitle: CharSequence?
        get() = getString(R.string.app_title)
    override val toolbar: CenterToolbar
        get() = app_toolbar

}