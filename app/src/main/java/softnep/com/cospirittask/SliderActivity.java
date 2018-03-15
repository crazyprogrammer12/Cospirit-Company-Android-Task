package softnep.com.cospirittask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SliderActivity extends AppCompatActivity {

    private static final float RATIO_SCALE = 0.3f;
    ViewPager sliderVP;
    ViewPagerAdapter adapter;

    private int[] images = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        sliderVP = (ViewPager) findViewById(R.id.imageViewPager);
        sliderVP.setClipToPadding(false);
        sliderVP.setPadding(120, 0, 120, 0);
        sliderVP.setOffscreenPageLimit(3);
        sliderVP.setPageMargin(10);

        sliderVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("", "onPageScrolled: " + position);

                ImageSliderFragment imageFragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(position);
                float scale = 1 - (positionOffset * RATIO_SCALE);
                imageFragment.scaleImage(scale);


                if (position + 1 < sliderVP.getAdapter().getCount()) {
                    imageFragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(position + 1);
                    scale = positionOffset * RATIO_SCALE + (1 - RATIO_SCALE);
                    imageFragment.scaleImage(scale);
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("", "onPageSelected: " + position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {
                ((ImageSliderFragment) adapter.getItem(sliderVP.getCurrentItem())).sliderImage.setForeground(getResources().getDrawable(R.drawable.shape_overaly_current));

                Log.i("", "onPageScrollStateChanged: " + state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    ImageSliderFragment fragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(sliderVP.getCurrentItem());
                    fragment.scaleImage(1);
                    fragment.setSelected(true);
                    if (sliderVP.getCurrentItem() > 0) {
                        fragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(sliderVP.getCurrentItem() - 1);
                        fragment.scaleImage(1 - RATIO_SCALE);
                    }

                    if (sliderVP.getCurrentItem() + 1 < sliderVP.getAdapter().getCount()) {
                        fragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(sliderVP.getCurrentItem() + 1);
                        fragment.scaleImage(1 - RATIO_SCALE);
                    }
                }else{
                    ImageSliderFragment fragment = (ImageSliderFragment) ((ViewPagerAdapter) sliderVP.getAdapter()).getItem(sliderVP.getCurrentItem());
                    fragment.setSelected(false);
                }


            }
        });

        setupViewPager(sliderVP);
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < images.length; i++) {
            adapter.addFragment(ImageSliderFragment.newInstance(images[i]));
        }
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}



