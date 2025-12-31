package com.example.qcare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val doctors: List<Doctor>
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.tvDoctorName)
        val tvSpec: TextView = itemView.findViewById(R.id.tvDoctorSpec)
        val tvReady: TextView = itemView.findViewById(R.id.tvReady)
        val tvFull: TextView = itemView.findViewById(R.id.tvFull)
        val tvManage: TextView = itemView.findViewById(R.id.tvManage)
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

        // TEXT
        holder.tvName.text = doctor.name
        holder.tvSpec.text = doctor.spec

        // STATUS
        if (doctor.isReady) {
            holder.tvReady.setBackgroundResource(R.drawable.bg_ready_active)
            holder.tvFull.setBackgroundResource(R.drawable.bg_full_inactive)
        } else {
            holder.tvReady.setBackgroundResource(R.drawable.bg_ready_inactive)
            holder.tvFull.setBackgroundResource(R.drawable.bg_full_active)
        }
    }

    override fun getItemCount(): Int = doctors.size
}
