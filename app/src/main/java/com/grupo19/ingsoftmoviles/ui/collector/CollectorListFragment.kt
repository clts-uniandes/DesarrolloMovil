package com.grupo19.ingsoftmoviles.ui.collector

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.grupo19.ingsoftmoviles.databinding.FragmentCollectorListBinding
import com.grupo19.ingsoftmoviles.ui.adapters.CollectorListAdapter
import com.grupo19.ingsoftmoviles.viewmodel.CollectorListViewModel


/**
 * Collectors List [Fragment] subclass, for HomeActivity.
 */
class CollectorListFragment : Fragment() {
    private var _binding: FragmentCollectorListBinding? = null
    private val collectorListViewModel by viewModels<CollectorListViewModel>()
    private val binding get() = _binding!!
    private var thisContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectorListViewModel.collectors.observe(this) {
            collectors -> binding.collectorsRecyclerView.adapter = thisContext?.let { CollectorListAdapter(it, collectors) { collectorListViewModel.onCollectorClick(it)} }
        }

        collectorListViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        collectorListViewModel.showSelectedCollector.observe(this) {
            /* showCollectorDetail(it)*/
        }

        collectorListViewModel.onCreate()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        thisContext = container?.context
        _binding = FragmentCollectorListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*private fun showCollectorDetail(args/id para invocacion detalle coleccionista, NO USAR item de lista) {
        val intent = Intent(thiscontext, CollectorDetailActivity::class.java).apply {
            putExtra(Constants.COLLECTOR?_OBJECT, album)????
        }
        startActivity(intent)
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of CollectorListFragment.
         */
        @JvmStatic
        fun newInstance() =
            CollectorListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}