package com.d3ifcool.parkirin.Backend

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ParkingViewModel : ViewModel() {

    private val _jumlahSlot = MutableStateFlow(0)
    val jumlahSlot: StateFlow<Int> = _jumlahSlot.asStateFlow()

    init {
        fetchSlotData()
    }

    private fun fetchSlotData() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("parkir/jumlah_slot")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val slotValue = snapshot.getValue(Int::class.java)
                if (slotValue != null) {
                    _jumlahSlot.value = slotValue
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Gagal membaca data: ${error.message}")
            }
        })
    }
}