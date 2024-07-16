public class Moeda {
    private String base_code;
    private Rates conversion_rates = new Rates();
    private float valor;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Rates getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Rates conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
