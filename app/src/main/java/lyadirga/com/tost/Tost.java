package lyadirga.com.tost;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;



public class Tost {

  private Context context;
  private TextView text;
  private LinearLayout rootView;
  public static final int SHORT = 1000;
  public static final int LONG = 2000;
  private int duration = SHORT;
  private int size = 18;

  public Tost(Context context) {
    this.context=context;
    init();
  }

  private void init() {
    text=new TextView(context);
    text.setGravity(Gravity.CENTER);
    text.setTextColor(Color.WHITE);
    text.setPadding(40,40,40,40);

    rootView = new LinearLayout(context);
    rootView.setGravity(Gravity.CENTER);
    rootView.addView(text);


    RoundRectShape roundRectShape = new RoundRectShape(new float[]{
        40, 40, 40, 40,
        40, 40, 40, 40}, null, null);
    ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
    shapeDrawable.getPaint().setColor(Color.parseColor("#af000000"));

    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
      text.setBackground(shapeDrawable);
    }else {
     text.setBackgroundDrawable(shapeDrawable);
    }

  }



  public void show(){

    text.setTextSize(size);

    ((Activity)context).getWindow().addContentView(rootView, new LinearLayout.LayoutParams(
        LayoutParams.MATCH_PARENT,
        LayoutParams.MATCH_PARENT));

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        startAnimation();
      }
    },duration);
  }

  private void startAnimation() {
    ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(text,"alpha",1.0f,0.0f);
    ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(text,"scaleX",1.0f,2.0f);
    ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(text,"scaleY",1.0f,2.0f);
    AnimatorSet set=new AnimatorSet();
    set.setDuration(700);
    set.playTogether(animatorAlpha,animatorScaleX,animatorScaleY);
    set.start();
    set.addListener(new AnimatorListenerAdapter() {
      @Override
      public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        text.setVisibility(View.GONE);
        rootView.setVisibility(View.GONE);
        text=null;
        rootView=null;
      }
    });
  }


  public void setMesaj(String s){
    text.setText(s);
  }

  public void setDuration(int duration){
    this.duration=duration;
  }

  public void setTextSize(int size){
    this.size=size;
  }


}
