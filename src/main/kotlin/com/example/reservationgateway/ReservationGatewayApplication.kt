package com.example.reservationgateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ReservationGatewayApplication {

    @Bean
    fun gateway(routeLocatorBuilder: RouteLocatorBuilder) = routeLocatorBuilder
            .routes()
            .route { routeSpec ->
                routeSpec
                        .host("*.spring.io")
                        .and()
                        .path("/proxy")
                        .filters { filterSpec ->
                            filterSpec
                                    .setPath("/reservations")
                        }
                        .uri("http://localhost:8080")
            }.build()
}
fun main(args: Array<String>) {
    runApplication<ReservationGatewayApplication>(*args)
}
