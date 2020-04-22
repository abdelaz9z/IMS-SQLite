package com.example.ims.fragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ims.R;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHealthRecord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHealthRecord extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText hrCurrentPhysicianNameEditText;
    private EditText currentPharmacyNameEditText;
    private EditText pharmacyPhoneEditText;
    private EditText capmMedicamentNameEditText;
    private EditText capmMedicationsPhysicianEditText;
    private EditText capmPurposeEditText;
    private EditText capmFreqText;
    private EditText capmDosageEditText;
    private EditText miIllnessEditText;
    private EditText miPhysicianEditText;
    private EditText miTreatmentNotesEditText;
    private EditText spProcedureEditText;
    private EditText spPhysicianEditText;
    private EditText spHospitalEditText;
    private EditText spNotesEditText;
    private EditText doctorsPhoneEditText;

    private Button healthRecordSaveButton;
    private Button capmMedicationsSaveButton;
    private Button miSaveButton;
    private Button spAddButton;
    private Button pvAddButton;

    private TextView dateOfTheLastUpdateTextView;
    private TextView patientNameTextView;
    private TextView capmMedicationsStartDateTextView;
    private TextView capmMedicationsEndDateTextView;
    private TextView miStartDateTextView;
    private TextView miEndDateTextView;
    private TextView spDateTextView;
    private TextView pvTetanusTextView;
    private TextView pvInfluenzaVaccineTextView;
    private TextView pvZostavaxTextView;
    private TextView pvMeningitisTextView;
    private TextView pvYellowFeverTextView;
    private TextView pvPolioTextView;
    private TextView pvHistoryOfVaccinationTextView;


    private ListView majorIllnessesListView;
    private ListView surgicalProceduresListView;
    private ListView patientVaccinesListView;

    private Spinner pvTetanusSpinner;

    // ContentValues values;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int id;


    public FragmentHealthRecord(int patientId) {
        // Required empty public constructor
        this.id = patientId;
    }

    public FragmentHealthRecord() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment health_record.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHealthRecord newInstance(String param1, String param2) {
        FragmentHealthRecord fragment = new FragmentHealthRecord(0);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void saveHealthRecord() {
        String patientPhysicianNameString = hrCurrentPhysicianNameEditText.getText().toString().trim();
        String patientPharmacyNameString = currentPharmacyNameEditText.getText().toString().trim();
        String patientPharmacyPhoneString = pharmacyPhoneEditText.getText().toString().trim();
        String patientDoctorPhoneString = doctorsPhoneEditText.getText().toString().trim();
        String patientDateLastUpdateString = dateOfTheLastUpdateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(patientPhysicianNameString)
                && TextUtils.isEmpty(patientPharmacyNameString)
                && TextUtils.isEmpty(patientPharmacyPhoneString)
                && TextUtils.isEmpty(patientDoctorPhoneString)
                && TextUtils.isEmpty(patientDateLastUpdateString)
        ) {
            currentPharmacyNameEditText.setError("please write to name");
            hrCurrentPhysicianNameEditText.setError("please write to name");
            pharmacyPhoneEditText.setError("please write to name");
            return;
        }
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(patientPhysicianNameString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME, patientPhysicianNameString);
        }
        if (TextUtils.isEmpty(patientPharmacyNameString)) {
            currentPharmacyNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME, patientPharmacyNameString);
        }
        if (TextUtils.isEmpty(patientPharmacyPhoneString)) {
            pharmacyPhoneEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE, patientPharmacyPhoneString);
        }
        if (TextUtils.isEmpty(patientDoctorPhoneString)) {
            dateOfTheLastUpdateTextView.setError("please write to pharmacy");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE, patientDoctorPhoneString);
        }
        if (TextUtils.isEmpty(patientDateLastUpdateString)) {
            dateOfTheLastUpdateTextView.setError("please write to phone");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE, patientDateLastUpdateString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PATIENT_ID, id);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void saveMedications() {
        String medicationMedicamentNameString = capmMedicamentNameEditText.getText().toString().trim();
        String medicationPhysicianString = capmMedicationsPhysicianEditText.getText().toString().trim();
        String medicationPurposeString = capmPurposeEditText.getText().toString().trim();
        String medicationFREQString = capmFreqText.getText().toString().trim();
        String medicationDosageString = capmDosageEditText.getText().toString().trim();
        String medicationStartDateString = capmMedicationsStartDateTextView.getText().toString().trim();
        String medicationEndDateString = capmMedicationsEndDateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(medicationMedicamentNameString)
                && TextUtils.isEmpty(medicationPhysicianString)
                && TextUtils.isEmpty(medicationPurposeString) &&
                TextUtils.isEmpty(medicationFREQString) &&
                TextUtils.isEmpty(medicationEndDateString) &&
                TextUtils.isEmpty(medicationStartDateString) &&
                TextUtils.isEmpty(medicationDosageString)

        ) {
            capmMedicamentNameEditText.setError("please write to name");
            capmMedicationsPhysicianEditText.setError("please write to name");
            capmPurposeEditText.setError("please write to name");
            return;
        }
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(medicationStartDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE, medicationStartDateString);
        }

        if (TextUtils.isEmpty(medicationEndDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE, medicationEndDateString);
        }

        if (TextUtils.isEmpty(medicationMedicamentNameString)) {
            return;

        } else {
            //  values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, medicationMedicamentNameString);
        }

        if (TextUtils.isEmpty(medicationPhysicianString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, medicationPhysicianString);
        }
        if (TextUtils.isEmpty(medicationPurposeString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE, medicationPurposeString);
        }
        if (TextUtils.isEmpty(medicationFREQString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ, medicationFREQString);
        }
        if (TextUtils.isEmpty(medicationDosageString)) {
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE, medicationDosageString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID, id);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void addMajor() {
        String majorIllnessString = miIllnessEditText.getText().toString().trim();
        String majorPhysicianString = miPhysicianEditText.getText().toString().trim();
        String majorTreatmentNotesString = miTreatmentNotesEditText.getText().toString().trim();
        String majorStartDateString = miStartDateTextView.getText().toString().trim();
        String majorEndDateString = miEndDateTextView.getText().toString().trim();

        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(majorIllnessString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS, majorIllnessString);
        }
        if (TextUtils.isEmpty(majorPhysicianString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN, majorPhysicianString);
        }
        if (TextUtils.isEmpty(majorTreatmentNotesString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES, majorTreatmentNotesString);
        }
        if (TextUtils.isEmpty(majorStartDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE, majorStartDateString);
        }
        if (TextUtils.isEmpty(majorEndDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_END_DATE, majorEndDateString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            hrCurrentPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PATIENT_ID, id);
        }

        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.MajorIllnessesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void addProcedures() {
        String proceduresString = spProcedureEditText.getText().toString().trim();
        String proceduresPhysicianString = spPhysicianEditText.getText().toString().trim();
        String proceduresHospitalString = spHospitalEditText.getText().toString().trim();
        String proceduresNotesString = spNotesEditText.getText().toString().trim();
        String proceduresDateSurgicalString = spDateTextView.getText().toString().trim();
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(proceduresString)) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE, proceduresString);
        }
        if (TextUtils.isEmpty(proceduresPhysicianString)) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN, proceduresPhysicianString);
        }
        if (TextUtils.isEmpty(proceduresHospitalString)) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL, proceduresHospitalString);
        }
        if (TextUtils.isEmpty(proceduresNotesString)) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_NOTES, proceduresNotesString);
        }
        if (TextUtils.isEmpty(proceduresDateSurgicalString)) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES, proceduresDateSurgicalString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PATIENT_ID, id);
        }


    }

    public void addVaccines() {
        Spinner vaccinesSpinner;

        String vaccinesTETANUSString = pvTetanusTextView.getText().toString().trim();
        String vaccinesINFLUENZAVACCINEString = pvInfluenzaVaccineTextView.getText().toString().trim();
        String vaccinesZOSTAVAXString = pvZostavaxTextView.getText().toString().trim();
        String vaccinesMENINGITISString = pvMeningitisTextView.getText().toString().trim();
        String vaccinesYELLOWFEVERString = pvYellowFeverTextView.getText().toString().trim();
        String vaccinesPOLIOString = pvPolioTextView.getText().toString().trim();

        String vaccinesHistoryString = pvHistoryOfVaccinationTextView.getText().toString().trim();

/*
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }*/

    }

    // initialization
    public void init() {
        // Health record
        hrCurrentPhysicianNameEditText = view.findViewById(R.id.edit_hr_current_physician_name);
        currentPharmacyNameEditText = view.findViewById(R.id.edit_hr_current_pharmacy_name);
        dateOfTheLastUpdateTextView = view.findViewById(R.id.text_hr_date_of_the_last_update);
        doctorsPhoneEditText = view.findViewById(R.id.edit_hr_doctors_phone);
        pharmacyPhoneEditText = view.findViewById(R.id.edit_hr_pharmacy_phone);
        healthRecordSaveButton = view.findViewById(R.id.button_hr_save);
//aa
        // Current and past medications
        capmMedicamentNameEditText = view.findViewById(R.id.edit_capm_medicament_name);
        capmDosageEditText = view.findViewById(R.id.edit_capm_dosage);
        capmFreqText = view.findViewById(R.id.edit_capm_freq);
        capmMedicationsStartDateTextView = view.findViewById(R.id.text_capm_medications_start_date);
        capmMedicationsEndDateTextView = view.findViewById(R.id.text_capm_medications_end_date);
        capmMedicationsPhysicianEditText = view.findViewById(R.id.edit_capm_medications_physician);
        capmPurposeEditText = view.findViewById(R.id.edit_capm_purpose);
        capmMedicationsSaveButton = view.findViewById(R.id.button_capm_medications_save);

        // Major illnesses
        miIllnessEditText = view.findViewById(R.id.edit_mi_illness);
        miStartDateTextView = view.findViewById(R.id.text_mi_start_date);
        miEndDateTextView = view.findViewById(R.id.text_mi_end_date);
        miPhysicianEditText = view.findViewById(R.id.edit_mi_physician);
        miTreatmentNotesEditText = view.findViewById(R.id.edit_mi_treatment_notes);
        miSaveButton = view.findViewById(R.id.button_mi_save);
        majorIllnessesListView = view.findViewById(R.id.list_major_illnesses);

        // Surgical procedures
        spProcedureEditText = view.findViewById(R.id.edit_sp_procedure);
        spPhysicianEditText = view.findViewById(R.id.edit_sp_physician);
        spHospitalEditText = view.findViewById(R.id.edit_sp_hospital);
        spDateTextView = view.findViewById(R.id.text_sp_date);
        spNotesEditText = view.findViewById(R.id.edit_sp_notes);
        spAddButton = view.findViewById(R.id.button_sp_add);
        surgicalProceduresListView = view.findViewById(R.id.list_surgical_procedures);

        // Patient vaccines
        pvTetanusTextView = view.findViewById(R.id.text_pv_tetanus);
        pvInfluenzaVaccineTextView = view.findViewById(R.id.text_pv_influenza_vaccine);
        pvZostavaxTextView = view.findViewById(R.id.text_pv_zostavax);
        pvMeningitisTextView = view.findViewById(R.id.text_pv_meningitis);
        pvYellowFeverTextView = view.findViewById(R.id.text_pv_yellow_fever);
        pvPolioTextView = view.findViewById(R.id.text_pv_polio);
        pvTetanusSpinner = view.findViewById(R.id.spinner_pv_tetanus);
        pvHistoryOfVaccinationTextView = view.findViewById(R.id.text_pv_history_of_vaccination);
        pvAddButton = view.findViewById(R.id.button_pv_add);
        patientVaccinesListView = view.findViewById(R.id.list_patient_vaccines);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_health_record, container, false);

        init();
        dateOfTheLastUpdateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        dateOfTheLastUpdateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });
        healthRecordSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHealthRecord();
            }
        });
        miSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
