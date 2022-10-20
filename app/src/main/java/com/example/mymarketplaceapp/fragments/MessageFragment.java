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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymarketplaceapp.R;
import com.example.mymarketplaceapp.adapters.MessageAdapter;
import com.example.mymarketplaceapp.models.Chat;
import com.example.mymarketplaceapp.models.User;
import com.example.mymarketplaceapp.models.UserDao;
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
 * @author u7326123 Rita Zhou
 */
public class MessageFragment extends Fragment {
    String senderID;
    String receiverID;

    View view;
    TextView username;
    EditText textSent;
    ImageButton btSent;

    MessageAdapter messageAdapter;
    List<Chat> chatList;

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

        // get the sender and receiver
        Bundle bundle = getArguments();
        UserSession userSession = bundle.getParcelable("userSession");
        int receiverId = bundle.getInt("receiver");
        String userName = UserDao.getInstance().getUsername(receiverId);
        User receiver = UserDao.getInstance().searchUser(userName);

        senderID = String.valueOf(userSession.getUser().getUid());
        receiverID = String.valueOf(receiver.getUid());

        // set the receiver's name on the top of the screen
        username = view.findViewById(R.id.username);
        username.setText(userName);

        textSent = view.findViewById(R.id.text_send);
        btSent = view.findViewById(R.id.btn_send);

        // show the message, from bottom to top
        recyclerView = view.findViewById(R.id.rv_chat_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        // get the data from the firebase
        reference = FirebaseDatabase.getInstance().getReference().child("user");

        btSent.setOnClickListener(sendMessageOnClickListener);
        reference.addValueEventListener(readChatEventListener);
    }

    private View.OnClickListener sendMessageOnClickListener = new View.OnClickListener() {
        /**
         * Check send message is valid
         */
        @Override
        public void onClick(View view) {
            String message = textSent.getText().toString();
            if (!message.equals("")){
                sendMessage(senderID, receiverID, message);
            } else{
                Toast.makeText(getContext(), "Message cannot be empty!", Toast.LENGTH_SHORT).show();
            }
            textSent.setText("");
        }
    };

    /**
     * Update the date to the firebase
     * @param sender the sender uid in string
     * @param receiver the receiver uid in string
     * @param message the message want to send
     */
    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);
        hashMap.put("timestamp", ServerValue.TIMESTAMP);

        // update
        reference.child("chats").push().setValue(hashMap);
    }

    private ValueEventListener readChatEventListener = new ValueEventListener() {
        /**
         * read the data in firebase
         */
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            readMessage(senderID, receiverID);
        }
        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    /**
     * Load the data in firebase and show on the recycler view
     * @param myId the sender's uid in string
     * @param userid the receiver's uid in string
     */
    private void readMessage(String myId, String userid) {
        chatList = new ArrayList<>();

        // load data from firebase
        reference = FirebaseDatabase.getInstance().getReference("chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                // loop all chat history
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Chat chat = ds.getValue(Chat.class);

                    assert chat != null;
                    // find the chat history between sender and receiver
                    if ((chat.getSender().equals(myId) && chat.getReceiver().equals(userid)) ||
                            (chat.getSender().equals(userid) && chat.getReceiver().equals(myId))
                    ) {
                        chatList.add(chat);

                    }
                    // send data to adapter to make the message visiable
                    messageAdapter = new MessageAdapter(getActivity(), chatList, senderID);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}