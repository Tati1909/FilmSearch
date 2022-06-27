package com.example.filmsearch.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.filmsearch.R
import com.example.filmsearch.databinding.FragmentDetailsBinding
import com.example.filmsearch.model.details.DetailsMovie
import com.example.filmsearch.model.topmovies.Movie
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.presentation.loadPicture
import com.example.filmsearch.presentation.main.MainViewModel
import com.example.filmsearch.presentation.showSnackBar

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    //movieBundle мы получим во время создания фрагмента
    private lateinit var movieBundle: Movie
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Movie()
        viewModel.requestLiveData().observe(viewLifecycleOwner) { renderData(it) }
        movieBundle.id?.let { viewModel.loadDetailsMovie(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Success<*> -> {
                binding.progressBar.visibility = View.GONE
                showDetailsMovie(screenState.movieData as DetailsMovie)
            }
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.root.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        movieBundle.id?.let { it1 -> viewModel.loadDetailsMovie(it1) }
                    })
            }
        }
    }

    private fun showDetailsMovie(detailsMovie: DetailsMovie) {
        binding.title.text = detailsMovie.fullTitle
        binding.info.text = detailsMovie.genres
        binding.image.loadPicture(detailsMovie.image)
    }

    companion object {

        //наш ключ-константа,по которому будем находить бандл
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}