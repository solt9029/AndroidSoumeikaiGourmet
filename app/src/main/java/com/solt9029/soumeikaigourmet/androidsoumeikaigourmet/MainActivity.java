package com.solt9029.soumeikaigourmet.androidsoumeikaigourmet;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 10;

    private String selectedClub = "部活指定なし";
    private String selectedDistance = "距離指定なし";

    private Shop shops[] = {
            new Shop(1,"畔居", "和食", "03-3271-2000", "東京都中央区日本橋1-2-10", 39, 139, "https://tabelog.com/tokyo/A1302/A130203/13034278/", "日本橋駅15秒！掘炬燵個室22名・椅子個室28名迄。接待・歓送迎・結納を静かな個室で。", "北村元嚝", "無", "昭和39年", "三九会"),
            new Shop(2,"東洋", "洋食", "03-3271-0003", "東京都中央区日本橋1-2-10", 39, 139, "https://tabelog.com/tokyo/A1302/A130203/13034278/", "銀座線東西線日本橋駅B9出口より徒歩30秒！最大320席！同期会にぜひご利用ください！", "北村元嚝", "無", "昭和39年", "三九会"),
            new Shop(3,"ラコルタ", "イタリアン", "03-3231-0610", "東京都中央区日本橋2-9-6", 39, 139, "https://tabelog.com/tokyo/A1302/A130203/13034278/", "リピート率90％を誇る、歓送迎会セットあります。", "長谷川隆洋", "水泳部", "昭和61年", "新世輝"),
            new Shop(4,"吉野鮨本店", "寿司", "03-3274-3001", "東京都中央区日本橋3-8-11", 39, 139, "https://tabelog.com/tokyo/A1302/A130203/13034278/", "塩と酢のみで仕上げたシャリ、仕上げに自家製の煮切醤油を塗って出すスタイルは、創業当時のまま。", "吉野正敏", "ESS", "昭和61年", "新世輝")
    };
    private ArrayList<Shop> shopList; // 表示するお店のみを格納する
    private String distanceOptions[] = {"距離指定なし", "5km圏内", "10km圏内", "20km圏内", "50km圏内", "100km圏内"};
    private String clubOptions[] = {"部活指定なし", "風紀委員会", "ボーイスカウト部", "硬式野球部", "生徒会本部", "硬式テニス部", "美術演劇部", "美術部", "山岳部", "ESS", "バドミントン部", "水泳部", "卓球部", "フェンシング部", "物理部", "化学部", "バスケ部", "剣道部", "柔道部", "マンドリン部", "吹奏楽班", "相撲部", "応援指導班", "JRC インターアクト", "商業研究部", "スキー部"};

    private ListView shopListView; // お店のリストを表示している部分（View）

    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location currentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // GoogleApiClientの初期化
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, this)
                .build();

        // 位置情報リクエストをどのくらいの間隔で出すか
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        startLocationUpdates();



        // 表示するお店のみを格納する
        shopListView = (ListView)findViewById(R.id.shop_list);

        // shopListは初めは全て表示することにする
        shopList = new ArrayList<>();
        for (int i = 0; i < shops.length; i++) {
            shopList.add(shops[i]);
        }

        ShopAdapter shopAdapter = new ShopAdapter(this, shopList);
        shopListView.setAdapter(shopAdapter);
        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                // リンクに飛ばす
                String link = (String)shopList.get(pos).getLink();
                Uri uri = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });



        // 距離Spinner
        Spinner distanceSpinner = findViewById(R.id.distance_spinner);
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distanceOptions);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);
        distanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                MainActivity.this.selectedDistance = (String)spinner.getSelectedItem();

                shopList.clear();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // 何も選択されなかったとき
            }
        });



        // 部活動Spinner
        Spinner clubSpinner = findViewById(R.id.club_spinner);
        ArrayAdapter<String> clubAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clubOptions);
        clubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clubSpinner.setAdapter(clubAdapter);
        clubSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner)parent;
                selectedClub = (String)spinner.getSelectedItem();

                // 表示する用のお店リストを更新する
                shopList.clear();
                for (int i = 0; i < shops.length; i++) {
                    if (!selectedClub.equals("部活指定なし")) {
                        if (!selectedClub.equals(shops[i].getOwnerClub())) {
                            continue;
                        }
                    }
                    shopList.add(shops[i]);
                }
                shopListView.invalidateViews();
             }
            public void onNothingSelected(AdapterView<?> parent) {
                // 何も選択されなかったとき
            }
        });
    }



    private void startLocationUpdates() {
        // GooglePlayServicesが有効でないならダイアログを出して終了する
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog((Activity) this, resultCode, 2).show();
            return;
        }

        // スマートフォン自体の位置情報設定が有効になっているか確認する
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().setAlwaysShow(true).addLocationRequest(locationRequest);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    // スマートフォン自体の位置情報設定は有効になっている
                    case LocationSettingsStatusCodes.SUCCESS:
                        // アプリに対して位置情報を許可している？
                        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, MainActivity.this);
                        } else {
                            // RationaleDialogを出す必要があるかどうか？
                            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                                // RationaleDialogを出しています
                                new AlertDialog.Builder(MainActivity.this)
                                        .setPositiveButton("許可する", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                                            }
                                        })
                                        .setNegativeButton("しない", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                            }
                                        })
                                        .setCancelable(false)
                                        .setMessage("このアプリは位置情報の利用を許可する必要があります。")
                                        .show();
                                return;
                            }
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                        }
                        break;
                    // スマートフォン自体の位置情報設定が有効になっていない
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                        }
                        break;
                    // 位置情報がそもそも使えないという特殊状況
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    // このメソッドは、スマホ自体の位置情報設定画面から戻ってきたとき専用になるね
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CHECK_SETTINGS) {
            return;
        }
        startLocationUpdates();
    }

    // アプリに対する情報提供を許可してくれるかどうかのダイアログに対する返答を受け取った時
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        // if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) の条件をつけると、位置情報に関することだとわかる
        startLocationUpdates();
    }

    @Override
    protected void onStop() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this); // 位置情報の更新を止める
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

}
