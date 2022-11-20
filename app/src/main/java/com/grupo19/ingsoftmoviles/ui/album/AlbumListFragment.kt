package com.grupo19.ingsoftmoviles.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.grupo19.ingsoftmoviles.databinding.FragmentAlbumListBinding
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.MainActivity2
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter
import com.grupo19.ingsoftmoviles.viewmodel.AlbumViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumListFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding: FragmentAlbumListBinding? = null
    private val albumViewModel by viewModels<AlbumViewModel>()
    private val binding get() = _binding!!
    private var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumViewModel.albums.observe(this) { albums ->
            binding.albumsRecyclerView.adapter = thiscontext?.let { AlbumAdapter(it, albums) { albumViewModel.onAlbumClick(it) } }
        }

        albumViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        albumViewModel.showMessage.observe(this) {
            showAlbumDetail(it)
        }

        albumViewModel.onCreate()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        thiscontext = container?.context
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun showAlbumDetail(album: AlbumResponse) {
        val intent = Intent(thiscontext, MainActivity2::class.java).apply {
            putExtra(Constants.ALBUM_OBJECT, album)
        }
        startActivity(intent)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlbumListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlbumListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}