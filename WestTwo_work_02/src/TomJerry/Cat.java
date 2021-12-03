package TomJerry;

public  abstract class Cat {


    protected String catName;
    protected int catAge;
    protected boolean sex;
    protected double catPrice;
    public Cat(String catName, int catAge, boolean sex, double catPrice) {
        this.catName = catName;
        this.catAge = catAge;
        this.sex = sex;
        this.catPrice = catPrice;
    }

    public Cat(String catName, int catAge, boolean sex) {
        this.catName = catName;
        this.catAge = catAge;
        this.sex = sex;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getCatAge() {
        return catAge;
    }

    public void setCatAge(int catAge) {
        this.catAge = catAge;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getCatPrice() {
        return catPrice;
    }

    public void setCatPrice(double catPrice) {
        this.catPrice = catPrice;
    }

    @Override
    public String toString() {
        return "Cat" +
                "一只叫'" + catName + '\'' +
                ", 年龄" + catAge +
                "," + (sex?"带把的":"不带把的")+"猫" ;
    }
}
