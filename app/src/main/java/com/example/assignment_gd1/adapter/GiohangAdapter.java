package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.Api_Giohang;
import com.example.assignment_gd1.model.GioHang;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.GiohangViewHolder>{

    ArrayList<GioHang> list;
    Context context;

    public GiohangAdapter(ArrayList<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GiohangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_giohang,parent,false);
        return new GiohangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiohangViewHolder holder, int position) {
        GioHang gioHang = list.get(position);

        holder.idgiohang.setText(gioHang.getId());
        holder.tv_title.setText(gioHang.getTitle());
        holder.tvsoluong.setText(gioHang.getQuantity()+"");
        holder.tv_price.setText(gioHang.getPricegh()+""+ "VNĐ" + "/sản phẩm");
        holder.tv_thanhtien.setText("Tổng tiền: "+gioHang.getThanhtien()+""+ "VNĐ");
        holder.tv_ngay.setText("Ngày: "+ gioHang.getDate());

        Glide.with(context)
                .load(gioHang.getImage())
                .into(holder.image);

        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = gioHang.getId();
//                Toast.makeText(context, "id_giohang"+ gioHang.getId(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng?");

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Api_Giohang.apiGiohang.deleteGiohang(id).enqueue(new Callback<GioHang>() {
                            @Override
                            public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                                Toast.makeText(context, "Xoa thanh cong!!!", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<GioHang> call, Throwable t) {

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

    public static class GiohangViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_price,tvsoluong,tv_thanhtien,tv_ngay,image_delete,idgiohang;
        ImageView image;

        public GiohangViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_price = itemView.findViewById(R.id.tv_price_giohang);
            tvsoluong = itemView.findViewById(R.id.tv_soluongmua);
            tv_thanhtien = itemView.findViewById(R.id.tv_thanhtien);
            tv_title = itemView.findViewById(R.id.tv_title_gihang);
            image = itemView.findViewById(R.id.img_giohang);
            image_delete = itemView.findViewById(R.id.image_delete);
            tv_ngay = itemView.findViewById(R.id.tv_ngay);
            idgiohang = itemView.findViewById(R.id.idgiohang);
        }
    }
}
