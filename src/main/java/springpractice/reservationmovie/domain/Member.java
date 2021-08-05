package springpractice.reservationmovie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private int age;

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    //== 생성 매서드 ==//
    protected Member() {
    }

    public static Member create(String name, int age) {
        Member member = new Member();
        member.name = name;
        member.age = age;
        return member;
    }
}
