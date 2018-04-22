package au.edu.tafesa.itstudies.groupsmsversion1;

import android.app.Activity;
import android.os.Bundle;
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

public class EditSentTo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sent_to);

        Intent editIntent;
        EditText etPhon;
        editIntent = this.getIntent();
        String thePhon;
        thePhon = editIntent.getStringExtra(GroupSMS.CURRENT_PHON_DATA);
        etPhon = this.findViewById(R.id.etPhone);
        etPhon.setText(thePhon);

        Button btnDone = this.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new ButtonDoneOnClickHandler());
    }
    private class ButtonDoneOnClickHandler implements View.OnClickListener {
        public void onClick(View v){

            Intent intent = new Intent();
            intent.putExtra(GroupSMS.CURRENT_PHON_DATA,((EditText) findViewById(R.id.etPhone)).getText().toString());

            setResult(RESULT_OK, intent);
            finish();
        }

    }
}
