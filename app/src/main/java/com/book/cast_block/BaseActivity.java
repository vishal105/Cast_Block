/*
package com.book.cast_block;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.media.MediaRouter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

*/
/**
 * Created by VISHAL on 1/5/2018.
 *//*


public class BaseActivity extends AppCompatActivity {
    private MediaRouter f2554d;
    private DemoPresentation f2557g;
    private final OnDismissListener f2558h = new C03521(this);

//    private final MediaRouter.SimpleCallback f2562m = new C03532(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.f2554d = (MediaRouter) getSystemService("media_router");

//        this.f2554d.addCallback(2, this.f2562m);
    }


    class C03521 implements OnDismissListener {
        final */
/* synthetic *//*
 BaseActivity f2598a;

        C03521(BaseActivity baseActivity) {
            this.f2598a = baseActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (dialogInterface == this.f2598a.f2557g) {
                this.f2598a.f2557g = null;
            }
        }
    }

   */
/* class C03532 extends MediaRouter.SimpleCallback {
        final *//*
*/
/* synthetic *//*
*/
/* BaseActivity mainActivity;

        C03532(BaseActivity baseActivity) {
            this.mainActivity = baseActivity;
        }

        public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRouteSelected: type=" + i + ", info=" + routeInfo);
            this.mainActivity.mo1529b();
        }

        public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRouteUnselected: type=" + i + ", info=" + routeInfo);
            this.mainActivity.mo1529b();
        }

        public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRoutePresentationDisplayChanged: info=" + routeInfo);
            this.mainActivity.mo1529b();
        }
    }*//*


  */
/*  private void mo1529b() {
        MediaRouter.RouteInfo selectedRoute = this.f2554d.getSelectedRoute(2);
        Display presentationDisplay = selectedRoute != null ? selectedRoute.getPresentationDisplay() : null;
        if (!(this.f2557g == null || this.f2557g.getDisplay() == presentationDisplay)) {
            this.f2557g.dismiss();
            this.f2557g = null;
        }
        if (this.f2557g == null && presentationDisplay != null) {
            this.f2557g = new DemoPresentation(this, presentationDisplay);
            this.f2557g.setOnDismissListener(this.f2558h);
            try {
                this.f2557g.show();
            } catch (WindowManager.InvalidDisplayException e) {
                this.f2557g = null;
            }
        }
    }
*//*

    @Override
    protected void onStart() {
        super.onStart();
//        this.f2554d.addCallback(2, this.f2562m);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        this.f2554d.removeCallback(this.f2562m);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mo1529b();
//        this.f2554d.addCallback(2, this.f2562m);
    }
}
*/
