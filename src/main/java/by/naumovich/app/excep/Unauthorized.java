package by.naumovich.app.excep;

public class Unauthorized extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Unauthorized() {
    }

    public Unauthorized(String arg0) {
        super(arg0);
    }

    public Unauthorized(Throwable arg0) {
        super(arg0);
    }

    public Unauthorized(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public Unauthorized(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
