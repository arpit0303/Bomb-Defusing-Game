package perk.appsaholic.bombdefusing;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Arpit on 26/05/15.
 */
public class FontableButton extends Button {
    public FontableButton(Context context) {
        super(context);
        init();
    }

    public FontableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/LobsterTwo-Regular.otf");
        setTypeface(tf, 1);
    }
}
