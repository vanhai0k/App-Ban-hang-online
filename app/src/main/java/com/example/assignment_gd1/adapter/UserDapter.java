package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.API;
import com.example.assignment_gd1.api.Api_User;
import com.example.assignment_gd1.model.User;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDapter extends RecyclerView.Adapter<UserDapter.UserViewHolder>{

    List<User> list;
    Context context;

    public UserDapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);
        holder.tv_name.setText(user.getUsername());
        holder.tv_email.setText(user.getEmail());

        Glide.with(context)
                .load(user.getImage())
                .into(holder.image);


        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa tài  này?");

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Api_User.apiUser.deleteUser(id).enqueue(new Callback<User>() {
                           @Override
                           public void onResponse(Call<User> call, Response<User> response) {
                               Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                               list.remove(position);
                               notifyDataSetChanged();
                           }

                           @Override
                           public void onFailure(Call<User> call, Throwable t) {
                               Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
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

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name,tv_email;
        ImageView image;
        Button btn_delete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
            image = itemView.findViewById(R.id.im_image);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
