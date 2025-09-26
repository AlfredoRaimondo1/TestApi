package com.example.testapi.validation;

import java.lang.annotation.Repeatable;

@Repeatable(ICustomValidations.class)
public @interface ICustomValidation {

    // Messaggio di errore di default se la validazione fallisce
    // Può essere sovrascritto quando usi l'annotazione
    String message() default "Conditional validation failed";



    // ---- PARAMETRI PERSONALIZZATI PER LA NOSTRA LOGICA ----

    // Nome della proprietà che determina la condizione (es: "beneficiaryType")
    // Questo è il campo che controlliamo per decidere se applicare la validazione
    String conditionalProperty();

    // Array di valori che attivano la validazione condizionale
    // Es: se beneficiaryType è "NATURAL_PERSON" o "0", allora valida
    String[] values();

    // Nome della proprietà che deve essere validata (es: "naturalPersonBeneficiary")
    // Questo è il campo che controlliamo se è presente/assente
    String requiredProperty();

    // Se true: la proprietà DEVE essere presente quando la condizione è vera
    // Se false: la proprietà NON DEVE essere presente quando la condizione è vera
    // Default: true (la proprietà è obbligatoria)
    boolean required() default true;
}
