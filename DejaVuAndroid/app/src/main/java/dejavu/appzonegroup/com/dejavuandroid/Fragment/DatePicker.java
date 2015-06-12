package dejavu.appzonegroup.com.dejavuandroid.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;

/**
 * Created by CrowdStar on 2/19/2015.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static DateSetListener mDateSetListener;


    public DatePicker newInstance(DateSetListener listener) {
        DatePicker datePicker = new DatePicker();
        mDateSetListener = listener;
        return datePicker;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mDateSetListener.onDateSet(simpleDateFormat.format(calendar.getTime()));
    }
}
