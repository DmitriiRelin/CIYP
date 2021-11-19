package com.ciyp.ui.genresDetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciyp.App
import com.ciyp.databinding.FragmentGenresDetailBinding
import com.ciyp.domain.entites.Movie
import com.ciyp.ui.genresDetail.genresDetailsRecyclerView.GenresDetailsAdapter
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@Keep
class GenresDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: GenresDetailsVieModelFactory

    val viewModel: GenresDetailsVieModel by viewModels { viewModelFactory }

    private var _binding: FragmentGenresDetailBinding? = null
    private val binding get() = _binding!!

    private val arguments: GenresDetailsFragmentArgs by navArgs()

    private var disposable: Disposable? = null

    private val adapter = GenresDetailsAdapter { movie ->
        val action =
            GenresDetailsFragmentDirections.actionGenresDetailsFragmentToDetailsFragment(movie.id)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGenresDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        disposable = (viewModel.getMovies(arguments.id).subscribe {
            setListToAdapter(it)
        })
    }


    private fun setAdapter() {
        binding.genresDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.genresDetailRecyclerView.adapter = adapter
    }

    private fun setListToAdapter(pagingDate: PagingData<Movie>) {
        adapter.submitData(lifecycle, pagingDate)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}