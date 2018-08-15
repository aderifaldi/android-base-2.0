package labs.radya.androidcodebase.data.source.remote;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by RadyaLabs PC on 28/09/2017.
 */
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class BaseModel implements Serializable {

    @SerializedName("error")
    private boolean error;
    @SerializedName("alert")
    private Alerts alert;

    public BaseModel(boolean error, Alerts alert) {
        this.error = error;
        this.alert = alert;
    }

    @NoArgsConstructor
    @Data
    public static class Alerts implements Serializable {
        @SerializedName("code")
        private int code;
        @SerializedName("message")
        private String message;

        public Alerts(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

}
