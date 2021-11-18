package com.ciyp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ciyp.App
import com.ciyp.R
import com.ciyp.databinding.FragmentDetailsBinding
import com.ciyp.domain.entites.MovieDetails
import com.ciyp.utils.youtubeVideo.YoutubeLoader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import javax.inject.Inject

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

        binding.collapsingToolbar.title = "ffasfasf"
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))



        viewModel.getVideo(arguments.movie?.id)
        viewModel.videoLiveData.observe(viewLifecycleOwner) {listUrls->
            youtubeLoader.loadVideo(listUrls)
        }

        viewModel.errorVideoLiveData.observe(viewLifecycleOwner) {

        }


        viewModel.getMovieDetail(arguments.movie?.id)

        viewModel.movieLiveData.observe(viewLifecycleOwner) {
            if (it.isInFavorite == true) {
                 binding.add.setImageResource(R.drawable.ic_baseline_favorites_24dp)
            } else {
                 binding.add.setImageResource(R.drawable.ic_home_black_24dp)
            }

        }

        binding.add.setOnClickListener{
            viewModel.changeFavoriteStatus()
        }


    }

    private fun setAllFields(movie: MovieDetails) {
        binding.originalTitleTextView.text = movie.original_title
        binding.releaseDateTextView.text = movie.release_date
        binding.voteAverageTextView.text = movie.vote_average.toString()
        binding.runtimeTextView.text = movie.runtime.toString()
        binding.overviewTextView.text = movie.overview
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}