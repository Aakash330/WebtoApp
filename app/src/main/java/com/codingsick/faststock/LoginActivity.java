package com.codingsick.faststock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import me.ibrahimsn.lib.OnItemReselectedListener;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class LoginActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressDialog mProgressDialog;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private SmoothBottomBar mBottomBar;
    private Dialog mDialog;
    private static int i=0;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intiView();
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();


    }

    private void intiView() {
        mProgressDialog=new ProgressDialog(this);

        mWebView=findViewById(R.id.webview);
      //  checkpermssion();

        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage("Wait for momment");
        mWebView.loadUrl("https://faststock.online/");

        mProgressDialog.setCancelable(false);
        mProgressDialog.setIcon(R.mipmap.ic_launcher_round);

        mProgressDialog.show();
        mWebView.setVisibility(View.VISIBLE);

        webSettings=mWebView.getSettings(); //setting initialize


        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setMixedContentMode(0);
        webSettings.setJavaScriptEnabled(true);//java script enable
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient());


        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(false);




        mWebView.setWebViewClient(new MyWebClient());
        i++;

        mNavigationView = (NavigationView) findViewById(R.id.navigationsss);
        mNavigationView.bringToFront();
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                Intent intent;
                mDialog = new Dialog(LoginActivity.this);

                String report;
                Toast toast;
                switch (item.getItemId()) {
                    case R.id.developers:
                        mDialog.setContentView(R.layout.developer_profile);
                        mDialog.setCancelable(true);
                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.show();
                        //    mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        String s = "https://play.google.com/store/search?q=com.codingsick.faststock";
                        intent = new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, s);
                        startActivity(intent);
                        break;
                    case R.id.Feedback:
                        report = "Feedback";
                        Report(report);
                        break;
                    case R.id.Report:
                        report = "Query";
                        Report(report);
                        break;
                    case R.id.suggestion:
                        report = "Suggestion";
                        Report(report);
                        break;
                    case R.id.whatsapp:
                        redireToWhatsapp();
                        break;

                }
                //hello kajaal ho gya hai ruko aur me kr deta hu wait two minute  okay

                return false;

            }
        });
        mDrawerLayout =findViewById(R.id.drawlayout);
        mBottomBar=findViewById(R.id.bottomBar);
        mBottomBar.setOnItemReselectedListener(new OnItemReselectedListener() {
            @Override
            public void onItemReselect(int i) {
                switch (i)
                {
                    case 3:
                        openDrawera();
                        break;

                }
            }
        });
        mBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int item) {
                switch (item)
                {
                    case 0:
                        // mWebView.loadUrl("https://ecom.muftigroup.com/");
                        mWebView.loadUrl("https://faststock.online/");
                        Toast.makeText(LoginActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mWebView.loadUrl("https://faststock.online/registration");

                        Toast.makeText(LoginActivity.this, "Registration", Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        mWebView.loadUrl("https://faststock.online/login");
                        Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_SHORT).show();


                        break;

                    case 3:
                        openDrawera();
                        Toast.makeText(LoginActivity.this, "Draw open", Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });



    }

    private void checkpermssion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},200);
        }



    }





    private void openDrawera() {
        if(!mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }else
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);

        }
    }
    //change the logo background color same as buttonavigation color
    private void Report(String report) {
        Intent  intent = new Intent(Intent.ACTION_SEND);
        String[] receipt = {getString(R.string.mail_id)};
        intent.putExtra(Intent.EXTRA_EMAIL, receipt);
        intent.putExtra(Intent.EXTRA_SUBJECT, report);
        intent.putExtra(Intent.EXTRA_TEXT, "");
        //  intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
        //intent.putExtra(Intent.EXTRA_BCC,"mailbcc@gmail.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(intent.createChooser(intent, "send mail"));

    }
//send me apk ..wait one minute logo change karo aare h

    private void redireToWhatsapp() {
        String toNumber="+919168752235";
        String url = "https://api.whatsapp.com/send?phone=" + toNumber;
        try {
            PackageManager pm =this.getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            this.startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private class MyWebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if(!mProgressDialog.isShowing() && i==0)
                mProgressDialog.show();
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(mProgressDialog.isShowing())
            {
                mProgressDialog.dismiss();
            }
        }

    }

    @Override
    public void onBackPressed() {


        if(mWebView.canGoBack())
            mWebView.goBack();
        else
            super.onBackPressed();
    }
}