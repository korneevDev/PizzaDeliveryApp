package github.mik0war.pizzadeliveryapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.pizzadeliveryapp.PizzaDeliveryApp
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.dish.presentation.CategoryRecyclerViewAdapter
import github.mik0war.pizzadeliveryapp.dish.presentation.CategoryTransferDataGetter
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.GetDataListViewModel
import github.mik0war.recycler_list.presentation.ImageLoader
import javax.inject.Inject

class HomeFragment : Fragment() {


    @Inject
    lateinit var dishViewModel: GetDataListViewModel<DishUIModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as PizzaDeliveryApp).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.objectsList)

        list.layoutManager = GridLayoutManager(requireContext(), 3)
        list.adapter = setupAdapter()

        dishViewModel.getDataList()
    }




    private fun setupAdapter(): CategoryRecyclerViewAdapter {
        val adapter = CategoryRecyclerViewAdapter(
            dishViewModel,
            ImageLoader.Base(),
            CategoryTransferDataGetter()
        )


        val onErrorClockListener: () -> Unit = {
            dishViewModel.getDataList()
        }

        adapter.onErrorClickListener = onErrorClockListener

        dishViewModel.observe(this) {
            adapter.update()
        }

        return adapter
    }
}