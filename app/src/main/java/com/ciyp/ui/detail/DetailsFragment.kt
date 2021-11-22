package com.ciyp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciyp.App
import com.ciyp.R
import com.ciyp.databinding.FragmentDetailsBinding
import com.ciyp.domain.entites.Cast
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.utils.youtubeVideo.YoutubeLoader
import com.ciyp.utils.youtubeVideo.addOnCloseListener
import com.ciyp.utils.youtubeVideo.pause
import com.ciyp.utils.youtubeVideo.play
import javax.inject.Inject

@Keep
class DetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: DetailsViewModelFactory

    private lateinit var youtubeLoader: YoutubeLoader

    val viewModel: DetailsVieModel by viewModels { viewModelFactory }


    private val arguments: DetailsFragmentArgs by navArgs()

    private var _binding: FragmentDetailsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        youtubeLoader = YoutubeLoader(lifecycle, binding.youtubePlayerView, binding.youtubeView)


        viewModel.getVideo(arguments.moviId)
        viewModel.videoLiveData.observe(viewLifecycleOwner) { listUrls ->
            youtubeLoader.loadVideo(listUrls)
        }

        viewModel.errorVideoLiveData.observe(viewLifecycleOwner) {

        }

        viewModel.getMovieDetail(arguments.moviId)

        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            setAllFields(it)
            stopPlayer()
            setAdapter(it.casts)


            if (it.isInFavorite == true) {
                binding.add.setImageResource(R.drawable.ic_baseline_in_favorites_24)
            } else {
                binding.add.setImageResource(R.drawable.ic_baseline_favorites_24dp)
            }

        }

        binding.add.setOnClickListener {
            viewModel.changeFavoriteStatus()
        }


    }

    private fun setAllFields(movie: MovieDetails) {
        binding.collapsingToolbar.title = movie.original_title
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))

        binding.originalTitleTextView.text = movie.original_title
        binding.releaseDateTextView.text = "Release date:  ${movie.release_date}"
        binding.voteAverageTextView.text = "Vote average: ${movie.vote_average.toString()}"
        if (movie.runtime != null) {
            binding.runtimeTextView.text = "Runtime: ${movie.runtime.toString()}"
        } else {
            binding.runtimeTextView.text = "--"
        }

        binding.overviewTextView.text = movie.overview
    }

    private fun setAdapter(listCasts: List<Cast>) {
        val adapter = DetailAdapter(listCasts)
        binding.detailsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.detailsRecyclerView.adapter = adapter
    }

    private fun stopPlayer() {
        binding.appbar.addOnCloseListener(
            onClose = {
                binding.youtubePlayerView.pause()
            },
            onOpen = {
                binding.youtubePlayerView.play()
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}