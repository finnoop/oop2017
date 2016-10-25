package eightyDays.java8.bank.partner;

public class LegalEntity extends Partner {

    private String form;

    public LegalEntity(String pName, String pForm) {
        super(pName);
        form = pForm;
    }

    public String getForm() {
        return form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalEntity)) return false;
        if (!super.equals(o)) return false;

        LegalEntity that = (LegalEntity) o;

        return form != null ? form.equals(that.form) : that.form == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (form != null ? form.hashCode() : 0);
        return result;
    }
}
