package com.example.huub.tablefootbal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.PlayerPositionDO;

import static com.example.huub.tablefootbal.R.id.imageView;

public class TableFootbalController extends Activity {

    private final static String LOG_TAG = TableFootbalController.class.getSimpleName();
    private GamePanel gPanel;

    public SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        //Force portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Sensor shit
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Screensize shit
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        gPanel = new GamePanel(this,sensorManager,deviceWidth,deviceHeight, this);

        setContentView(gPanel);

        Log.d(LOG_TAG, "Application.onCreate - Initializing application...");
        initializeApplication();
        Log.d(LOG_TAG, "Application.onCreate - Application initialized OK");

    }

    //Hoi
    public void InsertData() {
        // Fetch the default configured DynamoDB ObjectMapper
        final DynamoDBMapper dynamoDBMapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        final PlayerPositionDO data = new PlayerPositionDO(); // Initialize the Notes Object

        // The userId has to be set to user's Cognito Identity Id for private / protected tables.
        // User's Cognito Identity Id can be fetched by using:
        // AWSMobileClient.defaultMobileClient().getIdentityManager().getCachedUserID()
        data.setId(String.valueOf(gPanel.frameID));
        data.setPlayerName("player_2");
        data.setPositionX(gPanel.playerData.x);
        data.setPositionY(gPanel.playerData.y);
        AmazonClientException lastException = null;

        try {
            dynamoDBMapper.save(data);
        } catch (final AmazonClientException ex) {
            Log.e(LOG_TAG, "Failed saving item : " + ex.getMessage(), ex);
            lastException = ex;
        }
    }

    private void initializeApplication() {

        // Initialize the AWS Mobile Client
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());

        // ... Put any application-specific initialization logic here ...
    }
}
