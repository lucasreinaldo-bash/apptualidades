package vostore.apptualidade.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import vostore.apptualidade.R;
import vostore.apptualidade.facebook;

/**
 * A simple {@link Fragment} subclass.
 */
public class SobreFragment extends Fragment {

    private Button saiba;



    public static SobreFragment newInstance() {
        // Required empty public constructor
        SobreFragment sobreFragment = new SobreFragment();
        return sobreFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_sobre,
                container, false);

        saiba = rootView.findViewById(R.id.saibamais);

        saiba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), facebook.class);
                startActivity(intent);
            }
        });

        return rootView;


    }

}
