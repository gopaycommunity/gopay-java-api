package cz.gopay.api.v3;

import java.util.Date;
import java.util.List;

import cz.gopay.api.v3.model.APIError;
import cz.gopay.api.v3.model.ErrorElement;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class GPClientException extends Exception {

    private final int httpResultCode;
    private final APIError error;

    public GPClientException(int httpResultCode, APIError error) {
        this.error = error;
        this.httpResultCode = httpResultCode;
    }

    public APIError getError() {
        return error;
    }

    public Date getDateIssued() {
        return error.getDateIssued();
    }

    public List<ErrorElement> getErrorMessages() {
        return error.getErrorMessages();
    }

    public String extractMessage() {
        StringBuilder sb = new StringBuilder();
        for (ErrorElement msg : getErrorMessages()) {
            sb.append("RC[").append(httpResultCode).append("]");
            if (msg.getField() != null) {
                sb.append("Field: ").append(msg.getField());
            }
            if (msg.getErrorName() != null) {
                sb.append(" Name: ").append(msg.getErrorName()).append("[").append(msg.getErrorCode()).append("]");
            }
            if (msg.getMessage() != null) {
                sb.append(" Msg: ").append(msg.getMessage());
            }
            if (msg.getDescription() != null) {
                sb.append(" Desc: ").append(msg.getDescription());
            }
        }
        return sb.toString();
    }
}
