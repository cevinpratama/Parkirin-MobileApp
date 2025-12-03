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

    private val _daftarSlot = MutableStateFlow<List<SlotParkir>>(emptyList())
    val daftarSlot: StateFlow<List<SlotParkir>> = _daftarSlot.asStateFlow()

    init {
        fetchAllParkingData()
    }

    private fun fetchAllParkingData() {
        val database = FirebaseDatabase.getInstance()

        val myRef = database.getReference("parkirin")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val slotValue = snapshot.child("jumlah_slot").getValue(Int::class.java)
                if (slotValue != null) {
                    _jumlahSlot.value = slotValue
                }

                val tempList = mutableListOf<SlotParkir>()

                for (i in 1..5) {
                    val key = "slot_$i"

                    val status = snapshot.child(key).getValue(String::class.java) ?: "Tidak Diketahui"

                    tempList.add(SlotParkir(id = key, status = status))
                }

                _daftarSlot.value = tempList
            }

            override fun onCancelled(error: DatabaseError) {
                println("Gagal membaca data: ${error.message}")
            }
        })
    }
}

data class SlotParkir(
    val id: String,
    val status: String
)