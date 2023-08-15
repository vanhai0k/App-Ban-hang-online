package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.Comment_Product;
import com.example.assignment_gd1.Comment_Product_Admin;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.Test_Comment;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.api.Api_Product;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapterAdmin extends RecyclerView.Adapter<ProductAdapterAdmin.ProductViewHolderAdmin>{

    List<Product> list;
    Context context;

    public ProductAdapterAdmin(List<Product> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ProductViewHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_roduct_admin,parent,false);
        return new ProductViewHolderAdmin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolderAdmin holder, int position) {
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

        String _id = product.get_id();

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(context, "id: "+ _id, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao!!!");
                builder.setMessage("Ban muon xoa san pham nay!!!");


                builder.setNegativeButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Api_Product.apiProduct.deleteProduct(_id).enqueue(new Callback<Product>() {
                            @Override
                            public void onResponse(Call<Product> call, Response<Product> response) {
                                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Product> call, Throwable t) {

                            }
                        });
                    }
                });

                builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });
        holder.tv_capnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_capnhap_sanpham,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                EditText ed_title_pro,ed_linkanh_pro,ed_infomation_pro,ed_phongcach_pro,
                        ed_xuatsu_pro,ed_size_pro,ed_soluong_pro,ed_price_pro;
                Button btn_addpro, btn_huy;

                ed_linkanh_pro = view.findViewById(R.id.ed_linkanh_pro_edit);
                ed_infomation_pro = view.findViewById(R.id.ed_infomation_pro_edit);
                ed_price_pro = view.findViewById(R.id.ed_price_pro_edit);
                ed_size_pro = view.findViewById(R.id.ed_size_pro_edit);
                ed_title_pro = view.findViewById(R.id.ed_title_pro_edit);
                ed_phongcach_pro = view.findViewById(R.id.ed_phongcach_pro_edit);
                ed_xuatsu_pro = view.findViewById(R.id.ed_xuatsu_pro_edit);
                ed_soluong_pro = view.findViewById(R.id.ed_soluong_pro_edit);
                btn_addpro = view.findViewById(R.id.btn_addpro);
                btn_huy = view.findViewById(R.id.btn_huy);

                String _id = product.get_id();
                ed_linkanh_pro.setText(product.getImage());
                ed_infomation_pro.setText(product.getInfomation());
                ed_price_pro.setText(product.getPrice()+"");
                ed_size_pro.setText(product.getSize());
                ed_title_pro.setText(product.getTitle());
                ed_phongcach_pro.setText(product.getPhongcach());
                ed_xuatsu_pro.setText(product.getXuatsu());
                ed_soluong_pro.setText(product.getQuantity()+"");

                btn_addpro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String title = ed_title_pro.getText().toString().trim();
                        double price = Double.parseDouble(ed_price_pro.getText().toString().trim());
                        int soluong = Integer.parseInt(ed_soluong_pro.getText().toString().trim());
                        String size = ed_size_pro.getText().toString().trim();
                        String xuatsu = ed_xuatsu_pro.getText().toString().trim();
                        String phongcach = ed_phongcach_pro.getText().toString().trim();
                        String infomation = ed_infomation_pro.getText().toString().trim();
                        String image = ed_linkanh_pro.getText().toString().trim();
                        Api_Product.apiProduct.updateProduct(_id, new Product(image,title,price,soluong,size,xuatsu,phongcach,infomation))
                                .enqueue(new Callback<Product>() {
                                    @Override
                                    public void onResponse(Call<Product> call, Response<Product> response) {
                                        Toast.makeText(context, "Cap nhap thanh cong", Toast.LENGTH_SHORT).show();
                                        list.set(holder.getAdapterPosition(), new Product(_id,image,title,price,soluong,size,xuatsu,phongcach,infomation));
                                        notifyDataSetChanged();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<Product> call, Throwable t) {
                                        Toast.makeText(context, "Sua that bai", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });
        holder.tv_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comMent(product);
            }
        });

    }
    private void comMent(Product product){
//        Intent intent = new Intent(context, Comment_Product_Admin.class);
        Intent intent = new Intent(context, Test_Comment.class);
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

    public static class ProductViewHolderAdmin extends RecyclerView.ViewHolder {
        TextView tv_title,tv_quantity,tv_price,tv_mota,
                tv_phongcach,tv_size,tv_id,tv_binhluan,tv_capnhap;
        ImageView image;
        public ProductViewHolderAdmin(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title_admin);
            tv_quantity = itemView.findViewById(R.id.tv_soluong_admin);
            tv_price = itemView.findViewById(R.id.tv_price_admin);
            image = itemView.findViewById(R.id.image_admin);
            tv_mota = itemView.findViewById(R.id.tv_mota_admin);
            tv_phongcach = itemView.findViewById(R.id.tv_phongcach_admin);
            tv_size = itemView.findViewById(R.id.tv_size_admin);
            tv_id = itemView.findViewById(R.id.tv_id_admin);
            tv_binhluan = itemView.findViewById(R.id.tv_binhluan_admin);
            tv_capnhap = itemView.findViewById(R.id.tv_capnhap);

        }
    }
}
