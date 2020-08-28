package cn.myh.bookstore.category.domain;

/**
 * description: Category <br>
 * date: 2020/8/3 21:37 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class Category {
    private String cid;
    private String cname;

    @Override
    public String toString() {
        return "Category{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
