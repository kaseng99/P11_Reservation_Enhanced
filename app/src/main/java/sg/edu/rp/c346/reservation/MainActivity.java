package sg.edu.rp.c346.reservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    EditText etDay;
    EditText etTime;
    Button btReserve;
    Button btReset;

    int Year;
    int Month;
    int Day;
    int Hour;
    int Min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Reservation");

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        etDay = findViewById(R.id.editTextDay);
        etTime = findViewById(R.id.editTextTime);
        checkBox = findViewById(R.id.checkBox);

        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);

        Calendar c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        Month = c.get(Calendar.MONTH);
        Day = c.get(Calendar.DAY_OF_MONTH);
        Hour = c.get(Calendar.HOUR);
        Min = c.get(Calendar.MINUTE);

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                } else {
                    isSmoke = "non-smoking";
                }

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm your order");
                myBuilder.setMessage("New Reservation \nName: " + etName.getText().toString() + "\nSmoking : " +
                        isSmoke + "\nSize : " +etSize.getText().toString() + "\nDate : " + Day + "/" + Month + "/" + Year + "\nTime : " + Hour + ":" + Min );
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("CONFIRM", null);
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etTelephone.setText(null);
                etSize.setText(null);
                checkBox.setChecked(false);
                Calendar c = Calendar.getInstance();
                c.get(Calendar.YEAR);
                c.get(Calendar.MONTH);
                c.get(Calendar.DAY_OF_MONTH);
                c.get(Calendar.HOUR_OF_DAY);
                c.get(Calendar.MINUTE);

            }
        });

        etDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set the date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        Year=year;
                        Month=monthOfYear;
                        Day=dayOfMonth;

                    }
                };

                Calendar c = Calendar.getInstance();

                //Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
                myDateDialog.show();
            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        etTime.setText(hourOfDay + ":" + minute);
                        Hour=hourOfDay;
                        Min=minute;

                    }


                };
                Calendar time = Calendar.getInstance();

                //Create the Time Picker Dialog
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),true);
                myTimeDialog.show();
            }
        });

    }
}