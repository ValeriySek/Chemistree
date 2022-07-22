package selflearning.chemistree.domain.chemistry.inorganic.bases

import selflearning.chemistree.domain.chemistry.inorganic.Inorganic

class Base(
        val id: Int,
        val formula: String,
        val name: String,
        val nameCation: String,
        val cation: String,
        val oxidationState: Int,
        val classification: String,
        val difficult: Int
) : Inorganic {
    //    @Ignore
    //    public Bases(int id, String baseFormula, String baseRuName, String baseEnName, String baseFormulaCation, int oxidationStateOfCation, int difficult, int classification) {
    //        this.id = id;
    //        this.baseFormula = baseFormula.replaceAll("([2-9]*)", "<sub><small>$1</small></sub>");
    //        this.baseRuName = baseRuName;
    //        this.baseEnName = baseEnName;
    //        this.baseRuNameCation = baseRuName.replaceAll("[а-яА-Я]\\s", "");
    //        this.baseEnNameCation = baseEnName.replaceAll("\\s[a-zA-Z]", "");
    //        this.baseFormulaCation = baseFormulaCation;
    //        this.oxidationStateOfCation = oxidationStateOfCation;
    //        this.difficult = difficult;
    //        this.classification = classification;
    //    }
    constructor(formula: String,
                name: String,
//                nameCation: String?,
                oxidationState: Int,
                cation: String,
                classification: String,
                difficult: Int) : this(
            id = -1,
            formula,
            name,
            "",
            cation,
            oxidationState,
            classification,
            difficult
    )
}