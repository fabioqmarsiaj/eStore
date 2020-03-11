package com.fabioqmarsiaj.estore;

import com.fabioqmarsiaj.estore.domain.*;
import com.fabioqmarsiaj.estore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class EstoreApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public static void main(String[] args) {
        SpringApplication.run(EstoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informatics");
        Category cat2 = new Category(null, "Office");

        Product prod1 = new Product(null, "Computer", 2000.0);
        Product prod2 = new Product(null, "Scanner", 800.0);
        Product prod3 = new Product(null, "Mouse", 80.0);

        State s1 = new State(null, "Rio Grande do Sul");
        State s2 = new State(null, "São Paulo");

        City c1 = new City(null, "Porto Alegre", s1);
        City c2 = new City(null, "São Paulo", s2);

        cat1.getProducts().addAll(Arrays.asList(prod1, prod2, prod3));

        cat2.getProducts().addAll(Arrays.asList(prod2));

        prod1.getCategories().addAll(Arrays.asList(cat1));

        prod2.getCategories().addAll(Arrays.asList(cat1, cat2));

        prod3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

        s1.getCities().addAll(Arrays.asList(c1));
        s2.getCities().addAll(Arrays.asList(c2));

        Client client1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.NATURALPERSON);
        client1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

        Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", client1, c1);
        Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", client1, c2);

        client1.getAddresses().addAll(Arrays.asList(ad1, ad2));

        stateRepository.saveAll(Arrays.asList(s1, s2));
        cityRepository.saveAll(Arrays.asList(c1, c2));

        clientRepository.saveAll(Arrays.asList(client1));
        addressRepository.saveAll(Arrays.asList(ad1, ad2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Order order1 = new Order(null, sdf.parse("30/09/2017 10:32"), client1, ad1);
        Order order2 = new Order(null, sdf.parse("10/10/2017 19:35"), client1, ad2);

        client1.getOrders().addAll(Arrays.asList(order1, order2));

        Payment paym1 = new CardPayment(null, PaymentState.FINISHED, order1, 6);
        order1.setPayment(paym1);

        Payment paym2 = new PaymentSlip(null, PaymentState.WAITING, order2, sdf.parse("20/10/2017 00:00"), null);
        order2.setPayment(paym2);

        orderRepository.saveAll(Arrays.asList(order1, order2));
        paymentRepository.saveAll(Arrays.asList(paym1, paym2));

    }
}
