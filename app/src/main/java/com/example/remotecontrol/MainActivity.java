package com.example.remotecontrol;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button bUp;
    Button bDown;
    Button bLeft;
    Button bRight;
    SeekBar spd;
    public static String ip = "http://156.67.221.101:";
    public static String port = "8001/";
    public static String url = ip+port;
    public int Id;
    public int Horizontal;
    public int Vertikal;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Starting();
        bUp=findViewById(R.id.Up);
        bDown=findViewById(R.id.Down);
        bLeft=findViewById(R.id.Left);
        bRight=findViewById(R.id.Right);
        //TextView kal=(TextView)findViewById(R.id.angka);
        //TextView hor=(TextView)findViewById(R.id.angka2);
        spd=findViewById(R.id.seekBar);
        spd.setProgress(0);
        spd.setMax(200);
        spd.setMin(10);
        Id=1;
        Horizontal=1;
        Vertikal=1;
        bUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vertikal=Vertikal+5;
                String pesan=Integer.toString(Vertikal);

                //kal.setText(pesan);

            }
        });
        bDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vertikal=Vertikal-5;
                String pesan=Integer.toString(Vertikal);
                //kal.setText(pesan);
            }
        });
        bRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Horizontal=Horizontal+5;
                String pesan=Integer.toString(Horizontal);
                //hor.setText(pesan);
                uploadH(Id,Horizontal);

            }
        });
        bLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Horizontal=Horizontal-5;
                String pesan=Integer.toString(Horizontal);
                uploadH(Id,Horizontal);
                //hor.setText(pesan);
            }
        });
        spd.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress=progress/10;
                progress=progress*10;
                Toast.makeText(getApplicationContext(), "seekbar value:"+progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void Starting(){
        final Retrofit retrofi=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        PostObj obj=new PostObj(1);
        jsonplaceholder jasonplaceholder=retrofi.create(jsonplaceholder.class);
        Call<PostObj>call=jasonplaceholder.postobj(obj);
        call.enqueue(new Callback<PostObj>() {
            @Override
            public void onResponse(Call<PostObj> call, Response<PostObj> response) {
                PostObj obj1=response.body();
                String pesan=obj1.getMessage();
                int code=response.code();
                Toast.makeText(getApplicationContext(),pesan,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostObj> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadH(int Id, int Horizontal){
        final Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        postHorizontal postHorizontal=new postHorizontal(Id, Horizontal);
        jsonplaceholder jsonplaceholder=retrofit.create(com.example.remotecontrol.jsonplaceholder.class);
        Call<postHorizontal>call=jsonplaceholder.posthorizontal(postHorizontal);
        call.enqueue(new Callback<com.example.remotecontrol.postHorizontal>() {
            @Override
            public void onResponse(Call<com.example.remotecontrol.postHorizontal> call, Response<com.example.remotecontrol.postHorizontal> response) {
                if(response.code()==204){
                    Toast.makeText(getApplicationContext(),"No-Object",Toast.LENGTH_SHORT).show();
                }
                else{
                    postHorizontal postHorizontal1 = response.body();
                    String m = postHorizontal1.getMessage();
                    Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.remotecontrol.postHorizontal> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
