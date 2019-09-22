package xyz.bugcoder.bean;

public class Property {
    private Integer id;

    private Integer cid;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}