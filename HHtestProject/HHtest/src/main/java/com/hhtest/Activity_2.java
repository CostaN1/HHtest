package com.hhtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Costa on 05.09.13.
 */

public class Activity_2 extends Activity implements OnClickListener {

    private TextView name;
    private TextView date;
    private TextView sex;
    private TextView function;
    private TextView salary;
    private TextView phone;
    private TextView email;
    private EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        name = (TextView)findViewById(R.id.name);
        date = (TextView)findViewById(R.id.date);
        sex = (TextView)findViewById(R.id.sex);
        function = (TextView)findViewById(R.id.function);
        salary = (TextView)findViewById(R.id.salary);
        phone = (TextView)findViewById(R.id.phoneLabel);
        email = (TextView)findViewById(R.id.email);
        answer = (EditText)findViewById(R.id.answerEditText);

        String txtName = getIntent().getStringExtra("name");
        name.setText(name.getText().toString() + " " + txtName);

        String txtDate = getIntent().getStringExtra("date");
        date.setText(date.getText().toString() + " " + txtDate);

        String txtSex = getIntent().getStringExtra("sex");
        sex.setText(sex.getText().toString() + " " + txtSex);

        String txtFunction = getIntent().getStringExtra("function");
        function.setText(function.getText().toString() + " " + txtFunction);

        String txtSalary = getIntent().getStringExtra("salary");
        salary.setText(salary.getText().toString() + " " + txtSalary);


    //------------phone link------------------------------------------------
        String txtPhone = getIntent().getStringExtra("phone");
        SpannableString ssPhone = new SpannableString("Телефон: " + txtPhone);
        ssPhone.setSpan(new URLSpan("tel:" + txtPhone), 9,  9 + txtPhone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        phone.setText(ssPhone);
        phone.setMovementMethod(LinkMovementMethod.getInstance());
    //----------------------------------------------------------------------

        String txtEmail = getIntent().getStringExtra("email");
        email.setText(email.getText().toString() + " " + txtEmail);

        View answerButton = findViewById(R.id.answer_button);
        answerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.answer_button:

                Intent intent_answer = new Intent(this, Activity_1.class);
                intent_answer.putExtra("answer", answer.getText().toString());
                startActivity(intent_answer);
                finish();
                break;
            default:
                break;
        }
    }
}
