package jar;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 16:07
 *
 */
public class Car implements Driveable {
    private String name;
    private int price;

    public Car() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void running() {
        System.out.println("car is running");
    }
}