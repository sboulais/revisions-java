package bestpractices.patternmatching;

class Vehicule {

    private final String type;
    private final Integer nombreDeRoues;
    private final Integer vitesseMax;

    public Vehicule(
            final String type,
            final Integer nombreDeRoues,
            final Integer vitesseMax) {
        this.type = type;
        this.nombreDeRoues = nombreDeRoues;
        this.vitesseMax = vitesseMax;
    }

    public Integer getVitesseMax() {
        return vitesseMax;
    }

    public Integer getNombreDeRoues() {
        return nombreDeRoues;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vehicule other // plus besoin de caster depuis java 17
                && getType().equals(other.getType())
                && getNombreDeRoues().equals(other.getNombreDeRoues())
                && getVitesseMax().equals(other.getVitesseMax());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, nombreDeRoues, vitesseMax);
    }
}
