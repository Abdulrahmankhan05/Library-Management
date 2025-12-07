package model;

public class Member {
    private String id;
    private String name;
    private String membership;

    public Member(String id,String name, String membership){
        this.id= id;
        this.name=name;
        this.membership=membership;
    }

    public String getId() {
        return id;
    }

    public String getMembership() {
        return membership;
    }

    public String getName() {
        return name;
    }
}
 