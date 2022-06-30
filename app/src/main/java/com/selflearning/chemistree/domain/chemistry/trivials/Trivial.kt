package com.selflearning.chemistree.domain.chemistry.trivials

data class Trivial(
    val formula: String = "",
    val names: List<NamesByLang> = listOf()
) {

    fun name() = names[0].trivialNames[0].name
}

data class NamesByLang(
    val lang: String = "",
    val trivialNames: List<TrivialNames>,
    val systematicNames: List<SystematicNames>
)

data class TrivialNames(
    val name: String = "",
    val difficult: Int = 1
)

data class SystematicNames(
    val name: String = "",
    val difficult: Int = 1
)
