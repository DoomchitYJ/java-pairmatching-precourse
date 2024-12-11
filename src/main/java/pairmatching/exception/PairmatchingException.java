package pairmatching.exception;

public class PairmatchingException extends IllegalArgumentException {
    public PairmatchingException(final ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
