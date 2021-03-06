package com.example.chatifygmail;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatifygmail.data.Email;
import com.example.chatifygmail.database.Sender;

public class MailAdapter extends RecyclerView.Adapter<MailAdapter.MailViewHolder> {

    private Context mContext;
    private Sender sender;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
    public MailAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.mail_layout, parent, false);

        return new MailAdapter.MailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MailViewHolder holder, int position) {
        Email email = sender.getEmails().get(position);
        String subject = email.getSubject();
        if(subject==null)
            subject = "----";
        String boldSubject = "<b>"+subject+"</b>"+"<br>";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.emailContentView.setText(Html.fromHtml(boldSubject+(email.getContents().trim()), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.emailContentView.setText(Html.fromHtml(boldSubject+(email.getContents().trim())));
        }
        //holder.emailContentView.setText(email.getContents());
    }

    @Override
    public int getItemCount() {
        if(sender.getEmails()!=null) {
            return sender.getEmails().size();
        }
        else
            return 0;
    }

    public class MailViewHolder extends RecyclerView.ViewHolder {
        //TextView emailSubjectView;
        TextView emailContentView;
        public MailViewHolder(@NonNull View itemView) {
            super(itemView);
            //emailSubjectView = itemView.findViewById(R.id.email_subject_view);
            emailContentView = itemView.findViewById(R.id.email_content_view);
        }
    }
}
