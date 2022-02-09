package com.selflearning.chemistree.feature.f_profile.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.selflearning.chemistree.R
import com.selflearning.chemistree.core.adapter.BaseAdapter
import com.selflearning.chemistree.core.adapter.BindableItem
import com.selflearning.chemistree.feature.f_profile.settings.controller.SettingsTextController
import com.selflearning.chemistree.feature.f_profile.settings.controller.SettingsTextWithSwitchOrMaterialButtonController
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItem
import com.selflearning.chemistree.feature.f_profile.settings.data.SettingsItemType
import com.selflearning.chemistree.views.rv_items.data.ButtonType

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SettingsFragmentView : Fragment() {

    private val adapter = BaseAdapter()

    private val textController = SettingsTextController()
    private val textWithSwitchOrMaterialButtonController =
        SettingsTextWithSwitchOrMaterialButtonController(
            onButtonClick = {
                Log.i("TAGG", "onButtonClick $it")
            },
            onItemClick = {
                Log.i("TAGG", "onItemClick $it")
            },
            onSwitchClick = { item, isChecked ->
                Log.i("TAGG", "onSwitchClick $item $isChecked")
            }
        )

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
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
        view.findViewById<ImageButton>(R.id.settings_back_btn)
            .setOnClickListener { findNavController().popBackStack() }
        val rv = view.findViewById<RecyclerView>(R.id.settings_rv)
        rv.adapter = adapter
        adapter.add(
            listOf(
                BindableItem("Уведомления", textController),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.PUSH_NOTIFICATIONS,
                        buttonType = ButtonType.SwitchButton,
                        text = "Push-уведомления",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.DAILY_NOTIFICATIONS,
                        buttonType = ButtonType.SwitchButton,
                        text = "Ежедневные напоминания",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.TIME_OF_NOTIFICATION,
                        buttonType = ButtonType.MaterialButton,
                        text = "Время напоминания",
                        buttonText = "9:00",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem("Таблица Менделеева", textController),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.MENDELEEV_TABLE_TYPE,
                        buttonType = ButtonType.MaterialButton,
                        text = "Форма таблицы",
                        buttonText = "Длинная",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.MENDELEEV_TABLE_DESIGN,
                        buttonType = ButtonType.NoneButton,
                        text = "Дизайн таблицы",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem("Связь", textController),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.CONTACT_US,
                        buttonType = ButtonType.NoneButton,
                        text = "Напишите нам",
                    ), textWithSwitchOrMaterialButtonController
                ),
                BindableItem(
                    SettingsItem.ItemList(
                        itemType = SettingsItemType.ABOUT,
                        buttonType = ButtonType.NoneButton,
                        text = "О приложении",
                    ), textWithSwitchOrMaterialButtonController
                ),
            )
        )
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