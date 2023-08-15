package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.example.assignment_gd1.CommentService;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

    List<Comment>list;
    Context context;

    public CommentAdapter() {
        list = new ArrayList<>();
    }

    public CommentAdapter(List<Comment> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setCommentList(List<Comment> comment) {
        list = comment;
        notifyDataSetChanged();
    }
    public void addComment(Comment comment) {
        list.add(comment);
        notifyItemInserted(list.size() - 1);
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = list.get(position);

        holder.tv_username_user.setText(comment.getUsername());
        holder.tv_comment.setText(comment.getComment());
        holder.tv_title_pro.setText(comment.getTitle());

        holder.image_edit.setVisibility(View.INVISIBLE);
        holder.image_delete.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences = context.getSharedPreferences("luuduser", context.MODE_PRIVATE);
        String curid = sharedPreferences.getString("idusers", null);
        if (comment.getId_user().equalsIgnoreCase(curid)){
            holder.image_edit.setVisibility(View.VISIBLE);
        }else{
            holder.image_edit.setVisibility(View.INVISIBLE);
        }
        if (comment.getId_user().equalsIgnoreCase(curid)){
            holder.image_delete.setVisibility(View.VISIBLE);
        }else{
            holder.image_delete.setVisibility(View.INVISIBLE);
        }

        String id = comment.getId();
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao!!!!!");
                builder.setMessage("Ban muon xoa binh luan nay!!!!!");

                builder.setNegativeButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        CommentService.commentService.deleteComment(id).enqueue(new Callback<Comment>() {
                            @Override
                            public void onResponse(Call<Comment> call, Response<Comment> response) {
                                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Comment> call, Throwable t) {
                                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
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


            }
        });

        holder.image_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_update_comment,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                EditText ed_comment,ed_idu,ed_idp,ed_idtt,ed_idname;
                Button btn_huy,btn_capnhap;


                String idcm = comment.getId();
                ed_comment = view.findViewById(R.id.ed_comment);
                btn_huy = view.findViewById(R.id.btn_huy);
                ed_idu = view.findViewById(R.id.ed_idu);
                ed_idp = view.findViewById(R.id.ed_idp);
                ed_idtt = view.findViewById(R.id.ed_idtt);
                ed_idname = view.findViewById(R.id.ed_idname);
                btn_capnhap = view.findViewById(R.id.btn_capnhap);

                ed_comment.setText(comment.getComment());
                ed_idp.setText(comment.getId_product());
                ed_idu.setText(comment.getId_user());
                ed_idtt.setText(comment.getTitle());
                ed_idname.setText(comment.getUsername());

                btn_capnhap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String comment = ed_comment.getText().toString().trim();
                        String idu = ed_idu.getText().toString().trim();
                        String idp = ed_idp.getText().toString().trim();
                        String idtt = ed_idtt.getText().toString().trim();
                        String name = ed_idname.getText().toString().trim();


                        CommentService.commentService.updateComment(idcm, new Comment(name,comment,idtt,idu,idp)).enqueue(new Callback<Comment>() {
                            @Override
                            public void onResponse(Call<Comment> call, Response<Comment> response) {
                                list.set(holder.getAdapterPosition(), new Comment(idcm,name,comment,idtt,idu,idp));
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<Comment> call, Throwable t) {
                                Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username_user, tv_comment,tv_title_pro;
        ImageView image_edit,image_delete;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username_user = itemView.findViewById(R.id.tv_username_usert);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_title_pro = itemView.findViewById(R.id.tv_title_pro);
            image_edit = itemView.findViewById(R.id.image_edit);
            image_delete = itemView.findViewById(R.id.image_delete);

        }
    }
}
