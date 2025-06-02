package taxiapp.driver.config

import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class MessagingConfig {
    @Value("\${rabbit.exchange.users.name}")
    private val usersExchangeName: String? = null

    @Value("\${rabbit.queue.driver.name}")
    private val driverQueueName: String? = null

    @Value("\${rabbit.topic.cognito.user.confirmed}")
    private val userConfirmedTopic: String? = null

    @Value("\${rabbit.topic.driver-auth.approved}")
    private val driverAuthApprovedTopic: String? = null


    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange("$usersExchangeName")
    }

    @Bean
    fun driverAuthQueue(): Queue {
        return QueueBuilder.durable("$driverQueueName").build()
    }

    @Bean
    fun cognitoUserBinding(exchange: TopicExchange, driverAuthQueue: Queue): Binding {
        return BindingBuilder.bind(driverAuthQueue).to(exchange).with("$userConfirmedTopic")
    }

    @Bean
    fun driverAuthBinding(exchange: TopicExchange, driverAuthQueue: Queue): Binding {
        return BindingBuilder.bind(driverAuthQueue).to(exchange).with("$driverAuthApprovedTopic")
    }
}