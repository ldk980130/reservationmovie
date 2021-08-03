package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Member {

    @Id
    @Column(name = "member_id")
    private String id;
    private String password;

    private String number;

    private String email;

    private int age;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    public static Member create(String id, String password,
                                String number, String email, int age) {
        Member member = new Member();
        member.id = id;
        member.password = password;
        member.number = number;
        member.email = email;
        member.age = age;

        if(age>19) member.status = Status.ADULT;
        else member.status = Status.MINOR;

        return member;
    }
}
