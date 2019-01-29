package ir.beigirad.app

import androidx.appcompat.widget.Toolbar
import ir.beigirad.zeroapplication.bases.BaseActivity
import kotlinx.android.synthetic.main.app_bar.*

/**
 * Created by farhad-mbp on 3/18/18.
 */
class MainActivity : BaseActivity() {
    override val contentView: Int
        get() = R.layout.activity_main
    override val toolbar: Toolbar?
        get() = app_toolbar

    override val toolbarTitle: Int?
        get() = R.string.app_title
    override val hasBackConfirmation: Boolean
        get() = true

    override fun initToolbar(hasBack: Boolean) {
        super.initToolbar(hasBack = false)
    }


}