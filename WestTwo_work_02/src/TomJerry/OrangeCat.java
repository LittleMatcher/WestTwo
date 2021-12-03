package TomJerry;

public class OrangeCat extends Cat {

    public OrangeCat(String catName, int catAge, boolean sex) {
        super(catName, catAge, sex);
        this.catPrice=200;
    }
    public OrangeCat(String catName, int catAge, boolean sex,double catPrice) {
        super(catName, catAge, sex);
        this.catPrice=catPrice;
    }
    public boolean isFat;

    @Override
    public void setCatPrice(double catPrice) {
        super.setCatPrice(catPrice);
        this.catPrice=200;
    }
}
