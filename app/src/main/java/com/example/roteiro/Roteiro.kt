package com.example.roteiro

/*
 * PATRÓN SINGLETON EN KOTLIN ('object'):
 * El patrón Singleton es un patrón de diseño creacional que garantiza que una clase tenga
 * una ÚNICA instancia en toda la aplicación y proporciona un punto de acceso global a ella.
 *
 * En Kotlin, se implementa directamente usando la palabra clave 'object' (en lugar de 'class').
 * El compilador crea e inicializa automáticamente una única instancia de forma segura (thread-safe) y perezosa (lazy).
 *
 * ¿CUÁNDO SE USA?
 * 1. Para repositorios o catálogos de datos estáticos en memoria (como este catálogo de lugares turísticos).
 * 2. Para clases de utilidad, gestores de configuración o fuentes de datos globales que no requieren múltiples instancias.
 * 3. Evita el gasto de memoria de crear nuevos objetos cada vez y previene inconsistencias de datos.
 *
 * ¿DÓNDE Y CÓMO SE USA EN ESTA APP?
 * Se utiliza en ListaFragment.kt mediante la llamada directa 'Roteiro.lugares(args.provincia)',
 * sin necesidad de instanciar la clase mediante un constructor ('val r = Roteiro()').
 */
object Roteiro {
    // Lista privada de listas de objetos Lugar, organizada por el índice de provincia (0: A Coruña, 1: Lugo, 2: Ourense, 3: Pontevedra).
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

    /**
     * Devuelve la lista de lugares turísticos pertenecientes al índice de provincia indicado.
     * @param provincia Índice entero de la provincia (0 a 3).
     * @return Lista de objetos Lugar correspondientes.
     */
    fun lugares(provincia: Int): List<Lugar> = catalogo[provincia]
}
