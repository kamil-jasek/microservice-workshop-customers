package com.mycompany.customers.contract

import com.mycompany.customers.ReadCustomerRestEndpoint
import com.mycompany.customers.ReadCustomerRestEndpoint.Customer
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.springframework.data.mongodb.core.MongoTemplate

open class CustomerWarehouseBase {

    @BeforeEach
    fun setup() {
        val mongo = mock<MongoTemplate> {
            on { findById(eq("bdad7e29-b001-4058-b631-7a77e8119729"), eq(Customer::class.java)) } doReturn Customer(
                id = "bdad7e29-b001-4058-b631-7a77e8119729",
                deliveryAddress = ReadCustomerRestEndpoint.DeliveryAddress(
                    name = "Jan Kowalski",
                    address = "ul. Nowowiejska 15/4, 10-100 Nowe Miasto"
                )
            )
        }
        RestAssuredMockMvc.standaloneSetup(ReadCustomerRestEndpoint(mongo))
    }
}
