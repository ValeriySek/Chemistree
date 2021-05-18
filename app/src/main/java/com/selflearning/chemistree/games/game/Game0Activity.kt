package com.selflearning.chemistree.games.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.R
import com.selflearning.chemistree.databinding.ActivityGame0Binding
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import javax.inject.Inject

class Game0Activity : AppCompatActivity() {

    private lateinit var binding: ActivityGame0Binding

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: Game0ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGame0Binding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory)[Game0ViewModel::class.java]
    }
}