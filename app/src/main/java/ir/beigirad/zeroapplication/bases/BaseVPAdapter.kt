package ir.beigirad.zeroapplication.bases

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BaseVPAdapter<T : Fragment>(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private var pages: MutableList<T> = mutableListOf()
    private var titles: MutableList<String> = mutableListOf()

    fun addPage(title: String, page: T) {
        pages.add(page)
        titles.add(title)
    }

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}