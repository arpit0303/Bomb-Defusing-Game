package perk.appsaholic.bombdefusing;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Arpit on 26/05/15.
 */
public class FontableTextView extends TextView {
    public FontableTextView(Context context) {
        super(context);
        init();
    }

    public FontableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/LobsterTwo-Regular.otf");
        setTypeface(tf, 1);
    }
}
