package cl.cutiko.aboutcutiko;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {


    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View main = inflater.inflate(R.layout.fragment_email, container, false);


        final Button contactBtn = (Button) main.findViewById(R.id.contactBtn);
        final TextInputLayout subjectHolder = (TextInputLayout) main.findViewById(R.id.subjectHolder);
        final EditText subjectEt = (EditText) main.findViewById(R.id.subjectInput);
        final TextInputLayout msgHolder = (TextInputLayout) main.findViewById(R.id.messageHolder);
        final EditText msgInput = (EditText) main.findViewById(R.id.messageInput);
        final Button sendBtn = (Button) main.findViewById(R.id.sendBtn);

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactBtn.setVisibility(View.GONE);
                subjectHolder.setVisibility(View.VISIBLE);
                msgHolder.setVisibility(View.VISIBLE);
                sendBtn.setVisibility(View.VISIBLE);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subjectEt.getText().toString();
                String msg = msgInput.getText().toString();

                if (subject != null && !subject.isEmpty() && !subject.equals("") && subject.trim().length() > 0) {

                    if (msg != null && !msg.isEmpty() && !msg.equals("") && msg.trim().length() > 0) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setType("*/*");
                        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cutiko@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                        intent.putExtra(Intent.EXTRA_TEXT, msg);
                        startActivity(intent);
                    } else {
                        msgHolder.setError("Cuénteme en qué le ayudo");
                    }

                } else {
                    subjectHolder.setError("Un asunto por favor");
                }
            }
        });

        return main;
    }



}
