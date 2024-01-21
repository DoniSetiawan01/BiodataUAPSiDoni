package com.example.biodatasidoni

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.biodatasidoni.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var profilBinding : ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilBinding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(profilBinding.root)
        ambilData()
        profilBinding.btnEditName.setOnClickListener { navigasiKeEditProfil() }
        profilBinding.btnEditAll.setOnClickListener { navigasiKeEditProfilAll() }
        profilBinding.btnCall.setOnClickListener { dialPhoneNumber(profilBinding.txtTelp.text.toString()) }
    }
    private fun ambilData(){
        val bundle = intent.extras
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")
        profilBinding.txtName.text = nama
        profilBinding.txtGender.text = gender
        profilBinding.txtEmail.text = email
        profilBinding.txtTelp.text = telp
        profilBinding.txtAddress.text = alamat
    }
    companion object {
        val REQUEST_CODE = 100
    }
    // start edit profil
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        intent.putExtra("nama", namaUser)
        resultLauncher.launch(intent)
    }
    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val result = result.data?.getStringExtra("nama")
                profilBinding.txtName.text = result
            } else {
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    // end edit profil
    // start edit all profil
    private fun navigasiKeEditProfilAll(){
        val intent = Intent(this, EditProfilAllActivity::class.java)
        val namaUser = profilBinding.txtName.text.toString()
        val genderUser = profilBinding.txtGender.text.toString()
        val emailUser = profilBinding.txtEmail.text.toString()
        val telpUser = profilBinding.txtTelp.text.toString()
        val alamatUser = profilBinding.txtAddress.text.toString()
        intent.putExtra("nama", namaUser)
        intent.putExtra("jenis kelamin", genderUser)
        intent.putExtra("email", emailUser)
        intent.putExtra("telp", telpUser)
        intent.putExtra("alamat", alamatUser)
        resultAllLauncher.launch(intent)
    }
    var resultAllLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultnama = result.data?.getStringExtra("nama")
                val resultgender = result.data?.getStringExtra("jenis kelamin")
                val resultemail = result.data?.getStringExtra("email")
                val resulttelp = result.data?.getStringExtra("telp")
                val resultalamat = result.data?.getStringExtra("alamat")
                profilBinding.txtName.text = resultnama
                profilBinding.txtGender.text = resultgender
                profilBinding.txtEmail.text = resultemail
                profilBinding.txtTelp.text = resulttelp
                profilBinding.txtAddress.text = resultalamat
            } else {
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    // end edit all profil
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}