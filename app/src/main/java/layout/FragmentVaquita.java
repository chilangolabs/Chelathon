package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.chilangolabs.modelonow.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVaquita extends Fragment {

    boolean show = false;

    public FragmentVaquita() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragment_vaquita, container, false);


        Button btn = (Button) rootView.findViewById(R.id.btnVaquita);
        final RelativeLayout relative = (RelativeLayout) rootView.findViewById(R.id.relativevaquita);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!show) {
                    relative.setBackgroundResource(R.mipmap.vakitafondo);
                } else {
                    relative.setBackgroundResource(R.mipmap.bakitaform);
                }
            }
        });

        return rootView;
    }

}
