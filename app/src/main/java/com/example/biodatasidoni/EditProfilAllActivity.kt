package com.example.biodatasidoni

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.biodatasidoni.databinding.ActivityEditProfilAllBinding

class EditProfilAllActivity : AppCompatActivity() {
    private lateinit var editAllBinding : ActivityEditProfilAllBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editAllBinding = ActivityEditProfilAllBinding.inflate(layoutInflater)
        setContentView(editAllBinding.root)
        //menerima data yang dikirimkan dari ProfilActivity.kt
        val intentData = intent.extras
        val namaUser = intentData?.getString("nama")
        val genderUser = intentData?.getString("gender")
        val emailUser = intentData?.getString("email")
        val telpUser = intentData?.getString("telp")
        val alamatUser = intentData?.getString("alamat")
        //set edittext dengan data yang dikirimkan tadi
        editAllBinding.edtName.setText(namaUser)
        editAllBinding.edtEmail.setText(emailUser)
        editAllBinding.edtTelp.setText(telpUser)
        editAllBinding.edtAddress.setText(alamatUser)
        // Di bagian inisialisasi activity atau fragment
        val genderOptions = arrayOf("Laki-laki", "Perempuan") // Gantilah dengan opsi gender yang sesuai
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        editAllBinding.spinnerGender.adapter = adapter
        editAllBinding.spinnerGender.setSelection(adapter.getPosition(genderUser))
        //memberikan action click ke tombol Simpan
        editAllBinding.btnSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val namaEdit = editAllBinding.edtName.text.toString()
        val emailEdit = editAllBinding.edtEmail.text.toString()
        val telpEdit = editAllBinding.edtTelp.text.toString()
        val alamatEdit = editAllBinding.edtAddress.text.toString()
        val genderEdit = editAllBinding.spinnerGender.selectedItem.toString()
        if (!namaEdit.isEmpty()) {
            //jika nama,email,telp,alamat,gender tidak kosong, maka kirimkan value nya ke ProfilActivity, dan beri tanda RESULT_OK
            val result = Intent()
            result.putExtra("nama", namaEdit)
            result.putExtra("email", emailEdit)
            result.putExtra("telp", telpEdit)
            result.putExtra("alamat", alamatEdit)
            result.putExtra("gender", genderEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
            //jika editText namaEdit kosong, maka kirimkan tanda
            RESULT_CANCELED

            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}


