//public class Test_15_06 {
//}
////
////public class LegacyPointsCalculator {
////
//
//    public int calculateLoyaltyPoints(String customerType, double purchaseAmount) {
//        if (customerType == null || customerType.trim().isEmpty()) {
//            // Default to the lowest tier if type is missing
//            return (int) (purchaseAmount * 0.01);
//        }
//
//        String type = customerType.toUpperCase();
//
//        if ("GOLD".equals(type)) {
//            // Gold tier logic: 10%
//            return (int) (purchaseAmount * 0.10);
//        } else if ("SILVER".equals(type)) {
//            // Silver tier logic: 5%
//            return (int) (purchaseAmount * 0.05);
//        } else {
//            // Bronze/Default logic: 1%
//            return (int) (purchaseAmount * 0.01);
//        }
//    }
//}
//
//// valid scenarios:
//// expecting, gold, silver and default
//
//// new user scenario
//
//
////public class calculateLoyaltyPointsTest{
////
////    @InjectMock
////    private LegacyPointsCalculator legacyPointsCalculator;
////}
////@Test
////public void testCalculateLoyalityPointForNewUser()
////{
////    String customerType = null;
////    int purchaseAmount = 100;
////    int expectedLoyalityPoint = 1;
////    int actualLoaylityPoint = legacyPointsCalculator.calculateLoyaltyPoints(customerType,purchaseAmount);
////    AssertEquals(expectedLoyalityPoint,actualLoaylityPoint);
////}
//
//// expection rest api:
////
////LegacyPointsCalculator
//
