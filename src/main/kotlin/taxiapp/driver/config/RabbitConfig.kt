package taxiapp.driver.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import taxiapp.driver.dto.event.DriverAuthenticationApprovedEvent
import taxiapp.driver.dto.event.UserConfirmedEvent

@Configuration
class RabbitConfig {

    @Bean
    fun jsonMessageConverter(): MessageConverter {
        val mapper = ObjectMapper().apply {
            registerKotlinModule()
        }

        val typeMapper = DefaultJackson2JavaTypeMapper().apply {
            setTrustedPackages("*")
            setIdClassMapping(
                mapOf(
                    "UserConfirmedEvent" to UserConfirmedEvent::class.java,
                    "com.taxiapp.driver_auth.dto.event.DriverAuthenticationApprovedEvent" to DriverAuthenticationApprovedEvent::class.java,
                )
            )
        }

        return Jackson2JsonMessageConverter(mapper).apply {
            setJavaTypeMapper(typeMapper)
        }
    }
}