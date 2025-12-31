package com.example.qcare

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TambahDokterActivity : AppCompatActivity() {

    // FOTO OPSIONAL
    private var imageUri: Uri? = null

    // Image picker modern (AMAN Android terbaru)
    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            if (uri != null) {
                imgPreview.setImageURI(uri)
                imgPreview.visibility = View.VISIBLE
            }
        }

    // View
    private lateinit var imgPreview: ImageView
    private lateinit var etNama: EditText
    private lateinit var etKategori: EditText
    private lateinit var etTempat: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_dokter)

        // INIT VIEW
        imgPreview = findViewById(R.id.imgPreview)
        etNama = findViewById(R.id.etNamaDokter)
        etKategori = findViewById(R.id.etKategori)
        etTempat = findViewById(R.id.etTempat)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnChooseImage = findViewById<MaterialButton>(R.id.btnChooseImage)
        val btnTambahDokter = findViewById<MaterialButton>(R.id.btnTambahDokter)

        // BACK
        btnBack.setOnClickListener {
            finish()
        }

        // CHOOSE IMAGE (OPSIONAL)
        btnChooseImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        // SUBMIT
        btnTambahDokter.setOnClickListener {

            val nama = etNama.text.toString().trim()
            val kategori = etKategori.text.toString().trim()
            val tempat = etTempat.text.toString().trim()

            // VALIDASI (FOTO TIDAK WAJIB)
            if (nama.isEmpty() || kategori.isEmpty() || tempat.isEmpty()) {
                Toast.makeText(
                    this,
                    "Nama, kategori, dan tempat kerja wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // FOTO OPSIONAL
            val fotoDokter: String? = imageUri?.toString()

            // 🔥 NANTI DI SINI SIMPAN KE DATABASE
            // contoh:
            // db.insertDoctor(nama, kategori, tempat, fotoDokter)

            Toast.makeText(
                this,
                "Dokter berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()

            finish()


        }
    }
}
