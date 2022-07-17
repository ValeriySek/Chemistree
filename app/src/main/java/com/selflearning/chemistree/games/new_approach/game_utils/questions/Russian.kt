package com.selflearning.chemistree.games.new_approach.game_utils.questions

class Russian : Language {

    override fun countSPElectrons(countOfElements: Int): String {
        val which = if (countOfElements > 1) "ие" else "ой"
        val ending = if (countOfElements > 1) "ы" else ""
        val has = if (countOfElements > 1) "ю" else "е"
        return "Как$which элемент$ending име${has}т в основном состоянии %s %s-электронов"
    }


}