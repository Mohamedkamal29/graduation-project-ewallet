
package com.graduation.ewallet.Model.Identificationaninfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("national_id")
    @Expose
    private String nationalId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("identity_qr")
    @Expose
    private String identityQr;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("religion")
    @Expose
    private String religion;
    @SerializedName("relationship_type")
    @Expose
    private String relationshipType;
    @SerializedName("relationship_with")
    @Expose
    private String relationshipWith;
    @SerializedName("licence_type")
    @Expose
    private String licenceType;
    @SerializedName("blood_type")
    @Expose
    private String bloodType;

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdentityQr() {
        return identityQr;
    }

    public void setIdentityQr(String identityQr) {
        this.identityQr = identityQr;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getRelationshipWith() {
        return relationshipWith;
    }

    public void setRelationshipWith(String relationshipWith) {
        this.relationshipWith = relationshipWith;
    }

    public String getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(String licenceType) {
        this.licenceType = licenceType;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

}
