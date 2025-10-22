package com.serviciudad; // ⚠ importante: debe coincidir con la carpeta

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("default") // usa tu perfil por defecto
class ServiciudadCaliApplicationTests {

    @Test
    void contextLoads() {
        // este test solo verifica que Spring Boot pueda iniciar el contexto
    }
}
