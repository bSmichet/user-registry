package fr.airfrance.userregistry.service.dto.error;

/**
 * REST Error Body used in error REST responses.
 */
public class RestErrorBody {

    private String title;

    private Integer status;

    private String detail;

    public RestErrorBody() {
    }

    public RestErrorBody(String title, Integer status, String detail) {
        this.title = title;
        this.status = status;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
