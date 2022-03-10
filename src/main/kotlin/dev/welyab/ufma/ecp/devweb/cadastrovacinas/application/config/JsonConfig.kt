package dev.welyab.ufma.ecp.devweb.cadastrovacinas.application.config

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE
import com.fasterxml.jackson.annotation.PropertyAccessor.ALL
import com.fasterxml.jackson.annotation.PropertyAccessor.FIELD
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JsonConfig {

    @Bean
    fun objectMapperBuilder(): Jackson2ObjectMapperBuilder? {
        return object : Jackson2ObjectMapperBuilder() {
            override fun configure(objectMapper: ObjectMapper) {
                super.configure(objectMapper)
                objectMapper.registerModule(KotlinModule.Builder().build())
                objectMapper.setVisibility(ALL, NONE)
                objectMapper.setVisibility(FIELD, ANY)
            }
        }
    }
}
