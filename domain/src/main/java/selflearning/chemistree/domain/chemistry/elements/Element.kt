package selflearning.chemistree.domain.chemistry.elements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Element(
    val atomicNumber: Int,
    val symbol: String,
    val title: String,
    val weight: Double,
    val group: Int,
    val groupOld: Int,
    val subgroup: Subgroup,
    val period: Int,
    val block: String,
    val elementCategory: String,
    val electronConfiguration: String,
    val electronsPerShell: String,
    val phase: Phase,
    val atomRadius: AtomRadius,
    val oxidationStates: List<String>
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
        -1,
        Subgroup.NONE,
        -1,
        "",
        elementCategory,
        electronConfiguration,
        electronsPerShell,
        Phase.UNKNOWN,
        AtomRadius(),
        listOf()
    )
}

fun Element.electronsOnLastShell() = electronsPerShell.split(" ").last().toInt()

fun Element.fullElectronConfiguration(): String {
    return if (electronConfiguration.contains("["))
        fullElectronConfiguration(electronConfiguration)
    else
        electronConfiguration
}

private fun fullElectronConfiguration(elConfig: String): String {
    return if (!elConfig.contains("[")) elConfig
    else {
        val elementName = elConfig.substring(1, elConfig.lastIndexOf("]"))
        val element = Elements.elements.first { it.symbol == elementName }
        val config = elConfig.replace("\\[[A-Z][a-z]?\\]".toRegex(), element.electronConfiguration)
        return fullElectronConfiguration(config)
    }
}

fun main() {
    print(fullElectronConfiguration("[Rn] 5f14 6d10 7s2 7p5"))
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

@Parcelize
data class AtomRadius(
    val radius: String = "0",
    val covalentRadius: String = "0",
    val vanDerWaalsRadius: String = "0",
    val ionicRadius: String = "0"
): Parcelable
