package com.ciyp.ui.genres

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ciyp.App
import com.ciyp.databinding.FragmentGenresBinding
import com.ciyp.datalayer.remote.dto.genres.listOfGenres.Genre
import com.ciyp.ui.genres.genresRecyclerView.GenresAdapter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class GenresFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: GenresViewModelFactory

    private val viewModel: GenresViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!

    private lateinit var genresAdapter: GenresAdapter
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var storageReference: StorageReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        viewModel.getListOfGenres()

        viewModel.listGenresLiveData.observe(viewLifecycleOwner, Observer { listOfGenre ->
            setListToAdapter(listOfGenre)
        })

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
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

    private fun initAdapter() {
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        binding.genresRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.genresRecyclerView.setHasFixedSize(true)
        binding.genresRecyclerView.setItemViewCacheSize(19)

        genresAdapter = GenresAdapter(storageReference) {
            val action =
                GenresFragmentDirections.actionNavigationGenresToGenresDetailsFragment(it.id)
            findNavController().navigate(action)
        }
        binding.genresRecyclerView.adapter = genresAdapter
    }

    private fun isLoading() {
        binding.genresRecyclerView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun loadingComplete() {
        binding.genresRecyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun setListToAdapter(genresList: List<Genre>) {
        genresAdapter.listOfGenres = genresList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}