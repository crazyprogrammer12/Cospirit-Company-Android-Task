package softnep.com.cospirittask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Prem on 1/8/2018.
 */

public class ImageSliderFragment extends Fragment {

    private int imageResId;
    ImageView sliderImage;
    View rootView;

    public static ImageSliderFragment newInstance(int imageResId) {
        
        Bundle args = new Bundle();
        args.putInt("imageResId", imageResId);

        ImageSliderFragment fragment = new ImageSliderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_image_slider_item, container, false);
        rootView.setScaleY(0.7f);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageResId = getArguments().getInt("imageResId");

        sliderImage = (ImageView) view.findViewById(R.id.image);
        sliderImage.setImageDrawable(getResources().getDrawable(imageResId, null));
    }

    public void scaleImage(float scaleX)
    {
        rootView.setScaleY(scaleX);
        rootView.invalidate();
    }

    public void setSelected(boolean isSelected){
        if (isSelected){
            sliderImage.setForeground(getResources().getDrawable(R.drawable.shape_overaly_current));
        }else{
            sliderImage.setForeground(getResources().getDrawable(R.drawable.shape_overaly_normal));
        }
    }
}
