import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.Courseprice());

//        print total of course
        int countCourse = js.getInt("courses.size()");
        System.out.println(countCourse);

//        print purchaseAmount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

//        print the first of course's title
        String firstTitle = js.getString("courses.title[0]");
        System.out.println(firstTitle);

        System.out.println("----++++----");

//        print all course titles and their respective prices
        for (int i = 0; i < countCourse; i++) {
            String courses = js.getString("courses.title["+i+"]");
            System.out.println(courses);
            String respectivePrices = js.getString("courses.price["+i+"]");
//            System.out.println(js.getString("courses.price["+i+"]").toString());
            System.out.println(respectivePrices);
        }

//        print no of copies sold by RPA Course
        System.out.println("----++++----");
        for (int i = 0; i < countCourse ; i++) {
            String courses = js.getString("courses.title["+i+"]");
            if (courses.equalsIgnoreCase("RPA")){
                int copies = js.getInt("courses.copies["+i+"]");
                System.out.println(copies);
                break;
            }
        }

//        print sum of price with copies must matches with purchaseAmount

        System.out.println("----++++----");
        for (int i = 0; i < countCourse ; i++) {
            int price = js.getInt("courses.price["+i+"]");
            int copies = js.getInt("courses.copies["+i+"]");
            System.out.println(price + copies);
        }
    }
}
