package smart.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smart.house.sockets.ImageReciever;

public class PictureFragment extends Fragment {

    private View view;

    public PictureFragment() {
        // Required empty public constructor
    }

    public static PictureFragment newInstance() {
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.picture_fragment, container, false);

        new ImageReciever().execute(getContext(), view.findViewById(R.id.picture_fragment_app_compat_imageview));

        view.findViewById(R.id.picture_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageReciever().execute(getContext(), view.findViewById(R.id.picture_fragment_app_compat_imageview));
            }
        });

        return view;
    }

}
