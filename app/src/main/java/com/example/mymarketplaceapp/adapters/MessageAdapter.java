package com.example.mymarketplaceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.models.Chat;

import java.util.List;

/**
 * The Message Adapter
 * Feature: Peer to Peer Messaging
 * @author u7326123 Rita Zhou
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>  {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<Chat> mChat;
    private String senderID;

    public MessageAdapter(Context mContext, List<Chat> mChat, String sender){
        this.mChat = mChat;
        this.mContext = mContext;
        this.senderID = sender;
    }

    /**
     * show the conversation, the sender's (i.e. current user) message on the right and the
     * receiver's message on the left
     * @return the message adapter
     */
    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);

        }
    }

    /**
     * check the sender and receiver
     * @return different state of the chat
     */
    @Override
    public int getItemViewType(int position) {
        if (mChat.get(position).getSender().equals(senderID)){
            return MSG_TYPE_RIGHT;
        } else{
            return MSG_TYPE_LEFT;
        }
    }
}
