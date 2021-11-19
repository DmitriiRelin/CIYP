package com.ciyp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciyp.App
import com.ciyp.databinding.FragmentHomeBinding
import com.ciyp.ui.home.homeRecyclerView.base.CategoryWithListItem
import com.ciyp.ui.home.homeRecyclerView.base.HomeAdapter
import javax.inject.Inject

@Keep
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHomeList()
        viewModel.listsMoviesLiveData.observe(viewLifecycleOwner) { categoryList ->
            binding.errorLiner.visibility = View.GONE
            binding.homeRecycler.visibility = View.VISIBLE
            setAdapter(categoryList)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            binding.errorLiner.visibility = View.VISIBLE
            binding.homeRecycler.visibility = View.GONE
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                isLoading()
            } else
                loadingComplete()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    private fun setAdapter(listCategory: List<CategoryWithListItem>) {
        val adapter = HomeAdapter(listCategory) {
            val action = HomeFragmentDirections.actionNavigationHomeToDetailsFragment(it.id)
            findNavController().navigate(action)
        }
        binding.homeRecycler.layoutManager = LinearLayoutManager(context)
        binding.homeRecycler.adapter = adapter
    }

    private fun isLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.homeRecycler.visibility = View.GONE
        binding.errorLiner.visibility = View.GONE
    }

    private fun loadingComplete() {
        binding.progressBar.visibility = View.GONE
        binding.homeRecycler.visibility = View.VISIBLE
        binding.errorLiner.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}