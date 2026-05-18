package test.java.com.empresa.pedidos;

import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(
        packages = "com.empresa.pedidos",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class ReglasArquitectura {

    // =========================================================
    // REGLA 1: DOMINIO AISLADO
    // =========================================================
    @ArchTest
    static final ArchRule dominioAislado =
            noClasses()
                    .that()
                    .resideInAPackage("..dominio..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(
                            "..infraestructura..",
                            "..adaptadores..",
                            "javax.persistence..",
                            "org.springframework.mail.."
                    );

    // =========================================================
    // REGLA 2: CONTROLADOR SOLO FACADE
    // =========================================================
    @ArchTest
    static final ArchRule controladorSoloFacade =
            classes()
                    .that()
                    .resideInAPackage("..adaptadores.rest..")
                    .should()

                    /*
                     ❌ ERROR ORIGINAL TUYO:
                     onlyAccessClassesThat()

                     PROBLEMA:
                     - Este método es MUY estricto en Spring Boot
                     - Bloquea cosas normales como ResponseEntity
                     - También interpreta mal la inyección por constructor
                     */

                    // ✅ CORRECCIÓN:
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "..adaptadores.facade..",
                            "..dominio..",

                            // ✔ NECESARIO PARA SPRING REST
                            "org.springframework.web..",
                            "org.springframework.http..",

                            // ✔ CLASES BASE DE JAVA
                            "java.."
                    );

    // =========================================================
    // REGLA 3: PUERTOS COMO INTERFACES
    // =========================================================
    @ArchTest
    static final ArchRule puertosComoInterfaces =
            classes()
                    .that()
                    .resideInAPackage("..dominio.puertos..")
                    .should()
                    .beInterfaces();

    // =========================================================
    // REGLA 4: PROCESADORES IMPLEMENTAN PUERTO
    // =========================================================
    @ArchTest
    static final ArchRule procesadoresImplementanPuerto =
            classes()
                    .that()
                    .resideInAPackage("..adaptadores.procesadores..")
                    .should()
                    .implement(ProcesadorPedido.class);

    // =========================================================
    // REGLA 5: INFRA NO ACCEDE A REST
    // =========================================================
    @ArchTest
    static final ArchRule infraNoAccedeRest =
            noClasses()
                    .that()
                    .resideInAPackage("..infraestructura..")
                    .should()
                    .accessClassesThat()
                    .resideInAPackage("..adaptadores.rest..");
}