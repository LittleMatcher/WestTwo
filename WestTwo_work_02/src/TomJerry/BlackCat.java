package TomJerry;

public class BlackCat extends Cat {

    public BlackCat(String catName, int catAge, boolean sex) {
        super(catName, catAge, sex);
        this.catPrice=350;
    }
    public BlackCat(String catName, int catAge, boolean sex,double catPrice) {
        super(catName, catAge, sex);
        this.catPrice=catPrice;
    }

    @Override
    public void setCatPrice(double catPrice) {
        super.setCatPrice(catPrice);
        this.catPrice= 350;
    }
}
