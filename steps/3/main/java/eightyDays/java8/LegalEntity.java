package eightyDays.java8;

public class LegalEntity extends Partner {
    private final String form;

    public LegalEntity(String pName, String pForm) {
        super(pName);
        form = pForm;
    }

    public String getForm() {
        return form;
    }

    @Override
    public boolean equals(Object pO) {
        if (this == pO) return true;
        if (!(pO instanceof LegalEntity)) return false;
        if (!super.equals(pO)) return false;

        LegalEntity that = (LegalEntity) pO;

        return form != null ? form.equals(that.form) : that.form == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (form != null ? form.hashCode() : 0);
        return result;
    }
}
