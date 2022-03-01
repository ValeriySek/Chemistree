package com.selflearning.chemistree.domain.chemistry.trivials


val trivials = listOf(
    NH3(),
    LiAlH4(),
    C(),
    KAlSO4212H2O(),
    N2O4(),
    CaSO4(),
    MgClO42(),
    Na2S2O35H2O(),
    AsH3(),
    BaNO32(),
    BaSO4(),
    BaOH2(),
    TiO2(),
    KClO3(),
    MgCl26H2O(),
    Al2O3(),
    BN(),
    BH3(),
    Na2B4O710H2O(),
    Na2B4O75H2O(),
    Na2B4O74H2O(),
    NO2(),
    N2O(),
    PbS(),
    CaOH2(),
    Fe2O3()
)

private fun NH3() = Trivial(
    formula = "NH3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Аммиак"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Нитрид водорода"
                )
            )
        )
    )
)
private fun LiAlH4() = Trivial(
    formula = "LiAlH4",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Аланат лития"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Алюмогидрид лития"
                )
            )
        )
    )
)
private fun C() = Trivial(
    formula = "C",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Сажа"
                ),
                TrivialNames(
                    name = "Уголь"
                ),
                TrivialNames(
                    name = "Кокс"
                ),
                TrivialNames(
                    name = "Алмаз"
                ),
                TrivialNames(
                    name = "Графит"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Углерод"
                )
            )
        )
    )
)
private fun KAlSO4212H2O() = Trivial(
    formula = "KAl(SO4)2 • 12H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Алюмокалиевые квасцы"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Додекагидрат сульфата алюминия-калия"
                )
            )
        )
    )
)
private fun N2O4() = Trivial(
    formula = "N2O4",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Амил"
                ),
                TrivialNames(
                    name = "Азотный тетраоксид"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Тетраоксид диазота"
                )
            )
        )
    )
)
private fun CaSO4() = Trivial(
    formula = "CaSO4",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Ангидрит"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Сульфат кальция безводный"
                )
            )
        )
    )
)
private fun MgClO42() = Trivial(
    formula = "Mg(ClO4)2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Ангидрон"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Перхлорат магния"
                )
            )
        )
    )
)
private fun Na2S2O35H2O() = Trivial(
    formula = "Na2S2O3 • 5H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Антихлор"
                ),
                TrivialNames(
                    name = "Гипосульфит"
                ),
                TrivialNames(
                    name = "Сульфидотриоксосульфат натрия"
                ),
                TrivialNames(
                    name = "Натрий серноватистокислый"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Пентагидрат тиосульфата натрия"
                )
            )
        )
    )
)
private fun AsH3() = Trivial(
    formula = "AsH3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Арсин"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Гидрид мышьяка(III)"
                )
            )
        )
    )
)
private fun BaNO32() = Trivial(
    formula = "Ba(NO3)2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бариевая селитра"
                ),
                TrivialNames(
                    name = "Баритовая селитра"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Нитрат бария"
                )
            )
        )
    )
)
private fun BaSO4() = Trivial(
    formula = "BaSO4",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Барит"
                ),
                TrivialNames(
                    name = "Белила баритовые"
                ),
                TrivialNames(
                    name = "Бланфикс"
                ),
                TrivialNames(
                    name = "Баритовая каша"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Сульфат бария"
                )
            )
        )
    )
)
private fun BaOH2() = Trivial(
    formula = "Ba(OH)2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Баритовая вода"
                ),
                TrivialNames(
                    name = "Едкий барит"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Гидроксид бария"
                )
            )
        )
    )
)
private fun TiO2() = Trivial(
    formula = "TiO2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Титановые белила"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Оксид титана(IV)"
                )
            )
        )
    )
)
private fun KClO3() = Trivial(
    formula = "KClO3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бертолетова соль"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Хлорат калия"
                )
            )
        )
    )
)
private fun MgCl26H2O() = Trivial(
    formula = "MgCl2 • 6H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бишофит"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Гексагидрат хлорида магния"
                )
            )
        )
    )
)
private fun Al2O3() = Trivial(
    formula = "Al2O3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Боксит"
                ),
                TrivialNames(
                    name = "Глинозем"
                ),
                TrivialNames(
                    name = "Корунд"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Оксид алюминия"
                )
            )
        )
    )
)
private fun BN() = Trivial(
    formula = "BN",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Боразон"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Нитрид бора"
                )
            )
        )
    )
)
private fun BH3() = Trivial(
    formula = "BH3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Боран"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Гидрид бора"
                )
            )
        )
    )
)
private fun Na2B4O710H2O() = Trivial(
    formula = "Na2B4O7 • 10H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бура"
                ),
                TrivialNames(
                    name = "Боракс"
                ),
                TrivialNames(
                    name = "Тинкал"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Декагидрат тетрабората натрия"
                )
            )
        )
    )
)
private fun Na2B4O75H2O() = Trivial(
    formula = "Na2B4O7 • 5H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бура ювелирная"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Пентагидрат тетрабората натрия"
                )
            )
        )
    )
)
private fun Na2B4O74H2O() = Trivial(
    formula = "Na2B4O7 • 4H2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Кернит"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Тетрагидрат тетрабората натрия"
                )
            )
        )
    )
)
private fun NO2() = Trivial(
    formula = "NO2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Бурый газ"
                ),
                TrivialNames(
                    name = "Лисий хвост"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Оксид азота (IV)"
                )
            )
        )
    )
)
private fun N2O() = Trivial(
    formula = "N2O",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Веселящий газ"
                ),
                TrivialNames(
                    name = "Закись азота"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Оксид азота (I)"
                )
            )
        )
    )
)
private fun PbS() = Trivial(
    formula = "PbS",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Галенит"
                ),
                TrivialNames(
                    name = "Свинцовый блеск"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Сульфид свинца (II)"
                )
            )
        )
    )
)
private fun CaOH2() = Trivial(
    formula = "Ca(OH)2",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Гашеная известь"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Гидроксид кальция"
                )
            )
        )
    )
)
private fun Fe2O3() = Trivial(
    formula = "Fe2O3",
    names = listOf(
        NamesByLang(
            lang = "ru",
            trivialNames = listOf(
                TrivialNames(
                    name = "Гематит"
                ),
                TrivialNames(
                    name = "Красный железняк"
                )
            ),
            systematicNames = listOf(
                SystematicNames(
                    name = "Оксид железа (III)"
                )
            )
        )
    )
)

