package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

// Make this parcelable for our intent
public final class NoteInfo implements Parcelable {
    // Need to get this data type parcelable
    private CourseInfo mCourse;

    // Primitvies parcelable by default
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel parcel) {
        mTitle = parcel.readString();
        mText = parcel.readString();
        mCourse = parcel.readParcelable(CourseInfo.class.getClassLoader());
    }

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mCourse.getCourseId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        // Only required when we have special parcleing needs which we don't so '0' is fine
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // Write out what goes into the parcel
        dest.writeString(mTitle);
        dest.writeString(mText);

        // Need a creator for this
        dest.writeParcelable(mCourse, 0);
    }

    public static final Parcelable.Creator<NoteInfo> CREATOR = new Parcelable.Creator<NoteInfo>() {
        @Override
        public NoteInfo createFromParcel(Parcel source) {
            // You must set values in the same order to which you wrote them in "writeToParcel"
            // Use a private instructor
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
}
