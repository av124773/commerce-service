package com.gtelant.commerce_service.configs;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gtelant.commerce_service.enums.OrderStatus;
import com.gtelant.commerce_service.enums.ReviewStatus;
import com.gtelant.commerce_service.models.Category;
import com.gtelant.commerce_service.models.Item;
import com.gtelant.commerce_service.models.Order;
import com.gtelant.commerce_service.models.Product;
import com.gtelant.commerce_service.models.Review;
import com.gtelant.commerce_service.models.Segment;
import com.gtelant.commerce_service.models.User;
import com.gtelant.commerce_service.models.UserSegment;
import com.gtelant.commerce_service.repositories.CategoryRepository;
import com.gtelant.commerce_service.repositories.ItemRepository;
import com.gtelant.commerce_service.repositories.OrderRepository;
import com.gtelant.commerce_service.repositories.ProductRepository;
import com.gtelant.commerce_service.repositories.ReviewRepository;
import com.gtelant.commerce_service.repositories.SegmentRepository;
import com.gtelant.commerce_service.repositories.UserRepository;
import com.gtelant.commerce_service.repositories.UserSegmentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Profile("dev")
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = Logger.getLogger(DataSeeder.class.getName());

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final SegmentRepository segmentRepository;
    private final UserSegmentRepository userSegmentRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public DataSeeder(
        UserRepository userRepository, 
        CategoryRepository categoryRepository, 
        PasswordEncoder passwordEncoder, 
        SegmentRepository segmentRepository, 
        UserSegmentRepository userSegmentRepository,
        ProductRepository productRepository,
        ReviewRepository reviewRepository,
        OrderRepository orderRepository,
        ItemRepository itemRepository
    ) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.passwordEncoder = passwordEncoder;
        this.segmentRepository = segmentRepository;
        this.userSegmentRepository = userSegmentRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting data seeding...");
        seedUsers();
        seedSegments();
        seedUserSegments();
        seedCategories();
        seedProducts();
        seedReviews();
        seedOrdersAndItems();
        log.info("Data seeding completed.");
    }
    
    public void seedUsers() {
        if (userRepository.count() == 0) {
            log.info("Seeding users...");
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);

            User regularUser = new User();
            regularUser.setFirstName("Regular");
            regularUser.setLastName("User");
            regularUser.setEmail("user@example.com");
            regularUser.setPassword(passwordEncoder.encode("123456"));
            regularUser.setRole("ROLE_USER");
            userRepository.save(regularUser);
        } else {
            log.info("Users already exist. Skipping seeding.");
        }
    }
    
    public void seedSegments() {
        if (segmentRepository.count() == 0) {
            log.info("Seeding segments...");
            Segment regular = new Segment();
            regular.setName("Regular");
            segmentRepository.save(regular);
        } else {
            log.info("Segments already exist. Skipping seeding.");
        }
    }

    public void seedUserSegments() {
        if (userSegmentRepository.count() == 0) {
            log.info("Seeding user segments...");
            UserSegment userSegment = new UserSegment();
            userSegment.setUser(userRepository.findByEmail("user@example.com").get());
            userSegment.setSegment(segmentRepository.findByName("Regular"));
        } else {
            log.info("User segments already exist. Skipping seeding.");
        }
    }

    public void seedCategories() {
        if (categoryRepository.count() == 0) {
            log.info("Seeding categories...");
            Category electronics = new Category();
            electronics.setName("Electronics");
            categoryRepository.save(electronics);

            Category books = new Category();
            books.setName("Books");
            categoryRepository.save(books);
        } else {
            log.info("Categories already exist. Skipping seeding.");
        }
    }

    public void seedProducts() {
        if (productRepository.count() == 0) {
            log.info("Seeding products...");
            Product product = new Product();
            product.setReference("Harry Potter 1");
            product.setCategory(categoryRepository.findByName("Books"));
            product.setDescription("哈利波特系列小說的第一集，由英國作家J.K.羅琳創作。劇情講述了一位名叫哈利波特的小巫師發現了自己的魔法天賦後，去霍格華茲魔法與巫術學院就讀一年級的經歷。在朋友的幫助下，哈利與殺害了自己父母並企圖東山再起的黑巫師佛地魔對峙。");
            product.setHeight(21);
            product.setWidth(14.8);
            product.setImageUrl("test");
            product.setThumbnailUrl("test");
            product.setPrice(new BigDecimal("350"));
            product.setStock(50);
            product.setSales(20);
            productRepository.save(product);
        } else {
            log.info("Segments already exist. Skipping seeding.");
        }
    }

    public void seedReviews() {
        if (reviewRepository.count() == 0) {
            log.info("Seeding reviews...");
            Review review = new Review();
            review.setRating(5);
            review.setComment("goot book");
            review.setUser(userRepository.findByEmail("user@example.com").get());
            review.setProduct(productRepository.findByReference("Harry Potter 1"));
            review.setStatus(ReviewStatus.PENDING);
        } else {
            log.info("Reviews already exist. Skipping seeding.");
        }

    }

    public void seedOrdersAndItems() {
        if (orderRepository.count() == 0 && itemRepository.count() == 0) {
            log.info("Seeding orders and items...");
            Order order = new Order();
            order.setReference(RandomStringUtils.secure().nextAlphanumeric(6).toUpperCase());
            order.setUser(userRepository.findByEmail("user@example.com").get());
            order.setShippingAddress("123 Main St, City, Country");
            order.setReturned(false);
            order.setStatus(OrderStatus.NULL);
            order.setDelivery(new BigDecimal(70));
            order.setTax(new BigDecimal(20));

            List<Item> items = new ArrayList<>();
            Item item = new Item();
            item.setOrderReference(order.getReference());
            item.setProduct(productRepository.findByReference("Harry Potter 1"));
            item.setAmount(1);
            item.setPrice(productRepository.findByReference("Harry Potter 1").getPrice());
            items.add(item);

            orderRepository.save(order);
            itemRepository.saveAll(items);
        } else {
            log.info("Orders and items already exist. Skipping seeding.");
        }
    }
}
