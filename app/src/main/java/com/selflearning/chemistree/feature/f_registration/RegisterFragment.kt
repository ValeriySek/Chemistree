package com.selflearning.chemistree.feature.f_registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.activities.MainActivity
import com.selflearning.chemistree.databinding.LoginFrBinding
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import javax.inject.Inject

class RegisterFragment: Fragment(), OnSuccessRegistration {

    private lateinit var binding: LoginFrBinding

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ((activity as RegisterActivity).application as ChemistreeApplication).appComponentFactory.inject(this)
        binding = LoginFrBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
        viewModel.setListener(this)

        binding.registerGoogle.setOnClickListener { viewModel.signIn() }

        initListeners()
        return binding.root
    }

    private fun initListeners() {
        viewModel.signInIntent.observe(viewLifecycleOwner) { startActivityForResult(it, RC_SIGN_IN) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            viewModel.register(data)
        }
    }

    override fun onSuccess() {
        startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }

    override fun onRevokeAccess() {

    }

    companion object {
        private const val RC_SIGN_IN = 100
    }
}