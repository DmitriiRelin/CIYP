package com.ciyp.ui.favorites

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciyp.App
import com.ciyp.databinding.FragmentFavoritesBinding
import com.ciyp.ui.favorites.favoritesRecyclerVIew.FavoriteAdapter
import javax.inject.Inject

@Keep
class FavoritesFragment : Fragment() {

    @Inject
    lateinit var factory: FavoritesViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels { factory }

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesAdapter = FavoriteAdapter(
        onItemClickListenerFavorites = {
            val action = FavoritesFragmentDirections.actionNavigationFavoritesToDetailsFragment(it.id)
            findNavController().navigate(action)
        },
        onItemClickDelete = {
            viewModel.deleteFavoriteMovie(it)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.inputEditText.requestFocus()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllFavoritesList()

        setAdapter()

        viewModel.favoritesListLivedata.observe(viewLifecycleOwner) {
            it?.let { favoriteList ->
                favoritesAdapter.submitList(favoriteList)
                if (favoriteList.isNotEmpty()) {
                    listIsNotEmptyVisibility()
                } else {
                    listIsEmptyVisibility()
                }
            }
        }
        binding.inputEditText.addTextChangedListener {
            viewModel.filter(it.toString())
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    private fun setAdapter() {
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoritesRecyclerView.adapter = favoritesAdapter
    }

    private fun listIsEmptyVisibility() {
        binding.emptyListBackground.visibility = View.VISIBLE
        binding.favoritesRecyclerView.visibility = View.GONE
    }

    private fun listIsNotEmptyVisibility() {
        binding.favoritesRecyclerView.visibility = View.VISIBLE
        binding.emptyListBackground.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}