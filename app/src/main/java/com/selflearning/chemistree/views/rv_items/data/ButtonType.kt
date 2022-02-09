package com.selflearning.chemistree.views.rv_items.data

sealed class ButtonType {
    object MaterialButton : ButtonType()
    object SwitchButton : ButtonType()
    object NoneButton : ButtonType()
}
