package com.example.roteiro
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.roteiro.databinding.FragmentInicioBinding
class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = checkNotNull(_binding) {
        "No se puede acceder al binding porque es nulo. ¿Está visible la vista?"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
         * ARRAY ADAPTER:
         * El ArrayAdapter actúa como puente/intermediario entre la fuente de datos
         * (en este caso, un recurso de array de strings definido en XML) y la vista (el Spinner).
         */
        // 1. Construcción del ArrayAdapter usando el método de fábrica createFromResource.
        // - requireContext(): Proporciona el Contexto necesario para inflar layouts y acceder a recursos.
        // - R.array.provincias: Fuente de datos (string-array en res/values/arrays.xml).
        // - android.R.layout.simple_spinner_item: Diseño (layout) estándar para mostrar el elemento seleccionado dentro del Spinner.

        //ALTERNATIVA SIN KOTLIN: android:entries
        // Este Spinner se podría rellenar solo desde el XML, en fragment_inicio.xml:
        //
        //     <Spinner
        //         android:id="@+id/spProvincia"
        //         android:entries="@array/provincias"
        //         ... />
        //
        // y sobrarían las cinco líneas de abajo. Por dentro, el Spinner hace lo mismo que
        // ellas: crea un ArrayAdapter<CharSequence> con simple_spinner_item, le pone
        // simple_spinner_dropdown_item para la lista desplegada y se lo asigna a sí mismo.
        // Lo único que cambiaría en el listener: no habría variable "adaptador", así que
        // adaptador.getItem(position) pasaría a ser parent?.getItemAtPosition(position).
        //
        // Aquí vale porque la lista es FIJA (cuatro provincias en un recurso), es SOLO
        // TEXTO y se ve con los layouts que trae Android.
        //
        // NO valdría para el ListView de ListaFragment: sus datos dependen de la provincia
        // que llega por Safe Args, salen de Roteiro.kt (no de un recurso), son objetos
        // Lugar que hay que pasar a texto con map, y cada fila usa un layout propio
        // (fila_lugar.xml). Nada de eso se puede escribir en un XML.
        //
        // Lo hacemos en Kotlin porque es el caso más sencillo para aprender el patrón que
        // luego hace falta en la lista: contexto, datos, layout de fila y asignar.
        val adaptador = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.provincias,
            android.R.layout.simple_spinner_item
        )

        // Asigna el layout visual que se utilizará para cada elemento cuando se despliegue la lista flotante del Spinner.
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Conecta la instancia del ArrayAdapter con la vista Spinner a través del ViewBinding.
        binding.spProvincia.adapter = adaptador

        /*
         * EVENT LISTENER - AdapterView.OnItemSelectedListener:
         * Interfaz para registrar eventos cuando el usuario interactúa con un componenteAdapterView (el Spinner).
         * Requiere implementar un objeto anónimo de la interfaz AdapterView.OnItemSelectedListener.
         */
        binding.spProvincia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            /**
             * Callback que se ejecuta de forma asíncrona cuando el usuario selecciona una opción en el Spinner.
             * @param parent El AdapterView (Spinner) donde ocurrió el evento de selección.
             * @param view La View específica dentro del AdapterView que fue pulsada.
             * @param position Índice entero de la opción seleccionada dentro del adaptador (comienza en 0).
             * @param id Identificador de fila del elemento seleccionado (útil en adaptadores con BD/Cursor).
             */
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Recupera el elemento de datos correspondiente a la posición seleccionada mediante el ArrayAdapter.
                val provincia = adaptador.getItem(position)
                binding.tvEscolla.text = getString(R.string.escolliches, provincia)
            }

            /**
             * Callback obligatorio de la interfaz que se dispara cuando la selección desaparece del AdapterView
             * (por ejemplo, si el adaptador se vacía o la vista se destruye dinámicamente).
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        /*
         * EVENT LISTENER - View.OnClickListener:
         * Registra un listener que escucha eventos de pulsación/clic en la vista especificada (el Button btVerLugares).
         * Se implementa mediante una expresión lambda que recibe la vista pulsada como parámetro implícito.
         */
        binding.btVerLugares.setOnClickListener {
            val nome = binding.etNome.text.toString().trim()
            if (nome.isEmpty()) {
                Toast.makeText(requireContext(), R.string.falta_nome, Toast.LENGTH_SHORT).show()
            } else {
                /*
                 * SAFE ARGS (GENERACIÓN DE ACCIÓN CON ARGUMENTOS):
                 * InicioFragmentDirections es una clase autogenerada por el plugin Gradle de Safe Args
                 * a partir del grafo de navegación (nav_graph.xml).
                 * El método accionInicioALista(...) encapsula la acción de navegación hacia ListaFragment,
                 * empaquetando los parámetros de origen ('nome' y 'provincia') de forma fuertemente tipada
                 * en un objeto NavDirections. Esto previene errores de claves de texto o tipos incompatibles
                 * a diferencia del uso directo de un Bundle convencional.
                 */
                val accion = InicioFragmentDirections.accionInicioALista(
                    nome = nome,
                    provincia = binding.spProvincia.selectedItemPosition
                )

                /*
                 * JETPACK NAVIGATION (TRANSICIÓN ENTRE PANTALLAS):
                 * findNavController(): Método de extensión que obtiene la instancia del NavController
                 * encargado de gestionar las transiciones en el NavHostFragment.
                 * navigate(accion): Ejecuta la navegación y el cambio de Fragment hacia el destino
                 * pasando los datos definidos en la acción.
                 */
                findNavController().navigate(accion)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
