package com.example.edgelibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.view.ActionProvider;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;


import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private static final String ImageURLbase = "https://covers.openlibrary.org/b/id/";
    String mImageURL;
    private ShareActionProvider shareP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(R.layout.activity_detail);
//        getWindow().setFeatureInt(Window.FEATURE_CONTEXT_MENU,R.menu.main);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        imageView = (ImageView) findViewById(R.id.bookimg);

        String coverID = this.getIntent().getExtras().getString("coverID");
        if(coverID.length()>0){
            mImageURL = ImageURLbase+coverID+"-L.jpg";
            Picasso.with(DetailActivity.this).load(mImageURL).
                    placeholder(R.mipmap.ic_launcher).into(imageView);
        }
    }
    private void setShareP(){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,
                R.string.sharesays);
        shareIntent.putExtra(Intent.EXTRA_TEXT, mImageURL);
        shareP.setShareIntent(shareIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        if(shareItem!=null){
            shareP = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_share:
                setShareP();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}





