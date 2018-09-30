package vostore.apptualidade;

import android.content.Context;
import android.graphics.Typeface;

public class FontChanger {
    public static Typeface setRobotoRegular(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),"PANTON-BLACKAPS.ttf");
    }

}
