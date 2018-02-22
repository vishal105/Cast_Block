package com.book.cast_block;

import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.media.MediaRouter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private MediaRouter mediaRouter;
    private DemoPresentation demoPresentation;
    private DisplayManager mDisplayManager;
    // List of all currently visible presentations indexed by display id.
    private final SparseArray<DemoPresentation> mActivePresentations =
            new SparseArray<DemoPresentation>();


//    private final DialogInterface.OnDismissListener onDismissListener = new C03521(this);

    private final MediaRouter.SimpleCallback simpleCallback = new C03532(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hdmi = (TextView) findViewById(R.id.hdmi);


        hdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PresentationActivity.class));
            }
        });


        this.mediaRouter = (MediaRouter) getSystemService(MEDIA_ROUTER_SERVICE);
        castMethod();
    }

    private String TAG = MainActivity.class.getCanonicalName();
    private DisplayListAdapter mDisplayListAdapter;
    private final DisplayManager.DisplayListener mDisplayListener =
            new DisplayManager.DisplayListener() {
                @Override
                public void onDisplayAdded(int displayId) {
                    Log.d(TAG, "Display #" + displayId + " added.");
                    mDisplayListAdapter.updateContents();
                }

                @Override
                public void onDisplayChanged(int displayId) {
                    Log.d(TAG, "Display #" + displayId + " changed.");
                    mDisplayListAdapter.updateContents();
                }

                @Override
                public void onDisplayRemoved(int displayId) {
                    Log.d(TAG, "Display #" + displayId + " removed.");
                    mDisplayListAdapter.updateContents();
                }
            };

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

      /*  // Update our list of displays on resume.
        mDisplayListAdapter.updateContents();

        // Restore presentations from before the activity was paused.
        final int numDisplays = mDisplayListAdapter.getCount();
        for (int i = 0; i < numDisplays; i++) {
            final Display display = mDisplayListAdapter.getItem(i);
            final DemoPresentationContents contents =
                    mSavedPresentationContents.get(display.getDisplayId());
            if (contents != null) {
                showPresentation(display, contents);
            }
        }
        mSavedPresentationContents.clear();*/

        // Register to receive events from the display manager.
//        mDisplayManager.registerDisplayListener(mDisplayListener, null);
        /**
         * Shows a {@link Presentation} on the specified display.
         */
   /* private void showPresentation(Display display, DemoPresentationContents contents) {
        final int displayId = display.getDisplayId();
        if (mActivePresentations.get(displayId) != null) {
            return;
        }

        Log.d(TAG, "Showing presentation photo #" + contents.photo
                + " on display #" + displayId + ".");

        DemoPresentation presentation = new DemoPresentation(this, display, contents);
        presentation.show();
        presentation.setOnDismissListener(mOnDismissListener);
        mActivePresentations.put(displayId, presentation);*/
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.mediaRouter.removeCallback(this.simpleCallback);


      /*  // Unregister from the display manager.
        mDisplayManager.unregisterDisplayListener(mDisplayListener);

        // Dismiss all of our presentations but remember their contents.
        Log.d(TAG, "Activity is being paused.  Dismissing all active presentation.");
        for (int i = 0; i < mActivePresentations.size(); i++) {
            DemoPresentation presentation = mActivePresentations.valueAt(i);
            int displayId = mActivePresentations.keyAt(i);
            mSavedPresentationContents.put(displayId, presentation.mContents);
            presentation.dismiss();
        }
        mActivePresentations.clear();*/
    }


    private final class DisplayListAdapter extends ArrayAdapter<Display> {
        final Context mContext;
        private CompoundButton.OnCheckedChangeListener mCheckedRemoteDisplay = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                synchronized (mCheckedRemoteDisplay) {
                    final Display display = (Display) view.getTag();
                    if (isChecked) {
//                        showPresentation(display);
                    } else {
//                        hidePresentation(display);
                    }
                }
            }
        };

        /**
         * Update the contents of the display list adapter to show
         * information about all current displays.
         */
        public void updateContents() {
            clear();
            Display[] displays = mDisplayManager.getDisplays();
            addAll(displays);
            Log.d(TAG, "There are currently " + displays.length + " displays connected.");
            for (Display display : displays) {
                Log.d(TAG, "  " + display);
            }
        }

        public DisplayListAdapter(Context context) {
            super(context, R.layout.list_item);
            mContext = context;
        }
    }


    /**
     * The presentation to show on the secondary display.
     * <p>
     * Note that this display may have different metrics from the display on which
     * the main activity is showing so we must be careful to use the presentation's
     * own {@link Context} whenever we load resources.
     */
    private final class DemoPresentationHDMI extends Presentation {
        // Specifies the content that we want to show in this presentation.
        private final int mChannelNumber;

        public DemoPresentationHDMI(Context context, Display display, int channelNumber) {
            super(context, display);
            mChannelNumber = channelNumber;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // Be sure to call the super class.
            super.onCreate(savedInstanceState);
            // Get the resources for the context of the presentation.
            // Notice that we are getting the resources from the context of the presentation.
//            Resources r = getContext().getResources();
            // Inflate the layout.
            setContentView(R.layout.cake);
            // Show a text message to describe what's going on.
//            TextView text = (TextView)findViewById(R.id.text);
//            text.setText(r.getString(R.string.presentation_channel_text, mChannelNumber + 1,
//                    getDisplay().getDisplayId()));
            // Show a n image for visual interest.
//            ImageView image = (ImageView)findViewById(R.id.image);
//            image.setImageDrawable(r.getDrawable(CHANNELS[mChannelNumber]));
        }
    }
}