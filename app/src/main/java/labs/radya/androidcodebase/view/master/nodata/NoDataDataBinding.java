package labs.radya.androidcodebase.view.master.nodata;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class NoDataDataBinding extends BaseObservable {

    private int icon;
    private String message;

    public NoDataDataBinding(int icon, String message) {
        this.icon = icon;
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public String getMessage() {
        return message;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, int icon) {
        Glide.with(imageView.getContext()).load(icon).into(imageView);
    }

}
