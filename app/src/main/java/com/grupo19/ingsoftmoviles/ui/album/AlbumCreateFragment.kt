package com.grupo19.ingsoftmoviles.ui.album

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.FragmentAlbumCreateBinding
import com.grupo19.ingsoftmoviles.databinding.FragmentAlbumListBinding
import com.grupo19.ingsoftmoviles.viewmodel.NewAlbumViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlbumCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlbumCreateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentAlbumCreateBinding? = null
    private val newAlbumViewModel by viewModels<NewAlbumViewModel>()
    private val binding get() = _binding!!
    private var thiscontext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val genres = resources.getStringArray(R.array.Genres)
        val recordLabels = resources.getStringArray(R.array.RecordLabels)

        var selectedGenre : String = genres[0]
        var selectedRecordLabel : String = recordLabels[0]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        thiscontext = container?.context
        _binding = FragmentAlbumCreateBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlbumCreateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                AlbumCreateFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}