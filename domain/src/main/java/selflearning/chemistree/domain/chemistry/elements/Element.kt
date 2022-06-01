package selflearning.chemistree.domain.chemistry.elements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val elementSymbolInBricks = "\\[[A-Z][a-z]?\\]"


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


    fun electronsPerOrbital(electronConfiguration: String) =
        electronConfiguration.split(" ")


    //        orbitals.forEach {
//            val shell = it.shell()
//            val typeOrbital = it.typeOrbital()
//            val electronsCount = it.electronsCount()
//
//            println("element = ${symbol}, shell = $shell, typeOrbital = $typeOrbital, electronsCount = $electronsCount")
//        }

    fun getOrbitals(electronConfiguration: String): List<Orbital> {
        return electronsPerOrbital(electronConfiguration).map {
            val shell = it.shell()
            val orbital = it.orbital()
            val electrons = it.electrons()
            Orbital(
                it.shell(),
                orbital,
                electrons,
                orbital.unpairElectrons(electrons)
            )
        }
    }

    fun String.shell() = substring(0, 1).toInt()
    fun String.orbital() = substring(1, 2)
    fun String.electrons() = substring(2).toInt()

    fun electronsOnLastShell() = electronsPerShell.split(" ").last().toInt()

    fun electronConfigurationOfPeriod() =
        electronConfiguration.replace("(\\[[A-Z][a-z]?\\]\\s)?".toRegex(), "")

    fun fullElectronConfiguration() =
        fullElectronConfiguration(electronConfiguration)

    private fun fullElectronConfiguration(elConfig: String): String {
        return if (!elConfig.contains("[")) elConfig
        else {
            val elementName = elConfig.substring(1, elConfig.lastIndexOf("]"))
            val element = Elements.elements.first { it.symbol == elementName }
            val config = elConfig.replace(
                elementSymbolInBricks.toRegex(),
                element.electronConfiguration
            )
            return fullElectronConfiguration(config)
        }
    }

}

data class Orbital(
    val shell: Int,
    val orbital: String,
    val electrons: Int,
    val unpairElectrons: Int
)


fun String.maxElectronsPerShell() = when (this) {
    "s" -> 2
    "p" -> 6
    "d" -> 10
    else -> 14
}

fun String.unpairElectrons(electrons: Int) =
    if (electrons <= this.maxElectronsPerShell() / 2)
        electrons
    else
        this.maxElectronsPerShell() - electrons

//fun String.valenсeElectrons(electrons: Int) =


fun main() {
    val el = Elements.elements.toList()[75].apply {
//        electronsPerOrbital(electronConfigurationOfPeriod())
//        electronsPerOrbital(fullElectronConfiguration())
        getOrbitals(fullElectronConfiguration()).forEach {

            println(it)
        }
    }
//    print(el.block.valentnElectrons(el.electronsOnLastShell()))
//    print(el.electronConfigurationOfPeriod())
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
) : Parcelable
