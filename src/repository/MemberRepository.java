package repository;

import model.Member;

import java.util.ArrayList;
import java.util.List;
public class MemberRepository {

    private final List<Member> members =new ArrayList<>();

    public void addMember(Member member){
        members.add(member);
    }

    public List<Member> findAll(){
        return new ArrayList<>(members);

    }

    public Member findMemberById(String memberId){
        for(Member m :members){
            if(m.getId().equals(memberId)){
                return m;
            }
        }
        return null;
    }
}
