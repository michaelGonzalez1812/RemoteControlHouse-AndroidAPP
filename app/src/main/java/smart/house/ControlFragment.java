package smart.house;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import smart.house.Model.Light;
import smart.house.sockets.DoorsUpdater;
import smart.house.sockets.LightUpdater;

public class ControlFragment extends Fragment{


    public ControlFragment() {
        // Required empty public constructor
    }

    public static ControlFragment newInstance() {
        return new ControlFragment();
    }

    private void setLightsSwitchesListeners(View view) {
        ((Switch) view.findViewById(R.id.control_fragment_switch_light1))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        new LightUpdater().execute(new Light("LS", isChecked));
                    }
                });

        ((Switch) view.findViewById(R.id.control_fragment_switch_light2))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        new LightUpdater().execute(new Light("LC", isChecked));
                    }
                });

        ((Switch) view.findViewById(R.id.control_fragment_switch_light3))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        new LightUpdater().execute(new Light("CA", isChecked));
                    }
                });

        ((Switch) view.findViewById(R.id.control_fragment_switch_light4))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        new LightUpdater().execute(new Light("CB", isChecked));
                    }
                });

        ((Switch) view.findViewById(R.id.control_fragment_switch_light5))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        new LightUpdater().execute(new Light("LB", isChecked));
                    }
                });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.control_fragment, container, false);

        view.findViewById(R.id.control_fragment_update_doors_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DoorsUpdater().execute(
                        (Switch) view.findViewById(R.id.control_fragment_switch_door1),
                        (Switch) view.findViewById(R.id.control_fragment_switch_door2),
                        (Switch) view.findViewById(R.id.control_fragment_switch_door3),
                        (Switch) view.findViewById(R.id.control_fragment_switch_door4),
                        (Switch) view.findViewById(R.id.control_fragment_switch_door5)
                );
            }
        });

        setLightsSwitchesListeners(view);

        ((AppCompatImageView) view.findViewById(R.id.control_fragment__app_compat_imageview))
                .setImageDrawable(getResources().getDrawable(R.drawable.croquis));

        return view;
    }
}
