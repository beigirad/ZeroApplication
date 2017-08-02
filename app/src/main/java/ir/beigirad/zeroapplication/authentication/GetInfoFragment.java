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

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.beigirad.zeroapplication.R;
import ir.beigirad.zeroapplication.model.LoginInfoModel;


public class GetInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int userId;
    private String mParam2;

    private LoginInterface mListener;


    @BindView(R.id.get_info_et_family)
    EditText et_family;
    @BindView(R.id.get_info_et_name)
    EditText et_name;
    @BindView(R.id.get_info_til_family)
    TextInputLayout til_family;
    @BindView(R.id.get_info_til_name)
    TextInputLayout til_name;
    @BindView(R.id.get_info_btn_submit)
    Button btn_submit;


    public GetInfoFragment() {
        // Required empty public constructor
    }

    public static GetInfoFragment newInstance(int user_id) {
        GetInfoFragment fragment = new GetInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, user_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_info, container, false);
        ButterKnife.bind(this, view);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInfo();
            }
        });


        return view;
    }

    private void validateInfo() {
        String family = et_family.getText().toString().trim();
        String name = et_name.getText().toString().trim();

        if (name.length() < 3) {
            til_name.setError(getResources().getString(R.string.invalid_name));
            return;
        }
        if (family.length() < 3) {
            til_family.setError(getResources().getString(R.string.invalid_family));

            return;
        }

        LoginInfoModel loginInfoModel = new LoginInfoModel(name, family, "null@gmail.com", userId, "");
        mListener.onSubmitInfo(loginInfoModel);

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
