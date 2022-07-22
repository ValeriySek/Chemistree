package selflearning.chemistree.domain.chemistry.elements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

const val elementSymbolInBricks = "\\[[A-Z][a-z]?\\]"


@Parcelize
data class Element(
    val atomicNumber: Int = 0,
    val symbol: String = "",
    val title: String = "",
    val weight: Double = .0,
    val group: Int = 0,
    val groupOld: Int = 0,
    val subgroup: Subgroup = Subgroup.NONE,
    val period: Int = 0,
    val block: String = "",
    val elementCategory: String = "",
    val electronConfiguration: String = "",
    val electronsPerShell: String = "",
    val phase: Phase = Phase.UNKNOWN,
    val atomRadius: AtomRadius = AtomRadius(),
    val oxidationStates: List<String> = listOf()
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


    /**
     * Return List of electron orbitals in format [3d10, 4s2, 4p5]
     *
     * */
    private fun electronsPerOrbital(electronConfiguration: String) =
        electronConfiguration.split(" ")

    /**
     * Return full List of electron orbitals in format [1s2, 2s2, 2p1]
     *
     * */
    fun electronsConfigurationFull() =
        electronsPerOrbital(fullElectronConfiguration())


    /**
     * Return List of electron orbitals in format [3d10, 4s2, 4p5]
     *
     * */
    fun electronsConfigurationLastPeriod() =
        electronsPerOrbital(electronConfigurationOfPeriod())

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

    private fun String.shell() = substring(0, 1).toInt()
    private fun String.orbital() = substring(1, 2)
    private fun String.electrons() = substring(2).toInt()

    fun electronsOnLastShell() = electronsPerShell.split(" ").last().toInt()


    /**
     * return el config of last period in format "2s2 2p1"
     * */
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
            fullElectronConfiguration(config)
        }
    }

    fun formulaOfLastShell() = electronConfigurationOfPeriod().replace(
        "([1-8])([spdf][0-9])".toRegex(),
        "$2"
    )


    fun isElectronFormulaOfLastShell(formula: String) =
        formulaOfLastShell() == formula


    fun sumOfUnpairElectrons() =
        getOrbitals(electronConfigurationOfPeriod()).sumOf { orbital ->
            orbital.unpairElectrons
        }

    fun hasUnpairElectrons() = sumOfUnpairElectrons() > 0

    fun String.unpairElectrons(electrons: Int) =
        if (electrons <= maxElectronsPerShell() / 2)
            electrons
        else
            maxElectronsPerShell() - electrons

    fun String.maxElectronsPerShell() = when (this) {
        "s" -> 2
        "p" -> 6
        "d" -> 10
        else -> 14
    }

    fun valenceElectrons(): Int {
        return if (block == "s" || block == "p") groupOld
        else error("WE DON'T KNOW YET")
    }

    fun getPeriods(periodType: PeriodType) =
        if (periodType == PeriodType.OLD) (1..8) else (1..18)

    fun getGroups() = (1..8)

}

fun metalProperties(
    properties: Properties,
    axis: Axis,
    metalProperties: MetalProperties,
    list: List<Int>
): Boolean {
    val dir = isIncreased(list)
    if (dir == Dir._312) return false
    val metal = if (properties == Properties.INCREASE) {
        if (axis == Axis.PERIOD) {
            dir == Dir._321
        } else {
            dir == Dir._123
        }
    } else {
        if (axis == Axis.PERIOD) {
            dir == Dir._123
        } else {
            dir == Dir._321
        }
    }
    return if (metalProperties == MetalProperties.METAL) metal else !metal
}

data class Orbital(
    val shell: Int,
    val orbital: String,
    val electrons: Int,
    val unpairElectrons: Int
) {
    val hasUnpairsElectrons = unpairElectrons > 0
}

fun isIncreased(list: List<Int>): Dir {
    if (list.size < 2) error("List have to contains at least 2 elements")
    if (list[0] < list[1]) {
        for (i in 1..list.lastIndex) {
            if (list[i - 1] > list[i]) return Dir._312
        }
        return Dir._123
    } else {
        for (i in 1..list.lastIndex) {
            if (list[i - 1] < list[i]) return Dir._312
        }
        return Dir._321
    }
}


fun main() {
//    val el = Elements.elements.toList()[74].apply {
////        println(electronsPerOrbital(electronConfigurationOfPeriod()))
//        println(isElectronFormulaOfLastShell("f14"))
////        getOrbitals(fullElectronConfiguration()).forEach {
////
////            println(it)
////        }
//    }

//    println(isIncreased(listOf(1, 2, 3)))
//    println(isIncreased(listOf(3, 2, 1)))
//    println(isIncreased(listOf(0, 2, 1)))
    println(
        metalProperties(
            Properties.INCREASE, Axis.PERIOD,
            MetalProperties.METAL, listOf(1, 2, 3)
        )
    )
    println(
        metalProperties(
            Properties.INCREASE, Axis.PERIOD,
            MetalProperties.NONMETAL, listOf(1, 2, 3)
        )
    )
    println()
    println(
        metalProperties(
            Properties.INCREASE, Axis.GROUP,
            MetalProperties.METAL, listOf(1, 2, 3)
        )
    )
    println(
        metalProperties(
            Properties.INCREASE, Axis.GROUP,
            MetalProperties.NONMETAL, listOf(1, 2, 3)
        )
    )
    println()
    println(
        metalProperties(
            Properties.DECREASE, Axis.PERIOD,
            MetalProperties.METAL, listOf(1, 2, 3)
        )
    )
    println(
        metalProperties(
            Properties.DECREASE, Axis.PERIOD,
            MetalProperties.NONMETAL, listOf(1, 2, 3)
        )
    )
    println()
    println(
        metalProperties(
            Properties.DECREASE, Axis.GROUP,
            MetalProperties.METAL, listOf(1, 2, 3)
        )
    )
    println(
        metalProperties(
            Properties.DECREASE, Axis.GROUP,
            MetalProperties.NONMETAL, listOf(1, 2, 3)
        )
    )
    println()
    println(
        metalProperties(
            Properties.INCREASE, Axis.PERIOD,
            MetalProperties.METAL, listOf(3, 2, 1)
        )
    )
    println(
        metalProperties(
            Properties.INCREASE, Axis.PERIOD,
            MetalProperties.NONMETAL, listOf(3, 2, 1)
        )
    )
    println()
    println(
        metalProperties(
            Properties.INCREASE, Axis.GROUP,
            MetalProperties.METAL, listOf(3, 2, 1)
        )
    )
    println(
        metalProperties(
            Properties.INCREASE, Axis.GROUP,
            MetalProperties.NONMETAL, listOf(3, 2, 1)
        )
    )
    println()
    println(
        metalProperties(
            Properties.DECREASE, Axis.PERIOD,
            MetalProperties.METAL, listOf(3, 2, 1)
        )
    )
    println(
        metalProperties(
            Properties.DECREASE, Axis.PERIOD,
            MetalProperties.NONMETAL, listOf(3, 2, 1)
        )
    )
    println()
    println(
        metalProperties(
            Properties.DECREASE, Axis.GROUP,
            MetalProperties.METAL, listOf(3, 2, 1)
        )
    )
    println(
        metalProperties(
            Properties.DECREASE, Axis.GROUP,
            MetalProperties.NONMETAL, listOf(3, 2, 1)
        )
    )

//    Elements.elements.forEach {
////        electronsPerOrbital(electronConfigurationOfPeriod())
////        electronsPerOrbital(fullElectronConfiguration())
//        val sum = it.sumOfUnpairElectrons()
//        if (sum > 0) {
//            println("Element: ${it.symbol}, ${it.electronConfigurationOfPeriod()}, $sum, ${it.hasUnpairElectrons()}")
//        }
//    }
//    print(el.block.valentnElectrons(el.electronsOnLastShell()))
//    print(el.electronConfigurationOfPeriod())
}

fun Any.equalsToAll(items: Iterable<Any>): Boolean {
    return items.all {
        println("this $this == it $it")
        this == it
    }
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

enum class PeriodType {
    OLD,
    NEW
}

enum class Properties {
    INCREASE,
    DECREASE
}

enum class MetalProperties {
    METAL,
    NONMETAL
}


enum class Axis {
    GROUP,
    PERIOD
}

enum class Dir {
    _123,
    _321,
    _312
}