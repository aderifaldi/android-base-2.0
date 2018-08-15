package labs.radya.androidcodebase.view.sample;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import labs.radya.androidcodebase.data.source.remote.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Data
public class ContactList extends BaseModel implements Serializable {

    @SerializedName("data")
    private List<Contact> data;

}
