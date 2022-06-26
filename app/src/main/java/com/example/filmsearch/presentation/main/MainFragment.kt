package com.example.filmsearch.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmsearch.R
import com.example.filmsearch.databinding.FragmentMainBinding
import com.example.filmsearch.model.Movie
import com.example.filmsearch.presentation.ScreenState
import com.example.filmsearch.presentation.details.DetailsFragment
import com.example.filmsearch.presentation.showSnackBar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    //создаем интерфейс(через object) и передаем его в адаптер
    private val adapter by lazy {
        MoviesAdapter(::openDetailsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
        viewModel.requestLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.requestMovies()
    }

    private fun renderData(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Success -> {
                binding.progressBar.visibility = View.GONE
                adapter.setList(screenState.movieData)
            }
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.rootView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.requestMovies()
                    })
            }
        }
    }

    private fun openDetailsFragment(movie: Movie) {

    }

    companion object {

        fun newInstance() = MainFragment()
    }
}