package com.example.attyuttamsaha.facultyassigner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link facultyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link facultyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class facultyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public facultyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment facultyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static facultyFragment newInstance() {
        facultyFragment fragment = new facultyFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_faculty, container, false);

        final EditText uText = ((EditText)view.findViewById(R.id.username));


        final EditText pText = ((EditText)view.findViewById(R.id.password));


        Button login_button =(Button)view.findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String username = uText.getText().toString();
                final String password = pText.getText().toString();
                Login lc = new Login(username,password,"F",getContext());//checking for the authenticity of the user
                lc.checkUser();
            }
        });


        Button signup_button =(Button)view.findViewById(R.id.signup_button);
        signup_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String username = uText.getText().toString();
                final String password = pText.getText().toString();
                Login lc = new Login(username,password,"F",getContext());//checking for the authenticity of the user
                lc.insertUser();
            }
        });

        return view;
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
