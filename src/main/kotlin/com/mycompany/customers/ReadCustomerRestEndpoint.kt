package com.mycompany.customers

import com.mycompany.customers.MediaType.APPLICATION_JSON_V1
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Thread.sleep
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

@RestController
@RequestMapping("/api/customers")
class ReadCustomerRestEndpoint(private val mongo: MongoTemplate) {

    private val requestCount: AtomicInteger = AtomicInteger(0)

    @Document(collection = "customers")
    data class Customer(@Id val id: String, val deliveryAddress: DeliveryAddress)

    data class DeliveryAddress(val name: String, val address: String) {
    }

    @GetMapping("/{id}", produces = [APPLICATION_JSON_V1], headers = ["version=1.0.0"])
    fun getCustomer(@PathVariable id: UUID): ResponseEntity<Customer> {
        if (requestCount.incrementAndGet() % 3 == 0) {
            return ResponseEntity.internalServerError().build()
        }
        sleep(Random.nextLong(800, 3000))
        val customer = mongo.findById(id.toString(), Customer::class.java)
        return if (customer != null) {
            ResponseEntity.ok(customer)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
