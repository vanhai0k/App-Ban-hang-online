package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.Api_Giohang;
import com.example.assignment_gd1.api.Api_SPmua;
import com.example.assignment_gd1.model.GioHang;
import com.example.assignment_gd1.model.SPMua;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpMuaAdapter extends  RecyclerView.Adapter<SpMuaAdapter.SpMuaViewHolder>{

    ArrayList<SPMua> list;
    Context context;

    public SpMuaAdapter(ArrayList<SPMua> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SpMuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_spdamua,parent,false);
        return new SpMuaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpMuaViewHolder holder, int position) {
        SPMua spMua = list.get(position);

        holder.tv_title.setText(spMua.getTitle());
        holder.tvsoluong.setText(spMua.getQuantity()+"");
        holder.tv_price.setText(spMua.getPricegh()+""+ "VNĐ" + "/sản phẩm");
        holder.tv_thanhtien.setText("Tổng tiền: "+spMua.getThanhtien()+""+ "VNĐ");
        holder.tv_ngay.setText("Ngày: "+ spMua.getDate());
        holder.tv_trangthai.setText(spMua.getTrangthai());

        if (spMua.getSize().equalsIgnoreCase("S")){
            holder.tv_size.setText("S");
        }
        if (spMua.getSize().equalsIgnoreCase("L")){
            holder.tv_size.setText("L");
        }
        if (spMua.getSize().equalsIgnoreCase("XL")){
            holder.tv_size.setText("XL");
        }

        Glide.with(context)
                .load(spMua.getImage())
                .into(holder.image);

        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = spMua.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn hủy đơn hàng này?");

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Api_SPmua.apiSPmua.deleteSPmua(id).enqueue(new Callback<SPMua>() {
                            @Override
                            public void onResponse(Call<SPMua> call, Response<SPMua> response) {
                                Toast.makeText(context, "Xoa thanh cong!!!", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<SPMua> call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SpMuaViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_price,tvsoluong,tv_thanhtien,tv_ngay,
                tv_trangthai,tv_size;
        ImageView image;
        Button image_delete;

        public SpMuaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_price = itemView.findViewById(R.id.tv_price_giohang);
            tvsoluong = itemView.findViewById(R.id.tv_soluongmua);
            tv_thanhtien = itemView.findViewById(R.id.tv_thanhtien);
            tv_title = itemView.findViewById(R.id.tv_title_gihang);
            image = itemView.findViewById(R.id.img_giohang);
            image_delete = itemView.findViewById(R.id.image_delete);
            tv_ngay = itemView.findViewById(R.id.tv_ngay);
            tv_trangthai = itemView.findViewById(R.id.tv_trangthai);
            tv_size = itemView.findViewById(R.id.tv_size);
        }
    }
}
