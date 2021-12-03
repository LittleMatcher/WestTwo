package TomJerry;

public interface CatCafe {
    public void buyCat(Cat cat) throws InsufficientBalanceException;
    public void treatCustomer(Customer customer) throws CatNotFoundException;
    public void closeDoor() throws InterruptedException;
}
