package TomJerry;

import java.util.ArrayList;
import java.util.Random;

public class MyCatCafe implements CatCafe {
    private double money;
    private ArrayList<Cat> myCats;
    public ArrayList<Customer> customers;
    private MyCatCafe myCatCafe;

    public MyCatCafe(double money, ArrayList<Cat> myCats,ArrayList<Customer> customers) {
        this.money = money;
        this.myCats = myCats;
        this.customers=customers;
        System.out.println("我们开业了");
    }

    public double getMoney() {
        return money;
    }
    public void addCat(Cat cat)
    {
        myCats.add(cat);
    }


    @Override
    public void buyCat(Cat cat) throws InsufficientBalanceException {
        System.out.println("");
        System.out.println("想买猫");
        System.out.println("这是一只"+cat.getCatAge()+"岁的小"+(cat.sex?"公":"母")+"猫");
        System.out.println("挑的真好");
        System.out.println("哟，这只猫价格挺合适，只要"+cat.getCatPrice()+"块");
        if(this.money>= cat.getCatPrice())
        {
            myCats.add(cat);
            System.out.println("恭喜猫咖新增员工一位");
            this.money-=cat.getCatPrice();
        }else
        {
            throw new InsufficientBalanceException();
        }
        System.out.println("店里还剩"+money);
    }

    @Override
    public void treatCustomer(Customer customer) throws CatNotFoundException {
        customers.add(customer);
        Random random=new Random();
        if(myCats.size()==0)
        {
            throw new CatNotFoundException();
        }else {
            System.out.println(customer.getCustomerName());
            for(int i=0;i<customer.getRuaCat();i++)
            {

                int a=random.nextInt(myCats.size());
                System.out.println("Rua到了第"+(a+1)+"只小猫");
                System.out.println(myCats.get(a).toString());
                money+=15;
            }
            System.out.println("店里还剩"+money);
        }

    }

    @Override
    public void closeDoor() throws InterruptedException {
        for(Customer customer:customers)
        {
            System.out.println(customer.toString());
        }
    }
}
