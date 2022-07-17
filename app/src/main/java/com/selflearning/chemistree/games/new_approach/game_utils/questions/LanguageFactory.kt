package com.selflearning.chemistree.games.new_approach.game_utils.questions

import java.util.*

object LanguageFactory {

    fun lang(): Language {
        val localeLang = Locale.getDefault().language
        return when {
            localeLang.equals(Locale("ru").language) -> Russian()

            else -> English()
        }
    }
}