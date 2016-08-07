package spring.constructors;

public class DriverData {

    private String browser;
    private Integer timeout;
    private String autRelativePath;


    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getAutRelativePath() {
        return autRelativePath;
    }

    public void setAutRelativePath(String autRelativePath) {
        this.autRelativePath = autRelativePath;
    }
}
