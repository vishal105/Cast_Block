package com.book.cast_block;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;

class DemoPresentation extends Presentation {
    public DemoPresentation(Context context, Display display) {
        super(context, display);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContext().getResources();
        setContentView(R.layout.cake);
    }
}
