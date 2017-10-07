package se.jola.whatsthis.models;

public final class CreateUserRequestBean {

    private String username;

    private String password;

    public CreateUserRequestBean(String username, String password){
        this.username = username;
        this.password = password;
    }

    protected CreateUserRequestBean(){
        ;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateUserRequestBean that = (CreateUserRequestBean) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
