package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    interface onGridItemClickListener
    {
        void onGridItemClickListener(int position);
    }
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<Dataclass> dataclassArrayList=new ArrayList<>();
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataclassArrayList.add(new Dataclass("Aravind","Reddy"));
        dataclassArrayList.add(new Dataclass("Aravind","Reddy"));
        dataclassArrayList.add(new Dataclass("Aravind","Reddy"));
        dataclassArrayList.add(new Dataclass("Aravind","Reddy"));
        dataclassArrayList.add(new Dataclass("Aravind","Reddy"));
        setRecyclerview();
        editText=findViewById(R.id.edittext);
        button=findViewById(R.id.btn);
        button.setOnClickListener(this);
    }

    private void setRecyclerview() {
    recyclerView=findViewById(R.id.recycle);
    GridLayoutManager linearLayoutManager=new GridLayoutManager(getApplicationContext(),2);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setHasFixedSize(true);
    myAdapter=new MyAdapter(dataclassArrayList);
    recyclerView.setAdapter(myAdapter);

    myAdapter.setOnGridItemClickListener1(new onGridItemClickListener() {
        @Override
        public void onGridItemClickListener(int position) {
            dataclassArrayList.remove(position);
            myAdapter.notifyDataSetChanged();
        }
    });
    }

    @Override
    public void onClick(View v) {
     String hi=   editText.getText().toString();
        dataclassArrayList.add(new Dataclass(hi,"Reddy"));
        myAdapter.notifyDataSetChanged();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderClass>
    {
        ArrayList<Dataclass>arrayList;
        onGridItemClickListener onGridItemClickListener1;
        public MyAdapter(ArrayList<Dataclass>  dataclasses)
        {
            this.arrayList=dataclasses;
        }

        public void setOnGridItemClickListener1(onGridItemClickListener onGridItemClickListener1) {
            this.onGridItemClickListener1 = onGridItemClickListener1;
        }

        @NonNull
        @Override
        public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.singleviewforrecycle,parent,false);
            ViewHolderClass viewHolderClass=new ViewHolderClass(view);
            return viewHolderClass;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
            holder.textView1.setText(arrayList.get(position).name);
            holder.textView2.setText(arrayList.get(position).lastname);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        private class ViewHolderClass extends RecyclerView.ViewHolder
        {
            TextView textView1,textView2;
            public ViewHolderClass(@NonNull View itemView) {
                super(itemView);
                textView1=itemView.findViewById(R.id.txt1);
                textView2=itemView.findViewById(R.id.txt2);

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos=getAdapterPosition();
                        onGridItemClickListener1.onGridItemClickListener(pos);
                        return true;
                    }
                });

            }
        }
    }


}
