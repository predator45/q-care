package com.example.qcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class AntrianAdapter(
    private val items: MutableList<Antrian>
) : RecyclerView.Adapter<AntrianAdapter.AntrianViewHolder>() {

    inner class AntrianViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvNomor: TextView = itemView.findViewById(R.id.tvNomor)
        val tvEstimasi: TextView = itemView.findViewById(R.id.tvEstimasi)
        val tvTiba: TextView = itemView.findViewById(R.id.tvTiba)
        val btnPanggil: MaterialButton = itemView.findViewById(R.id.btnPanggil)
        val btnSelesai: MaterialButton = itemView.findViewById(R.id.btnSelesai)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AntrianViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_antrian, parent, false)
        return AntrianViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: AntrianViewHolder,
        position: Int
    ) {
        val item = items[position]

        holder.tvNomor.text = item.nomor
        holder.tvEstimasi.text =
            if (item.estimasiMenit == 0) "-" else "${item.estimasiMenit} Menit"

        holder.tvTiba.text =
            if (item.sudahSampai) "Sudah Sampai" else "Belum Sampai"

        // PANGGIL HANYA AKTIF JIKA SUDAH SAMPAI
        holder.btnPanggil.isEnabled = item.sudahSampai
        holder.btnPanggil.alpha = if (item.sudahSampai) 1f else 0.4f

        holder.btnPanggil.setOnClickListener {
            item.sedangDipanggil = true
            notifyItemChanged(position)
        }

        holder.btnSelesai.setOnClickListener {
            item.selesai = true
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = items.size
}
