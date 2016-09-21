package eightyDays.java8.bank.partner;

/**
 * Created by jrb on 20/09/16.
 */
public class EMail extends Communication {
    private String mail;

    public EMail(String pMail) {
        mail = pMail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String pMail) {
        mail = pMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EMail)) return false;

        EMail eMail = (EMail) o;

        return mail != null ? mail.equals(eMail.mail) : eMail.mail == null;
    }

    @Override
    public int hashCode() {
        return mail != null ? mail.hashCode() : 0;
    }
}
