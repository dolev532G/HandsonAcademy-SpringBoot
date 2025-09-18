package com.handson.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.handson.basic.util.Dates;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import org.joda.time.LocalDate;

import static com.handson.basic.model.Student.StudentBuilder.aStudent;

public class StudentIn implements Serializable {

    @Length(max = 60)
    private String fullname;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Min(100)
    @Max(800)
    private Integer satScore;

    @Min(30)
    @Max(110)
    private Double graduationScore;

    @Length(max = 20)
    private String phone;


    public Student toStudent() {
        return aStudent().createdAt(Dates.nowUTC()).birthDate(Dates.atUtc(birthDate)).fullname(fullname)
                .satScore(satScore).graduationScore(graduationScore)
                .phone(phone)
                .build();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public @Length(max = 60) String getFullname() {
        return fullname;
    }

    public void setFullname(@Length(max = 60) String fullname) {
        this.fullname = fullname;
    }

    public @Min(100) @Max(800) Integer getSatScore() {
        return satScore;
    }

    public void setSatScore(@Min(100) @Max(800) Integer satScore) {
        this.satScore = satScore;
    }

    public @Min(30) @Max(110) Double getGraduationScore() {
        return graduationScore;
    }

    public void setGraduationScore(@Min(30) @Max(110) Double graduationScore) {
        this.graduationScore = graduationScore;
    }

    public @Length(max = 20) String getPhone() {
        return phone;
    }

    public void setPhone(@Length(max = 20) String phone) {
        this.phone = phone;
    }

    public void updateStudent(Student student) {
        student.setBirthDate(Dates.atUtc(birthDate));
        student.setFullname(fullname);
        student.setSatScore(satScore);
        student.setGraduationScore(graduationScore);
        student.setPhone(phone);
    }

}