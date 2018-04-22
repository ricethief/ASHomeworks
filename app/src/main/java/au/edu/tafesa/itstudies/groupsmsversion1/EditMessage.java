package au.edu.tafesa.itstudies.groupsmsversion1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class EditMessage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_message);

        Intent editIntent;
        EditText etMessage;
        editIntent = this.getIntent();
        String theMessage;
        theMessage = editIntent.getStringExtra(GroupSMS.CURRENT_MESSAGE_DATA);
        etMessage = this.findViewById(R.id.etMessage);
        etMessage.setText(theMessage);

        Button btnDone = this.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new ButtonDoneOnClickHandler());

    }
    private class ButtonDoneOnClickHandler implements View.OnClickListener {
        public void onClick(View v) {

            Intent intent = new Intent();
           intent.putExtra (GroupSMS.CURRENT_MESSAGE_DATA,((EditText) findViewById(R.id.etMessage)).getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
