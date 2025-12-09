package model;

public class Member {
    private String id;
    private String name;
    private Membership membership;

    public Member(String id,String name, Membership membership){
        this.id= id;
        this.name=name;
        this.membership=membership;
    }

    public String getId() {
        return id;
    }

    public Membership getMembership() {
        return membership;
    }

    public String getName() {
        return name;
    }
}
 