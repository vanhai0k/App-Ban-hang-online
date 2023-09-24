package com.example.assignment_gd1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_gd1.R;
import com.example.assignment_gd1.model.Message;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    Context context;
    List<Message> list;

    public MessageAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_message,parent,false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = list.get(position);

        holder.tv_content.setText(message.getContent());
        Glide.with(context)
                .load(message.getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        ImageView image;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            image = itemView.findViewById(R.id.image);
        }
    }
}
