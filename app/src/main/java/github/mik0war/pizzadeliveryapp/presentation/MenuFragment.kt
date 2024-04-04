package github.mik0war.pizzadeliveryapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.ImageLoader
import github.mik0war.pizzadeliveryapp.databinding.FragmentMenuBinding
import github.mik0war.pizzadeliveryapp.dish.presentation.DishListAdapter
import github.mik0war.pizzadeliveryapp.dish.presentation.DishUIMapper
import github.mik0war.pizzadeliveryapp.dish.presentation.DishViewModel
import github.mik0war.pizzadeliveryapp.feature.advertising.presentation.AdvertisingRecyclerViewAdapter


@AndroidEntryPoint
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val dishViewModel: DishViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMenuBinding.inflate(inflater).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val onSuccessClick: (uiMapper: DishUIMapper) -> Unit = {
            dishViewModel.showExtendedData()
        }

        val dishListAdapter = DishListAdapter(
            dishViewModel,
            ImageLoader.Base(),
            onSuccessClick
        )
        dishViewModel.observe(this) {
            dishListAdapter.update()
        }
        binding.objectsList.layoutManager = LinearLayoutManager(requireContext())
        binding.objectsList.adapter = dishListAdapter
        dishViewModel.showData()


        binding.advertisingList.adapter = AdvertisingRecyclerViewAdapter(
            listOf(
                getDrawable(requireContext(), R.drawable.promo_1)!!,
                getDrawable(requireContext(), R.drawable.promo_2)!!,
                getDrawable(requireContext(), R.drawable.promo_1)!!,
                getDrawable(requireContext(), R.drawable.promo_2)!!,
            )
        )

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}