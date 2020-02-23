package com.kazymir.tripweaver.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kazymir.tripweaver.R

class TripFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip, container, false)
//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}