package com.ubaya.adv160420002week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        val txtAnswer = view.findViewById<EditText>(R.id.txtAnswer)
        var playerScore: Int = 0
        var playerName: String = ""
        var randNum1 = (0..100).random()
        var randNum2 = (0..100).random()
        var num1 = view.findViewById<TextView>(R.id.txtNum1)
        var num2 = view.findViewById<TextView>(R.id.txtNum2)
        num1.text = randNum1.toString()
        num2.text = randNum2.toString()

        if(arguments != null) {
            playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
            playerScore =
                GameFragmentArgs.fromBundle(requireArguments()).playerScore
        }
        btnSubmit.setOnClickListener {
            var result: Int = randNum1 + randNum2
            if(txtAnswer.text.toString() == ""){
                Snackbar.make(view, "Answer cannot be empty", Snackbar.LENGTH_SHORT).show()
            }else{
                var answer = txtAnswer.text.toString().toInt()
                if(answer == result){
                    randNum1 = (0..100).random()
                    randNum2 = (0..100).random()
                    num1.text = randNum1.toString()
                    num2.text = randNum2.toString()
                    playerScore += 1
                    val action = GameFragmentDirections.actionGameToGameFragment(playerName, playerScore)
                    Navigation.findNavController(it).navigate(action)
                }else{
                    val action = GameFragmentDirections.actionResultFragment(playerScore)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
}