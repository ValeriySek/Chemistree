package selflearning.chemistree.domain.chemistry.elements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Element(
    val atomicNumber: Int,
    val symbol: String,
    val title: String,
    val weight: Double,
    val group: Int,
    val subgroup: Subgroup,
    val period: Int,
    val block: String,
    val elementCategory: String,
    val electronConfiguration: String,
    val electronsPerShell: String,
    val phase: Phase
) : Data(), Parcelable {

    constructor(
        atomicNumber: Int,
        symbol: String,
        title: String,
        weight: Double,
        elementCategory: String,
        electronConfiguration: String = "",
        electronsPerShell: String = ""
    ) : this(
        atomicNumber,
        symbol,
        title,
        weight,
        -1,
        Subgroup.NONE,
        -1,
        "",
        elementCategory,
        electronConfiguration,
        electronsPerShell,
        Phase.UNKNOWN
    )
}

enum class Phase(phase: String) {
    GAS("gas"),
    SOLID("solid"),
    LIQUID("liquid"),
    UNKNOWN("unknownPhase"),
    LIQUID_PREDICTED("liquid")
}

enum class Subgroup(subgroup: String) {
    A("A"),
    B("B"),
    NONE("")
}