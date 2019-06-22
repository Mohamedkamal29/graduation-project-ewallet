
package com.graduation.ewallet.Model.Identificationaninfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("national_id")
    @Expose
    private Object nationalId;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("identity_qr")
    @Expose
    private Object identityQr;
    @SerializedName("birth_date")
    @Expose
    private Object birthDate;
    @SerializedName("job")
    @Expose
    private Object job;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("religion")
    @Expose
    private Object religion;
    @SerializedName("relationship_type")
    @Expose
    private Object relationshipType;
    @SerializedName("relationship_with")
    @Expose
    private Object relationshipWith;
    @SerializedName("licence_type")
    @Expose
    private Object licenceType;
    @SerializedName("blood_type")
    @Expose
    private Object bloodType;

    public Object getNationalId() {
        return nationalId;
    }

    public void setNationalId(Object nationalId) {
        this.nationalId = nationalId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getIdentityQr() {
        return identityQr;
    }

    public void setIdentityQr(Object identityQr) {
        this.identityQr = identityQr;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getJob() {
        return job;
    }

    public void setJob(Object job) {
        this.job = job;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getReligion() {
        return religion;
    }

    public void setReligion(Object religion) {
        this.religion = religion;
    }

    public Object getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(Object relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Object getRelationshipWith() {
        return relationshipWith;
    }

    public void setRelationshipWith(Object relationshipWith) {
        this.relationshipWith = relationshipWith;
    }

    public Object getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(Object licenceType) {
        this.licenceType = licenceType;
    }

    public Object getBloodType() {
        return bloodType;
    }

    public void setBloodType(Object bloodType) {
        this.bloodType = bloodType;
    }

}
