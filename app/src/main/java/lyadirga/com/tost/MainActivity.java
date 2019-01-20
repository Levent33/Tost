package lyadirga.com.tost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


  }

  public void clickTost(View view) {
    Tost tost=new Tost(this);
    tost.setMesaj("Bu bir\ntosttur.");
    tost.setTextSize(20);
    tost.setDuration(Tost.SHORT);
    tost.show();
  }

  public void clickToast(View view) {
    Toast.makeText(this,"bu bir toasttır.",Toast.LENGTH_SHORT).show();
  }
}
