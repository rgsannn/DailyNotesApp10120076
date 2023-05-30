package com.rgsan.dailynotesapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rgsan.dailynotesapp.R;

/**
 * 10120076
 * Rifqi Galih Nur Ikhsan
 * IF-2
 */

public class ViewPagerAdapter extends PagerAdapter {
    static class ViewHolder {
        TextView sliderTitle;
        TextView sliderDesc;
    }

    Context context;
    int sliderAllTitle[] = {
            R.string.screen1,
            R.string.screen2,
            R.string.screen3,
    };
    int sliderAllDesc[] = {
            R.string.screen1desc,
            R.string.screen2desc,
            R.string.screen3desc,
    };

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderAllTitle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slider_screen, container, false);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.sliderTitle = view.findViewById(R.id.sliderTitle);
        viewHolder.sliderDesc = view.findViewById(R.id.sliderDesc);
        view.setTag(viewHolder);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.sliderTitle.setText(context.getString(sliderAllTitle[position]));
        holder.sliderDesc.setText(context.getString(sliderAllDesc[position]));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}

