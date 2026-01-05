package com.qcare.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.qcare.app.R
import com.google.android.material.button.MaterialButton

class RequestAdapter(
    private val requests: MutableList<RequestAntrian>
) : RecyclerView.Adapter<RequestAdapter.RequestViewHolder>() {

    inner class RequestViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvInfo: TextView = itemView.findViewById(R.id.tvInfo)
        val btnKonfirmasi: MaterialButton = itemView.findViewById(R.id.btnKonfirmasi)
        val btnTolak: MaterialButton = itemView.findViewById(R.id.btnTolak)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_request, parent, false)
        return RequestViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RequestViewHolder,
        position: Int
    ) {
        val item = requests[position]

        holder.tvNama.text = item.namaPasien
        holder.tvInfo.text = "${item.dokter}, Kode (${item.kode})"

        holder.btnKonfirmasi.setOnClickListener {
            item.status = RequestStatus.DITERIMA
            Toast.makeText(
                holder.itemView.context,
                "Antrian dikonfirmasi",
                Toast.LENGTH_SHORT
            ).show()
            notifyItemChanged(position)
        }

        holder.btnTolak.setOnClickListener {
            item.status = RequestStatus.DITOLAK
            Toast.makeText(
                holder.itemView.context,
                "Antrian ditolak",
                Toast.LENGTH_SHORT
            ).show()
            requests.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = requests.size
}