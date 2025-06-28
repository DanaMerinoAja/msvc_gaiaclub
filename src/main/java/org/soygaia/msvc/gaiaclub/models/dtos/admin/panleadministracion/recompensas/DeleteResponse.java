package org.soygaia.msvc.gaiaclub.models.dtos.admin.panleadministracion.recompensas;

public class DeleteResponse {
    private Long id;
    private String mensaje;
    private boolean eliminado;

    public DeleteResponse(Long id, String mensaje, boolean eliminado) {
        this.id = id;
        this.mensaje = mensaje;
        this.eliminado = eliminado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
