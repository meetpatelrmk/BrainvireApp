package com.nirmit.brainvireapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.nirmit.brainvireapp.network.APIclient;
import com.nirmit.brainvireapp.model.ImageResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {


    private List<ImageResponse> imageResponseList = new ArrayList<>();
    GridView gridView;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_data);

        gridView = (GridView) findViewById(R.id.gridview);

        Call<ArrayList<ImageResponse>> call = APIclient.apIinterface().getAllImages();
        call.enqueue(new Callback<ArrayList<ImageResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ImageResponse>> call, Response<ArrayList<ImageResponse>> response) {

                if (response.isSuccessful()){

                    customAdapter = new CustomAdapter(getApplicationContext(), (ArrayList<ImageResponse>) response.body());
                    gridView.setAdapter(customAdapter);

                }else {
                    Toast.makeText(getApplicationContext(),"Error occurd",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ArrayList<ImageResponse>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Error occurd" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
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
}