package com.rsmitrahusada.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.rsmitrahusada.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private LinearLayout indicatorLayout;
    private ImageView[] indicatorDots;
    private int[] images = {R.drawable.banner, R.drawable.banner2, R.drawable.banner3};


    public SliderAdapter(Context context) {
        this.context = context;
        this.indicatorDots = new ImageView[getCount()];
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

        if (indicatorLayout != null) {
            selectIndicator(position);
        }
    }

    public void setIndicatorLayout(LinearLayout layout) {
        indicatorLayout = layout;
        createIndicators();
    }

    private void createIndicators() {
        if (indicatorLayout == null || getCount() <= 1) {
            return;
        }

        indicatorLayout.removeAllViews();
        indicatorDots = new ImageView[getCount()];

        int dotSize = 10; // Ukuran dots dalam dp
        int dotMargin = 5; // Margin antara dots dalam dp

        for (int i = 0; i < getCount(); i++) {
            indicatorDots[i] = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dpToPx(dotSize), dpToPx(dotSize)); // Mengubah ukuran dots dari dp ke piksel
            params.setMargins(dpToPx(dotMargin), 0, dpToPx(dotMargin), 0); // Mengubah margin dari dp ke piksel
            indicatorDots[i].setLayoutParams(params);
            indicatorDots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_inactive));
            indicatorLayout.addView(indicatorDots[i]);
        }

        selectIndicator(0);
    }

    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }


    private void selectIndicator(int position) {
        if (indicatorLayout == null || indicatorDots == null) {
            return;
        }

        for (int i = 0; i < indicatorDots.length; i++) {
            if (indicatorDots[i] != null) {
                if (i == position) {
                    indicatorDots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_active));
                } else {
                    indicatorDots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_inactive));
                }
            }
        }
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slide_item, container, false);

        ImageView imageView = view.findViewById(R.id.image_slide);
        imageView.setImageResource(images[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
