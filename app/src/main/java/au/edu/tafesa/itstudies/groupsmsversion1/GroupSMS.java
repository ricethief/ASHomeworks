package au.edu.tafesa.itstudies.groupsmsversion1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.net.URI;


public class GroupSMS extends Activity {


    public static final String CURRENT_MESSAGE_DATA = "CURRENT_MESSAGE_DATA";
    public static final int NEW_MESSAGE_REQUEST = 1;
    public static final String CURRENT_PHON_DATA = "CURRENT_PHON_DATA";
    public static final int NEW_PHONE_REQUEST = 2;
    private String message = "";
    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_sms);

        //getting to the views defined in the XML files.
        TextView tvMessageDetails = findViewById(R.id.tvMessageDetails);
        tvMessageDetails.setBackgroundColor(Color.GREEN);
        tvMessageDetails.setMovementMethod(new ScrollingMovementMethod());
        message = "Is it St. Patricks Day?";
        phone = "12345679";
        setSummary();

        //responding to an event - the onClick for the Edit Message button
        //using a named inner class

        Button btnEditMessage;
        btnEditMessage = this.findViewById(R.id.btnEditMessage);
        HandleButtonEditMessageOnClick buttonEditMesaageOnClick;
        buttonEditMesaageOnClick = new HandleButtonEditMessageOnClick();
        btnEditMessage.setOnClickListener(buttonEditMesaageOnClick);

        Button btnEditSendTo;
        btnEditSendTo = this.findViewById(R.id.btnEditSendTo);
        HandleButtonSendToOnClick buttonSendToOnClick;
        buttonSendToOnClick = new HandleButtonSendToOnClick();
        btnEditSendTo.setOnClickListener(buttonSendToOnClick);

        Button btnSend;
        btnSend = this.findViewById(R.id.btnSend);
        HandleButtonSendOnClick buttonSendOnClick;
        buttonSendOnClick = new HandleButtonSendOnClick();
        btnSend.setOnClickListener(buttonSendOnClick);
//        btnSend.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                Intent sendSMSIntent;
////////                Uri uri = Uri.parse("smsto:" + phone);
////////                sendSMSIntent = new Intent(Intent.ACTION_SENDTO, uri);
////////                sendSMSIntent.putExtra("sms body", message);
////////                startActivity(sendSMSIntent);
////                try {
////                    SmsManager smsManager = SmsManager.getDefault();
////                    smsManager.sendTextMessage(phone, null, message, null, null);
////                    Toast.makeText(getApplicationContext(), "SMS Sent To " + phone, Toast.LENGTH_LONG).show();
////                }
////                catch (Exception e){
////                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
////
////                }
////                }
//        });

    }

    private void setSummary() {
        StringBuilder summary;
        summary = new StringBuilder("Sending to: \n");
        summary.append(phone);
        summary.append("\n\nMessage:\n");
        summary.append(message);
        TextView tvMessageDetails = (TextView) findViewById(R.id.tvMessageDetails);
        tvMessageDetails.setText(summary);

    }

    //handle edit button on click by starting the activity this is an example of starting another activity using an explicit intent

    @SuppressWarnings("rawtypes")
    public class HandleButtonEditMessageOnClick implements View.OnClickListener {
        public static final String CLASS_TAG ="HandleButtonEditMessageOnClick";

        public void onClick(View v){
            Log.i(CLASS_TAG, "onClick started...");
            //example of an explicit intent, as we are naming the java class to use (EditMessage.Class)
            Intent editIntent;
            Activity sourceActivity;
            Class destinationClass;

            sourceActivity = GroupSMS.this;
            destinationClass = EditMessage.class;
            editIntent = new Intent(sourceActivity, destinationClass);
            //sending information to the intent receiver through the intent object
            editIntent.putExtra(CURRENT_MESSAGE_DATA, GroupSMS.this.message);

          // startActivity(editIntent);
            startActivityForResult(editIntent, NEW_MESSAGE_REQUEST);

        }


    }
    @SuppressWarnings("rawtypes")
    public class HandleButtonSendToOnClick implements  View.OnClickListener{
        public static final String CLASS_TAG ="HandleButtonEditSendToOnClick";

        public void onClick(View v){
            Log.i(CLASS_TAG, "onClick started...");
            //example of an explicit intent, as we are naming the java class to use (EditMessage.Class)
            Intent editIntent;
            Activity sourceActivity;
            Class destinationClass;

            sourceActivity = GroupSMS.this;
            destinationClass = EditSentTo.class;
            editIntent = new Intent(sourceActivity, destinationClass);
            //sending information to the intent receiver through the intent object
            editIntent.putExtra(CURRENT_PHON_DATA, GroupSMS.this.phone);

            // startActivity(editIntent);
            startActivityForResult(editIntent, NEW_PHONE_REQUEST);
         }
    }

    public class HandleButtonSendOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
//                Intent sendSMSIntent;
////                Uri uri = Uri.parse("smsto:" + phone);
////                sendSMSIntent = new Intent(Intent.ACTION_SENDTO, uri);
////                sendSMSIntent.putExtra("sms body", message);
////                startActivity(sendSMSIntent);
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent To " + phone, Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_MESSAGE_REQUEST){

            if(resultCode == RESULT_OK){
                String newMessage = (String) (data.getStringExtra(CURRENT_MESSAGE_DATA));
                message = newMessage;

                setSummary();
            }
        }

        if(requestCode == NEW_PHONE_REQUEST){

            if(resultCode == RESULT_OK){
                String newPhon = (String) (data.getStringExtra(CURRENT_PHON_DATA));
                phone = newPhon;

                setSummary();
            }
        }


    }


}
