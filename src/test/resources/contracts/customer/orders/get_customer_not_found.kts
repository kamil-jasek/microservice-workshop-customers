package contracts.customer

import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract

contract {
    description = "check if endpoint returns 404 NOT FOUND when customer not exists"
    request {
        method = GET
        url = url("/api/customers/573aae80-bbec-4264-b62c-f09859baa1d4")
        headers {
            header("version", "1.0.0")
        }
    }
    response {
        status = NOT_FOUND
    }
}
