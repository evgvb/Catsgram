package ru.yandex.practicum.catsgram.exception;

public class ParameterNotValidException extends IllegalArgumentException{

    private final String parameter;
    private final String reason;

    public ParameterNotValidException(String parameter, String reason) {
        super(String.format("Parameter '%s' is not valid. Reason:", parameter, reason));
        this.parameter = parameter;
        this.reason = reason;
    }

    public String getParameter() {
        return parameter;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "ParameterNotValidException{" +
                "parameter='" + parameter + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
