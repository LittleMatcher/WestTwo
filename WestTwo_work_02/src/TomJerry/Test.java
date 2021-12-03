package TomJerry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Cat> cats=new ArrayList<Cat>();
        ArrayList<Customer> customers =new ArrayList<Customer>();

        //已有猫猫
        Cat cat_01=new BlackCat("小黑",3,true);
        Cat cat_02=new OrangeCat("小橘",2,true);
        Cat cat_03=new OrangeCat("小王八",6,false);
        //开一家猫咖
        MyCatCafe xx=new MyCatCafe(280,cats,customers);
        xx.addCat(cat_01);
        xx.addCat(cat_02);
        xx.addCat(cat_03);

        //准备买的猫猫
        Cat cat_04=new OrangeCat("小王八蛋",6,false,290);

        //当前时间
        LocalDate date = LocalDate.now();

        //未来时间
        String strData1 = "2022-01-11";
        String strData2 = "2023-12-11";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date1 = LocalDate.parse(strData1, formatter);
        LocalDate date2 = LocalDate.parse(strData2, formatter);


        Customer []customer=new Customer[5];
        customer[0]=new Customer("小王",3,date);
        customer[1]=new Customer("小张",6,date2);
        //rua猫
        int index=0;
        while(customer[index]!=null)
        {
            if(customer[index].getArriveTime().isAfter(date1))
            {
                System.out.println(customer[index].getCustomerName()+"预约失败，关门大吉了这天已经");
                index++;
                continue;
            }
            try {
                xx.treatCustomer(customer[index]);
            }catch (CatNotFoundException e)
            {
                e.information();
            }
            index++;
        }
        xx.closeDoor();



        //买猫
        try {
            xx.buyCat(cat_04);
        } catch (InsufficientBalanceException e) {
            e.noMoney();
        }


    }
}
