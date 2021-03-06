package io.github.herocode.ecmat.model;

import io.github.herocode.ecmat.entity.Participant;
import io.github.herocode.ecmat.entity.Payment;
import io.github.herocode.ecmat.entity.ShortCourse;
import io.github.herocode.ecmat.enums.ErrorMessages;
import io.github.herocode.ecmat.interfaces.ParticipantBusiness;
import io.github.herocode.ecmat.interfaces.ParticipantDao;
import io.github.herocode.ecmat.persistence.ParticipantDaoImpl;
import io.github.herocode.ecmat.persistence.ParticipantRecoverDao;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class ParticipantBusinessImpl implements ParticipantBusiness {

    private ParticipantDao participantDao;
    private ParticipantRecoverDao recoverDao;

    public ParticipantBusinessImpl() {

        this.participantDao = new ParticipantDaoImpl();
        this.recoverDao = new ParticipantRecoverDao();
    }

    @Override
    public Participant saveParticipant(Participant participant, String paymentId) {

        return participantDao.save(participant, paymentId);
    }

    @Override
    public boolean updateParticipant(Participant participant) {

        return participantDao.update(participant);
    }

    @Override
    public boolean deleteParticipant(Participant participant) {

        return participantDao.delete(participant);
    }

    @Override
    public Participant searchParticipantById(int id) throws IllegalArgumentException {

        Participant participant = participantDao.searchById(id);

        if (participant == null) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_ID.getErrorMessage());
        }

        return participant;
    }

    @Override
    public List<ShortCourse> getRegisteredShortCourse(Participant participant) {

        return Collections.unmodifiableList(participantDao.getRegisteredShortCourse(participant));
    }

    @Override
    public List<Participant> listAllParticipants() {

        return Collections.unmodifiableList(participantDao.listAll());
    }

    @Override
    public List<Participant> searchParticipantByAttribute(String key, String value) {

        return Collections.unmodifiableList(participantDao.searchByAttribute(key, value));
    }

    @Override
    public List<Participant> searchParticipantByAttributes(Map<String, String> map) {

        return Collections.unmodifiableList(participantDao.searchByAttributes(map));
    }

    @Override
    public Participant searchParticipantByEmail(String email) throws IllegalArgumentException {

        try {

            Participant participant = searchParticipantByAttribute("email", email).get(0);
            return participant;
        } catch (Exception ex) {
            System.err.println("Email invalido - " + email);
            throw new IllegalArgumentException(ErrorMessages.INVALID_EMAIL.getErrorMessage());

        }

    }

    @Override
    public Participant login(String email, String password) throws IllegalArgumentException {

        Map<String, String> map = new HashMap<>();

        map.put("email", email);
        map.put("password", password);

        List<Participant> participants = searchParticipantByAttributes(map);

        if (participants.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.FAIL_LOGIN.getErrorMessage());
        }

        return participants.get(0);
    }

    @Override
    public Participant saveParticipant(Participant participant, Payment payment) {

        return participantDao.save(participant, payment);
    }

    @Override
    public String getEmailFromPaymentReference(String paymentReference) {

        return participantDao.getEmailFromPaymentReference(paymentReference);
    }

    @Override
    public boolean existsEmail(String email) {

        return participantDao.existsEmail(email);
    }

    @Override
    public boolean needRecover(String email) {

        return recoverDao.needRecover(email);
    }

}
