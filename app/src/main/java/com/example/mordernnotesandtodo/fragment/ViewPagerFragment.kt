package com.example.mordernnotesandtodo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mordernnotesandtodo.R
import com.example.mordernnotesandtodo.util.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf(
            ListNoteFragment(),
            ToDoListFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        val viewPager = view.viewPager

        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position== 0) {
                    view.notesImage.setImageResource(R.drawable.ic_baseline_sticky_note_2_24)
                    view.toDoImage.setImageResource(R.drawable.ic_outline_library_add_check_24)
                    view.notesImage.setColorFilter(resources.getColor(R.color.titleColor))
                    view.toDoImage.setColorFilter(resources.getColor(R.color.textHintColor))
                } else if(position== 1) {
                    view.notesImage.setImageResource(R.drawable.ic_outline_sticky_note_2_24)
                    view.toDoImage.setImageResource(R.drawable.ic_baseline_library_add_check_24)
                    view.toDoImage.setColorFilter(resources.getColor(R.color.titleColor))
                    view.notesImage.setColorFilter(resources.getColor(R.color.textHintColor))
                }
                super.onPageSelected(position)
            }
        })

        return view
    }
}