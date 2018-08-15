package labs.radya.androidcodebase.data.source.remote;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Hizkia on 16/01/2017.
 */
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    @SerializedName("data")
    private T data;
}
