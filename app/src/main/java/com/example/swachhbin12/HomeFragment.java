package com.example.swachhbin12;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
public class HomeFragment extends Fragment implements View.OnClickListener {

    private int[] mImages=new int[]{
      R.drawable.image_main,R.drawable.image_organic,R.drawable.image_veg,R.drawable.image_shepherd
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CarouselView carouselView = view.findViewById(R.id.home_carousel);
        carouselView.setImageListener(new ImageListener(){
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);

            }
        });
        carouselView.setPageCount(mImages.length);
        Button b1 = (Button)view.findViewById(R.id.buy_button);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new BuyFragment()).commit();
    }
}
