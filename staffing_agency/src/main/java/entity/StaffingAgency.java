package entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;


@Entity
public class StaffingAgency extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String nationalIdNumber;
    @Column(nullable = false)
    private String emailContact1;
    @Column(nullable = false)
    private String phoneNumContact1;
    @Column()
    private String emailContact2;
    @Column()
    private String phoneNumContact2;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String complement;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String cityCode;
    @Column(nullable = false)
    private String countryName;
    @Column()
    private String linkToFacebook;
    @Column()
    private String linkToTwitter;
    @Column()
    private String linkToLinkedIn;
    @Column()
    private String imagePath;
    @Column()
    private String description;


    public StaffingAgency() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmailContact1() {
        return emailContact1;
    }

    public void setEmailContact1(String emailContact1) {
        this.emailContact1 = emailContact1;
    }

    public String getPhoneNumContact1() {
        return phoneNumContact1;
    }

    public void setPhoneNumContact1(String phoneNumContact1) {
        this.phoneNumContact1 = phoneNumContact1;
    }

    public String getEmailContact2() {
        return emailContact2;
    }

    public void setEmailContact2(String emailContact2) {
        this.emailContact2 = emailContact2;
    }

    public String getPhoneNumContact2() {
        return phoneNumContact2;
    }

    public void setPhoneNumContact2(String phoneNumContact2) {
        this.phoneNumContact2 = phoneNumContact2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLinkToFacebook() {
        return linkToFacebook;
    }

    public void setLinkToFacebook(String linkToFacebook) {
        this.linkToFacebook = linkToFacebook;
    }

    public String getLinkToTwitter() {
        return linkToTwitter;
    }

    public void setLinkToTwitter(String linkToTwitter) {
        this.linkToTwitter = linkToTwitter;
    }

    public String getLinkToLinkedIn() {
        return linkToLinkedIn;
    }

    public void setLinkToLinkedIn(String linkToLinkedIn) {
        this.linkToLinkedIn = linkToLinkedIn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    public void setNationalIdNumber(String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }
}
