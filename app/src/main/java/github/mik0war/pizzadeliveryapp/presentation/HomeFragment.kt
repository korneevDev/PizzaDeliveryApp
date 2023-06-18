package github.mik0war.pizzadeliveryapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.entity.ColorResourceProvider
import github.mik0war.pizzadeliveryapp.PizzaDeliveryApp
import github.mik0war.pizzadeliveryapp.R
import github.mik0war.pizzadeliveryapp.feature.dish.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.CategoryRecyclerViewAdapter
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.DishTransferDataGetter
import github.mik0war.pizzadeliveryapp.feature.dish.presentation.PriceFormatter
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagsRecyclerViewAdapter
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagsViewModel
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.GetDataListViewModel
import github.mik0war.recycler_list.presentation.ImageLoader
import javax.inject.Inject


class HomeFragment : Fragment() {


    @Inject
    lateinit var dishViewModel: GetDataListViewModel<DishUIModel>

    @Inject
    lateinit var tagViewModel: TagsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as PizzaDeliveryApp).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)

        val list = view.findViewById<RecyclerView>(R.id.objectsList)

        list.adapter = setupAdapter()

        dishViewModel.getDataList()
        
        val tagsList = view.findViewById<RecyclerView>(R.id.tagsListView)
        
        tagsList.adapter = setupTagsAdapter(tagViewModel)

        tagViewModel.setTagsList()
    }

    private fun setupTagsAdapter(tagsViewModel: TagsViewModel): TagsRecyclerViewAdapter {
        val onChangeTagClickListener: (tag: Tag) -> Unit = { tag ->
            tagsViewModel.updateTag(tag)
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.tag_toast, tag.getTagName()),
                Toast.LENGTH_SHORT
            ).show()
        }

        val adapter = TagsRecyclerViewAdapter(
            tagsViewModel,
            ColorResourceProvider.Base(requireContext()),
            onChangeTagClickListener
        )

        tagsViewModel.observe(this){
            adapter.update()
        }

        return adapter
    }

    private fun setupAdapter(): CategoryRecyclerViewAdapter {
        val adapter = CategoryRecyclerViewAdapter(
            dishViewModel,
            ImageLoader.Base(),
            DishTransferDataGetter(),
            PriceFormatter.Base(requireContext())
        )

        val onSuccessListener: (name: Int) -> Unit = {
            Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.price_toast, it),
                Toast.LENGTH_SHORT
            ).show()
        }


        val onErrorClockListener: () -> Unit = {
            dishViewModel.getDataList()
        }

        adapter.onErrorClickListener = onErrorClockListener
        adapter.onSuccessClickListener = onSuccessListener

        dishViewModel.observe(this) {
            adapter.update()
        }

        return adapter
    }
}