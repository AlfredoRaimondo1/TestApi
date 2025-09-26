package com.example.testapi.validation.impl;

import com.example.testapi.validation.ICustomValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomValidationImpl implements ConstraintValidator<ICustomValidation, Object> {
    // Variabili di istanza per memorizzare i parametri dell'annotazione
    // Vengono inizializzate nel metodo initialize()

    // Nome della proprietà condizionale (es: "beneficiaryType")
    private String conditionalProperty;

    // Valori che attivano la validazione (es: ["NATURAL_PERSON", "0"])
    private String[] values;

    // Nome della proprietà da validare (es: "naturalPersonBeneficiary")
    private String requiredProperty;

    // Se la proprietà deve essere presente (true) o assente (false)
    private boolean required;

    // Messaggio di errore personalizzato
    private String message;

    /**
     * Chiamato da Spring una sola volta quando crea il validator
     * Estrae i parametri dall'annotazione e li memorizza nelle variabili di istanza
     */
    @Override
    public void initialize(ICustomValidation constraint) {
        // Estrae il nome della proprietà condizionale dall'annotazione
        this.conditionalProperty = constraint.conditionalProperty();

        // Estrae l'array di valori che attivano la validazione
        this.values = constraint.values();

        // Estrae il nome della proprietà da validare
        this.requiredProperty = constraint.requiredProperty();

        // Estrae se la proprietà deve essere presente o assente
        this.required = constraint.required();

        // Estrae il messaggio di errore personalizzato
        this.message = constraint.message();
    }


    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        try {
            // STEP 1: Ottieni il valore della proprietà condizionale
            // Es: se conditionalProperty = "beneficiaryType", ottieni il valore di dto.getBeneficiaryType()
            Object conditionalPropertyValue = getPropertyValue(object, conditionalProperty);

            // STEP 2: Ottieni il valore della proprietà da validare
            // Es: se requiredProperty = "naturalPersonBeneficiary", ottieni dto.getNaturalPersonBeneficiary()
            Object requiredPropertyValue = getPropertyValue(object, requiredProperty);

            // STEP 3: Verifica se la condizione è soddisfatta
            // Controlla se il valore della proprietà condizionale corrisponde a uno dei valori specificati
            boolean hasRequiredValue = false;

            // Se la proprietà condizionale non è null
            if (conditionalPropertyValue != null) {
                // Cicla attraverso tutti i valori specificati nell'annotazione
                for (String value : values) {
                    // Converte in stringa e confronta (gestisce sia enum che valori primitivi)
                    if (conditionalPropertyValue.toString().equals(value)) {
                        hasRequiredValue = true; // Condizione soddisfatta
                        break; // Esce dal ciclo, abbiamo trovato una corrispondenza
                    }
                }
            }

            // STEP 4: Applica la logica di validazione se la condizione è soddisfatta
            if (hasRequiredValue) {
                // Determina se la validazione è corretta in base al flag 'required'
                boolean isValid;

                if (required) {
                    // Se required = true, la proprietà DEVE essere presente (non null)
                    isValid = (requiredPropertyValue != null);
                } else {
                    // Se required = false, la proprietà NON DEVE essere presente (deve essere null)
                    isValid = (requiredPropertyValue == null);
                }

                // STEP 5: Se la validazione fallisce, costruisci un errore personalizzato
                if (!isValid) {
                    // Disabilita il messaggio di errore di default
                    context.disableDefaultConstraintViolation();

                    // Costruisce un nuovo messaggio di errore associato alla proprietà specifica
                    context.buildConstraintViolationWithTemplate(message)
                            .addPropertyNode(requiredProperty) // Associa l'errore al campo specifico
                            .addConstraintViolation();

                    return false; // Validazione fallita
                }
            }

            // Se arriviamo qui, la validazione è passata
            return true;

        } catch (Exception e) {
            // Se c'è un errore nella reflection (es: proprietà non esistente),
            // considera la validazione fallita
            return false;
        }
    }

    /**
     * Metodo helper che usa la reflection per ottenere il valore di una proprietà
     * Assume che la proprietà abbia un metodo getter standard (getPropertyName)
     *
     * @param object - L'oggetto da cui estrarre la proprietà
     * @param propertyName - Nome della proprietà (es: "beneficiaryType")
     * @return Il valore della proprietà
     * @throws Exception Se la proprietà non esiste o non è accessibile
     */
    private Object getPropertyValue(Object object, String propertyName) throws Exception {
        // Costruisce il nome del metodo getter seguendo la convenzione JavaBean
        // Es: "beneficiaryType" diventa "getBeneficiaryType"
        String methodName = "get" +
                propertyName.substring(0, 1).toUpperCase() + // Prima lettera maiuscola
                propertyName.substring(1); // Resto del nome

        // Usa la reflection per trovare il metodo e invocarlo
        return object.getClass()          // Ottieni la classe dell'oggetto
                .getMethod(methodName) // Trova il metodo getter (senza parametri)
                .invoke(object);       // Invoca il metodo sull'oggetto
    }
}
