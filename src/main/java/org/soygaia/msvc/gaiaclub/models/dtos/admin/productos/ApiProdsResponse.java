package org.soygaia.msvc.gaiaclub.models.dtos.admin.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiProdsResponse {
    private String transfer_status;
    private String response_code;
    private String response_description;
    private DataWrapper data;

    // getters y setters
    public String getTransfer_status() { return transfer_status; }
    public void setTransfer_status(String transfer_status) { this.transfer_status = transfer_status; }
    public String getResponse_code() { return response_code; }
    public void setResponse_code(String response_code) { this.response_code = response_code; }
    public String getResponse_description() { return response_description; }
    public void setResponse_description(String response_description) { this.response_description = response_description; }
    public DataWrapper getData() { return data; }
    public void setData(DataWrapper data) { this.data = data; }
}
