package ir.beigirad.zeroapplication.authentication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ir.beigirad.zeroapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetCodeFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String phone;
    private String mParam2;

    private LoginInterface mListener;


    @BindView(R.id.get_code_et_code)
    EditText et_code;
    @BindView(R.id.get_code_btn_submit)
    Button btn_submit;
    @BindView(R.id.get_code_til)
    TextInputLayout til_code;
    @BindView(R.id.get_code_tv_phone)
    TextView tv_phone;


    public GetCodeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GetCodeFragment newInstance(String phone) {
        GetCodeFragment fragment = new GetCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phone = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_get_code, container, false);
        ButterKnife.bind(this, view);

        tv_phone.setText(phone);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCode();
            }
        });
        return view;
    }


    private void validateCode() {
        String code = et_code.getText().toString().trim();
        if (code.length() >3) {
            mListener.onSubmitCode(code,phone);
        } else {
            til_code.setError(getResources().getString(R.string.code_not_valid));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginInterface) {
            mListener = (LoginInterface) context;
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
}
