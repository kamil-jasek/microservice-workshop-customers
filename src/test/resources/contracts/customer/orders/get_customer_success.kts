package contracts.customer

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "check if customer exists by id"
    request {
        method = GET
        url = url("/api/customers/bdad7e29-b001-4058-b631-7a77e8119729")
        headers {
            header("version", "1.0.0")
        }
    }
    response {
        status = OK
        body = body(mapOf(
            "id" to "bdad7e29-b001-4058-b631-7a77e8119729"
        ))
        headers {
            header(CONTENT_TYPE, "application/mycompany.v1.0.0+json")
        }
    }
}
