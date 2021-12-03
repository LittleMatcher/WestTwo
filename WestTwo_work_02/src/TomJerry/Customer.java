package TomJerry;

import java.time.LocalDate;

public class Customer {
    private String customerName;
    private int ruaCat;
    private LocalDate arriveTime;

    public Customer(String customerName, int ruaCat, LocalDate arriveTime) {
        this.customerName = customerName;
        this.ruaCat = ruaCat;
        this.arriveTime = arriveTime;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRuaCat() {
        return ruaCat;
    }

    public void setRuaCat(int ruaCat) {
        this.ruaCat = ruaCat;
    }

    public LocalDate getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalDate arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Override
    public String toString() {
        return "顾客： " + customerName  +
                " 想rua猫 " + ruaCat +
                "次, 他约了：" + arriveTime ;
    }
}
