package selflearning.chemistree.domain.chemistry.elements

import selflearning.chemistree.domain.chemistry.elements.Subgroup.*

object Elements {

    val elements = sequenceOf(
        Element(
            1,
            "H",
            "hydrogen",
            1.0079,
            1,
            A,
            1,
            "s",
            "reactiveNonmetal",
            "1s<sup>1</sup>",
            "1",
            Phase.GAS
        ),
        Element(
            2,
            "He",
            "helium",
            4.0026,
            8,
            A,
            1,
            "s",
            "nobleGas",
            "1s<sup>2</sup>",
            "2",
            Phase.GAS
        ),
        Element(
            3,
            "Li",
            "lithium",
            6.941,
            1,
            A,
            2,
            "s",
            "alkaliMetal",
            "[He] 2s<sup>1</sup>",
            "2 1",
            Phase.SOLID
        ),
        Element(
            4,
            "Be",
            "beryllium",
            9.0121,
            2,
            A,
            2,
            "s",
            "alkalineEarthMetal",
            "[He] 2s<sup>2</sup>",
            "2 2",
            Phase.SOLID
        ),
        Element(
            5,
            "B",
            "boron",
            10.811,
            3,
            A,
            2,
            "p",
            "metalloid",
            "[He] 2s<sup>2</sup> 2p<sup>1</sup>",
            "2 3",
            Phase.SOLID
        ),
        Element(
            6,
            "C",
            "carbon",
            12.0107,
            4,
            A,
            2,
            "p",
            "reactiveNonmetal",
            "[He] 2s<sup>2</sup> 2p<sup>2</sup>",
            "2 4",
            Phase.SOLID
        ),
        Element(
            7,
            "N",
            "nitrogen",
            14.0067,
            5,
            A,
            2,
            "p",
            "reactiveNonmetal",
            "[He] 2s<sup>2</sup> 2p<sup>3</sup>",
            "2 5",
            Phase.GAS
        ),
        Element(
            8,
            "O",
            "oxygen",
            15.9994,
            6,
            A,
            2,
            "p",
            "reactiveNonmetal",
            "[He] 2s<sup>2</sup> 2p<sup>4</sup>",
            "2 6",
            Phase.GAS
        ),
        Element(
            9,
            "F",
            "fluorine",
            18.9984,
            7,
            A,
            2,
            "p",
            "reactiveNonmetal",
            "[He] 2s<sup>2</sup> 2p<sup>5</sup>",
            "2 7",
            Phase.GAS
        ),
        Element(
            10,
            "Ne",
            "neon",
            20.1797,
            8,
            A,
            2,
            "p",
            "nobleGas",
            "[He] 2s<sup>2</sup> 2p<sup>6</sup>",
            "2 8",
            Phase.GAS
        ),
        Element(
            11,
            "Na",
            "sodium",
            22.9897,
            1,
            A,
            3,
            "s",
            "alkaliMetal",
            "[Ne] 3s<sup>1</sup>",
            "2 8 1",
            Phase.SOLID
        ),
        Element(
            12,
            "Mg",
            "magnesium",
            24.305,
            2,
            A,
            3,
            "s",
            "alkalineEarthMetal",
            "[Ne] 3s<sup>2</sup>",
            "2 8 2",
            Phase.SOLID
        ),
        Element(
            13,
            "Al",
            "aluminum",
            26.9815,
            3,
            A,
            3,
            "p",
            "postTransitionMetal",
            "[Ne] 3s<sup>2</sup> 3p<sup>1</sup>",
            "2 8 3",
            Phase.SOLID
        ),
        Element(
            14,
            "Si",
            "silicon",
            28.0855,
            4,
            A,
            3,
            "p",
            "metalloid",
            "[Ne] 3s<sup>2</sup> 3p<sup>2</sup>",
            "2 8 4",
            Phase.SOLID
        ),
        Element(
            15,
            "P",
            "phosphorus",
            30.9737,
            5,
            A,
            3,
            "p",
            "reactiveNonmetal",
            "[Ne] 3s<sup>2</sup> 3p<sup>3</sup>",
            "2 8 5",
            Phase.SOLID
        ),
        Element(
            16,
            "S",
            "sulfur",
            32.065,
            6,
            A,
            3,
            "p",
            "reactiveNonmetal",
            "[Ne] 3s<sup>2</sup> 3p<sup>4</sup>",
            "2 8 6",
            Phase.SOLID
        ),
        Element(
            17,
            "Cl",
            "chlorine",
            35.453,
            7,
            A,
            3,
            "p",
            "reactiveNonmetal",
            "[Ne] 3s<sup>2</sup> 3p<sup>5</sup>",
            "2 8 7",
            Phase.GAS
        ),
        Element(
            18,
            "Ar",
            "argon",
            39.948,
            8,
            A,
            3,
            "p",
            "nobleGas",
            "[Ne] 3s<sup>2</sup> 3p<sup>6</sup>",
            "2 8 8",
            Phase.GAS
        ),
        Element(
            19,
            "K",
            "potassium",
            39.0983,
            1,
            A,
            4,
            "s",
            "alkaliMetal",
            "[Ar] 4s<sup>1</sup>",
            "2 8 8 1",
            Phase.SOLID
        ),
        Element(
            20,
            "Ca",
            "calcium",
            40.078,
            2,
            A,
            4,
            "s",
            "alkalineEarthMetal",
            "[Ar] 4s<sup>2</sup>",
            "2 8 8 2",
            Phase.SOLID
        ),
        Element(
            21,
            "Sc",
            "scandium",
            44.9559,
            3,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>1</sup> 4s<sup>2</sup>",
            "2 8 9 2",
            Phase.SOLID
        ),
        Element(
            22,
            "Ti",
            "titanium",
            47.867,
            4,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>2</sup> 4s<sup>2</sup>",
            "2 8 10 2",
            Phase.SOLID
        ),
        Element(
            23,
            "V",
            "vanadium",
            50.9415,
            5,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>3</sup> 4s<sup>2</sup>",
            "2 8 11 2",
            Phase.SOLID
        ),
        Element(
            24,
            "Cr",
            "chromium",
            51.9961,
            6,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>5</sup> 4s<sup>1</sup>",
            "2 8 13 1",
            Phase.SOLID
        ),
        Element(
            25,
            "Mn",
            "manganese",
            54.938,
            7,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>5</sup> 4s<sup>2</sup>",
            "2 8 13 2",
            Phase.SOLID
        ),
        Element(
            26,
            "Fe",
            "iron",
            55.845,
            8,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>6</sup> 4s<sup>2</sup>",
            "2 8 14 2",
            Phase.SOLID
        ),
        Element(
            27,
            "Co",
            "cobalt",
            58.9331,
            8,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>7</sup> 4s<sup>2</sup>",
            "2 8 15 2",
            Phase.SOLID
        ),
        Element(
            28,
            "Ni",
            "nickel",
            58.6934,
            8,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>8</sup> 4s<sup>2</sup>",
            "2 8 16 2",
            Phase.SOLID
        ),
        Element(
            29,
            "Cu",
            "copper",
            63.546,
            1,
            B,
            4,
            "d",
            "transitionMetal",
            "[Ar] 3d<sup>10</sup> 4s<sup>1</sup>",
            "2 8 18 1",
            Phase.SOLID
        ),
        Element(
            30,
            "Zn",
            "zinc",
            65.409,
            2,
            B,
            4,
            "d",
            "postTransitionMetal",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup>",
            "2 8 18 2",
            Phase.SOLID
        ),
        Element(
            31,
            "Ga",
            "gallium",
            69.723,
            3,
            A,
            4,
            "p",
            "postTransitionMetal",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>1</sup>",
            "2 8 18 3",
            Phase.SOLID
        ),
        Element(
            32,
            "Ge",
            "germanium",
            72.630,
            4,
            A,
            4,
            "p",
            "metalloid",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>2</sup>",
            "2 8 18 4",
            Phase.SOLID
        ),
        Element(
            33,
            "As",
            "arsenic",
            74.9216,
            5,
            A,
            4,
            "p",
            "metalloid",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>3</sup>",
            "2 8 18 5",
            Phase.SOLID
        ),
        Element(
            34,
            "Se",
            "selenium",
            78.971,
            6,
            A,
            4,
            "p",
            "reactiveNonmetal",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>4</sup>",
            "2 8 18 6",
            Phase.SOLID
        ),
        Element(
            35,
            "Br",
            "bromine",
            79.904,
            7,
            A,
            4,
            "p",
            "reactiveNonmetal",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>5</sup>",
            "2 8 18 7",
            Phase.LIQUID
        ),
        Element(
            36,
            "Kr",
            "krypton",
            83.798,
            8,
            A,
            4,
            "p",
            "nobleGas",
            "[Ar] 3d<sup>10</sup> 4s<sup>2</sup> 4p<sup>6</sup>",
            "2 8 18 8",
            Phase.GAS
        ),
        Element(
            37,
            "Rb",
            "rubidium",
            85.4678,
            1,
            A,
            5,
            "s",
            "alkaliMetal",
            "[Kr] 5s<sup>1</sup>",
            "2 8 18 8 1",
            Phase.SOLID
        ),
        Element(
            38,
            "Sr",
            "strontium",
            87.62,
            2,
            A,
            5,
            "s",
            "alkalineEarthMetal",
            "[Kr] 5s<sup>2</sup>",
            "2 8 18 8 2",
            Phase.SOLID
        ),
        Element(
            39,
            "Y",
            "yttrium",
            88.905,
            3,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>1</sup> 5s<sup>2</sup>",
            "2 8 18 9 2",
            Phase.SOLID
        ),
        Element(
            40,
            "Zr",
            "zirconium",
            91.224,
            4,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>2</sup> 5s<sup>2</sup>",
            "2 8 18 10 2",
            Phase.SOLID
        ),
        Element(
            41,
            "Nb",
            "niobium",
            92.906,
            5,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>4</sup> 5s<sup>1</sup>",
            "2 8 18 12 1",
            Phase.SOLID
        ),
        Element(
            42,
            "Mo",
            "molybdenum",
            95.94,
            6,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>5</sup> 5s<sup>1</sup>",
            "2 8 18 13 1",
            Phase.SOLID
        ),
        Element(
            43,
            "Tc",
            "technetium",
            97.906,
            7,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>5</sup> 5s<sup>2</sup>",
            "2 8 18 13 2",
            Phase.SOLID
        ),
        Element(
            44,
            "Ru",
            "ruthenium",
            101.07,
            8,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>7</sup> 5s<sup>1</sup>",
            "2 8 18 15 1",
            Phase.SOLID
        ),
        Element(
            45,
            "Rh",
            "rhodium",
            102.905,
            8,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>8</sup> 5s<sup>1</sup>",
            "2 8 18 16 1",
            Phase.SOLID
        ),
        Element(
            46,
            "Pd",
            "palladium",
            106.42,
            8,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>10</sup>",
            "2 8 18 18",
            Phase.SOLID
        ),
        Element(
            47,
            "Ag",
            "silver",
            107.868,
            1,
            B,
            5,
            "d",
            "transitionMetal",
            "[Kr] 4d<sup>10</sup> 5s<sup>1</sup>",
            "2 8 18 18 1",
            Phase.SOLID
        ),
        Element(
            48,
            "Cd",
            "cadmium",
            112.411,
            2,
            B,
            5,
            "d",
            "postTransitionMetal",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup>",
            "2 8 18 18 2",
            Phase.SOLID
        ),
        Element(
            49,
            "In",
            "indium",
            114.818,
            3,
            A,
            5,
            "p",
            "postTransitionMetal",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>1</sup>",
            "2 8 18 18 3",
            Phase.SOLID
        ),
        Element(
            50,
            "Sn",
            "tin",
            118.71,
            4,
            A,
            5,
            "p",
            "postTransitionMetal",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>2</sup>",
            "2 8 18 18 4",
            Phase.SOLID
        ),
        Element(
            51,
            "Sb",
            "antimony",
            121.76,
            5,
            A,
            5,
            "p",
            "metalloid",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>3</sup>",
            "2 8 18 18 5",
            Phase.SOLID
        ),
        Element(
            52,
            "Te",
            "tellurium",
            127.6,
            6,
            A,
            5,
            "p",
            "metalloid",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>4</sup>",
            "2 8 18 18 6",
            Phase.SOLID

        ),
        Element(
            53,
            "I",
            "iodine",
            126.904,
            7,
            A,
            5,
            "p",
            "reactiveNonmetal",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>5</sup>",
            "2 8 18 18 7",
            Phase.SOLID
        ),
        Element(
            54,
            "Xe",
            "xenon",
            131.293,
            8,
            A,
            5,
            "p",
            "nobleGas",
            "[Kr] 4d<sup>10</sup> 5s<sup>2</sup> 5p<sup>6</sup>",
            "2 8 18 18 8",
            Phase.GAS
        ),
        Element(
            55,
            "Cs",
            "cesium",
            132.905,
            1,
            A,
            6,
            "s",
            "alkaliMetal",
            "[Xe] 6s<sup>1</sup>",
            "2 8 18 18 8 1",
            Phase.SOLID
        ),
        Element(
            56,
            "Ba",
            "barium",
            137.327,
            2,
            A,
            6,
            "s",
            "alkalineEarthMetal",
            "[Xe] 6s<sup>2</sup>",
            "2 8 18 18 8 2",
            Phase.SOLID
        ),
        Element(
            57,
            "La",
            "lanthanum",
            138.905,
            3,
            B,
            6,
            "d",
            "lanthanide",
            "[Xe] 5d<sup>1</sup> 6s<sup>2</sup>",
            "2 8 18 18 9 2",
            Phase.SOLID
        ),
        Element(
            58,
            "Ce",
            "cerium",
            140.116,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>1</sup> 5d<sup>1</sup> 6s<sup>2</sup>",
            "2 8 18 19 9 2",
            Phase.SOLID
        ),
        Element(
            59,
            "Pr",
            "praseodynium",
            140.904,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>3</sup> 6s<sup>2</sup>",
            "2 8 18 21 8 2",
            Phase.SOLID
        ),
        Element(
            60,
            "Nd",
            "neodymium",
            144.242,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>4</sup> 6s<sup>2</sup>",
            "2 8 18 22 8 2",
            Phase.SOLID
        ),
        Element(
            61,
            "Pm",
            "promethium",
            144.915,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>5</sup> 6s<sup>2</sup>",
            "2 8 18 23 8 2",
            Phase.SOLID
        ),
        Element(
            62,
            "Sm",
            "samarium",
            150.36,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>6</sup> 6s<sup>2</sup>",
            "2 8 18 24 8 2",
            Phase.SOLID
        ),
        Element(
            63,
            "Eu",
            "europium",
            151.964,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>7</sup> 6s<sup>2</sup>",
            "2 8 18 25 8 2",
            Phase.SOLID
        ),
        Element(
            64,
            "Gd",
            "gadolinium",
            157.25,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>7</sup> 5d<sup>1</sup> 6s<sup>2</sup>",
            "2 8 18 25 9 2",
            Phase.SOLID
        ),
        Element(
            65,
            "Tb",
            "terbium",
            158.925,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>9</sup> 6s<sup>2</sup>",
            "2 8 18 27 8 2",
            Phase.SOLID
        ),
        Element(
            66,
            "Dy",
            "dysprosium",
            162.5,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>10</sup> 6s<sup>2</sup>",
            "2 8 18 28 8 2",
            Phase.SOLID
        ),
        Element(
            67,
            "Ho",
            "holmium",
            164.93,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>11</sup> 6s<sup>2</sup>",
            "2 8 18 29 8 2",
            Phase.SOLID
        ),
        Element(
            68,
            "Er",
            "erbium",
            167.259,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>12</sup> 6s<sup>2</sup>",
            "2 8 18 30 8 2",
            Phase.SOLID
        ),
        Element(
            69,
            "Tm",
            "thulium",
            168.934,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>13</sup> 6s<sup>2</sup>",
            "2 8 18 31 8 2",
            Phase.SOLID
        ),
        Element(
            70,
            "Yb",
            "ytterbium",
            173.04,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>14</sup> 6s<sup>2</sup>",
            "2 8 18 32 8 2",
            Phase.SOLID
        ),
        Element(
            71,
            "Lu",
            "lutetium",
            174.967,
            0,
            NONE,
            6,
            "f",
            "lanthanide",
            "[Xe] 4f<sup>14</sup> 5d<sup>1</sup> 6s<sup>2</sup>",
            "2 8 18 32 9 2",
            Phase.SOLID
        ),
        Element(
            72,
            "Hf",
            "hafnium",
            178.49,
            4,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>2</sup> 6s<sup>2</sup>",
            "2 8 18 32 10 2",
            Phase.SOLID
        ),
        Element(
            73,
            "Ta",
            "tantalum",
            180.947,
            5,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>3</sup> 6s<sup>2</sup>",
            "2 8 18 32 11 2",
            Phase.SOLID
        ),
        Element(
            74,
            "W",
            "tungsten",
            183.84,
            6,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>4</sup> 6s<sup>2</sup>",
            "2 8 18 32 12 2",
            Phase.SOLID
        ),
        Element(
            75,
            "Re",
            "rhenium",
            186.207,
            7,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>5</sup> 6s<sup>2</sup>",
            "2 8 18 32 13 2",
            Phase.SOLID
        ),
        Element(
            76,
            "Os",
            "osmium",
            190.23,
            8,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>6</sup> 6s<sup>2</sup>",
            "2 8 18 32 14 2",
            Phase.SOLID
        ),
        Element(
            77,
            "Ir",
            "iridium",
            192.217,
            8,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>7</sup> 6s<sup>2</sup>",
            "2 8 18 32 15 2",
            Phase.SOLID
        ),
        Element(
            78,
            "Pt",
            "platinum",
            195.084,
            8,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>9</sup> 6s<sup>1</sup>",
            "2 8 18 32 17 1",
            Phase.SOLID
        ),
        Element(
            79,
            "Au",
            "gold",
            196.966,
            1,
            B,
            6,
            "d",
            "transitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>1</sup>",
            "2 8 18 32 18 1",
            Phase.SOLID
        ),
        Element(
            80,
            "Hg",
            "mercury",
            200.59,
            2,
            B,
            6,
            "d",
            "postTransitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup>",
            "2 8 18 32 18 2",
            Phase.LIQUID
        ),
        Element(
            81,
            "Tl",
            "thallium",
            204.383,
            3,
            B,
            6,
            "p",
            "postTransitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>1</sup>",
            "2 8 18 32 18 3",
            Phase.SOLID
        ),
        Element(
            82,
            "Pb",
            "lead",
            207.2,
            4,
            B,
            6,
            "p",
            "postTransitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>2</sup>",
            "2 8 18 32 18 4",
            Phase.SOLID
        ),
        Element(
            83,
            "Bi",
            "bismuth",
            208.980,
            5,
            B,
            6,
            "p",
            "postTransitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>3</sup>",
            "2 8 18 32 18 5",
            Phase.SOLID
        ),
        Element(
            84,
            "Po",
            "polonium",
            208.9824,
            6,
            B,
            6,
            "p",
            "postTransitionMetal",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>4</sup>",
            "2 8 18 32 18 6",
            Phase.SOLID
        ),
        Element(
            85,
            "At",
            "astatine",
            209.9871,
            7,
            A,
            6,
            "p",
            "metalloid",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>5</sup>",
            "2 8 18 32 18 7",
            Phase.SOLID
        ),
        Element(
            86,
            "Rn",
            "radon",
            222.0176,
            8,
            A,
            7,
            "p",
            "nobleGas",
            "[Xe] 4f<sup>14</sup> 5d<sup>10</sup> 6s<sup>2</sup> 6p<sup>6</sup>",
            "2 8 18 32 18 8",
            Phase.GAS
        ),
        Element(
            87,
            "Fr",
            "francium",
            223.0197,
            1,
            A,
            7,
            "s",
            "alkaliMetal",
            "[Rn] 7s<sup>1</sup>",
            "2 8 18 32 18 8 1",
            Phase.SOLID
        ),
        Element(
            88,
            "Ra",
            "radium",
            226.0254,
            2,
            A,
            7,
            "s",
            "alkalineEarthMetal",
            "[Rn] 7s<sup>2</sup>",
            "2 8 18 32 18 8 2",
            Phase.SOLID
        ),
        Element(
            89,
            "Ac",
            "actinium",
            227.0278,
            3,
            B,
            7,
            "d",
            "actinide",
            "[Rn] 6d<sup>1</sup> 7s<sup>2</sup>",
            "2 8 18 32 18 9 2",
            Phase.SOLID
        ),
        Element(
            90,
            "Th",
            "thorium",
            232.038,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 6d<sup>2</sup> 7s<sup>2</sup>",
            "2 8 18 32 18 10 2",
            Phase.SOLID
        ),
        Element(
            91,
            "Pa",
            "protactinium",
            231.0358,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>2</sup> 6d<sup>1</sup> 7s<sup>2</sup>",
            "2 8 18 32 20 9 2",
            Phase.SOLID
        ),
        Element(
            92,
            "U",
            "uranium",
            238.0289,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>3</sup> 6d<sup>1</sup> 7s<sup>2</sup>",
            "2 8 18 32 21 9 2",
            Phase.SOLID
        ),
        Element(
            93,
            "Np",
            "neptunium",
            237.0482,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>4</sup> 6d<sup>1</sup> 7s<sup>2</sup>",
            "2 8 18 32 22 9 2",
            Phase.SOLID
        ),
        Element(
            94,
            "Pu",
            "plutonium",
            244.0642,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>6</sup> 7s<sup>2</sup>",
            "2 8 18 32 24 8 2",
            Phase.SOLID
        ),
        Element(
            95,
            "Am",
            "americium",
            243.0614,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>7</sup> 7s<sup>2</sup>",
            "2 8 18 32 25 8 2",
            Phase.SOLID
        ),
        Element(
            96,
            "Cm",
            "curium",
            247.0703,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>7</sup> 6d<sup>1</sup> 7s<sup>2</sup>",
            "2 8 18 32 25 9 2",
            Phase.SOLID
        ),
        Element(
            97,
            "Bk",
            "berklium",
            247.0703,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>9</sup> 7s<sup>2</sup>",
            "2 8 18 32 27 8 2",
            Phase.SOLID
        ),
        Element(
            98,
            "Cf",
            "californium",
            251.0796,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>10</sup> 7s<sup>2</sup>",
            "2 8 18 32 28 8 2",
            Phase.SOLID
        ),
        Element(
            99,
            "Es",
            "einsteinium",
            252.0829,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>11</sup> 7s<sup>2</sup>",
            "2 8 18 32 29 8 2",
            Phase.SOLID
        ),
        Element(
            100,
            "Fm",
            "fermium",
            257.0951,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>12</sup> 7s<sup>2</sup>",
            "2 8 18 32 30 8 2",
            Phase.SOLID
        ),
        Element(
            101,
            "Md",
            "mendelevium",
            258.0951,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>13</sup> 7s<sup>2</sup>",
            "2 8 18 32 31 8 2",
            Phase.SOLID
        ),
        Element(
            102,
            "No",
            "nobelium",
            259.1009,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>14</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 8 2",
            Phase.SOLID
        ),
        Element(
            103,
            "Lr",
            "lawrencium",
            266.1193,
            0,
            NONE,
            7,
            "f",
            "actinide",
            "[Rn] 5f<sup>14</sup> 7s<sup>2</sup> 7p<sup>1</sup>",
            "2 8 18 32 32 8 3",
            Phase.SOLID
        ),
        Element(
            104,
            "Rf",
            "rutherfordium",
            267.0,
            4,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>2</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 10 2",
            Phase.SOLID
        ),
        Element(
            105,
            "Db",
            "dubnium",
            262.0,
            5,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>3</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 11 2",
            Phase.SOLID
        ),
        Element(
            106,
            "Sg",
            "seaborgium",
            269.0,
            6,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>4</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 12 2",
            Phase.SOLID
        ),
        Element(
            107,
            "Bh",
            "bohrium",
            270.0,
            7,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>5</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 13 2",
            Phase.UNKNOWN
        ),
        Element(
            108,
            "Hs",
            "hassium",
            269.0,
            8,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>6</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 14 2",
            Phase.SOLID
        ),
        Element(
            109,
            "Mt",
            "meitnerium",
            278.0,
            8,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>7</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 15 2",
            Phase.SOLID
        ),
        Element(
            110,
            "Ds",
            "darmstadtium",
            281.1620,
            8,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>8</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 16 2",
            Phase.SOLID
        ),
        Element(
            111,
            "Rg",
            "roentgenium",
            281.1684,
            1,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>9</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 17 2",
            Phase.SOLID
        ),
        Element(
            112,
            "Cn",
            "copernicium",
            285.1744,
            2,
            B,
            7,
            "d",
            "transitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup>",
            "2 8 18 32 32 18 2",
            Phase.LIQUID_PREDICTED
        ),
        Element(
            113,
            "Nh",
            "nihonium",
            286.181,
            3,
            A,
            7,
            "p",
            "postTransitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>1</sup>",
            "2 8 18 32 32 18 3",
            Phase.SOLID
        ),
        Element(
            114,
            "Fl",
            "flerovium",
            289.1904,
            4,
            A,
            7,
            "p",
            "postTransitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>2</sup>",
            "2 8 18 32 32 18 4",
            Phase.LIQUID_PREDICTED
        ),
        Element(
            115,
            "Mc",
            "moscovium",
            290.1943,
            5,
            A,
            7,
            "p",
            "postTransitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>3</sup>",
            "2 8 18 32 32 18 5",
            Phase.SOLID
        ),
        Element(
            116,
            "Lv",
            "livermorium",
            293.2045,
            6,
            A,
            7,
            "p",
            "postTransitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>4</sup>",
            "2 8 18 32 32 18 6",
            Phase.SOLID
        ),
        Element(
            117,
            "Ts",
            "tennessine",
            294.2104,
            7,
            A,
            7,
            "p",
            "postTransitionMetal",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>5</sup>",
            "2 8 18 32 32 18 7",
            Phase.SOLID
        ),
        Element(
            118,
            "Og",
            "oganesson",
            294.2139,
            8,
            A,
            7,
            "p",
            "nobleGas",
            "[Rn] 5f<sup>14</sup> 6d<sup>10</sup> 7s<sup>2</sup> 7p<sup>6</sup>",
            "2 8 18 32 32 18 8",
            Phase.SOLID
        ),
    )
}