package hello.login.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {

        // store의 Map values (Member)가 반환이 된다.
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {


        return findAll().stream()
                // filter = 만족하는 조건만 findAny
                .filter(m -> m.getLoginId().equals(loginId))
                .findAny();

/*
        List<Member> all = findAll();

        List<String> collect = all.stream().map(a -> a.getLoginId())
                .collect(Collectors.toList());
        return collect;

        for(Member m : all){
            if(m.getLoginId() == loginId){
                return Optional.of(m);
            }
        }
        // Optional 객체를 사용해 null 반환 대신에 empty로 찾을수 있다.
        return Optional.empty();
        */

    }

    public void ClearStore() {
        store.clear();
    }
}
