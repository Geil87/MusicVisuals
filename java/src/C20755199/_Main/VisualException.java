package C20755199._Main;

public class VisualException extends Throwable
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String message;
    
    public VisualException(String message)
    {
        this.message = message;
    }

    public String toString()
    {
        return message;
    }
}