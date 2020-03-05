package com.example.fastfooddiet


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fastfooddiet.databinding.FragmentAdvancedSearchBinding

class AdvancedSearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAdvancedSearchBinding.inflate(inflater,container,false)
        return binding.root
    }


}
