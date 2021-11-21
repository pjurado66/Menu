package com.example.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.Menu.NONE
import android.widget.PopupMenu
import com.example.menu.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        registerForContextMenu(binding.texto)
        binding.moreActionsButton.setOnClickListener {
            mostrarAcciones(it)
        }
    }

    private fun mostrarAcciones(button: View) {
        val popupMenu = PopupMenu(this, button)
        menuInflater.inflate(R.menu.menu_pop, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(::manageItemClick)
        popupMenu.setOnDismissListener(::manageDismiss)
        popupMenu.show()
    }

    private fun manageItemClick(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.salir, R.id.grabar -> {
                Snackbar.make(binding.root, "Popup", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        menu.add(NONE, 99, NONE, "Nueva opcion")
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.new_game -> {
                Snackbar.make(binding.root, "Empieza el juego", Snackbar.LENGTH_SHORT).show()
                true
            }
            R.id.help -> {
                Snackbar.make(binding.root, "Ayuda", Snackbar.LENGTH_SHORT).show()
                true
            }
            99 ->{
                Snackbar.make(binding.root, "Nueva opción", Snackbar.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.menu, menu)
        menu.setHeaderTitle("Description")
    }
}

fun manageDismiss(popupMenu: PopupMenu?) {

}
