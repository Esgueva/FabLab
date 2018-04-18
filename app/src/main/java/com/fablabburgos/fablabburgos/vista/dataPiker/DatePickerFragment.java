package com.fablabburgos.fablabburgos.vista.dataPiker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;

import com.fablabburgos.fablabburgos.R;

import java.text.DateFormat;
import java.util.Calendar;


@SuppressWarnings("ALL")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


    
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        DateFormat df =  DateFormat.getDateInstance();
        String fecha = day+"/"+(month+1) +"/"+year;
        ((Button) getActivity().findViewById(R.id.btnFechaNacReg)).setText(fecha);
    }

}
