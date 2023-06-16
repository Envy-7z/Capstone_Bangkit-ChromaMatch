package com.bangkit.capstonebangkit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bangkit.capstonebangkit.databinding.FragmentHistoryBinding
import com.bangkit.capstonebangkit.models.ColorPicker
import com.bangkit.capstonebangkit.models.HistoryResponse
import com.bangkit.capstonebangkit.ui.adpater.ColorPickerAdapter
import com.bangkit.capstonebangkit.ui.adpater.HistoryAdapter

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    private var dataHistory = arrayListOf<HistoryResponse>()

    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter()

        dataHistory.add(HistoryResponse(1, "Testing 1","16 June 2023","https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTMNinR729w6McoRYl05URrRpwIU0qAQ5JW5TGP6zHUiK5jfgkY"))
        dataHistory.add(HistoryResponse(2, "Testing 2","16 June 2023","https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQnEfnPxmjvvFPhw1D_utOtARq7jfD3j_RzHvyPA-Q55eSz2AtK"))

        historyAdapter.setData(dataHistory)

        binding.run {


            with(rvHistory) {
                setHasFixedSize(true)
                rvHistory.adapter = historyAdapter
            }
        }

    }


}