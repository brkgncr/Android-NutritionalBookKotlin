package com.burak.nutritionalvaluebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.burak.nutritionalvaluebook.adapter.NutritionRecyclerAdapter
import com.burak.nutritionalvaluebook.databinding.FragmentNutritionListBinding
import com.burak.nutritionalvaluebook.viewmodel.NutritionListViewModel

class NutritionListFragment : Fragment() {

    private var _binding: FragmentNutritionListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NutritionListViewModel
    private  val nutritionRecyclerAdapter = NutritionRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutritionListViewModel::class.java]
        viewModel.refreshData()

        binding.nutritionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.nutritionRecyclerView.adapter = nutritionRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.nutritionRecyclerView.visibility = View.GONE
            binding.nutritionErrorMessage.visibility = View.GONE
            binding.nutritionLoading.visibility = View.VISIBLE
            viewModel.refreshFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.nutritions.observe(viewLifecycleOwner) {
            binding.nutritionRecyclerView.visibility = View.VISIBLE
            nutritionRecyclerAdapter.nutritionUpdateList(it)
        }

        viewModel.nutritionErrorMessage.observe(viewLifecycleOwner) {
            if (it) {
                binding.nutritionErrorMessage.visibility = View.VISIBLE
                binding.nutritionRecyclerView.visibility = View.GONE
            } else {
                binding.nutritionErrorMessage.visibility = View.GONE
            }
        }

        viewModel.nutritionLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.nutritionErrorMessage.visibility = View.GONE
                binding.nutritionRecyclerView.visibility = View.GONE
                binding.nutritionLoading.visibility = View.VISIBLE
            } else {
                binding.nutritionLoading.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}