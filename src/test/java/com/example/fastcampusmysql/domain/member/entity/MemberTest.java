package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    void changeNickname() {
        var member = MemberFixtureFactory.create();
        var expected = "Jerry";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원은 닉네임은 10자를 초과할 수 없다.")
    @Test
    void nicknameMaxLength() {
        var member = MemberFixtureFactory.create();
        var overMaxLength = "JerryJerry1";

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLength));
    }
}