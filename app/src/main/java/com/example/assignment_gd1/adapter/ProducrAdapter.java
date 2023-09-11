package com.example.assignment_gd1.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.Comment_Product;
import com.example.assignment_gd1.Infomation_Pro;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;
import com.example.assignment_gd1.model.User;

import java.util.List;

public class ProducrAdapter extends RecyclerView.Adapter<ProducrAdapter.ProductViewholder>{

    List<Product> list;
    Context context;


    public ProducrAdapter(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_product,parent,false);

        return new ProductViewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {
        Product product = list.get(position);

        holder.tv_title.setText(product.getTitle());
        holder.tv_quantity.setText(product.getQuantity()+"");
        holder.tv_price.setText(product.getPrice()+"");
        holder.tv_mota.setText(product.getInfomation());
        holder.tv_phongcach.setText(product.getPhongcach());
        holder.tv_size.setText(product.getSize());

        Glide.with(context)
                .load(product.getImage())
                .into(holder.image);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infomation(product);
            }
        });
        holder.tv_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "idUser"+ user.getId(), Toast.LENGTH_SHORT).show();
                comMent(product);
//                listener.onItemClick(product);

            }
        });

    }
    private void infomation(Product product){
        Intent intent = new Intent(context, Infomation_Pro.class);
        Bundle bundle = new Bundle();

        bundle.putString("id_pro", product.get_id());
        bundle.putString("title", product.getTitle());
        bundle.putString("image", product.getImage());
        bundle.putString("infomation", product.getInfomation());
        bundle.putString("phongcach", product.getPhongcach());
        bundle.putString("size", product.getSize());
        bundle.putString("_id", product.get_id());

        bundle.putDouble("price",product.getPrice());
        bundle.putInt("quantity",product.getQuantity());


        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    private void comMent(Product product){
        Intent intent = new Intent(context, Comment_Product.class);
        Bundle bundle = new Bundle();

        bundle.putString("title", product.getTitle());
        bundle.putString("_id", product.get_id());

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ProductViewholder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_quantity,tv_price,tv_mota,
                tv_phongcach,tv_size,tv_id,tv_binhluan;
        ImageView image;

        public ProductViewholder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_quantity = itemView.findViewById(R.id.tv_soluong);
            tv_price = itemView.findViewById(R.id.tv_price);
            image = itemView.findViewById(R.id.image);
            tv_mota = itemView.findViewById(R.id.tv_mota);
            tv_phongcach = itemView.findViewById(R.id.tv_phongcach);
            tv_size = itemView.findViewById(R.id.tv_size);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_binhluan = itemView.findViewById(R.id.tv_binhluan);


        }
    }
}
