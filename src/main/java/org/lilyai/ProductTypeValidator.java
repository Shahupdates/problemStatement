import java.util.HashMap;
import java.util.Map;

public class ProductTypeValidator {
    private static final Map<Integer, String> productTypes = new HashMap<>();

    static {
        // Add product type mappings from the database
        productTypes.put(9, "Gifting Accessories");
        productTypes.put(27, "Unknown Product Type");
        productTypes.put(29, "Sets");
        productTypes.put(56, "Equipment Gear");
        productTypes.put(65, "Unprocessed");
        productTypes.put(67, "Activewear Sets");
        productTypes.put(126, "Unsupported");
        productTypes.put(17, "Hats");
        productTypes.put(18, "Scarves");
        productTypes.put(35, "Watches");
        productTypes.put(36, "Sunglasses");
        productTypes.put(42, "Wallets");
        productTypes.put(45, "Ties");
        productTypes.put(46, "Cuff Links");
        productTypes.put(47, "Belts");
        productTypes.put(48, "Hair Accessories");
        productTypes.put(49, "Gloves");
        productTypes.put(50, "Umbrellas");
        productTypes.put(64, "Key Chain");
        productTypes.put(68, "Masks");
        productTypes.put(0, "Dresses");
        productTypes.put(1, "Tops");
        productTypes.put(2, "Pants");
        productTypes.put(3, "Outerwear");
        productTypes.put(4, "Jeans");
        productTypes.put(5, "Shorts");
        productTypes.put(6, "Skirts");
        productTypes.put(7, "Activewear Tops");
        productTypes.put(19, "Activewear Outerwear");
        productTypes.put(20, "Activewear Pants");
        productTypes.put(21, "Activewear Dresses");
        productTypes.put(22, "Activewear Shorts");
        productTypes.put(23, "Activewear Skirts");
        productTypes.put(28, "Rompers");
        productTypes.put(33, "Robes");
        productTypes.put(55, "Sweaters");
        productTypes.put(12, "Bags");
        productTypes.put(127, "Luggage And Accessories");
        productTypes.put(30, "Underwear");
        productTypes.put(31, "Bras");
        productTypes.put(32, "Slips");
        productTypes.put(43, "Shapewear");
        productTypes.put(53, "Lingerie");
        productTypes.put(13, "Necklaces");
        productTypes.put(14, "Earrings");
        productTypes.put(15, "Rings");
        productTypes.put(16, "Bracelets");
        productTypes.put(69, "Charms");
        productTypes.put(70, "Jewelry Box");
        productTypes.put(71, "Brooches Pins");
        productTypes.put(34, "Maternity Belts");
        productTypes.put(44, "Maternity Belly Bands");
        productTypes.put(11, "Shoes");
        productTypes.put(54, "Socks And Hosiery");
        productTypes.put(51, "Swimwear Tops");
        productTypes.put(52, "Swimwear Bottoms");
        productTypes.put(37, "Cell Phone Grips");
        productTypes.put(38, "Cell Phone Cases");
        productTypes.put(39, "Cables Chargers");
        productTypes.put(40, "Smartwatch Bands");
        productTypes.put(41, "Headphones");
        productTypes.put(72, "Tech Accessories");
        productTypes.put(86, "Skincare");
        productTypes.put(87, "Fragrance");
        productTypes.put(88, "Lips");
        productTypes.put(89, "Eyes");
        productTypes.put(90, "Face");
        productTypes.put(91, "Nails");
        productTypes.put(92, "Brushes Tools");
        productTypes.put(93, "Hair Care");
        productTypes.put(94, "Hair Tools");
        productTypes.put(95, "Bath And Body");
        productTypes.put(96, "Spa Tools");
        productTypes.put(97, "Home Fragrance");
        productTypes.put(125, "Shaving And Beard");
        productTypes.put(133, "Beauty Supplements");
        productTypes.put(134, "Hair Removal And Grooming");
        productTypes.put(73, "Pillows");
        productTypes.put(74, "Sheets");
        productTypes.put(75, "Rugs");
        productTypes.put(76, "Dinnerware");
        productTypes.put(77, "Drinkware");
        productTypes.put(78, "Serveware");
        productTypes.put(79, "Flatware");
        productTypes.put(80, "Bar And Wine Accessories");
        productTypes.put(81, "Table Linens");
        productTypes.put(82, "Bedding Essentials");
        productTypes.put(83, "Lighting");
        productTypes.put(84, "Beds And Bedframes");
        productTypes.put(85, "Mattresses");
        productTypes.put(98, "Desks And Tables");
        productTypes.put(99, "Chairs And Stools");
        productTypes.put(100, "Decorative Accents");
        productTypes.put(101, "Storage And Dresser");
        productTypes.put(102, "Sectional And Sofa");
        productTypes.put(103, "Towels And Mats");
        productTypes.put(104, "Curtains And Window Treatments");
        productTypes.put(105, "Curtain Hardware Accessories");
        productTypes.put(106, "Bath Decor Accessories");
        productTypes.put(107, "Mirrors And Wall Arts");
        productTypes.put(108, "Room Dividers");
        productTypes.put(109, "Jewelry Organizers");
        productTypes.put(110, "Office And Desk Accessories");
        productTypes.put(111, "Vases Bowls Trays");
        productTypes.put(112, "Plants And Floral");
        productTypes.put(113, "Holiday Ornaments And Decor");
        productTypes.put(114, "Picture Frames");
        productTypes.put(115, "Garden And Planters");
        productTypes.put(116, "Outdoor Patio Accessories");
        productTypes.put(117, "Bakeware");
        productTypes.put(118, "Cookware");
        productTypes.put(119, "Cutlery");
        productTypes.put(120, "Gadgets And Tools");
        productTypes.put(121, "Kitchen Electrics");
        productTypes.put(122, "Outdoor Shades Greenhouses");
        productTypes.put(123, "Containers Storage System");
        productTypes.put(124, "Outdoor Garage Storage Units");
        productTypes.put(128, "Books");
        productTypes.put(129, "Games");
        productTypes.put(130, "Personal Care");
        productTypes.put(131, "Sexual Wellness");
        productTypes.put(132, "Fitness Accessories");
        productTypes.put(135, "Oral Care");
    }

    public static boolean validate(String productType) {
        // Compare with product types in the database and return validation result
        for (String dbProductType : productTypes.values()) {
            if (dbProductType.equalsIgnoreCase(productType)) {
                return true;
            }
        }
        return false;
    }
}
