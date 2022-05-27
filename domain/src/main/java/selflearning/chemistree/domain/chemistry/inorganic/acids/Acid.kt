package selflearning.chemistree.domain.chemistry.inorganic.acids

import selflearning.chemistree.domain.chemistry.inorganic.Inorganic

class Acid(
    val id: Int,
    val formula: String,
    val formulaBeauty: String,
    val name: String,
    val nameSalt: String,
    val anion: String,
    val charge: Int,
    val classification: String,
    val difficult: Int
) : Inorganic {

    constructor(
        formula: String,
        name: String,
        nameSalt: String,
        anion: String,
        charge: Int,
        classification: String,
        difficult: Int
    ) : this(
        id = 0,
        formula,
        "",
        name,
        nameSalt,
        anion,
        charge,
        classification,
        difficult
    )
}