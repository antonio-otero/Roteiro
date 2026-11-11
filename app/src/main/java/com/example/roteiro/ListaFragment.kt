package com.example.roteiro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.roteiro.databinding.FragmentListaBinding
class ListaFragment : Fragment() {
    /*
     * SAFE ARGS (RECEPCIÓN DE ARGUMENTOS ENTREGADOS):
     * 'by navArgs()': Delegado de propiedad (Property Delegate) de Kotlin proporcionado por Jetpack Navigation.
     * Obtiene de forma perezosa (lazy) la instancia de la clase autogenerada 'ListaFragmentArgs'.
     * Esta clase abstrae la extracción de datos desde los 'arguments' (Bundle) del Fragment,
     * permitiendo acceder a los parámetros pasados desde InicioFragment con total seguridad de tipos
     * (p. ej., args.provincia de tipo Int y args.nome de tipo String).
     */
    private val args: ListaFragmentArgs by navArgs()
    private var _binding: FragmentListaBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "No se puede acceder al binding porque es nulo. ¿Está visible la vista?"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
         * RECUPERACIÓN DE DATOS DESDE EL SINGLETON ('Roteiro'):
         * Accedemos de forma directa al objeto Singleton 'Roteiro' (declarado con 'object Roteiro') sin instanciarlo.
         * Llamamos al método 'Roteiro.lugares(args.provincia)' pasando el índice recuperado de los argumentos Safe Args.
         * Este método consulta el catálogo en memoria y devuelve una lista de objetos 'List<Lugar>'.
         */
        val lugares = Roteiro.lugares(args.provincia)
        val nomeProvincia = resources.getStringArray(R.array.provincias)[args.provincia]
        binding.tvSaudo.text = getString(R.string.saudo, args.nome, nomeProvincia)

        /*
         * ARRAY ADAPTER Y TRANSFORMACIÓN DE DATOS CON .map:
         *
         * EL MÉTODO .map { it.nome }:
         * '.map' es una función de orden superior de Kotlin que transforma cada elemento de una colección original.
         * Toma la lista de objetos complejos 'List<Lugar>' y proyecta/extrae únicamente la propiedad 'nome' de cada objeto.
         * Como resultado, genera una nueva lista de cadenas de texto 'List<String>' de la misma longitud.
         *
         * NOTA DE SINTAXIS (it vs parámetro explícito):
         * 'it.nome' es exactamente equivalente a escribir '{ lugar -> lugar.nome }'.
         * En Kotlin, cuando una función lambda recibe UN SÓLO parámetro, no es necesario nombrarlo explícitamente;
         * el lenguaje le asigna automáticamente el nombre implícito 'it'.
         *
         * ¿POR QUÉ SE USA AQUÍ?
         * El ArrayAdapter requiere una lista de textos ('List<String>') para asignarlos automáticamente
         * al TextView de la fila ('R.layout.fila_lugar'). El método '.map' permite extraer la propiedad deseada
         * sin modificar los objetos originales del Singleton.
         *
         * Parámetros del ArrayAdapter:
         * - requireContext(): Contexto activo del Fragment necesario para inflar vistas.
         * - R.layout.fila_lugar: Layout XML personalizado para cada fila del ListView.
         * - lugares.map { it.nome }: Lista de Strings obtenida al transformar la colección recuperada del Singleton.
         */
        binding.lvLugares.adapter = ArrayAdapter(
            requireContext(),
            R.layout.fila_lugar,
            lugares.map { it.nome }
        )

        /*
         * EVENT LISTENER - AdapterView.OnItemClickListener:
         * Listener exclusivo de vistas basadas en AdapterView (como ListView o GridView).
         * Captura la interacción cuando el usuario hace clic sobre una fila individual de la lista.
         * Parámetros de la lambda:
         * - _ (parent): El AdapterView (ListView) donde ocurrió el clic (se descarta con '_').
         * - _ (view): La vista física (View) correspondiente a la fila pulsada (se descarta con '_').
         * - posicion: El índice entero de la fila seleccionada dentro del adaptador (base 0).
         * - _ (id): El identificador numérico de la fila seleccionada (se descarta con '_').
         */
        binding.lvLugares.setOnItemClickListener { _, _, posicion, _ ->
            val lugar = lugares[posicion]
            binding.tvDescricion.text =
                getString(R.string.ficha_lugar, lugar.nome, lugar.concello, lugar.descricion)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
