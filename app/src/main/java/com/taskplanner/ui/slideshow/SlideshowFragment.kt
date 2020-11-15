package com.taskplanner.ui.slideshow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.taskplanner.LaunchActivity
import com.taskplanner.LoginActivity
import com.taskplanner.MainActivity
import com.taskplanner.R

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var act:Activity = this.context as Activity;
        var intent = Intent(act,LaunchActivity::class.java)
        var shared = context?.getSharedPreferences( getString( R.string.preference_file_key ), Context.MODE_PRIVATE )
        var editor = shared?.edit()
        editor?.clear()
        editor?.commit()
        act.startActivity(intent)
        act.finish()
        slideshowViewModel =
                ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}