package labs.radya.androidcodebase.view.sample;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class Contact implements Serializable {

    @SerializedName("customer_code")
    private String customer_code;
    @SerializedName("contact_name")
    private String contact_name;
    @SerializedName("contact_number")
    private String contact_number;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("contact_id")
    private String contact_id;
    @SerializedName("position")
    private String position;
    @SerializedName("email")
    private String email;
    @SerializedName("secondary_email")
    private String secondary_email;
    @SerializedName("photo")
    private String photo;

}
