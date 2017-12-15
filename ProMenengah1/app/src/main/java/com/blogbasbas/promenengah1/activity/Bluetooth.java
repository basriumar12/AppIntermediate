package com.blogbasbas.promenengah1.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.blogbasbas.promenengah1.R;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Bluetooth extends AppCompatActivity {

    @BindView(R.id.btnOn)
    Button btnOn;
    @BindView(R.id.btnOf)
    Button btnOf;
    @BindView(R.id.btnList)
    Button btnList;
    @BindView(R.id.btnVisible)
    Button btnVisible;
    @BindView(R.id.lisview)
    ListView lisview;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);
        BA = (BluetoothAdapter.getDefaultAdapter());
    }
    @OnClick({R.id.btnOn, R.id.btnOf, R.id.btnList, R.id.btnVisible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOn:
              if (BA.isEnabled()){
                  Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                  startActivityForResult(turnOn,2);
                  Toast.makeText(getApplicationContext(), "bluetooth hidup", Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(getApplicationContext(), "bluetooth telah hidup", Toast.LENGTH_SHORT).show();
              }
                break;
            case R.id.btnOf:
                BA.disable();
                Toast.makeText(getApplicationContext(), "mematikan bluetoooth", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnList:
                try {
                    pairedDevices = BA.getBondedDevices();
                    ArrayList list = new ArrayList();
                    for (BluetoothDevice bt : pairedDevices)
                        list.add(bt.getName());
                    Toast.makeText(getApplicationContext(), "menampilkan perangkat bluetooth", Toast.LENGTH_SHORT).show();

                    final ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
                    lisview.setAdapter(adpater);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnVisible:
                Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(getVisible, 2);
                break;
        }
    }
}
