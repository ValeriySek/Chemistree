package com.selflearning.chemistree.feature.f_profile.settings

import android.app.TimePickerDialog
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.*
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.selflearning.chemistree.ChemistreeApplication
import com.selflearning.chemistree.R
import com.selflearning.chemistree.di.view_model.di.ViewModelFactory
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItem
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.utils.extentions.dpToPx
import com.selflearning.chemistree.utils.extentions.heightWrapWidthMatchContent
import com.selflearning.chemistree.utils.extentions.setTopMargin
import com.selflearning.chemistree.utils.extentions.wrapContent
import com.selflearning.chemistree.views.compose.DayNightSwitch
import com.selflearning.chemistree.views.fullscreen.FragmentFullScreen
import com.selflearning.chemistree.views.rv_items.TextWithSwitchOrMaterialButton
import javax.inject.Inject
import kotlin.math.hypot

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragmentView : Fragment() {

    private lateinit var container: LinearLayout
    private lateinit var contentContainer: LinearLayout
    private lateinit var bigcontainer: ConstraintLayout
    private lateinit var toolbar: FrameLayout
    private lateinit var scrollView: ScrollView
    private lateinit var dayNightSwitch: ComposeView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: SettingsViewModel

    private var param1: String? = null
    private var param2: String? = null
    private var isSwitchingTheme = false
    private var isDarkTheme = false

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

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            initFullscreen()
        }, 300)
    }

    private fun initFullscreen() {
        (activity as? FragmentFullScreen)?.fullScreen(true)
        val insets = ViewCompat.getRootWindowInsets(requireView())
            ?.getInsets(WindowInsetsCompat.Type.systemBars())
        if (insets != null) {
            contentContainer.updatePadding(bottom = insets.bottom, top = insets.top)
            dayNightSwitch.setTopMargin(insets.top)
        }
    }

    private fun initView(view: View) {
        contentContainer = view.findViewById(R.id.settings_content_container)
        bigcontainer = view.findViewById(R.id.bigcontainer)
        toolbar = view.findViewById(R.id.settings_toolbar)
        scrollView = view.findViewById(R.id.settings_scrollview)
        view.findViewById<ImageButton>(R.id.settings_back_btn)
            .setOnClickListener { findNavController().popBackStack() }
        dayNightSwitch = view.findViewById(R.id.compose_view)
        dayNightSwitch.setContent {

            val checkedState = remember { mutableStateOf(false) }
            DayNightSwitch(checked = checkedState.value, onCheckedChange = {
                if (isSwitchingTheme) return@DayNightSwitch
                checkedState.value = it
                changeTheme()
            })
        }
        initSettings(view)
    }

    private fun initSettings(view: View) {
        container = view.findViewById(R.id.settings_container_ll)

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.isReceived)
                refreshSettings(it.list)
            else
                setSettings(it.list)
        }
    }

    private fun refreshSettings(items: List<SettingsItem>) {
        items.forEachIndexed { index, settingsItem ->
            if (settingsItem is SettingsItem.ItemList) {
                (container.getChildAt(index) as TextWithSwitchOrMaterialButton)
                    .enabled(settingsItem.isEnabled)
                    .buttonText(settingsItem.buttonText)
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
                text = item.buttonText,
                isChecked = item.isChecked,
                onButtonClick = {
                    onItemButtonClick(item)
                },
                onItemClick = {

                },
                onSwitchClick = { item, isChecked ->
                    viewModel.saveB(item, isChecked)
                },
                buttonType = item.buttonType,
                itemType = item.itemType,
                isEnabled = item.isEnabled
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

    private fun onItemButtonClick(itemType: SettingsItem.ItemList) {
        when (itemType.itemType) {
            SettingsItemType.TIME_OF_NOTIFICATION -> openTimePicker(itemType.buttonText)
        }
    }

    private fun openTimePicker(time: String) {
        val (hours, minutes) = time.split(":").map { it.toIntOrNull() ?: 0 }
        val tpl = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            val timee = String.format("%02d:%02d", hourOfDay, minute)
            Log.i("TAGG", "time $timee")
            viewModel.saveTime(timee)
        }
        val tp = TimePickerDialog(requireContext(), tpl, hours, minutes, true)
        tp.show()
    }

    private fun changeTheme() {
        val containerImage = ImageView(requireContext())
        containerImage.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
        if (isDarkTheme) {
            bigcontainer.addView(containerImage, 0)
            containerImage.setImageBitmap(getScreenShoot())
            containerImage.isVisible = true
        } else {
            bigcontainer.addView(containerImage, 1)
            containerImage.setImageBitmap(getScreenShoot())
            containerImage.isVisible = true
        }
        val color = if (isDarkTheme) R.color.white else R.color.black
        contentContainer.setBackgroundColor(requireContext().resources.getColor(color))

        val location = IntArray(2)
        dayNightSwitch.getLocationOnScreen(location)

        val switchCenterX = location[0] + dayNightSwitch.measuredWidth / 2
        val switchCenterY = location[1] + dayNightSwitch.measuredHeight / 2


        val maxRadius =
            hypot(switchCenterX.toDouble(), (bigcontainer.height - switchCenterY).toDouble())

        val animation = ViewAnimationUtils
            .createCircularReveal(
                if (isDarkTheme) contentContainer else containerImage,
                switchCenterX,
                switchCenterY,
                if (isDarkTheme) 0f else maxRadius.toFloat(),
                if (isDarkTheme) maxRadius.toFloat() else 0f
            )

        animation.duration = 400
        animation.doOnEnd {
            containerImage.setImageBitmap(null)
            containerImage.isVisible = false
            bigcontainer.removeView(containerImage)
            isSwitchingTheme = false
            dayNightSwitch[0].isClickable = true
            isDarkTheme = !isDarkTheme
        }
        animation.start()
        isSwitchingTheme = true
        dayNightSwitch[0].isClickable = false
    }

    private fun getScreenShoot(): Bitmap {
        val bitmap = Bitmap.createBitmap(
            bigcontainer.measuredWidth,
            bigcontainer.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val bgDrawable = bigcontainer.background
        dayNightSwitch.isInvisible = true
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            val color = if (isDarkTheme) Color.BLACK else Color.WHITE
            canvas.drawColor(color)
        }
        bigcontainer.draw(canvas)
        dayNightSwitch.isInvisible = false
        return bitmap
    }

    override fun onStop() {
        super.onStop()
        (activity as? FragmentFullScreen)?.fullScreen(false)
        super.onDestroyView()
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