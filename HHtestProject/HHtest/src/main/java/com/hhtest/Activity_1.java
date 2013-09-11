package com.hhtest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Activity_1 extends Activity implements OnClickListener, OnItemSelectedListener {

    private EditText name;
    private EditText date;
    private Spinner spinner;
    private EditText function;
    private EditText salary;
    private EditText phone;
    private EditText email;

    static final int DATE_DIALOG_ID = 0;


    private int mYear;
    private int mMonth;
    private int mDay;

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth)
                {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDate();
                }
            };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        name = (EditText)findViewById(R.id.nameEditText);

    //-------------------------------date--------------------------------------

        date = (EditText)findViewById(R.id.dateEditText);
        date.setInputType(InputType.TYPE_NULL);
        date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    //-------------------------------------------------------------------------

        function = (EditText)findViewById(R.id.functionEditText);
        salary = (EditText)findViewById(R.id.salaryEditText);
        phone = (EditText)findViewById(R.id.phoneEditText);
        email = (EditText)findViewById(R.id.emailEditText);

    //----------------------------------sex-----------------------------------
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    //-------------------------------------------------------------------------


        View sendResumeButton = findViewById(R.id.sendResume_button);
        sendResumeButton.setOnClickListener(this);


        if(getIntent().getStringExtra("answer")!=null)
        {
            String txtAnswer = getIntent().getStringExtra("answer");

            AlertDialog alertdialog = new AlertDialog.Builder(Activity_1.this).create();
            alertdialog.setMessage(txtAnswer);
            alertdialog.show();
        }

    }





    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void updateDate() {
        date.setText(
                new StringBuilder()
                        .append(pad(mDay)).append(".")
                        .append(pad(mMonth + 1)).append(".")
                        .append(mYear));
    }

    //----------проверка на наличие пустого пол€-------------------------------
    public boolean emptyField()
    {
       if((name.getText()==null) ||
           (date.getText()==null) ||
           (spinner.getSelectedItemId()==0) ||
           (function.getText()==null) ||
           (salary.getText()==null) ||
           (phone.getText()==null) ||
           (email.getText()==null))
       {
           return true;
       }
        else
           return false;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.sendResume_button:

                if(emptyField())
                {
                    
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "«аполните все пол€", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                Intent intent_activity2 = new Intent(this, Activity_2.class);

                intent_activity2.putExtra("name", name.getText().toString());
                intent_activity2.putExtra("date", date.getText().toString());
                intent_activity2.putExtra("sex", spinner.getSelectedItem().toString());
                intent_activity2.putExtra("function", function.getText().toString());
                intent_activity2.putExtra("salary", salary.getText().toString());
                intent_activity2.putExtra("phone", phone.getText().toString());
                intent_activity2.putExtra("email", email.getText().toString());

                startActivity(intent_activity2);
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

        Object item = adapterView.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
