package com.nic.barun.mobilebasedtrackingmonitaringsystemforicdsmbtmsicds.Utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Maycol Meza on 14/11/2017.
 */

public class UserParcelable implements Parcelable {
    private String id;
    private String email;
    private String pass;
    private String name;
    private String image;
    private String pn_nmbr;




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserParcelable() {
    }

    public UserParcelable(String id, String email, String ph_nmbr, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pn_nmbr=ph_nmbr;
    }

    public UserParcelable(String id, String correo, String pn_nmbr, String pass, String name) {
        this.id = id;
        this.email = correo;
        this.pass = pass;
        this.name = name;
        this.pn_nmbr=pn_nmbr;

    }
//create get method id email,name
    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String pn_nmbr)
    {
        this.pn_nmbr=pn_nmbr;
    }
//create getMethod id,name,email,password
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }
    public String getNumber()
    {
        return pn_nmbr;
    }


    protected UserParcelable(Parcel in) {
        id = in.readString();
        email = in.readString();
        pass = in.readString();
        name = in.readString();
        image = in.readString();
        pn_nmbr=in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(pass);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(pn_nmbr);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserParcelable> CREATOR = new Parcelable.Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };
}