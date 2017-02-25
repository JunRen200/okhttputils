package comqq.example.asus_pc.okhttputils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private ImageView img;
    private Button btn_photo;
    private Button btn_down;
    private String URL_photo="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488021305121&di=e3ffa608c234eed636c83b313439b33b&imgtype=0&src=http%3A%2F%2Fp3.gexing.com%2Fshaitu%2F20120730%2F1411%2F501625ae7a632.jpg";
    private String URL_down="http://gdown.baidu.com/data/wisegame/fc328fa3a33efe57/QQ_482.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img= (ImageView) findViewById(R.id.img);
        btn_down= (Button) findViewById(R.id.btn_down);
        btn_photo= (Button) findViewById(R.id.btn_photo);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        btn_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url(URL_photo)
                .tag(this).build().execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }
                });
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get().url(URL_photo).tag(this).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"QQ_482.apk") {
                    @Override
                    public void inProgress(float v, long l) {
                        progressBar.setProgress((int)(100*v));
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(MainActivity.this,"sibai",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, File file) {
                        Toast.makeText(MainActivity.this,"chengong",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
