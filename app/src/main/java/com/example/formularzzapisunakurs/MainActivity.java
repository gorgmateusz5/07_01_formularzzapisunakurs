
package com.example.formularzzapisunakurs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail;
    private Button btnBirthDate, btnSubmit;
    private Spinner spCourse;
    private RadioGroup rgMode;
    private SeekBar sbLevel;
    private TextView tvLevelValue, tvSummary;
    private MaterialSwitch swNewsletter;
    private CheckBox cbTerms;

    private String birthDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        btnBirthDate = findViewById(R.id.btnBirthDate);
        btnSubmit = findViewById(R.id.btnSubmit);
        spCourse = findViewById(R.id.spCourse);
        rgMode = findViewById(R.id.rgMode);
        sbLevel = findViewById(R.id.sbLevel);
        tvLevelValue = findViewById(R.id.tvLevelValue);
        tvSummary = findViewById(R.id.tvSummary);
        swNewsletter = findViewById(R.id.swNewsletter);
        cbTerms = findViewById(R.id.cbTerms);

        btnBirthDate.setOnClickListener(v -> showDatePicker());

        tvLevelValue.setText(
                String.valueOf(sbLevel.getProgress())
        );

        sbLevel.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar,
                            int progress,
                            boolean fromUser) {

                        tvLevelValue.setText(String.valueOf(progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });

        btnSubmit.setOnClickListener(v -> {
            tvSummary.setText(buildSummary());
        });
    }

    private void showDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {

                    birthDate = String.format(
                            Locale.US,
                            "%04d-%02d-%02d",
                            year,
                            month + 1,
                            dayOfMonth
                    );

                    btnBirthDate.setText(birthDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        dialog.getDatePicker().setMaxDate(
                System.currentTimeMillis()
        );

        dialog.show();
    }

    private String buildSummary() {

        String mode = rgMode.getCheckedRadioButtonId()
                == R.id.rbOnsite
                ? getString(R.string.mode_onsite)
                : getString(R.string.mode_remote);

        String newsletter = swNewsletter.isChecked()
                ? getString(R.string.yes_short)
                : getString(R.string.no_short);

        String course = spCourse.getSelectedItem() != null
                ? spCourse.getSelectedItem().toString()
                : "";

        return getString(
                R.string.summary_format,
                etName.getText().toString().trim(),
                etEmail.getText().toString().trim(),
                birthDate,
                course,
                mode,
                sbLevel.getProgress(),
                newsletter
        );
    }
}
