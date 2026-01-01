package com.example.qcare

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val doctors: MutableList<Doctor>
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvDoctorName)
        val tvSpec: TextView = itemView.findViewById(R.id.tvDoctorSpec)
        val tvReady: TextView = itemView.findViewById(R.id.tvReady)
        val tvFull: TextView = itemView.findViewById(R.id.tvFull)
        val tvManage: TextView = itemView.findViewById(R.id.tvManage)
        val btnDelete: View = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DoctorViewHolder,
        position: Int
    ) {
        val doctor = doctors[position]

        // =====================
        // TEXT
        // =====================
        holder.tvName.text = doctor.name
        holder.tvSpec.text = doctor.spec

        // =====================
        // STATUS UI (READY / FULL)
        // =====================
        if (doctor.isReady) {
            holder.tvReady.setBackgroundResource(R.drawable.bg_ready_active)
            holder.tvFull.setBackgroundResource(R.drawable.bg_full_inactive)
        } else {
            holder.tvReady.setBackgroundResource(R.drawable.bg_ready_inactive)
            holder.tvFull.setBackgroundResource(R.drawable.bg_full_active)
        }

        // =====================
        // READY CLICK
        // =====================
        holder.tvReady.setOnClickListener {
            if (!doctor.isReady) {
                doctor.isReady = true
                notifyItemChanged(position)
            }
        }

        // =====================
        // FULL CLICK
        // =====================
        holder.tvFull.setOnClickListener {
            if (doctor.isReady) {
                doctor.isReady = false
                notifyItemChanged(position)
            }
        }

        // =====================
        // MANAGE ANTRIAN
        // =====================
        holder.tvManage.setOnClickListener {
            val context = it.context
            val intent = Intent(context, KelolaAntrianActivity::class.java)
            intent.putExtra("doctor_id", doctor.id)
            context.startActivity(intent)
        }

        // =====================
        // DELETE DOKTER
        // =====================
        holder.btnDelete.setOnClickListener {
            doctors.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = doctors.size
}
