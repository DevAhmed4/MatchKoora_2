package matchkoora.devteam.com.matchkoora_;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MySliderAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private int []mLayout;
    private Context context;

    public MySliderAdapter(int[] layouts, Context context) {
        mLayout = layouts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mLayout.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(mLayout[position],container,false);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}
