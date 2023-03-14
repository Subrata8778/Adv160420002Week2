package com.ubaya.adv160420002week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnStart = view.findViewById<Button>(R.id.btnStart)
        var txtName = view.findViewById<EditText>(R.id.txtName)
        btnStart.setOnClickListener {
            val playerName = txtName.text.toString()
            if(playerName.isEmpty()){
                Snackbar.make(view, "Player Name cannot be empty", Snackbar.LENGTH_SHORT).show()
            } else{
                var playerScore = 0
                val action = MainFragmentDirections.actionGameFragment(playerName, playerScore)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }
}