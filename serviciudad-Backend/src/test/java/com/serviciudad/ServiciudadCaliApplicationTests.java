package com.serviciudad; // ⚠ importante: debe coincidir con la carpeta

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // usa el perfil de pruebas con H2
class ServiciudadCaliApplicationTests {

    @Test
    void contextLoads() {
        // este test solo verifica que Spring Boot pueda iniciar el contexto
    }
}
