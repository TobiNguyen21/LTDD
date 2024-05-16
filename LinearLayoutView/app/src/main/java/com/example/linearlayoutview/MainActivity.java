package com.example.linearlayoutview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etFirstName, etLastName, etPhone, etEmail, etWork, etGroups, etAddress;
    private ImageButton imgBtnToggleName;
    private LinearLayout firstNameLastNameContainer;
    private Button btnCancel, btnSave;
    private boolean isNameExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etWork = findViewById(R.id.etWork);
        etGroups = findViewById(R.id.etGroups);
        etAddress = findViewById(R.id.etAddress);

        imgBtnToggleName = findViewById(R.id.imgBtnToggleName);
        firstNameLastNameContainer = findViewById(R.id.firstNameLastNameContainer);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        imgBtnToggleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNameExpanded = !isNameExpanded;
                firstNameLastNameContainer.setVisibility(isNameExpanded ? View.VISIBLE : View.GONE);
                imgBtnToggleName.setImageResource(isNameExpanded ? R.drawable.arrows_up_icon : R.drawable.arrow_expand_down);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Cancel button click
                Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Save button click
                String name = etName.getText().toString();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String work = etWork.getText().toString();
                String groups = etGroups.getText().toString();
                String address = etAddress.getText().toString();

                // Display the entered data
                Toast.makeText(MainActivity.this, "Saved: " + name + ", " + firstName + ", " + lastName + ", " + phone + ", " + email + ", " + work + ", " + groups + ", " + address, Toast.LENGTH_SHORT).show();
            }
        });
    }
}