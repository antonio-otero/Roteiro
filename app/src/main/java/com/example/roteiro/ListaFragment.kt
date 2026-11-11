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
         * USO DE LOS ARGUMENTOS DE NAVEGACIÓN:
         * Acceso seguro a las propiedades 'provincia' y 'nome' recibidas mediante la propiedad 'args'.
         * - args.provincia: Índice entero de la provincia elegida, usado para consultar los datos en Roteiro.
         * - args.nome: Nombre introducido por el usuario en la pantalla de inicio.
         */
        val lugares = Roteiro.lugares(args.provincia)
        val nomeProvincia = resources.getStringArray(R.array.provincias)[args.provincia]
        binding.tvSaudo.text = getString(R.string.saudo, args.nome, nomeProvincia)

        /*
         * ARRAY ADAPTER:
         * Instanciación directa del adaptador ArrayAdapter para alimentar un ListView.
         * Actúa como puente que transforma una lista de objetos en memoria (cadenas de texto con los nombres de los lugares)
         * en elementos visuales individuales dentro del ListView.
         * Parámetros:
         * - requireContext(): El Contexto activo del Fragment necesario para inflar vistas.
         * - R.layout.fila_lugar: Layout XML personalizado que representa la estructura visual de cada fila del ListView.
         * - lugares.map { it.nome }: Fuente de datos, transformada en una List<String> con los nombres a mostrar.
         * 
         * Nota IMPORTANTE: Si se trata de una lista completamente estática definida en XML (arrays.xml),
         * se podría asignar directamente en el layout XML mediante la propiedad android:entries="@array/mi_array".
         * Cuando los datos son datos dinámicos/calculados en código, es necesario usar un ArrayAdapter en Kotlin.
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
