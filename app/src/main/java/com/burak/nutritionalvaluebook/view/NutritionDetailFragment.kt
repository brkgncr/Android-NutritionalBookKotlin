package com.burak.nutritionalvaluebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.burak.nutritionalvaluebook.databinding.FragmentNutritionDetailBinding
import com.burak.nutritionalvaluebook.util.downloadImage
import com.burak.nutritionalvaluebook.util.makePlaceHolder
import com.burak.nutritionalvaluebook.viewmodel.NutritionDetailViewModel

class NutritionDetailFragment : Fragment() {

    private var _binding: FragmentNutritionDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NutritionDetailViewModel
    var nutritionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutritionDetailViewModel::class.java]

        arguments?.let {
            nutritionId = NutritionDetailFragmentArgs.fromBundle(it).nutritionId
        }

        viewModel.roomDataUse(nutritionId)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.nutritionLiveDava.observe(viewLifecycleOwner) {
            binding.nutritionName.text = it.nutritionName
            binding.nutritionCalories.text = it.nutritionCalorie
            binding.nutritionProtein.text = it.nutritionProtein
            binding.nutritionCarbohydrates.text = it.nutritionCarbohydrate
            binding.nutritionFat.text = it.nutritionFat
            binding.nutrientImage.downloadImage(it.nutritionImage, makePlaceHolder(requireContext()) )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}