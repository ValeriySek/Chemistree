package com.selflearning.chemistree

import androidx.fragment.app.Fragment

interface NavigationHost {
    fun navigateTo(fragment: Fragment?, addToBackstack: Boolean)
}