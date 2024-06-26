package cd.zgeniuscoders.bluethooth.view

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cd.zgeniuscoders.bluethooth.R
import cd.zgeniuscoders.bluethooth.adapter.DeviceAdapter
import cd.zgeniuscoders.bluethooth.databinding.ActivityMainBinding
import cd.zgeniuscoders.bluethooth.models.Device

class MainActivity : AppCompatActivity() {

    private val REQUEST_ENABLE_BT: Int = 100
    private var btPermissiom: Boolean = false

    private val devicesList: ArrayList<Device>? = null


    private lateinit var binding: ActivityMainBinding

    private val bluetoothPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val btManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
            val btAdapter: BluetoothAdapter = btManager.adapter
            btPermissiom = true
            if (btAdapter?.isEnabled == false) {
                val btIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                btActivityResultLauncer.launch(btIntent)
            } else {
                scanBT()
            }

        }
    }

    private val btActivityResultLauncer = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            scanBT()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.scanner.setOnClickListener {
            Toast.makeText(this, "Search....", Toast.LENGTH_LONG).show()
            val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
            val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()
            if (bluetoothAdapter == null) {
                Toast.makeText(this, "Device doesn't support Bluetooth", Toast.LENGTH_LONG).show()

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_CONNECT)
                } else {
                    bluetoothPermissionLauncher.launch(Manifest.permission.BLUETOOTH_ADMIN)
                }
            }
        }


    }

    private fun scanBT() {
        val btManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
        val btAdapter: BluetoothAdapter = btManager.adapter

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        } else {
            val pairedDevices: Set<BluetoothDevice> =
                btAdapter?.bondedDevices as Set<BluetoothDevice>
            if (pairedDevices.isNotEmpty()) {
                pairedDevices.forEach { device ->
                    val deviceModel = Device(device.name, device.address)
                    devicesList!!.add(deviceModel)
                }

                DeviceAdapter(this@MainActivity, devicesList!!)

            } else {
                Toast.makeText(this, "no device found", Toast.LENGTH_LONG).show()
            }
        }

    }

}