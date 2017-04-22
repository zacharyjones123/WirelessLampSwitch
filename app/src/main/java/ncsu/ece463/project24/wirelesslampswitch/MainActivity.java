package ncsu.ece463.project24.wirelesslampswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cutLampOn(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //connect, send it to cut on, leave
                try {
                    Socket clientSocket = new Socket();
                    clientSocket.connect(new InetSocketAddress("192.168.1.148", 4550), 200);
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    dos.write("LAMP ON\r".getBytes());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Successfully cut lamp on!", Toast.LENGTH_SHORT).show();
                            TextView tv = (TextView) findViewById(R.id.lampStatus);
                            tv.setText("Lamp is on!");
                        }
                    });
                    //dos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Unable to connect!", Toast.LENGTH_SHORT).show();
                            TextView tv = (TextView) findViewById(R.id.lampStatus);
                            tv.setText("Lamp is off!");
                        }
                    });

                }
            }
        }).start();
    }

    public void cutLampOff(View view) {
        new Thread(new Runnable() {
            @Override
            //connect, send it to cut on, leave
            public void run() {
                try {
                    Socket clientSocket = new Socket();
                    clientSocket.connect(new InetSocketAddress("192.168.1.148", 4550), 200);
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    dos.write("LAMP OFF\r".getBytes());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Successfully cut lamp off!", Toast.LENGTH_LONG).show();
                        }
                    });
                    //dos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Unable to connect!", Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }
        }

        ).start();
        }
    }
