public class UsuarioSeguroAvanzado {


    private String username;
    private String password;
    private int intentosFallidos;
    private boolean bloqueado;
    private int maxIntentos;
    private boolean accesoExitoso;


    public UsuarioSeguroAvanzado(String username, String password, int maxIntentos) {
        this.username = username;
        this.password = password;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.accesoExitoso = false;
        this.maxIntentos = (maxIntentos <= 0) ? 3 : maxIntentos;
    }


    public String getUsername() { return username; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isBloqueado() { return bloqueado; }
    public int getMaxIntentos() { return maxIntentos; }
    public boolean isAccesoExitoso() { return accesoExitoso; }




    public boolean autenticar(String passwordIngresada) {
        if (this.bloqueado) {
            return false;
        }

        if (this.password.equals(passwordIngresada)) {
            this.intentosFallidos = 0;
            this.accesoExitoso = true;
            return true;
        } else {
            this.intentosFallidos++;
            if (this.intentosFallidos >= this.maxIntentos) {
                this.bloqueado = true;
            }
            return false;
        }
    }


    public void reiniciarAcceso() {
        this.intentosFallidos = 0;
        this.bloqueado = false;
    }


    public boolean cambiarPassword(String actual, String nueva) {
        if (this.bloqueado) {
            return false;
        }
        if (!this.password.equals(actual)) {
            return false;
        }
        if (!validarPasswordSegura(nueva)) {
            return false;
        }

        this.password = nueva;
        return true;
    }


    public boolean validarPasswordSegura(String nueva) {
        if (nueva == null || nueva.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneNumero = false;

        for (int i = 0; i < nueva.length(); i++) {
            char c = nueva.charAt(i);
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
        }

        return tieneMayuscula && tieneNumero;
    }
}
