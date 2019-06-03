package com.appsaga.flip_bank_card;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CardView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CardView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView number,name,cvv,exp;
    EditText Number,Name,Cvv ,Exp;
    int count=0;
    ImageView typecard;
    long time;
    String monthYearStr, validthru = "";
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CardView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardView.
     */
    // TODO: Rename and change types and number of parameters
    public static CardView newInstance(String param1, String param2) {
        CardView fragment = new CardView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public String getNumber()
    {
        return Number.getText().toString();
    }
    public String getCvv()
    {
        return Cvv.getText().toString();
    }
    public String getName()
    {
        return Name.getText().toString();
    }
    public String getExp()
    {
        return Exp.getText().toString();
    }
    String formatMonthYear(String str) {
        Date date = null;
        try {
            date = input.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_card_view, container, false);
        name=view.findViewById(R.id.holdername);
        number=view.findViewById(R.id.cardnumber);
        exp=view.findViewById(R.id.expdate);
        cvv=view.findViewById(R.id.cvv);
        Name=view.findViewById(R.id.holder);
        Number=view.findViewById(R.id.number);
        Cvv=view.findViewById(R.id.cvvno);
        Exp=view.findViewById(R.id.expiry);
        final EasyFlipView fp = view.findViewById(R.id.flip);
        Number.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable mEdit) {
                if (fp.isBackSide())
                    fp.flipTheView();
                if (count <= Number.getText().toString().length()
                        && (Number.getText().toString().length() == 4
                        || Number.getText().toString().length() == 10
                        || Number.getText().toString().length() == 16)) {
                    Number.setText(Number.getText().toString() + "  ");
                    int pos = Number.getText().length();
                    Number.setSelection(pos);
                } else if (count >= Number.getText().toString().length()
                        && (Number.getText().toString().length() == 4
                        || Number.getText().toString().length() == 9
                        || Number.getText().toString().length() == 14)) {
                    Number.setText(Number.getText().toString().substring(0, Number.getText().toString().length() - 1));
                    int pos = Number.getText().length();
                    Number.setSelection(pos);
                }
                count = Number.getText().toString().length();
                if (mEdit.length() > 2) {
                    if (Integer.parseInt(mEdit.toString().substring(0, 2)) >= 40 && Integer.parseInt(mEdit.toString().substring(0, 2)) <= 49) {
                        typecard.setImageResource(R.drawable.visa);
                    } else if (Integer.parseInt(mEdit.toString().substring(0, 2)) >= 51 && Integer.parseInt(mEdit.toString().substring(0, 2)) <= 55) {
                        typecard.setImageResource(R.drawable.mastercard);
                    } else if (Integer.parseInt(mEdit.toString().substring(0, 2)) >= 56 && Integer.parseInt(mEdit.toString().substring(0, 2)) <= 59) {
                        typecard.setImageResource(R.drawable.maestro);
                    } else if (Integer.parseInt(mEdit.toString().substring(0, 2)) >= 60 && Integer.parseInt(mEdit.toString().substring(0, 2)) <= 69) {
                        typecard.setImageResource(R.drawable.rupay);
                    }
                } else if (mEdit.toString().equals("")) {
                    typecard.setImageDrawable(null);
                }
                number.setText(mEdit.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable mEdit) {
                if (fp.isBackSide())
                    fp.flipTheView();
                name.setText(mEdit.toString().toUpperCase());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        Cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable mEdit) {
                if (fp.isFrontSide())
                    fp.flipTheView();
                cvv.setText(mEdit.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        Exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fp.isBackSide())
                    fp.flipTheView();
                MonthYearPickerDialog pickerDialog = new MonthYearPickerDialog();
                pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                        monthYearStr = year + "-" + (month + 1) + "-" + i2;
                        validthru = monthYearStr;
                        try {
                            java.util.Date mDate = input.parse(monthYearStr);
                            time = mDate.getTime();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Exp.setText(formatMonthYear(monthYearStr));
                        exp.setText(formatMonthYear(monthYearStr));
                    }
                });
                pickerDialog.show(getChildFragmentManager(), "MonthYearPickerDialog");

            }
        });
        return  view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
