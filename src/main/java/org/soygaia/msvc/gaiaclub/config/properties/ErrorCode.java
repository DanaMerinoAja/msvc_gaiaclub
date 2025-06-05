package org.soygaia.msvc.gaiaclub.config.properties;

public enum ErrorCode {
    CANJE_DETAIL_NULL("4010","Lista de canjes vacía"),
    REGISTER_CANJE_FAILED("4011","Error al registrar el canje"),
    PUNTOS_INSUFICIENTES("4012", "Puntos del cliente insuficientes"),
    STOCK_INSUFICIENTE("4013", "Recompensa sin stock"),
    REGISTER_MEMBER("4014","Error en el registro");


    private final String code;
    private final String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
