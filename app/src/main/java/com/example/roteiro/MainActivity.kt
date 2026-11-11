package com.example.roteiro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.roteiro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Declaramos el binding
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        //inflamos el layout
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * INTEGRACIÓN DE LA TOOLBAR CON JETPACK NAVIGATION:
         * 1. Obtenemos la referencia al NavHostFragment (el contenedor en el layout XML que alberga los Fragments).
         *    - supportFragmentManager.findFragmentById(R.id.nav_host): Busca el Fragment por su id.
         *    - 'as NavHostFragment': Realiza el casteo seguro a NavHostFragment para acceder a su navController.
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        /*
         * 2. Vinculamos la Toolbar con el NavController mediante el método setupWithNavController:
         *    - Actualiza dinámicamente el título de la Toolbar usando la etiqueta 'android:label' definida en cada destino del nav_graph.xml.
         *    - Muestra automáticamente el botón de "Atrás" (flecha Up) al navegar a un destino secundario (ej: ListaFragment)
         *      y lo oculta en el destino raíz (ej: InicioFragment).
         *    - Gestiona automáticamente el comportamiento del botón atrás al ser pulsado sin necesidad de implementar onSupportNavigateUp().
         */
        binding.toolbar.setupWithNavController(navHostFragment.navController)

    }
}