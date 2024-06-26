package cd.zgeniuscoders.bluethooth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.bluethooth.databinding.ItemDeviceBinding
import cd.zgeniuscoders.bluethooth.models.Device

class DeviceAdapter(private val context: Context,private val devicesList: List<Device>): Adapter<DeviceAdapter.DeviceViewHolder>() {

    inner class DeviceViewHolder(private val binding: ItemDeviceBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            ItemDeviceBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int = devicesList.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}