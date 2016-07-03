/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.herocode.ecmat.model;

import br.com.caelum.stella.validation.CPFValidator;
import io.github.herocode.ecmat.entity.Participant;
import io.github.herocode.ecmat.enums.ErrorMessage;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class ParticipantBuilder {

    private final String name;
    private final String email;
    private final String cpf;

    private LocalDate birthDate;

    public ParticipantBuilder(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public ParticipantBuilder setBirthDate(LocalDate birthDate) {

        this.birthDate = birthDate;

        return this;
    }

    private void validateCpf() throws IllegalArgumentException {

        if (stringIsEmpty(cpf)) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_CPF.getErrorMessage());
        }

        CPFValidator cpfValidator = new CPFValidator();

        try {
            cpfValidator.assertValid(cpf);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CPF.getErrorMessage());
        }
    }

    private void validateName() throws IllegalArgumentException {

        if (stringIsEmpty(name)) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_NAME.getErrorMessage());
        }

    }

    private void validateEmail() throws IllegalArgumentException {

        if (stringIsEmpty(email)) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_EMAIL.getErrorMessage());
        }

        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);

        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_EMAIL.getErrorMessage());
        }

    }

    private boolean stringIsEmpty(String string) {

        return (string == null || string.trim().isEmpty());
    }

    public Participant build() throws IllegalArgumentException {

        validateName();
        validateEmail();
        validateCpf();

        Participant participant = new Participant();

        participant.setName(name);
        participant.setCpf(cpf);
        participant.setEmail(email);
        participant.setBirthDate(birthDate);

        return participant;
    }

}
