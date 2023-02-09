package com.example.msbdinfaza_edit_siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var InputNis : EditText
    private lateinit var InputNama : EditText
    private lateinit var jklakilaki : RadioButton
    private lateinit var jkPerempuan : RadioButton
    private lateinit var tambahData : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InputNis = findViewById(R.id.txtInputNIS)
        InputNama = findViewById(R.id.txtInputNama)
        jklakilaki = findViewById(R.id.rbLakiLaki)
        jkPerempuan = findViewById(R.id.rbPerempuan)
        tambahData = findViewById(R.id.btnTambah)
        recyclerView = findViewById(R.id.listData)
        // membuat variabel kosong tipe array mutableList untuk simpan data array
        // data array disimpan di class siswaData
        val data = mutableListOf<SiswaData>()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = SiswaAdapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = viewManager
        // setOnClickListener untuk tombol tambah data
        tambahData.setOnClickListener {
            // 1. membuat variabel penyimpanan data
            val nis = InputNis.text.toString()
            val nama = InputNama.text.toString()
            var jk = ""
            if (jklakilaki.isChecked) {
                jk = "laki-laki"
            } else {
                jk = "Perempuan"
            }
            //simpan data ke array mutableList
            val dataSiswa = SiswaData(nis, nama, jk)
            data.add(dataSiswa)
            recyclerAdapter.notifyDataSetChanged()
        }

    }
}