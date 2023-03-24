package com.eve.ricknmorty

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eve.ricknmorty.databinding.ActivityMainBinding
import com.eve.ricknmorty.ui.character.CharacterFragment
import com.eve.ricknmorty.ui.episode.EpisodeFragment
import com.eve.ricknmorty.ui.location.LocationFragment
import com.eve.ricknmorty.utils.MenuApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isCharacter = MenuApp.CHARACTER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragment()

        /* Not Best Practice (?) */
        callFragment(
            fragment = CharacterFragment(),
            menuApp = MenuApp.CHARACTER,
            menu = R.id.menuCharacter
        )

        onBackPressedDispatcher.addCallback(this, onBackPressed)
    }

    private val onBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isCharacter == MenuApp.CHARACTER) {
                finishAffinity()
            } else {
                binding.bottomNavigation.setItemSelected(R.id.menuCharacter)
                callFragment(
                    fragment = CharacterFragment(),
                    menuApp = MenuApp.CHARACTER,
                    menu = R.id.menuCharacter
                )
            }
        }
    }

    private fun setupFragment() {
//        binding.bottomNavigation.setItemSelected(R.id.menuCharacter)
        binding.bottomNavigation.setOnItemSelectedListener { nav ->
            when (nav) {
                R.id.menuCharacter -> {
                    callFragment(
                        fragment = CharacterFragment(),
                        menuApp = MenuApp.CHARACTER,
                        R.id.menuCharacter
                    )
                }
                R.id.menuEpisode -> {
                    callFragment(
                        fragment = EpisodeFragment(),
                        menuApp = MenuApp.EPISODE,
                        R.id.menuEpisode
                    )
                }
                R.id.menuLocation -> {
                    callFragment(
                        fragment = LocationFragment(),
                        menuApp = MenuApp.LOCATION,
                        R.id.menuLocation
                    )
                }
            }
        }
    }

    private fun callFragment(fragment: Fragment, menuApp: MenuApp, menu: Int) {
        isCharacter = menuApp
        binding.bottomNavigation.setItemSelected(menu)
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }
}