package cn.haitaoss.tinyspringmvc.webappProject.controller;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 14:47
 *
 */
public class Baby {
    private String babyName;
    private int babyAge;
    private float weight;

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName;
    }

    public int getBabyAge() {
        return babyAge;
    }

    public void setBabyAge(int babyAge) {
        this.babyAge = babyAge;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "babyName='" + babyName + '\'' +
                ", babyAge=" + babyAge +
                ", weight=" + weight +
                '}';
    }
}
