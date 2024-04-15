package br.com.danilooa.springboot.mongodb.tutorial.repository;

public class BrandCount {
    private String brandName;
    private Long count;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BrandCount{" +
                "brandName='" + brandName + '\'' +
                ", count=" + count +
                '}';
    }
}
