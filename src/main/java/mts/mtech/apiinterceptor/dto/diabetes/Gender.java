package mts.mtech.apiinterceptor.dto.diabetes;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    Male("Male"),
    Female("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    @JsonValue
    public String getGender() {
        return gender;
    }

    public static Gender fromValue(String gender) {
        for (Gender genderValue : Gender.values()) {
            if (genderValue.gender.equals(gender)) {
                return genderValue;
            }
        }
        throw new IllegalArgumentException("Invalid gender: " + gender);
    }

}
