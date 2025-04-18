package org.soygaia.msvc.gaiaclub.config.properties;

public enum ErrorCode {
    CANJE_DETAIL_NULL("4010","Lista de canjes vacía"),
    REGISTER_CANJE_FAILED("4011","Error al registrar el canje"),
    PUNTOS_INSUFICIENTES("4012", "Puntos del cliente insuficientes"),
    REGISTER_MEMBER("4013","Error en el registro");


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
