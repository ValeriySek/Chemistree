package com.selflearning.chemistree.feature.f_profile.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.R
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItem
import com.selflearning.chemistree.utils.extentions.dpToPx
import com.selflearning.chemistree.utils.extentions.heightWrapWidthMatchContent
import com.selflearning.chemistree.utils.extentions.wrapContent
import com.selflearning.chemistree.views.rv_items.TextWithSwitchOrMaterialButton
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragmentView : Fragment() {
    private lateinit var container: LinearLayout

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SettingsViewModel

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as ChemistreeApplication).appComponentFactory.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewModel::class.java]
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(view: View) {
        view.findViewById<ImageButton>(R.id.settings_back_btn)
            .setOnClickListener { findNavController().popBackStack() }

        initSettings(view)
    }

    private fun initSettings(view: View) {
        container = view.findViewById(R.id.settings_container_ll)

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isReceived) refreshSettings(it.list) else
                setSettings(it.list)
        }
    }

    private fun refreshSettings(items: List<SettingsItem>) {
        items.forEachIndexed { index, settingsItem ->
            if (settingsItem is SettingsItem.ItemList) {
                (container.getChildAt(index) as TextWithSwitchOrMaterialButton)
                    .enabled(settingsItem.isEnabled)
            }
        }
    }

    private fun setSettings(items: List<SettingsItem>) {
        items.forEach { item ->
            when (item) {
                is SettingsItem.Title -> container.addView(setTitle(item))
                is SettingsItem.ItemList -> container.addView(setItem(item))
            }
        }
    }

    private fun setTitle(item: SettingsItem.Title): View {
        return TextView(requireContext()).apply {
            text = item.title
            layoutParams = wrapContent()
            setTextAppearance(R.style.Text_Regular_16_SecondaryColor)
            updatePadding(
                left = dpToPx(24),
                top = dpToPx(16),
                right = dpToPx(24),
                bottom = dpToPx(16)
            )
        }
    }

    private fun setItem(item: SettingsItem.ItemList): View {
        return TextWithSwitchOrMaterialButton(
            requireContext()
        ).apply {
            text = item.text
            initView(
                item.buttonText,
                item.isChecked,
                {

                },
                {

                },
                { item, isChecked ->
                    viewModel.saveB(item, isChecked)
                },
                item.buttonType,
                item.itemType,
                item.isEnabled
            )
            layoutParams = heightWrapWidthMatchContent()
            minimumHeight = dpToPx(48)
            updatePadding(
                left = dpToPx(32),
                top = dpToPx(4),
                right = dpToPx(24),
                bottom = dpToPx(4)
            )
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragmentView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}