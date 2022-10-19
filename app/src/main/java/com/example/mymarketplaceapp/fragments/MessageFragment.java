package com.example.mymarketplaceapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.MessageAdapter;
import com.example.mymarketplaceapp.models.Chat;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Massage Fragment
 * Feature: Peer to Peer Messaging
 * @author Rita Zhou
 */
public class MessageFragment extends Fragment {
    private UserSession userSession;

    View view;
    TextView username;
    EditText textSent;
    ImageButton btSent;

    MessageAdapter messageAdapter;
    List<Chat> chatList;
    String senderIDStr;

    DatabaseReference reference;

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        //TODO: FIX THE SENDER AND RECEIVER
//        Bundle bundle = getArguments();
//        userSession = bundle.getParcelable("userSession");

        username = view.findViewById(R.id.username);
        textSent = view.findViewById(R.id.text_send);
        btSent = view.findViewById(R.id.btn_send);

        recyclerView = view.findViewById(R.id.rv_chat_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        reference = FirebaseDatabase.getInstance().getReference().child("user");

        if (userSession != null) {
            senderIDStr = String.valueOf(userSession.getUser().getUid());
        }

        btSent.setOnClickListener(sendMessageOnClickListener);
        reference.addValueEventListener(readChatEventListener);

        username.setText("admin");
    }

    private View.OnClickListener sendMessageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String message = textSent.getText().toString();
            if (!message.equals("")){
                // TODO: FIX THE SENDER AND RECEIVER
                sendMessage("0", "1", message);
            } else{
                Toast.makeText(getContext(), "Message cannot be empty!", Toast.LENGTH_SHORT).show();
            }
            textSent.setText("");
        }
    };

    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("timestamp", ServerValue.TIMESTAMP);

        reference.child("chats").push().setValue(hashMap);
    }

    private ValueEventListener readChatEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
//            User user = snapshot.getValue(User.class);
//            username.setText(user.getUsername());
            // TODO: FIX THE SENDER AND RECEIVER
            readMessage("0", "1");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    private void readMessage(String myId, String userid) {
        chatList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Chat chat = ds.getValue(Chat.class);

                    assert chat != null;
                    if ((chat.getSender().equals(myId) && chat.getReceiver().equals(userid)) ||
                            (chat.getSender().equals(userid) && chat.getReceiver().equals(myId))
                    ) {
                        chatList.add(chat);

                    }
                    // TODO: FIX THE SENDER
                    messageAdapter = new MessageAdapter(getActivity(), chatList, "0");
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}