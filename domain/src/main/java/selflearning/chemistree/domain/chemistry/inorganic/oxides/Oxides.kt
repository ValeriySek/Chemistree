package selflearning.chemistree.domain.chemistry.inorganic.oxides

import selflearning.chemistree.domain.chemistry.inorganic.Inorganic

class Oxides(val formula: String,
             val formulaBeauty: String,
             val name: String,
             val charge: Int,
             val classification: String,
             val difficult: Int) : Inorganic {
    private val id = 0
}