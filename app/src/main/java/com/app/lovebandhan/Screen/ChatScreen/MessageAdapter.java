package com.app.lovebandhan.Screen.ChatScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.app.lovebandhan.R;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private final List<ChatModel> chatModelList;

    public MessageAdapter(Context context, List<ChatModel> chatModelList) {
        this.context = context;
        this.chatModelList = chatModelList;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        ChatModel chatModelViewCheck = chatModelList.get(viewType);

        if (chatModelViewCheck.isCurrent()) {
            View imageLayout = LayoutInflater.from(context).inflate(R.layout.client_chat_side_layout, parent, false);
            return new ViewHolderClient(imageLayout);
        } else {
            View imageLayout = LayoutInflater.from(context).inflate(R.layout.doctor_chat_side_layout, parent, false);
            return new ViewHolderDoctor(imageLayout);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        ChatModel chatModelViewCheck = chatModelList.get(position);
        DateFormat formatter = new SimpleDateFormat("hh:mm a");
        String today ;;
        if(chatModelViewCheck.getTime().toDate() != null){
             today = formatter.format(chatModelViewCheck.getTime().toDate());
        }
        else {
            today = "25-07-22";
        }

        if (chatModelViewCheck.isCurrent()) {

            ViewHolderClient viewHolderClient = (ViewHolderClient) holder;

            /*SimpleDateFormat formatter2 = new SimpleDateFormat("h:mm a");
            String timeString = formatter2.format(chatModelViewCheck.getTime());
*/
            if (chatModelViewCheck.getText() != null) {
                viewHolderClient.text.setText(chatModelViewCheck.getText());
                viewHolderClient.time.setText(today);
                viewHolderClient.ll_one.setVisibility(View.VISIBLE);
            }

            if (chatModelViewCheck.getImageurl() != null){
                viewHolderClient.ll_one.setVisibility(View.GONE);
                viewHolderClient.layout_image.setVisibility(View.VISIBLE);

                Glide
                        .with(context)
                        .load(chatModelViewCheck.getImageurl())
                        .centerCrop()
                        .into(viewHolderClient.imageView);

            }

        } else {
            ViewHolderDoctor viewHolderDoctor = (ViewHolderDoctor) holder;

            if (chatModelViewCheck.getText() != null) {
                viewHolderDoctor.text.setText(chatModelViewCheck.getText());
                viewHolderDoctor.ll_one.setVisibility(View.VISIBLE);
                viewHolderDoctor.time.setText(today);
            }



        }

    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }


    static class ViewHolderDoctor extends ViewHolder {

        TextView text;
        LinearLayout ll_one;
        TextView time;

        public ViewHolderDoctor(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.chat_doctor);
            ll_one = itemView.findViewById(R.id.ll);
            time = itemView.findViewById(R.id.time);

        }
    }

    static class ViewHolderClient extends ViewHolder {

        TextView text;
        LinearLayout ll_one,layout_image;
        TextView time;
        ImageView imageView;


        public ViewHolderClient(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.chat_client);
            ll_one = itemView.findViewById(R.id.ll);
            time = itemView.findViewById(R.id.time);
            layout_image = itemView.findViewById(R.id.ll_Imageview);
            imageView = itemView.findViewById(R.id.chat_doctor_image);

        }

    }


}
