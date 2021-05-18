package com.selflearning.chemistree.feature.f_profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.activities.MainActivity
import com.selflearning.chemistree.data.User
import com.selflearning.chemistree.data.User.Companion.instance
import com.selflearning.chemistree.databinding.FragmentProfileBinding
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import com.selflearning.chemistree.feature.f_registration.RegisterActivity
import com.squareup.picasso.Picasso
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private var user: User? = null
    private lateinit var binding: FragmentProfileBinding

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        ((activity as MainActivity).application as ChemistreeApplication).appComponentFactory.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user = instance
        val name = user!!.firstName
        val email = user!!.uid

        binding.tvUserName.text = name
        binding.tvUserEmail.text = email

        binding.civUser.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, RESULT_LOAD)
        }
        binding.profileSignOutBtn.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        viewModel.signOut()
        startActivity(Intent(activity, RegisterActivity::class.java))
        requireActivity().finish()
    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            try {
                var imageUrl: Uri? = null
                if (data != null) {
                    imageUrl = data.data
                }
                val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imageUrl)

                Picasso.get().load(imageUrl).fit().centerCrop().into(binding.civUser)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val RESULT_LOAD = 101
    }
}