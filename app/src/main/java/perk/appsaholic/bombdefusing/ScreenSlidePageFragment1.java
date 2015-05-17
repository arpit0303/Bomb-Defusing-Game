package perk.appsaholic.bombdefusing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Arpit on 16/05/15.
 */
public class ScreenSlidePageFragment1 extends Fragment implements View.OnClickListener {

    ViewPager mPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page_1, container, false);

        mPager = (ViewPager) getActivity().findViewById(R.id.pager);

        TextView skip = (TextView) rootView.findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });

        Button nextBtn = (Button) rootView.findViewById(R.id.next);
        Button previousBtn = (Button) rootView.findViewById(R.id.previous);

        nextBtn.setOnClickListener(this);
        previousBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.next:
                mPager.setCurrentItem(2);
                break;
            case R.id.previous:
                mPager.setCurrentItem(0);
                break;
            default:
                break;
        }
    }

}
