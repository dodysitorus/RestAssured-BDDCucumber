package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourse(){
        JsonPath js = new JsonPath(payload.Courseprice());
        int coursesCount = js.getInt("courses.size()");

        int sum = 0;
        for (int i = 0; i < coursesCount; i++) {
            int price = js.getInt("courses.price["+i+"]");
            int copies = js.getInt("courses.copies["+i+"]");
            int amount = price * copies;
//            System.out.println(amount);
            sum += amount;
        }
        System.out.println(sum);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
//        System.out.println(purchaseAmount);
        Assert.assertEquals(sum, purchaseAmount);
    }
}
