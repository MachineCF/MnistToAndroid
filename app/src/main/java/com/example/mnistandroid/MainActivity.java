package com.example.mnistandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String MODEL_FILE = "file:///android_asset/mnist.pb"; //模型存放路径
    TextView txt;
    TextView tv;
    ImageView imageView;
    Bitmap bitmap;
    PredictionTF preTF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        txt=(TextView)findViewById(R.id.txt_id);
        imageView =(ImageView)findViewById(R.id.imageView1);
        bitmap = BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/test.bmp"));
        imageView.setImageBitmap(bitmap);
        preTF = new PredictionTF(getAssets(),MODEL_FILE);//输入模型存放路径，并加载TensoFlow模型
    }

    public void click01(View v){
        String res="预测结果为：";
        int[] result= preTF.getPredict(bitmap);
        for (int i=0;i<result.length;i++){
            Log.i(TAG, res+result[i] );
            res=res+String.valueOf(result[i])+" ";
        }
        txt.setText(res);
    }
}
