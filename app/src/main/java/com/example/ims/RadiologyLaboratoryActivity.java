package com.example.ims;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class RadiologyLaboratoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    //List of clients referred to the radiology laboratory
    private AutoCompleteTextView radiologyLocPatientNameAutoCompleteTextView;
    private ListView RadiologyLocPatientNameListView;

    //Patient records
    private TextView radiologyPrBillToNameTextView;
    private TextView radiologyPrDateOfBirthTextView;
    private TextView radiologyPrPatientIdTextView;
    private TextView radiologyPrMedicalRecordIdTextView;
    private TextView radiologyPrNextAppointmentDateTextView;
    private TextView radiologyPrNextTreatmentPlanReviewDateTextView;
    private TextView radiologyPrPhysicianSignatureTextView;
    private TextView radiologyPrDateSignedTextView;

    //Patient progress
    private ListView radiologyPpProgressNotesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiologylaboratory);

        init();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_item_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_reception:
                intent = new Intent(this, ReceptionActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_doctor:
                intent = new Intent(this, TheDoctorActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_analysis_lab:
                intent = new Intent(this, AnalysisLabActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_radiology_laboratory:
                intent = new Intent(this, RadiologyLaboratoryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_pharmacy:
                intent = new Intent(this, ThePharmacyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_financial_accounts:
                intent = new Intent(this, FinancialAccountsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_personnel:
                intent = new Intent(this, PersonnelActivity.class);
                startActivity(intent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // initial
    private void init() {
        mDrawerLayout = findViewById(R.id.activity_radiology_laboratory);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);


        //List of clients referred to the radiology laboratory
        radiologyLocPatientNameAutoCompleteTextView=findViewById(R.id.text_radiology_loc_patient_name);
        RadiologyLocPatientNameListView=findViewById(R.id.list_radiology_loc_patient_name);

        //Patient records
        radiologyPrBillToNameTextView=findViewById(R.id.text_radiology_pr_bill_to_name);
        radiologyPrDateOfBirthTextView=findViewById(R.id.text_radiology_pr_date_of_birth);
        radiologyPrPatientIdTextView=findViewById(R.id.text_radiology_pr_patient_id);
        radiologyPrMedicalRecordIdTextView=findViewById(R.id.text_radiology_pr_medical_record_id);
        radiologyPrNextAppointmentDateTextView=findViewById(R.id.text_radiology_pr_next_appointment_date);
        radiologyPrNextTreatmentPlanReviewDateTextView=findViewById(R.id.text_radiology_pr_next_treatment_plan_review_date);
        radiologyPrPhysicianSignatureTextView=findViewById(R.id.text_radiology_pr_physician_signature);
        radiologyPrDateSignedTextView=findViewById(R.id.text_radiology_pr_date_signed);

        //Patient progress
        radiologyPpProgressNotesListView=findViewById(R.id.list_radiology_pp_progress_notes);
    }

}
