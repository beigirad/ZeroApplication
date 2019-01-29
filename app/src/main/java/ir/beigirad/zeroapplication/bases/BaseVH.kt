package ir.beigirad.zeroapplication.bases

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind()
}