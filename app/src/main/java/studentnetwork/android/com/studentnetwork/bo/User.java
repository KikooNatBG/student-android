package studentnetwork.android.com.studentnetwork.bo;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String aboutMe;
    private Date registerDate;
    private Date birthDate;
    private String pictureUrl;
    private String smallPictureUrl;
    private String largePictureUrl;
    private String userType;
    private String gender;
    private List<UserMedia> userMediaList;
    private List<UserLink> userLinkList;
    private List<School> schoolList;
    private List<Content> contentList;

    public User() {
    }

    public User(int id, String firstName, String lastName, String email, String password, String aboutMe, Date registerDate, Date birthDate, String pictureUrl, String smallPictureUrl, String largePictureUrl, String userType, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.aboutMe = aboutMe;
        this.registerDate = registerDate;
        this.birthDate = birthDate;
        this.pictureUrl = pictureUrl;
        this.smallPictureUrl = smallPictureUrl;
        this.largePictureUrl = largePictureUrl;
        this.userType = userType;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public String getLargePictureUrl() {
        return largePictureUrl;
    }

    public void setLargePictureUrl(String largePictureUrl) {
        this.largePictureUrl = largePictureUrl;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<UserMedia> getUserMediaList() {
        return userMediaList;
    }

    public void setUserMediaList(List<UserMedia> userMediaList) {
        this.userMediaList = userMediaList;
    }

    public List<UserLink> getUserLinkList() {
        return userLinkList;
    }

    public void setUserLinkList(List<UserLink> userLinkList) {
        this.userLinkList = userLinkList;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", registerDate=" + registerDate +
                ", birthDate=" + birthDate +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", smallPictureUrl='" + smallPictureUrl + '\'' +
                ", largePictureUrl='" + largePictureUrl + '\'' +
                ", type='" + userType + '\'' +
                ", gender='" + gender + '\'' +
                ", userMediaList=" + userMediaList +
                ", userLinkList=" + userLinkList +
                ", schoolList=" + schoolList +
                ", contentList=" + contentList +
                '}';
    }
}
