package com.example.assignment_gd1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_gd1.R;
import com.example.assignment_gd1.api.Api_Product;
import com.example.assignment_gd1.api.CommentDelete;
import com.example.assignment_gd1.model.Comment;
import com.example.assignment_gd1.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentAdminAdapter extends RecyclerView.Adapter<CommentAdminAdapter.CommentAdminViewHolder> {

    List<Comment> list;
    Context context;



    public CommentAdminAdapter(List<Comment> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public CommentAdminAdapter() {
        list = new ArrayList<>();
    }

    public void setCommentList(List<Comment> lists) {
        list = lists;
        notifyDataSetChanged();
        Log.d("CommentAdapter", "Comment list size: " + list.size());
    }

    @NonNull
    @Override
    public CommentAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_comment_admin,parent,false);
        return new CommentAdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdminViewHolder holder, int position) {
        Comment comment = list.get(position);

        holder.tv_username_user.setText(comment.getUsername());
        holder.tv_comment.setText(comment.getComment());
        holder.tv_title_pro.setText(comment.getTitle());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String id = comment.getId();
                Toast.makeText(context, "id" + id, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Thong bao!!!");
                builder.setMessage("Ban muon xoa comment nay!!!");


                builder.setNegativeButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CommentDelete.commentDelete.deleteComment(id).enqueue(new Callback<Comment>() {
                            @Override
                            public void onResponse(Call<Comment> call, Response<Comment> response) {
                                Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                                list.remove(position);
                                notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Comment> call, Throwable t) {

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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommentAdminViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username_user, tv_comment, tv_title_pro;

        public CommentAdminViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username_user = itemView.findViewById(R.id.tv_username_usert);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_title_pro = itemView.findViewById(R.id.tv_title_pro);

        }
    }
}
