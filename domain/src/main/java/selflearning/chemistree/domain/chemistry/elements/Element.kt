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
    val subgroup: String,
    val period: Int,
    val block: String,
    val elementCategory: String,
    val electronConfiguration: String,
    val electronsPerShell: String,
    val phase: String
) : Data(), Parcelable {

    constructor(
        atomicNumber: Int,
        symbol: String,
        title: String,
        weight: Double,
        elementCategory: String,
        electronConfiguration: String = ""
    ) : this(
        atomicNumber,
        symbol,
        title,
        weight,
        -1,
        "",
        -1,
        "",
        elementCategory,
        electronConfiguration,
        "",
        ""
    )
}