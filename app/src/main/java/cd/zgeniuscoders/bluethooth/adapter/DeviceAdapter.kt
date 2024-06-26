package cd.zgeniuscoders.bluethooth.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import cd.zgeniuscoders.bluethooth.databinding.ItemDeviceBinding
import cd.zgeniuscoders.bluethooth.models.Device

class DeviceAdapter(private val context: Context,private val devicesList: List<Device>): Adapter<DeviceAdapter.DeviceViewHolder>() {

    inner class DeviceViewHolder(itemDeviceBinding: ItemDeviceBinding) :
        ViewHolder(itemDeviceBinding.root) {
        val binding = ItemDeviceBinding.bind(itemDeviceBinding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            ItemDeviceBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int = devicesList.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devicesList[position]
        holder.binding.deviceName.text = device.name
    }

}