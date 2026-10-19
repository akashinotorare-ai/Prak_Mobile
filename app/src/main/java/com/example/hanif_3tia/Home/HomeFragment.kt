package com.example.hanif_3tia.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hanif_3tia.Home.pertemuan_5.LimaActivity
import com.example.hanif_3tia.Home.pertemuan_6.EnamActivity
import com.example.hanif_3tia.LoginActivity
import com.example.hanif_3tia.R
import com.example.hanif_3tia.databinding.ActivityMainBinding
import com.example.hanif_3tia.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        val user = requireActivity().intent.getStringExtra("Username")
        val pass = requireActivity().intent.getStringExtra("Password")
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.txtUsername.text = user
        binding.txtPassword.setText(pass)

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(binding.root, "Mw Log Out ?",
                Snackbar.LENGTH_LONG)
                .setAction("Log Out") {
//                Kembalikan Item
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(requireContext(), "Kembali ke halaman Login", Toast.LENGTH_LONG).show()
                }
                .show()
        }

        binding.btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext()).setTitle("Hapus Data")
                .setMessage("Data yang dihapus tidak bisa dikembalikan")
                .setNegativeButton("Batal", ){dialog, _->

                }
                .setPositiveButton("Hapus") {dialog, _->

                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }

        binding.btnToLima.setOnClickListener {
            startActivity(Intent(requireContext(), LimaActivity::class.java))
        }
        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()
                    dialog.dismiss()
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }
        binding.btntoEnam.setOnClickListener {
            startActivity(Intent(requireContext(), EnamActivity::class.java))
        }
    }
}