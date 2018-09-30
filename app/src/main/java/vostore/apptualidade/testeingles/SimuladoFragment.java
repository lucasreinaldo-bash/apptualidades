package vostore.apptualidade.testeingles;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vostore.apptualidade.MainActivity;
import vostore.apptualidade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimuladoFragment extends Fragment {

    private View myFragment;

    private Button comecar, voltar;


    public static SimuladoFragment newInstance() {
        // Required empty public constructor
        SimuladoFragment simuladoFragment = new SimuladoFragment();
        return simuladoFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_simulado,
                container, false);

        comecar = rootView.findViewById(R.id.btn_comecar);



        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              updateDetail();

            }
        });
         return rootView;



    }
    public void updateDetail() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        startActivity(intent);
    }

}
