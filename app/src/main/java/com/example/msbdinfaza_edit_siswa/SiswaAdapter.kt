package com.example.msbdinfaza_edit_siswa

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class SiswaAdapter (private val dataset: MutableList<SiswaData>):
    RecyclerView.Adapter<SiswaAdapter.SiswaHolder> (){
    class SiswaHolder (view: View): RecyclerView.ViewHolder(view) {
        val nis = view.findViewById<TextView>(R.id.DataNis)
        val nama = view.findViewById<TextView>(R.id.DataNama)
        val jekel = view.findViewById<TextView>(R.id.DataJK)
        val btnEdit = view.findViewById<Button>(R.id.btnEdit)
        val btnHapus = view.findViewById<Button>(R.id.btnHapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiswaHolder{
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_siswa_adapter,parent,false
        )
        return SiswaHolder(view)
    }


    override fun onBindViewHolder(holder: SiswaHolder, position: Int) {
        holder.nis.text = dataset[position].nis
        holder.nama.text =dataset[position].nama
        holder.jekel.text = dataset[position].jekel
        holder.btnHapus.setOnClickListener {
            dataset.removeAt(position)
            notifyItemRangeRemoved(position,dataset.size)
            notifyDataSetChanged()
        }

        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val inflater =LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_siswa,null)
            val enis = view.findViewById<TextView>(R.id.editNIS_Update)
            val enama = view.findViewById<TextView>(R.id.editNama_Update)
            val jk = view.findViewById<TextView>(R.id.TVjekel)
            val ejekelLaki = view.findViewById<RadioButton>(R.id.rbLakiLaki_Update)
            val  ejekelPr = view.findViewById<RadioButton>(R.id.rbPerempuan_Update)
            //
            enis.text = dataset[position].nis
            enama.text = dataset[position].nama
            jk.text = dataset[position].jekel

            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Data")
                .setView(view)
                .setPositiveButton("Update",DialogInterface.OnClickListener{dialogInterface, i ->
                    dataset[position].nis =enis.text.toString()
                    dataset[position].nama = enama.text.toString()
                    if (ejekelLaki.isChecked){
                        dataset[position].jekel = "Laki-laki"
                    }else{
                        dataset[position].jekel = "Perempuan"
                    }
                    notifyDataSetChanged()
                })
                .setNegativeButton("Batal",DialogInterface.OnClickListener{dialogInterface, i ->})
            //menampilkan halaman edit yang berupa alertdialog
            alertDialog.create().show()

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}