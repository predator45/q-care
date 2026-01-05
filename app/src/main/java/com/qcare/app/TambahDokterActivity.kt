package com.qcare.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.qcare.app.DashboardDokterActivity
import com.qcare.app.KelolaAntrianActivity
import com.qcare.app.R
import com.google.android.material.button.MaterialButton

class TambahDokterActivity : AppCompatActivity() {

    // FOTO OPSIONAL
    private var imageUri: Uri? = null

    // Image picker modern
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

        // =========================
        // INIT VIEW FORM
        // =========================
        imgPreview = findViewById(R.id.imgPreview)
        etNama = findViewById(R.id.etNamaDokter)
        etKategori = findViewById(R.id.etKategori)
        etTempat = findViewById(R.id.etTempat)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnChooseImage = findViewById<MaterialButton>(R.id.btnChooseImage)
        val btnTambahDokter = findViewById<MaterialButton>(R.id.btnTambahDokter)

        // =========================
        // BACK BUTTON
        // =========================
        btnBack.setOnClickListener {
            finish()
        }

        // =========================
        // CHOOSE IMAGE (OPSIONAL)
        // =========================
        btnChooseImage.setOnClickListener {
            pickImage.launch("image/*")
        }

        // =========================
        // SUBMIT FORM
        // =========================
        btnTambahDokter.setOnClickListener {

            val nama = etNama.text.toString().trim()
            val kategori = etKategori.text.toString().trim()
            val tempat = etTempat.text.toString().trim()

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

            // TODO: SIMPAN KE DATABASE / API
            // db.insertDoctor(nama, kategori, tempat, fotoDokter)

            Toast.makeText(
                this,
                "Dokter berhasil ditambahkan",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }

        // =========================
        // BOTTOM NAV (CUSTOM)
        // =========================
        val navHome = findViewById<View>(R.id.navHome)
        val navTambahDokter = findViewById<View>(R.id.navTambahDokter)
        val navKelolaAntrian = findViewById<View>(R.id.navKelolaAntrian)

        // set ACTIVE menu (Tambah Dokter)
        navTambahDokter.isSelected = true

        navHome.setOnClickListener {
            startActivity(Intent(this, DashboardDokterActivity::class.java))
            finish()
        }

        navTambahDokter.setOnClickListener {
            // sudah di halaman ini
        }

        navKelolaAntrian.setOnClickListener {
            startActivity(Intent(this, KelolaAntrianActivity::class.java))
            finish()
        }
    }
}