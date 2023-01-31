package com.example.iotproyect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.iotproyect.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    // Declaramos todos los elementos de FirstFragment
    private lateinit var bt_register:Button
    private lateinit var bt_entrar:Button
    private lateinit var con:Conexion
    private lateinit var et_name:EditText
    private lateinit var et_password:EditText

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Asociamos ese boton al de Registrarse para or al segundo Fragmen
        bt_register = view.findViewById(R.id.bt_register)
        bt_register.setOnClickListener{findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)}

        // Identificamos ambos campos de texto para sacar los datos y mandarlos al servidor
        et_name = view.findViewById(R.id.et_nickname)
        et_password = view.findViewById(R.id.et_password)

        // usamos el boto de entrar con lambda para mandar el nich y la pass
        bt_entrar = view.findViewById(R.id.bt_enter)
        bt_entrar.setOnClickListener{
            con = Conexion()
            Thread {
                con.login(et_name.text.toString(), et_password.text.toString())
            }.start()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}