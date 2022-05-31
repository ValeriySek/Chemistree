package selflearning.chemistree.domain.chemistry.inorganic.oxides

import selflearning.chemistree.domain.chemistry.inorganic.Inorganic

class Oxide(
    val formula: String,
    val name: String,
    val charge: Int,
    val classification: String,
    val difficult: Int
) : Inorganic {
}