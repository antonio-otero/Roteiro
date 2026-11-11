package com.example.roteiro

object Roteiro {
    private val catalogo: List<List<Lugar>> = listOf(
        listOf(
            Lugar("Torre de Hércules", "A Coruña",
                "Faro romano aínda en funcionamento. Patrimonio da Humanidade desde 2009."),
            Lugar("Praza do Obradoiro", "Santiago de Compostela",
                "A praza onde remata o Camiño, fronte á fachada da catedral."),
            Lugar("Cabo Fisterra", "Fisterra",
                "Durante séculos considerouse o fin do mundo coñecido.")
        ),
        listOf(
            Lugar("Muralla de Lugo", "Lugo",
                "Muralla romana completa. Patrimonio da Humanidade desde 2000."),
            Lugar("Praia das Catedrais", "Ribadeo",
                "Arcos de rocha que só se poden percorrer coa marea baixa."),
            Lugar("O Cebreiro", "Pedrafita do Cebreiro",
                "Aldea de pallozas na entrada do Camiño Francés en Galicia.")
        ),
        listOf(
            Lugar("As Burgas", "Ourense",
                "Fontes termais no centro da cidade, con auga a máis de 60 graos."),
            Lugar("Mosteiro de Santo Estevo", "Nogueira de Ramuín",
                "Mosteiro beneditino na Ribeira Sacra, hoxe convertido en parador."),
            Lugar("Castro de San Cibrao de Las", "San Amaro",
                "Un dos castros máis grandes de Galicia.")
        ),
        listOf(
            Lugar("Illas Cíes", "Vigo",
                "Parte do Parque Nacional das Illas Atlánticas, coa praia de Rodas."),
            Lugar("Mosteiro de Armenteira", "Meis",
                "Mosteiro cisterciense onde comeza a Ruta da Pedra e da Auga."),
            Lugar("Combarro", "Poio",
                "Vila mariñeira con hórreos á beira do mar.")
        )
    )
    fun lugares(provincia: Int): List<Lugar> = catalogo[provincia]
}
