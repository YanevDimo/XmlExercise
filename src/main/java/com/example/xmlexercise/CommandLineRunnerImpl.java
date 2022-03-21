package com.example.xmlexercise;

import com.example.xmlexercise.model.dto.*;
import com.example.xmlexercise.service.CategoryService;
import com.example.xmlexercise.service.ProductService;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.XmlParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String RESOURCES_FILE_PATH = "src/main/resources/file/";
    public static final String OUTPUT_FILE_PATH = "src/main/resources/file/output/";
    public static final String CATEGORIES_FILE_NAME = "categories.xml";
    public static final String USERS_FILE_NAME = "users.xml";
    public static final String PRODUCTS_FILE_NAME = "products.xml";
    public static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.xml";
    public static final String SOLD_PRODUCTS_FILE_NAME = "sold-products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService, UserService userService1, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService1;
        this.productService = productService;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        System.out.println();
        System.out.println("Select exercise:");
        int exerciseNumber = Integer.parseInt(bufferedReader.readLine());

        switch (exerciseNumber) {
            case 1 -> productsInRange();
            case 2 -> successfullySoldProducts();

        }
    }

    private void successfullySoldProducts() throws JAXBException {

        UserViewRootDto userViewRootDto = userService
                .findUsersWithMoreThanOneProduct();

//        System.out.println();

        xmlParser.writeToFile(OUTPUT_FILE_PATH+SOLD_PRODUCTS_FILE_NAME,userViewRootDto);
    }

    private void productsInRange() throws JAXBException {

        ProductViewRootDto rootDto = productService
                .findProductsInRangeWithoutBuyer();

        xmlParser.writeToFile(OUTPUT_FILE_PATH + PRODUCTS_IN_RANGE_FILE_NAME, rootDto);

    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (categoryService.getEntityCount() == 0) {
            CategorySeedRootDto categorySeedRootDto =
                    xmlParser.fromFile(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME,
                            CategorySeedRootDto.class);
            categoryService.seedCategories(categorySeedRootDto.getCategories());
        }

        if (userService.getCount() == 0) {
            UserSeedRootDto userSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + USERS_FILE_NAME,
                            UserSeedRootDto.class);

            userService.seedUsers(userSeedRootDto.getUsers());
        }
        if (productService.getCount() == 0) {
            ProductSeedRootDto productSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME,
                            ProductSeedRootDto.class);

            productService.seedProducts(productSeedRootDto.getProducts());
        }

    }
}

