package com.grupo19.ingsoftmoviles.ui.collector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.grupo19.ingsoftmoviles.databinding.ActivityCollectorDetailBinding
import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.viewmodel.CollectorDetailViewModel

class CollectorDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCollectorDetailBinding

    private val collectorDetailViewModel by viewModels<CollectorDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollectorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val collectorId: Int = intent.getIntExtra(Constants.COLLECTOR_ID, Constants.COLLECTOR_ID_ERROR)

        collectorDetailViewModel.onCreate(collectorId)

        collectorDetailViewModel.collector.observe(this){
            populateLayout(it)
        }

        collectorDetailViewModel.progressVisible.observe(this){
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

    }

    private fun populateLayout(collector: CollectorResponse) {
        binding.cardCollectorName.text = collector.name
        binding.cardCollectorEmail.text = collector.email
        binding.cardCollectorNumber.text = collector.telephone

    }

}