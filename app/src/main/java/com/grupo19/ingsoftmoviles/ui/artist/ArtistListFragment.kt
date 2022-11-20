package com.grupo19.ingsoftmoviles.ui.artist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityArtistsListBinding
import com.grupo19.ingsoftmoviles.databinding.FragmentAlbumListBinding
import com.grupo19.ingsoftmoviles.databinding.FragmentArtistListBinding
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter
import com.grupo19.ingsoftmoviles.viewmodel.ArtistViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArtistListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArtistListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentArtistListBinding? = null
    private val artistViewModel by viewModels<ArtistViewModel>()
    private val binding get() = _binding!!
    private var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        artistViewModel.artists.observe(this){
                artists -> binding.artistRecyclerView.adapter = thiscontext?.let { ArtistAdapter(it, artists) }
        }
        artistViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        artistViewModel.onCreate()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        thiscontext = container?.context
        _binding = FragmentArtistListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment artistListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArtistListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}