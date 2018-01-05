package com.book.cast_block;

import android.media.MediaRouter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private MediaRouter mediaRouter;
    private DemoPresentation demoPresentation;
//    private final DialogInterface.OnDismissListener onDismissListener = new C03521(this);

    private final MediaRouter.SimpleCallback simpleCallback = new C03532(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mediaRouter = (MediaRouter) getSystemService(MEDIA_ROUTER_SERVICE);
        castMethod();
    }

  /*  class C03521 implements DialogInterface.OnDismissListener {
        final *//* synthetic *//* MainActivity f2598a;

        C03521(MainActivity baseActivity) {
            this.f2598a = baseActivity;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (dialogInterface == this.f2598a.demoPresentation) {
                this.f2598a.demoPresentation = null;
            }
        }
    }*/

    class C03532 extends MediaRouter.SimpleCallback {
        final /* synthetic */ MainActivity mainActivity;

        C03532(MainActivity baseActivity) {
            this.mainActivity = baseActivity;
        }

        public void onRouteSelected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRouteSelected: type=" + i + ", info=" + routeInfo);
            this.mainActivity.castMethod();
        }

        public void onRouteUnselected(MediaRouter mediaRouter, int i, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRouteUnselected: type=" + i + ", info=" + routeInfo);
            this.mainActivity.castMethod();
        }

        public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            Log.d("FOO", "onRoutePresentationDisplayChanged: info=" + routeInfo);
            this.mainActivity.castMethod();
        }
    }

    private void castMethod() {
        MediaRouter.RouteInfo selectedRoute = this.mediaRouter.getSelectedRoute(2);
        Display presentationDisplay = selectedRoute != null ? selectedRoute.getPresentationDisplay() : null;
        if (!(this.demoPresentation == null || this.demoPresentation.getDisplay() == presentationDisplay)) {
            this.demoPresentation.dismiss();
            this.demoPresentation = null;
        }
        if (this.demoPresentation == null && presentationDisplay != null) {
            this.demoPresentation = new DemoPresentation(this, presentationDisplay);
//            this.demoPresentation.setOnDismissListener(this.onDismissListener);
            try {
                this.demoPresentation.show();
            } catch (WindowManager.InvalidDisplayException e) {
                this.demoPresentation = null;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mediaRouter.addCallback(2, this.simpleCallback);
        castMethod();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mediaRouter.removeCallback(this.simpleCallback);
    }
}
