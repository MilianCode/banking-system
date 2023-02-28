package tests;

import customer.Customer;
import customer.CustomerLogin;
import customer.CustomerRegistration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRegistrationTest {

    private final static InputStream systemIn = System.in;
    private final static PrintStream systemOut = System.out;
    private ByteArrayInputStream typeIn;
    private static ByteArrayOutputStream typeOut;

    @BeforeEach
    void setUp() throws Exception {
        typeOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(typeOut));
    }
    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }


    @Test
    void shouldSuccessfullyCreateNewCustomerInDB(){
        String simulatedUserInput = "TESTING" + System.getProperty("line.separator") +
                "TESTING" + System.getProperty("line.separator") +
                "TESTING" + System.getProperty("line.separator") +
                "9999999999" + System.getProperty("line.separator") +
                "9999" + System.getProperty("line.separator");


        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        CustomerRegistration.singUp();

        assertTrue(Customer.checkCustomerId(Customer.getCustomerId()));
    }

}