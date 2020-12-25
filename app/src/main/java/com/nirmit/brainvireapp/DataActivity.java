package com.nirmit.brainvireapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nirmit.brainvireapp.network.APIclient;
import com.nirmit.brainvireapp.model.ImageResponse;
import com.nirmit.brainvireapp.usage.Common;
import com.squareup.picasso.Picasso;

import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {


    private List<ImageResponse> imageResponseList = new ArrayList<>();
    static GridView gridView;
    CustomAdapter customAdapter;
    private ProgressBar mProgressBar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_data);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        gridView = (GridView) findViewById(R.id.gridview);
        getdata();
    }

    public void getdata(){

        Call<ArrayList<ImageResponse>> call = APIclient.apIinterface().getAllImages();
        call.enqueue(new Callback<ArrayList<ImageResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageResponse>> call, Response<ArrayList<ImageResponse>> response) {

                if (response.isSuccessful()) {
                        setCustomAdapter(getApplicationContext(),response.body());
                } else {
                    Toast.makeText(getApplicationContext(), "Error occurd", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ImageResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Error occurd" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setCustomAdapter(Context context,ArrayList<ImageResponse> data){
        CustomAdapter customAdapter = new CustomAdapter(context,data);
        gridView.setAdapter(customAdapter);
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<ImageResponse> data = new ArrayList<>();
        LayoutInflater inflter;
        public CustomAdapter(Context applicationContext, ArrayList<ImageResponse> data) {
            this.context = applicationContext;
            this.data.addAll(data);
            inflter = (LayoutInflater.from(applicationContext));
        }
        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public Object getItem(int i) {
            return data.get(i);
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.imageitem, null);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            TextView name = (TextView) view.findViewById(R.id.name);

            name.setText(data.get(i).getTitle());

            Picasso.get()
                    .load(data.get(i).getUrl())
                    .into(image);
            return view;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Common.logout(getApplicationContext());
        Intent intent = new Intent(DataActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}