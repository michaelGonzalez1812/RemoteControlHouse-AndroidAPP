package smart.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smart.house.sockets.socket;

public class ControlFragment extends Fragment {


    public ControlFragment() {
        // Required empty public constructor
    }

    public static ControlFragment newInstance() {
        return new ControlFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        socket sock = new socket();
        sock.llamada();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.control_fragment, container, false);

        ((AppCompatImageView) view.findViewById(R.id.control_fragment__app_compat_imageview))
                .setImageDrawable(getResources().getDrawable(R.drawable.croquis));

        return view;
    }
}
