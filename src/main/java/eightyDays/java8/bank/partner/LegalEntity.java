package eightyDays.java8.bank.partner;

public class LegalEntity extends Partner {
    private String legalForm;

    public LegalEntity(String pName, String pLegalForm) {
        super(pName);
        legalForm = pLegalForm;
    }

    public String getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(String pLegalForm) {
        legalForm = pLegalForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalEntity)) return false;
        if (!super.equals(o)) return false;

        LegalEntity that = (LegalEntity) o;

        return legalForm != null ? legalForm.equals(that.legalForm) : that.legalForm == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (legalForm != null ? legalForm.hashCode() : 0);
        return result;
    }
}
